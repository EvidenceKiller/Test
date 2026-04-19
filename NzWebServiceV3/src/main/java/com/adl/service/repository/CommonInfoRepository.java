package com.adl.service.repository;

import android.text.TextUtils;

import com.adl.service.callback.GetPageResult;
import com.adl.service.common.FileDownManager;
import com.adl.service.common.IDefine;
import com.adl.service.common.InnerPreferences;
import com.adl.service.data.AcayearData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.ClassData;
import com.adl.service.data.ClassInfoData;
import com.adl.service.data.DictData;
import com.adl.service.data.GradeChineseNameData;
import com.adl.service.data.GradeTreeData;
import com.adl.service.data.LoginInfoData;
import com.adl.service.data.StudentData;
import com.adl.service.data.TeacherData;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.db.StudentDao;
import com.adl.service.exception.NzBaseException;
import com.adl.service.http.request.ClassInfoRequest;
import com.adl.service.http.request.ClassListRequest;
import com.adl.service.http.request.DeviceActiveRequest;
import com.adl.service.http.request.DictRequest;
import com.adl.service.http.request.EmptyRequest;
import com.adl.service.http.request.StudentPageRequest;
import com.adl.service.http.request.TeacherListRequest;
import com.adl.service.log.NzLog;
import com.adl.service.repository.fetch.CommonInfoFetchHelper;
import com.adl.service.repository.persist.CommonInfoPersistHelper;
import com.adl.service.repository.prepare.CommonInfoPrepareHelper;
import com.adl.service.utils.InnerUtil;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public final class CommonInfoRepository {
    private final CommonInfoPrepareHelper prepareHelper = new CommonInfoPrepareHelper();
    private final CommonInfoFetchHelper fetchHelper = new CommonInfoFetchHelper();
    private final CommonInfoPersistHelper persistHelper = new CommonInfoPersistHelper();

    public CommonInfoRepository() {
    }

    public void reloadCommonInfoData(boolean forceUpdate, int faceType, long pageSize) throws NzBaseException {
        InnerPreferences pf = InnerPreferences.instance();

        ////////////// 1.同步机构信息 ////////////
        NzLog.i("同步机构信息");
        //启动时，请求一次机构信息，如果机构变更，清除数据标识
        LoginInfoData loginInfo = queryLoginInfo().blockingGet();
        LoginInfoData newLoginInfo = getLoginInfo(EmptyRequest.builder().forceUpdate(true).build()).blockingGet();
        if (newLoginInfo != null && !TextUtils.isEmpty(newLoginInfo.getOrgId()) && !newLoginInfo.getOrgId().equals(loginInfo.getOrgId())) {
            DaoManagerProxy.getInstance().getStudentDao().clearAll();
            DaoManagerProxy.getInstance().getTeacherDao().clearAll();
            FileDownManager.instance().clearAllFace();
            pf.putLong(IDefine.LAST_STUDENT_UPDATE_TIME, IDefine.INVALID_TIME);
            pf.putLong(IDefine.LAST_TEACHER_UPDATE_TIME, IDefine.INVALID_TIME);
            pf.putLong(IDefine.LAST_CITIZEN_UPDATE_TIME, IDefine.INVALID_TIME);
        }


        ////////////// 2.同步学生基本信息 ////////////
        long serverTime = System.currentTimeMillis();
        try {
            serverTime = InnerUtil.getServerTime();
        } catch (NzBaseException e) {
            NzLog.e("获取服务器时间失败", e.getMessage());
        }
        StudentDao stuDao = DaoManagerProxy.getInstance().getStudentDao();
        int size = stuDao.countSize();
        long lastTime = pf.readLong(IDefine.LAST_STUDENT_UPDATE_TIME);
        NzLog.d("本机时间:size: " + size + ";forceUpdate:" + forceUpdate + ";serverTime:" + serverTime + ";lastTime:" + lastTime + ";UpdateIntervalTime:" + IDefine.UpdateIntervalTime);

        // 是否强制更新数据
        String recentUpdateTimeStr = stuDao.getLastStudentUpdateTime();
        long recentUpdateTime = IDefine.INVALID_TIME;
        try {
            recentUpdateTime = Long.parseLong(recentUpdateTimeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        NzLog.d("本机时间:recentUpdateTime: " + recentUpdateTime);

        StudentPageRequest studentPageRequest = StudentPageRequest.builder()
                .current(1L)
                .pageSize(pageSize)
                .faceType(faceType)
                .lastUpdateTime(forceUpdate ? null : lastTime != IDefine.INVALID_TIME ? InnerUtil.formatByTimeCode(recentUpdateTime) : null)
                .build();

        getStudentPagesAll(studentPageRequest).blockingGet();
        pf.putLong(IDefine.LAST_STUDENT_UPDATE_TIME, serverTime);

        ////////////// 3.同步老师基本信息 ////////////
        size = DaoManagerProxy.getInstance().getTeacherDao().countSize();
        lastTime = pf.readLong(IDefine.LAST_TEACHER_UPDATE_TIME);

        // 数据会空，或者时间间隔2分钟
        if (size == 0 || forceUpdate || serverTime - lastTime > IDefine.UpdateIntervalTime) {
            TeacherListRequest teacherListRequest = TeacherListRequest.builder()
                    .current(1L)
                    .pageSize(pageSize)
                    .lastUpdateTime(forceUpdate ? null : InnerUtil.formatByTimeCode(lastTime))
                    .build();
            getTeacherListsAll(teacherListRequest).blockingGet();
            pf.putLong(IDefine.LAST_TEACHER_UPDATE_TIME, serverTime);
        }
    }

    public Single<List<GradeTreeData>> getGradeTree(EmptyRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetGradeTreeFunc());
    }

    public Single<List<GradeChineseNameData>> getGradeChineseNameList(EmptyRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetGradeChineseNameListFunc());
    }

    public Single<List<ClassData>> getClassList(ClassListRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetClassListFunc());
    }

    public Single<ClassInfoData> getClassInfo(ClassInfoRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetClassInfoFunc());
    }

    public Single<LoginInfoData> queryLoginInfo() {
        return BaseRepositoryUtil.repositoryAsSingle(prepareHelper.createPrepareLoginInfoFunc());
    }

    public Single<LoginInfoData> getLoginInfo(EmptyRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request,
                fetchHelper.createGetLoginInfoFunc(),
                persistHelper.createPersistLoginInfoFunc());
    }

    public Single<List<AcayearData>> getAcayearList(EmptyRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetAcayearListFunc());
    }

    public Single<List<TeacherData>> queryAllTeacherList() {
        return BaseRepositoryUtil.repositoryAsSingle(prepareHelper.createPrepareTeacherListFunc());
    }

    public Single<List<TeacherData>> queryAllTeacherListWithFaceData() {
        return BaseRepositoryUtil.repositoryAsSingle(prepareHelper.createPrepareTeacherListWithFaceDataFunc());
    }

    public Single<Integer> queryAllTeacherCount() {
        return BaseRepositoryUtil.repositoryAsSingle(prepareHelper.createPrepareTeacherCountFunc());
    }

    public Single<TeacherData> queryTeacherByAccountId(String accountId) {
        return BaseRepositoryUtil.repositoryAsSingle(accountId,
                prepareHelper.createPrepareTeacherByAccountIdFunc());
    }

    public Single<List<TeacherData>> queryTeacherListByAccountIds(List<String> accountIds) {
        return BaseRepositoryUtil.repositoryAsSingle(accountIds,
                prepareHelper.createPrepareTeacherListByAccountIdsFunc());
    }

    public Single<GetPageResult> getTeacherListsAll(TeacherListRequest request) {
        return BaseRepositoryUtil.repositoryListsAsSingle(request,
                prepareHelper.createPrepareTeacherListsAllFunc(),
                fetchHelper.createGetTeacherListsAllFunc(request),
                persistHelper.createPersistTeacherList());
    }

    public Single<List<TeacherData>> getTeacherList(TeacherListRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request,
                fetchHelper.createGetTeacherListFunc(),
                persistHelper.createPersistTeacherList());
    }

    public Single<List<StudentData>> queryAllStudent() {
        return BaseRepositoryUtil.repositoryAsSingle(prepareHelper.createPrepareStudentFunc());
    }

    public Single<List<StudentData>> queryAllStudentWithFaceData() {
        return BaseRepositoryUtil.repositoryAsSingle(prepareHelper.createPrepareStudentWithFaceDataFunc());
    }

    public Single<Integer> queryAllStudentCount() {
        return BaseRepositoryUtil.repositoryAsSingle(prepareHelper.createPrepareStudentCountFunc());
    }

    public Single<StudentData> queryStudentByAccountId(String accountId) {
        return BaseRepositoryUtil.repositoryAsSingle(accountId, prepareHelper.createPrepareStudentByAccountIdFunc());
    }

    public Single<List<StudentData>> queryStudentByAccountIds(List<String> accountIds) {
        return BaseRepositoryUtil.repositoryAsSingle(accountIds, prepareHelper.createPrepareStudentByAccountIdsFunc());
    }

    public Single<List<StudentData>> queryStudentByClassIds(List<String> classIds) {
        return BaseRepositoryUtil.repositoryAsSingle(classIds, prepareHelper.createPrepareStudentByClassIdsFunc());
    }

    public Single<List<StudentData>> queryStudentByCardNum(String cardNum) {
        return BaseRepositoryUtil.repositoryAsSingle(cardNum, prepareHelper.createPrepareStudentByCardNumFunc());
    }

    public Single<GetPageResult> getStudentPagesAll(StudentPageRequest request) {
        return BaseRepositoryUtil.repositoryPagesAsSingle(request,
                prepareHelper.createPrepareStudentPagesAllFunc(),
                fetchHelper.createGetStudentPagesAllFunc(request),
                persistHelper.createPersistStudentPageFunc()
        );
    }

    public Single<BasePageData<StudentData>> getStudentPage(StudentPageRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request,
                fetchHelper.createGetStudentPageFunc(),
                persistHelper.createPersistStudentPageFunc());
    }

    public Single<List<DictData>> getDictList(DictRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request,
                prepareHelper.createPrepareDictListFunc(),
                fetchHelper.createGetDictListFunc(),
                persistHelper.createPersistDictListFunc(request));
    }

    public Single<Boolean> deviceActive(DeviceActiveRequest request) {
        return BaseRepositoryUtil.repositoryAsSingle(request, fetchHelper.createGetDeviceActiveFunc());
    }
}
