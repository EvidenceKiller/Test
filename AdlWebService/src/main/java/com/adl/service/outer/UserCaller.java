package com.adl.service.outer;

import android.text.TextUtils;

import com.adl.service.common.BaseService;
import com.adl.service.common.FileDownManager;
import com.adl.service.common.InnerPreferences;
import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestCallback;
import com.adl.service.common.RequestResult;
import com.adl.service.common.RequestUserCallback;
import com.adl.service.common.RequestWorker;
import com.adl.service.db.CitizenDao;
import com.adl.service.db.StudentDao;
import com.adl.service.db.TeacherDao;
import com.adl.service.entity.CitizenEntity;
import com.adl.service.entity.OrganizationEntity;
import com.adl.service.entity.StudentEntity;
import com.adl.service.entity.TeacherEntity;
import com.adl.service.web.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 类描述
 */
public final class UserCaller extends BaseService {

    private static UserCaller _instance;
    private OrganizationEntity currentOrg;
    private final int PAGE_SIZE = 1000;
    private boolean hasRequestOrg = false;

    private UserCaller() {

    }

    public static UserCaller instance() {
        if (_instance == null) {
            _instance = new UserCaller();
        }
        return _instance;
    }

    // 同步基础信息
    public void syncBaseInfo(RequestUserCallback callback) {
        syncBaseInfo(false, callback);
    }

    public void syncBaseInfo(boolean forceUpdate, RequestUserCallback callback) {
        syncBaseInfo(forceUpdate, PAGE_SIZE, callback);
    }

    // 同步基础信息
    public void syncBaseInfo(boolean forceUpdate, int pageSize, RequestUserCallback callback) {
        syncBaseInfo(forceUpdate, pageSize, 2, callback);
    }

    public void syncBaseInfo(boolean forceUpdate, int pageSize, int faceType, RequestUserCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                try {

                    InnerPreferences pf = InnerPreferences.instance();
                    AdlService adlService = AdlService.getService();

                    ////////////// 1.同步机构信息 ////////////
                    callback.nextStatus(SyncOrgStatus, "正在同步机构信息");
                    //启动时，请求一次机构信息，如果机构变更，清除数据标识
                    if (!hasRequestOrg) {
                        RequestResult result = UserService.queryLoginOrganization();
                        if (result.isOk()) {
                            String str = result.getContent().toString();
                            currentOrg = InnerUtil.jsonToObject(str, OrganizationEntity.class);
                            if (adlService != null) {
                                adlService.setOrgId(currentOrg.getOrgId());
                            }
                            String saveOrgStr = pf.readString(LoginOrgInfo);
                            if (!TextUtils.isEmpty(saveOrgStr)) {
                                OrganizationEntity saveOrg = InnerUtil.jsonToObject(saveOrgStr, OrganizationEntity.class);
                                String saveOrgId = saveOrg.getOrgId();
                                if (currentOrg != null && !TextUtils.isEmpty(saveOrgId) && !saveOrgId.equals(currentOrg.getOrgId())) {
                                    adlService.getStudentDao().clearAll();
                                    adlService.getTeacherDao().clearAll();
                                    FileDownManager.instance().clearAllFace();
                                    pf.putLong(LastStudentUpdateTime, -1);
                                    pf.putLong(LastTeacherUpdateTime, -1);
                                    pf.putLong(LastCitizenUpdateTime, -1);
                                    callback.orgChange(currentOrg.getOrgId(), saveOrgId);
                                }
                            }
                            pf.putString(LoginOrgInfo, str);
                            hasRequestOrg = true;
                        } else {
                            String str = pf.readString(LoginOrgInfo);
                            if (!TextUtils.isEmpty(str)) {
                                currentOrg = InnerUtil.jsonToObject(str, OrganizationEntity.class);
                            }
                        }
                    }

                    ////////////// 2.同步学生基本信息 ////////////
                    long serverTime = System.currentTimeMillis();
                    callback.nextStatus(SyncStudentStatus, "正在初始化学生信息");
                    StudentDao stuDao = adlService.getStudentDao();
                    int size = stuDao.countSize();
                    long lastTime = pf.readLong(LastStudentUpdateTime);
                    d("本机时间:size: " + size + ";forceUpdate:" + forceUpdate + ";serverTime:" + serverTime + ";lastTime:" + lastTime + ";UpdateIntervalTime:" + UpdateIntervalTime);

                    // 是否强制更新数据
                    String recentUpdateTimeStr = stuDao.getLastStudentUpdateTime();
                    long recentUpdateTime = -1;
                    try {
                        recentUpdateTime = Long.parseLong(recentUpdateTimeStr);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    d("本机时间:recentUpdateTime: " + recentUpdateTime);

                    long updateTime = forceUpdate ? -1 : lastTime == -1 ? -1 : recentUpdateTime;

                    List<StudentEntity> listStudent = UserService.queryStudentByPage(updateTime, pageSize, faceType);
                    if (updateTime <= 0) {
                        stuDao.clearAll();
                    }
                    if (listStudent != null && listStudent.size() > 0) {

                        //  区分可用和删除的数据
                        Map<Boolean, List<StudentEntity>> partitioned = listStudent.stream()
                                //  删除标识  0：可用  1：删除
                                .collect(Collectors.partitioningBy(student -> "1".equals(student.getDelFlag())));

                        //  更新数据
                        List<StudentEntity> updateData = partitioned.get(false);
                        if (updateData != null) {
                            int updateSize = updateData.size();
                            int n = updateSize / pageSize;
                            int r = updateSize % pageSize;
                            int total = r > 0 ? n + 1 : n;
                            d("++++++++++ 学生开始 +++++++++ 总数=" + updateSize);
                            for (int index = 0; index < total; index++) {
                                int start = index * pageSize;
                                int end = Math.min(start + pageSize, updateSize);
                                d("学生入库,start=" + start + ", end=" + end);
                                List<StudentEntity> tempList = updateData.subList(start, end);
                                stuDao.insertAll(tempList);
                                d("学生入库完成");
                            }
                        }

                        //  删除数据
                        List<StudentEntity> deleteData = partitioned.get(true);
                        if (deleteData != null) {
                            d("++++++++++ 学生库清理 +++++++++ 总数=" + deleteData.size());
                            stuDao.clear(deleteData);
                            try {
                                FileDownManager.instance().clearByUrls(deleteData.stream().map(StudentEntity::getUserFaceImgUrl).collect(Collectors.toList()));
                                FileDownManager.instance().clearByUrls(deleteData.stream().map(StudentEntity::getThumbnailUserFaceImgUrl).collect(Collectors.toList()));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        callback.updateStudent(updateData, deleteData);
                    } else {
                        callback.updateStudent(new ArrayList<>(), new ArrayList<>());
                    }
                    pf.putLong(LastStudentUpdateTime, serverTime);

                    ////////////// 3.同步老师基本信息 ////////////
                    callback.nextStatus(SyncTeacherStatus, "正在初始化老师信息");
                    TeacherDao tchDao = adlService.getTeacherDao();
                    size = tchDao.countSize();
                    lastTime = pf.readLong(LastTeacherUpdateTime);

                    // 数据会空，或者时间间隔2分钟
                    if (size == 0 || forceUpdate || serverTime - lastTime > UpdateIntervalTime) {

                        List<TeacherEntity> list = UserService.queryTeacher(forceUpdate ? -1 : lastTime);
                        if (forceUpdate || lastTime <= 0) {
                            tchDao.clearAll();
                        }
                        if (list != null && !list.isEmpty()) {
                            //  区分可用和删除的数据
                            Map<Boolean, List<TeacherEntity>> partitioned = list.stream()
                                    .collect(Collectors.partitioningBy(teacher -> !teacher.isDelFlag()));

                            //  更新数据
                            List<TeacherEntity> updateData = partitioned.get(true);
                            tchDao.insertAll(updateData);

                            //  删除数据
                            List<TeacherEntity> deleteData = partitioned.get(false);
                            tchDao.clear(deleteData);

                            callback.updateTeacher(updateData, deleteData);
                        }
                        pf.putLong(LastTeacherUpdateTime, serverTime);
                    }

                    ////////////// 2.同步市民基本信息 ////////////
                    callback.nextStatus(SyncCitizenStatus, "正在初始化市民信息");
                    CitizenDao citizenDao = adlService.getCitizenDao();
                    size = citizenDao.countSize();
                    lastTime = pf.readLong(LastCitizenUpdateTime);
                    // 数据会空，或者时间间隔2分钟
                    if (size == 0 || forceUpdate || serverTime - lastTime > UpdateIntervalTime) {

                        // 是否强制更新数据
                        List<CitizenEntity> list = UserService.queryCitizenByPage(forceUpdate ? -1 : lastTime, pageSize, faceType);
                        if (forceUpdate || lastTime <= 0) {
                            citizenDao.clearAll();
                        }
                        if (list != null && list.size() > 0) {

                            //  区分可用和删除的数据
                            Map<Boolean, List<CitizenEntity>> partitioned = list.stream()
                                    //  删除标识  0：可用  1：删除
                                    .collect(Collectors.partitioningBy(citizen -> !citizen.isDelFlag()));

                            //  更新数据
                            List<CitizenEntity> updateData = partitioned.get(true);
                            if (updateData != null) {
                                int updateSize = updateData.size();
                                int n = updateSize / pageSize;
                                int r = updateSize % pageSize;
                                int total = r > 0 ? n + 1 : n;
                                d("++++++++++ 市民开始 +++++++++ 总数=" + updateSize);
                                for (int index = 0; index < total; index++) {
                                    int start = index * pageSize;
                                    int end = Math.min(start + pageSize, updateSize);
                                    d("市民入库,start=" + start + ", end=" + end);
                                    List<CitizenEntity> tempList = updateData.subList(start, end);
                                    citizenDao.insertAll(tempList);
                                    d("市民入库完成");
                                }
                            }

                            //  删除数据
                            List<CitizenEntity> deleteData = partitioned.get(false);
                            if (deleteData != null) {
                                d("++++++++++ 市民库清理 +++++++++ 总数=" + deleteData.size());
                                citizenDao.clear(deleteData);
                            }

                            callback.updateCitizen(updateData, deleteData);
                        }
                        pf.putLong(LastCitizenUpdateTime, serverTime);
                    }


                    // 当前登录的组织id
                    if (currentOrg != null) {
                        pf.putString(LoginOrgId, currentOrg.getOrgId());
                    }

                    // end
                    callback.nextStatus(SyncCompleteStatus, "执行完成");

                    return RequestResult.okContent("同步完成");
                } catch (Exception e) {
                    return parserException(e);
                }
            }
        });
    }

    /**
     * 同步全量基础数据
     */
    public void syncAll(RequestUserCallback callback) {
        syncAll(2, callback);
    }

    public void syncAll(int faceType, RequestUserCallback callback) {
        InnerPreferences pf = InnerPreferences.instance();
        pf.putLong(LastStudentUpdateTime, -1);
        pf.putLong(LastTeacherUpdateTime, -1);
        pf.putLong(LastCitizenUpdateTime, -1);
        syncBaseInfo(false, PAGE_SIZE, faceType, callback);
    }

    //  全量更新学生信息
    @Deprecated
    public void syncFullStudentInfo(RequestUserCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                try {
                    AdlService adlService = AdlService.getService();
                    InnerPreferences pf = InnerPreferences.instance();

                    // 服务器系统时间
                    long serverTime = UserService.currentSystemTime();
                    if (serverTime <= 0) {
                        return RequestResult.okContent("同步完成");
                    }
                    d("服务器时间: " + InnerUtil.formatByTimeCode(serverTime));

                    ////////////// 1.同步学生基本信息 ////////////
                    callback.nextStatus(SyncStudentStatus, "正在初始化学生信息");
                    List<StudentEntity> studentList = UserService.queryStudent(0);
                    adlService.getStudentDao().clearAll();
                    if (studentList != null && !studentList.isEmpty()) {
                        //  删除标识  0：可用  1：删除
                        List<StudentEntity> filterStudents = studentList.stream().filter(f -> !"1".equals(f.getDelFlag())).collect(Collectors.toList());
                        adlService.getStudentDao().insertAll(filterStudents);
                    }
                    pf.putLong(LastStudentUpdateTime, serverTime);

                    ////////////// 1.同步市民基本信息 ////////////
                    callback.nextStatus(SyncStudentStatus, "正在初始化市民信息");
                    List<CitizenEntity> citizenList = UserService.queryCitizen(0);
                    adlService.getCitizenDao().clearAll();
                    if (citizenList != null && !citizenList.isEmpty()) {
                        //  删除标识  0：可用  1：删除
                        List<CitizenEntity> filterStudents = citizenList.stream().filter(f -> !f.isDelFlag()).collect(Collectors.toList());
                        adlService.getCitizenDao().insertAll(filterStudents);
                    }
                    pf.putLong(LastCitizenUpdateTime, serverTime);

                    ////////////// 2.同步老师基本信息 ////////////
                    callback.nextStatus(SyncTeacherStatus, "正在初始化老师信息");
                    List<TeacherEntity> teacherList = UserService.queryTeacher(0);
                    adlService.getTeacherDao().clearAll();
                    if (teacherList != null && !teacherList.isEmpty()) {
                        List<TeacherEntity> filterTeachers = teacherList.stream().filter(f -> !f.isDelFlag()).collect(Collectors.toList());
                        adlService.getTeacherDao().insertAll(filterTeachers);
                    }
                    pf.putLong(LastTeacherUpdateTime, serverTime);

                    // end
                    callback.nextStatus(SyncCompleteStatus, "执行完成");

                    return RequestResult.okContent("同步完成");
                } catch (Exception e) {
                    return parserException(e);
                }
            }
        });
    }

    // 当前学生列表
    public void queryAllStudent(RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                long startTime = System.currentTimeMillis();
                StudentDao dao = AdlService.getService().getStudentDao();
                List<StudentEntity> list = dao != null ? dao.getAll() : new ArrayList<>();
                d("查询学生耗时=" + (System.currentTimeMillis() - startTime)
                        + ",size=" + (list != null ? list.size() : 0));
                return RequestResult.okContent(list);
            }
        });
    }

    /**
     * 查询有人脸数据的学生列表
     *
     * @param callback
     */
    public void queryAllStudentWithFaceData(RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                long startTime = System.currentTimeMillis();
                StudentDao dao = AdlService.getService().getStudentDao();
                List<StudentEntity> list = dao != null ? dao.queryStudentWithFaceData() : new ArrayList<>();
                d("查询有人脸的学生耗时=" + (System.currentTimeMillis() - startTime)
                        + ",size=" + (list != null ? list.size() : 0));
                return RequestResult.okContent(list);
            }
        });
    }

    /**
     * 查询学生数量
     *
     * @param callback
     */
    public void queryAllStudentCount(RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                long startTime = System.currentTimeMillis();
                StudentDao dao = AdlService.getService().getStudentDao();
                int count = dao != null ? dao.countSize() : 0;
                d("查询学生总数耗时=" + (System.currentTimeMillis() - startTime)
                        + ",size= " + count);
                return RequestResult.okContent(count);
            }
        });
    }


    // 当前老师列表
    public void queryAllTeacher(RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                long startTime = System.currentTimeMillis();
                TeacherDao dao = AdlService.getService().getTeacherDao();
                List<TeacherEntity> list = dao != null ? dao.getAll() : new ArrayList<>();
                d("查询老师耗时=" + (System.currentTimeMillis() - startTime)
                        + ",size=" + (list != null ? list.size() : 0));
                return RequestResult.okContent(list);
            }
        });
    }

    /**
     * 查询有特征值的老师列表
     *
     * @param callback
     */
    public void queryAllTeacherWithFaceData(RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                long startTime = System.currentTimeMillis();
                TeacherDao dao = AdlService.getService().getTeacherDao();
                List<TeacherEntity> list = dao != null ? dao.queryTeacherWithFaceData() : new ArrayList<>();
                d("查询有人脸的老师耗时=" + (System.currentTimeMillis() - startTime)
                        + ",size=" + (list != null ? list.size() : 0));
                return RequestResult.okContent(list);
            }
        });
    }

    /**
     * 查询老师数量
     *
     * @param callback
     */
    public void queryAllTeacherCount(RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                long startTime = System.currentTimeMillis();
                TeacherDao dao = AdlService.getService().getTeacherDao();
                int count = dao != null ? dao.countSize() : 0;
                d("查询老师数量耗时=" + (System.currentTimeMillis() - startTime)
                        + ",size= " + count);
                return RequestResult.okContent(count);
            }
        });
    }

    // 当前市民
    public void queryAllCitizen(RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                long startTime = System.currentTimeMillis();
                CitizenDao dao = AdlService.getService().getCitizenDao();
                List<CitizenEntity> list = dao != null ? dao.getAll() : new ArrayList<>();
                d("查询所有市民耗时=" + (System.currentTimeMillis() - startTime)
                        + ",size=" + (list != null ? list.size() : 0));
                return RequestResult.okContent(list);
            }
        });
    }

    /**
     * 查询有特征值的市民列表
     *
     * @param callback
     */
    public void queryAllCitizenWithFaceData(RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                long startTime = System.currentTimeMillis();
                CitizenDao dao = AdlService.getService().getCitizenDao();
                List<CitizenEntity> list = dao != null ? dao.queryCitizenWithFaceData() : new ArrayList<>();
                d("查询有人脸的市民耗时=" + (System.currentTimeMillis() - startTime)
                        + ",size=" + (list != null ? list.size() : 0));
                return RequestResult.okContent(list);
            }
        });
    }

    /**
     * 查询市民数量
     *
     * @param callback
     */
    public void queryAllCitizenCount(RequestCallback callback) {
        postTask(new RequestWorker(callback) {
            @Override
            public RequestResult doWorking() {
                long startTime = System.currentTimeMillis();
                CitizenDao dao = AdlService.getService().getCitizenDao();
                int count = dao != null ? dao.countSize() : 0;
                d("查询市民数量耗时=" + (System.currentTimeMillis() - startTime)
                        + ",size= " + count);
                return RequestResult.okContent(count);
            }
        });
    }

    // 当前登录机构
    public OrganizationEntity getCurrentOrg() {
        if (currentOrg == null) {
            String str = InnerPreferences.instance().readString(LoginOrgInfo);
            if (!TextUtils.isEmpty(str)) {
                currentOrg = InnerUtil.jsonToObject(str, OrganizationEntity.class);
            }
        }
        return currentOrg;
    }

    // 从数据库查询学生
    public StudentEntity queryStudentByAccountId(String accountId) {
        return AdlService.getService().getStudentDao().queryStudentByAccountId(accountId);
    }

    // 从数据库查询学生
    public List<StudentEntity> queryStudentByAccountIds(List<String> accountIds) {
        return AdlService.getService().getStudentDao().queryStudentByAccountIds(accountIds);
    }

    // 从数据库查询老师
    public TeacherEntity queryTeacherByAccountId(String accountId) {
        return AdlService.getService().getTeacherDao().queryTeacherByAccountId(accountId);
    }

    // 从数据库查询老师
    public List<TeacherEntity> queryTeacherByAccountIds(List<String> accountIds) {
        return AdlService.getService().getTeacherDao().queryTeacherByAccountIds(accountIds);
    }

    // 从数据库查学生
    public List<StudentEntity> queryStudentByClassIds(List<String> classIds) {
        return AdlService.getService().getStudentDao().queryStudentByClassIds(classIds);
    }

    // 从数据库查学生,根据卡号
    public List<StudentEntity> queryStudentByCarNum(String cardNum) {
        return AdlService.getService().getStudentDao().queryStudentWithCardNum(cardNum);
    }

    public void release() {
        hasRequestOrg = false;
    }

}
