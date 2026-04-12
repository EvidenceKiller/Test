package com.adl.service.caller;

import android.text.TextUtils;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.callback.RequestUserCallback;
import com.adl.service.common.BaseService;
import com.adl.service.common.FileDownManager;
import com.adl.service.common.IDefine;
import com.adl.service.common.InnerPreferences;
import com.adl.service.common.InnerUtil;
import com.adl.service.db.CitizenDao;
import com.adl.service.db.StudentDao;
import com.adl.service.db.TeacherDao;
import com.adl.service.entity.CitizenEntity;
import com.adl.service.entity.OrganizationEntity;
import com.adl.service.entity.StudentEntity;
import com.adl.service.entity.TeacherEntity;
import com.adl.service.log.NzLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 用户基础信息同步与本地库查询（含 Dao）。
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

    /**
     * 在 IO 线程执行返回 {@link RequestResult} 的任务，在主线程回调（等价于原 {@link com.adl.service.common.RequestWorker} 行为）。
     */
    private Disposable executeRequestResult(RequestCallback callback, Callable<RequestResult> work) {
        return Single.fromCallable(work)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> {
                    if (callback != null) {
                        callback.start();
                    }
                })
                .doFinally(() -> {
                    if (callback != null) {
                        callback.end();
                    }
                })
                .subscribe(
                        result -> {
                            if (callback == null) {
                                return;
                            }
                            if (result != null && result.isOk()) {
                                callback.onResult(result);
                            } else {
                                if (result != null) {
                                    callback.showError(result);
                                }
                                callback.onError(result != null ? result : RequestResult.error("unknown"));
                            }
                        },
                        throwable -> {
                            if (callback != null) {
                                RequestResult err = RequestResult.error(throwable != null ? throwable.getMessage() : "error");
                                callback.showError(err);
                                callback.onError(err);
                            }
                        }
                );
    }

    public void syncBaseInfo(RequestUserCallback callback) {
        syncBaseInfo(false, callback);
    }

    public void syncBaseInfo(boolean forceUpdate, RequestUserCallback callback) {
        syncBaseInfo(forceUpdate, PAGE_SIZE, callback);
    }

    public void syncBaseInfo(boolean forceUpdate, int pageSize, RequestUserCallback callback) {
        syncBaseInfo(forceUpdate, pageSize, 2, callback);
    }

    public void syncBaseInfo(boolean forceUpdate, int pageSize, int faceType, RequestUserCallback callback) {
        executeRequestResult(callback, () -> {
            try {

                InnerPreferences pf = InnerPreferences.instance();
                AdlService adlService = AdlService.getService();

                callback.nextStatus(SyncOrgStatus, "正在同步机构信息");
                if (!hasRequestOrg) {
                    RequestResult result = UserService.queryLoginOrganization();
                    if (result.isOk()) {
                        String str = result.getContent().toString();
                        currentOrg = InnerUtil.jsonToObject(str, OrganizationEntity.class);
                        if (adlService != null) {
                            adlService.setOrgId(currentOrg.getOrgId());
                        }
                        String saveOrgStr = pf.readString(IDefine.LoginOrgInfo);
                        if (!TextUtils.isEmpty(saveOrgStr)) {
                            OrganizationEntity saveOrg = InnerUtil.jsonToObject(saveOrgStr, OrganizationEntity.class);
                            String saveOrgId = saveOrg.getOrgId();
                            if (currentOrg != null && !TextUtils.isEmpty(saveOrgId) && !saveOrgId.equals(currentOrg.getOrgId())) {
                                adlService.getDaoManagerProxy().getStudentDao().clearAll();
                                adlService.getDaoManagerProxy().getTeacherDao().clearAll();
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

                long serverTime = System.currentTimeMillis();
                callback.nextStatus(SyncStudentStatus, "正在初始化学生信息");
                StudentDao stuDao = adlService.getDaoManagerProxy().getStudentDao();
                int size = stuDao.countSize();
                long lastTime = pf.readLong(LastStudentUpdateTime);
                NzLog.d("本机时间:size: " + size + ";forceUpdate:" + forceUpdate + ";serverTime:" + serverTime + ";lastTime:" + lastTime + ";UpdateIntervalTime:" + UpdateIntervalTime);

                String recentUpdateTimeStr = stuDao.getLastStudentUpdateTime();
                long recentUpdateTime = -1;
                try {
                    recentUpdateTime = Long.parseLong(recentUpdateTimeStr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                NzLog.d("本机时间:recentUpdateTime: " + recentUpdateTime);

                long updateTime = forceUpdate ? -1 : lastTime == -1 ? -1 : recentUpdateTime;

                List<StudentEntity> listStudent = UserService.queryStudentByPage(updateTime, pageSize, faceType);
                if (updateTime <= 0) {
                    stuDao.clearAll();
                }
                if (listStudent != null && listStudent.size() > 0) {

                    Map<Boolean, List<StudentEntity>> partitioned = listStudent.stream()
                            .collect(Collectors.partitioningBy(student -> "1".equals(student.getDelFlag())));

                    List<StudentEntity> updateData = partitioned.get(false);
                    if (updateData != null) {
                        int updateSize = updateData.size();
                        int n = updateSize / pageSize;
                        int r = updateSize % pageSize;
                        int total = r > 0 ? n + 1 : n;
                        NzLog.d("++++++++++ 学生开始 +++++++++ 总数=" + updateSize);
                        for (int index = 0; index < total; index++) {
                            int start = index * pageSize;
                            int end = Math.min(start + pageSize, updateSize);
                            NzLog.d("学生入库,start=" + start + ", end=" + end);
                            List<StudentEntity> tempList = updateData.subList(start, end);
                            stuDao.insertAll(tempList);
                            NzLog.d("学生入库完成");
                        }
                    }

                    List<StudentEntity> deleteData = partitioned.get(true);
                    if (deleteData != null) {
                        NzLog.d("++++++++++ 学生库清理 +++++++++ 总数=" + deleteData.size());
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

                callback.nextStatus(SyncTeacherStatus, "正在初始化老师信息");
                TeacherDao tchDao = adlService.getDaoManagerProxy().getDagetTeacherDao();
                size = tchDao.countSize();
                lastTime = pf.readLong(LastTeacherUpdateTime);

                if (size == 0 || forceUpdate || serverTime - lastTime > UpdateIntervalTime) {

                    List<TeacherEntity> list = UserService.queryTeacher(forceUpdate ? -1 : lastTime);
                    if (forceUpdate || lastTime <= 0) {
                        tchDao.clearAll();
                    }
                    if (list != null && !list.isEmpty()) {
                        Map<Boolean, List<TeacherEntity>> partitioned = list.stream()
                                .collect(Collectors.partitioningBy(teacher -> !teacher.isDelFlag()));

                        List<TeacherEntity> updateData = partitioned.get(true);
                        tchDao.insertAll(updateData);

                        List<TeacherEntity> deleteData = partitioned.get(false);
                        tchDao.clear(deleteData);

                        callback.updateTeacher(updateData, deleteData);
                    }
                    pf.putLong(LastTeacherUpdateTime, serverTime);
                }

                callback.nextStatus(SyncCitizenStatus, "正在初始化市民信息");
                CitizenDao citizenDao = adlService.getDaoManagerProxy().getCitizenDao();
                size = citizenDao.countSize();
                lastTime = pf.readLong(LastCitizenUpdateTime);
                if (size == 0 || forceUpdate || serverTime - lastTime > UpdateIntervalTime) {

                    List<CitizenEntity> list = UserService.queryCitizenByPage(forceUpdate ? -1 : lastTime, pageSize, faceType);
                    if (forceUpdate || lastTime <= 0) {
                        citizenDao.clearAll();
                    }
                    if (list != null && list.size() > 0) {

                        Map<Boolean, List<CitizenEntity>> partitioned = list.stream()
                                .collect(Collectors.partitioningBy(citizen -> !citizen.isDelFlag()));

                        List<CitizenEntity> updateData = partitioned.get(true);
                        if (updateData != null) {
                            int updateSize = updateData.size();
                            int n = updateSize / pageSize;
                            int r = updateSize % pageSize;
                            int total = r > 0 ? n + 1 : n;
                            NzLog.d("++++++++++ 市民开始 +++++++++ 总数=" + updateSize);
                            for (int index = 0; index < total; index++) {
                                int start = index * pageSize;
                                int end = Math.min(start + pageSize, updateSize);
                                NzLog.d("市民入库,start=" + start + ", end=" + end);
                                List<CitizenEntity> tempList = updateData.subList(start, end);
                                citizenDao.insertAll(tempList);
                                NzLog.d("市民入库完成");
                            }
                        }

                        List<CitizenEntity> deleteData = partitioned.get(false);
                        if (deleteData != null) {
                            NzLog.d("++++++++++ 市民库清理 +++++++++ 总数=" + deleteData.size());
                            citizenDao.clear(deleteData);
                        }

                        callback.updateCitizen(updateData, deleteData);
                    }
                    pf.putLong(LastCitizenUpdateTime, serverTime);
                }

                if (currentOrg != null) {
                    pf.putString(LoginOrgId, currentOrg.getOrgId());
                }

                callback.nextStatus(SyncCompleteStatus, "执行完成");

                return RequestResult.okContent("同步完成");
            } catch (Exception e) {
                return parserException(e);
            }
        });
    }

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

    public void queryAllStudent(RequestCallback callback) {
        executeRequestResult(callback, () -> {
            long startTime = System.currentTimeMillis();
            StudentDao dao = AdlService.getService().getDaoManagerProxy().getStudentDao();
            List<StudentEntity> list = dao != null ? dao.getAll() : new ArrayList<>();
            NzLog.d("查询学生耗时=" + (System.currentTimeMillis() - startTime)
                    + ",size=" + (list != null ? list.size() : 0));
            return RequestResult.okContent(list);
        });
    }

    public void queryAllStudentWithFaceData(RequestCallback callback) {
        executeRequestResult(callback, () -> {
            long startTime = System.currentTimeMillis();
            StudentDao dao = AdlService.getService().getDaoManagerProxy().getStudentDao();
            List<StudentEntity> list = dao != null ? dao.queryStudentWithFaceData() : new ArrayList<>();
            NzLog.d("查询有人脸的学生耗时=" + (System.currentTimeMillis() - startTime)
                    + ",size=" + (list != null ? list.size() : 0));
            return RequestResult.okContent(list);
        });
    }

    public void queryAllStudentCount(RequestCallback callback) {
        executeRequestResult(callback, () -> {
            long startTime = System.currentTimeMillis();
            StudentDao dao = AdlService.getService().getDaoManagerProxy().getStudentDao();
            int count = dao != null ? dao.countSize() : 0;
            NzLog.d("查询学生总数耗时=" + (System.currentTimeMillis() - startTime)
                    + ",size= " + count);
            return RequestResult.okContent(count);
        });
    }

    public void queryAllTeacher(RequestCallback callback) {
        executeRequestResult(callback, () -> {
            long startTime = System.currentTimeMillis();
            TeacherDao dao = AdlService.getService().getDaoManagerProxy().getTeacherDao();
            List<TeacherEntity> list = dao != null ? dao.getAll() : new ArrayList<>();
            NzLog.d("查询老师耗时=" + (System.currentTimeMillis() - startTime)
                    + ",size=" + (list != null ? list.size() : 0));
            return RequestResult.okContent(list);
        });
    }

    public void queryAllTeacherWithFaceData(RequestCallback callback) {
        executeRequestResult(callback, () -> {
            long startTime = System.currentTimeMillis();
            TeacherDao dao = AdlService.getService().getDaoManagerProxy().getTeacherDao();
            List<TeacherEntity> list = dao != null ? dao.queryTeacherWithFaceData() : new ArrayList<>();
            NzLog.d("查询有人脸的老师耗时=" + (System.currentTimeMillis() - startTime)
                    + ",size=" + (list != null ? list.size() : 0));
            return RequestResult.okContent(list);
        });
    }

    public void queryAllTeacherCount(RequestCallback callback) {
        executeRequestResult(callback, () -> {
            long startTime = System.currentTimeMillis();
            TeacherDao dao = AdlService.getService().getDaoManagerProxy().getTeacherDao();
            int count = dao != null ? dao.countSize() : 0;
            NzLog.d("查询老师数量耗时=" + (System.currentTimeMillis() - startTime)
                    + ",size= " + count);
            return RequestResult.okContent(count);
        });
    }

    public void queryAllCitizen(RequestCallback callback) {
        executeRequestResult(callback, () -> {
            long startTime = System.currentTimeMillis();
            CitizenDao dao = AdlService.getService().getDaoManagerProxy().getCitizenDao();
            List<CitizenEntity> list = dao != null ? dao.getAll() : new ArrayList<>();
            NzLog.d("查询所有市民耗时=" + (System.currentTimeMillis() - startTime)
                    + ",size=" + (list != null ? list.size() : 0));
            return RequestResult.okContent(list);
        });
    }

    public void queryAllCitizenWithFaceData(RequestCallback callback) {
        executeRequestResult(callback, () -> {
            long startTime = System.currentTimeMillis();
            CitizenDao dao = AdlService.getService().getDaoManagerProxy().getCitizenDao();
            List<CitizenEntity> list = dao != null ? dao.queryCitizenWithFaceData() : new ArrayList<>();
            NzLog.d("查询有人脸的市民耗时=" + (System.currentTimeMillis() - startTime)
                    + ",size=" + (list != null ? list.size() : 0));
            return RequestResult.okContent(list);
        });
    }

    public void queryAllCitizenCount(RequestCallback callback) {
        executeRequestResult(callback, () -> {
            long startTime = System.currentTimeMillis();
            CitizenDao dao = AdlService.getService().getDaoManagerProxy().getCitizenDao();
            int count = dao != null ? dao.countSize() : 0;
            NzLog.d("查询市民数量耗时=" + (System.currentTimeMillis() - startTime)
                    + ",size= " + count);
            return RequestResult.okContent(count);
        });
    }

    public OrganizationEntity getCurrentOrg() {
        if (currentOrg == null) {
            String str = InnerPreferences.instance().readString(LoginOrgInfo);
            if (!TextUtils.isEmpty(str)) {
                currentOrg = InnerUtil.jsonToObject(str, OrganizationEntity.class);
            }
        }
        return currentOrg;
    }

    public StudentEntity queryStudentByAccountId(String accountId) {
        return AdlService.getService().getDaoManagerProxy().getStudentDao().queryStudentByAccountId(accountId);
    }

    public List<StudentEntity> queryStudentByAccountIds(List<String> accountIds) {
        return AdlService.getService().getDaoManagerProxy().getStudentDao().queryStudentByAccountIds(accountIds);
    }

    public TeacherEntity queryTeacherByAccountId(String accountId) {
        return AdlService.getService().getDaoManagerProxy().getTeacherDao().queryTeacherByAccountId(accountId);
    }

    public List<TeacherEntity> queryTeacherByAccountIds(List<String> accountIds) {
        return AdlService.getService().getDaoManagerProxy().getTeacherDao().queryTeacherByAccountIds(accountIds);
    }

    public List<StudentEntity> queryStudentByClassIds(List<String> classIds) {
        return AdlService.getService().getDaoManagerProxy().getStudentDao().queryStudentByClassIds(classIds);
    }

    public List<StudentEntity> queryStudentByCarNum(String cardNum) {
        return AdlService.getService().getDaoManagerProxy().getStudentDao().queryStudentWithCardNum(cardNum);
    }

    public void release() {
        hasRequestOrg = false;
    }
}
