package com.adl.service.caller.impl;

import android.text.TextUtils;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.CommonInfoCaller;
import com.adl.service.common.FileDownManager;
import com.adl.service.common.IDefine;
import com.adl.service.common.InnerPreferences;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.db.StudentDao;
import com.adl.service.db.entity.LoginInfoEntity;
import com.adl.service.db.entity.StudentEntity;
import com.adl.service.db.entity.TeacherEntity;
import com.adl.service.exception.NzBaseException;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.log.NzLog;
import com.adl.service.persistence.BasePagePersistence;
import com.adl.service.persistence.BasePersistence;
import com.adl.service.persistence.CommonInfoPersistenceHelper;
import com.adl.service.utils.InnerUtil;
import com.adl.service.http.request.ClassInfoRequest;
import com.adl.service.http.request.ClassListRequest;
import com.adl.service.http.request.DeviceActiveRequest;
import com.adl.service.http.request.DictRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.http.request.StudentPageRequest;
import com.adl.service.http.request.TeacherListRequest;
import com.adl.service.data.AcayearData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.ClassData;
import com.adl.service.data.ClassInfoData;
import com.adl.service.data.DictData;
import com.adl.service.data.GradeChineseNameData;
import com.adl.service.data.GradeTreeData;
import com.adl.service.data.LoginInfoData;
import com.adl.service.callback.GetPageResult;
import com.adl.service.data.StudentData;
import com.adl.service.data.TeacherData;
import com.adl.service.web.CommonInfoService;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * CommonInfoCaller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class CommonInfoCallerImpl implements CommonInfoCaller {

    private final CommonInfoService commonInfoService;

    public CommonInfoCallerImpl(CommonInfoService commonInfoService) {
        this.commonInfoService = commonInfoService;
    }

    @Override
    public void reloadCommonInfoData(boolean forceUpdate, int faceType, long pageSize) throws NzBaseException {
        InnerPreferences pf = InnerPreferences.instance();

        ////////////// 1.同步机构信息 ////////////
        NzLog.i("同步机构信息");
        //启动时，请求一次机构信息，如果机构变更，清除数据标识
        LoginInfoData loginInfo = queryLoginInfoSync();
        LoginInfoData newLoginInfo = getLoginInfoSync();
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

        getStudentPagesAllSync(studentPageRequest);
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
            getTeacherListsAllSync(teacherListRequest);
            pf.putLong(IDefine.LAST_TEACHER_UPDATE_TIME, serverTime);
        }
    }

    @Override
    public List<StudentData> queryAllStudentSync() {
        return DaoManagerProxy.getInstance().getStudentDao().getAll().stream()
                .map(data -> StudentEntity.convertToData(data))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentData> queryAllStudentWithFaceDataSync() {
        return DaoManagerProxy.getInstance().getStudentDao().queryStudentWithFaceData().stream()
                .map(data -> StudentEntity.convertToData(data))
                .collect(Collectors.toList());
    }

    @Override
    public int queryAllStudentCountSync() {
        return DaoManagerProxy.getInstance().getStudentDao().countSize();
    }

    @Override
    public StudentData queryStudentByAccountIdSync(String accountId) {
        return StudentEntity.convertToData(DaoManagerProxy.getInstance().getStudentDao().queryStudentByAccountId(accountId));
    }

    @Override
    public List<StudentData> queryStudentByAccountIdsSync(List<String> accountIds) {
        return DaoManagerProxy.getInstance().getStudentDao().queryStudentByAccountIds(accountIds).stream()
                .map(data -> StudentEntity.convertToData(data))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentData> queryStudentByClassIdsSync(List<String> classIds) {
        return DaoManagerProxy.getInstance().getStudentDao().queryStudentByClassIds(classIds).stream()
                .map(data -> StudentEntity.convertToData(data))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentData> queryStudentByCarNumSync(String cardNum) {
        return DaoManagerProxy.getInstance().getStudentDao().queryStudentWithCardNum(cardNum).stream()
                .map(data -> StudentEntity.convertToData(data))
                .collect(Collectors.toList());
    }

    @Override
    public List<TeacherData> queryAllTeacherSync() {
        return DaoManagerProxy.getInstance().getTeacherDao().getAll().stream()
                .map(data -> TeacherEntity.convertToData(data))
                .collect(Collectors.toList());
    }

    @Override
    public List<TeacherData> queryAllTeacherWithFaceDataSync() {
        return DaoManagerProxy.getInstance().getTeacherDao().queryTeacherWithFaceData().stream()
                .map(data -> TeacherEntity.convertToData(data))
                .collect(Collectors.toList());
    }

    @Override
    public int queryAllTeacherCountSync(RequestCallback callback) {
        return DaoManagerProxy.getInstance().getTeacherDao().countSize();
    }

    @Override
    public TeacherData queryTeacherByAccountIdSync(String accountId) {
        return TeacherEntity.convertToData(DaoManagerProxy.getInstance().getTeacherDao().queryTeacherByAccountId(accountId));
    }

    @Override
    public List<TeacherData> queryTeacherByAccountIdsSync(List<String> accountIds) {
        return DaoManagerProxy.getInstance().getTeacherDao().queryTeacherByAccountIds(accountIds).stream()
                .map(data -> TeacherEntity.convertToData(data))
                .collect(Collectors.toList());
    }

    @Override
    public long getGradeTreeAsync(RequestScope scope, RequestCallback<List<GradeTreeData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(commonInfoService.getGradeTree(), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<GradeTreeData> getGradeTreeSync() throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(commonInfoService.getGradeTree());
    }

    @Override
    public long getGradeChineseNameListAsync(RequestScope scope, RequestCallback<List<GradeChineseNameData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(commonInfoService.getGradeChineseNameList(), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<GradeChineseNameData> getGradeChineseNameListSync() throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(commonInfoService.getGradeChineseNameList());
    }

    @Override
    public long getClassListAsync(RequestScope scope, ClassListRequest request, RequestCallback<List<ClassData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(commonInfoService.getClassList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<ClassData> getClassListSync(ClassListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(commonInfoService.getClassList(request));
    }

    @Override
    public long getClassInfoAsync(RequestScope scope, ClassInfoRequest request, RequestCallback<ClassInfoData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(commonInfoService.getClassInfo(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public ClassInfoData getClassInfoSync(ClassInfoRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(commonInfoService.getClassInfo(request));
    }

    public LoginInfoData queryLoginInfoSync() {
        return LoginInfoEntity.convertToData(DaoManagerProxy.getInstance().getLoginInfoDao().getLoginInfo());
    }

    @Override
    public long getLoginInfoAsync(RequestScope scope, RequestCallback<LoginInfoData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(BasePersistence.persistAsSingle(
                BasePersistence.createBaseRequester(null, (Void) -> commonInfoService.getLoginInfo()),
                CommonInfoPersistenceHelper.createLoginPersister()), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public LoginInfoData getLoginInfoSync() throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(BasePersistence.persistAsSingle(
                BasePersistence.createBaseRequester(null, (Void) -> commonInfoService.getLoginInfo()),
                CommonInfoPersistenceHelper.createLoginPersister()));
    }

    @Override
    public long getAcayearListAsync(RequestScope scope, RequestCallback<List<AcayearData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(commonInfoService.getAcayearList(), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<AcayearData> getAcayearListSync() throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(commonInfoService.getAcayearList());
    }

    @Override
    public long getTeacherListsAllAsync(RequestScope scope, TeacherListRequest request, RequestCallback<GetPageResult> callback) {
        CallerUtil.assertCurrent(request);
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(BasePagePersistence.persistAllListsAsSingle(
                CommonInfoPersistenceHelper.createTeacherListPreparer(),
                CommonInfoPersistenceHelper.createTeacherListRequester(request, req -> commonInfoService.getTeacherList(req)),
                CommonInfoPersistenceHelper.createTeacherListPersister()), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public GetPageResult getTeacherListsAllSync(TeacherListRequest request) throws NzBaseException {
        CallerUtil.assertCurrent(request);
        return RxCallbackScheduler.blockingGet(BasePagePersistence.persistAllListsAsSingle(
                CommonInfoPersistenceHelper.createTeacherListPreparer(),
                CommonInfoPersistenceHelper.createTeacherListRequester(request, req -> commonInfoService.getTeacherList(req)),
                CommonInfoPersistenceHelper.createTeacherListPersister()));
    }

    @Override
    public long getTeacherListAsync(RequestScope scope, TeacherListRequest request, RequestCallback<List<TeacherData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(BasePagePersistence.persistOneListAsSingle(
                BasePersistence.createBaseRequester(null, (Void) -> commonInfoService.getTeacherList(request)),
                CommonInfoPersistenceHelper.createTeacherListPersister()), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<TeacherData> getTeacherListSync(TeacherListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(BasePagePersistence.persistOneListAsSingle(
                BasePersistence.createBaseRequester(null, (Void) -> commonInfoService.getTeacherList(request)),
                CommonInfoPersistenceHelper.createTeacherListPersister()));
    }

    @Override
    public long getStudentPagesAllAsync(RequestScope scope, StudentPageRequest request, RequestCallback<GetPageResult> callback) {
        CallerUtil.assertCurrent(request);
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(BasePagePersistence.persistAllPagesAsSingle(
                CommonInfoPersistenceHelper.createStudentPagePreparer(),
                CommonInfoPersistenceHelper.createStudentPageRequester(request, req -> commonInfoService.getStudentPage(req)),
                CommonInfoPersistenceHelper.createStudentPagePersister()), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public GetPageResult getStudentPagesAllSync(StudentPageRequest request) throws NzBaseException {
        CallerUtil.assertCurrent(request);
        return RxCallbackScheduler.blockingGet(BasePagePersistence.persistAllPagesAsSingle(
                CommonInfoPersistenceHelper.createStudentPagePreparer(),
                CommonInfoPersistenceHelper.createStudentPageRequester(request, req -> commonInfoService.getStudentPage(req)),
                CommonInfoPersistenceHelper.createStudentPagePersister()));
    }

    @Override
    public long getStudentPageAsync(RequestScope scope, StudentPageRequest request, RequestCallback<BasePageData<StudentData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(BasePagePersistence.persistOnePageAsSingle(
                BasePersistence.createBaseRequester(null, (Void) -> commonInfoService.getStudentPage(request)),
                CommonInfoPersistenceHelper.createStudentPagePersister()), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<StudentData> getStudentPageSync(StudentPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(BasePagePersistence.persistOnePageAsSingle(
                BasePersistence.createBaseRequester(null, (Void) -> commonInfoService.getStudentPage(request)),
                CommonInfoPersistenceHelper.createStudentPagePersister()));
    }

    @Override
    public long getDictListAsync(RequestScope scope, DictRequest request, RequestCallback<List<DictData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(BasePersistence.persistAsSingle(
                CommonInfoPersistenceHelper.createDictListPreparer(request.getDictId()),
                BasePersistence.createBaseRequester(request, req -> commonInfoService.getDictList(req)),
                CommonInfoPersistenceHelper.createDictListPersister()), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<DictData> getDictListSync(DictRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(BasePersistence.persistAsSingle(
                CommonInfoPersistenceHelper.createDictListPreparer(request.getDictId()),
                BasePersistence.createBaseRequester(request, req -> commonInfoService.getDictList(req)),
                CommonInfoPersistenceHelper.createDictListPersister()));
    }

    @Override
    public long deviceActiveAsync(RequestScope scope, DeviceActiveRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.scheduleBaseResponse(commonInfoService.deviceActive(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean deviceActiveSync(DeviceActiveRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGetFromResponse(commonInfoService.deviceActive(request));
    }
}
