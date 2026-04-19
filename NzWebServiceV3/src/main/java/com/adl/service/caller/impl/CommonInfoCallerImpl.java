package com.adl.service.caller.impl;

import com.adl.service.AdlService;
import com.adl.service.callback.GetPageResult;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.CommonInfoCaller;
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
import com.adl.service.exception.NzBaseException;
import com.adl.service.http.request.ClassInfoRequest;
import com.adl.service.http.request.ClassListRequest;
import com.adl.service.http.request.DeviceActiveRequest;
import com.adl.service.http.request.DictRequest;
import com.adl.service.http.request.EmptyRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.http.request.StudentPageRequest;
import com.adl.service.http.request.TeacherListRequest;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.repository.CommonInfoRepository;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

/**
 * CommonInfoCaller 实现（SDK 内部使用，通过 {@link AdlService} 暴露）。
 */
public final class CommonInfoCallerImpl implements CommonInfoCaller {

    private final CommonInfoRepository commonInfoRepository;

    public CommonInfoCallerImpl() {
        this.commonInfoRepository = new CommonInfoRepository();
    }

    @Override
    public void reloadCommonInfoData(boolean forceUpdate, int faceType, long pageSize) throws NzBaseException {
        // TODO
        commonInfoRepository.reloadCommonInfoData(forceUpdate, faceType, pageSize);
    }

    @Override
    public List<StudentData> queryAllStudentSync() throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.queryAllStudent());
    }

    @Override
    public List<StudentData> queryAllStudentWithFaceDataSync() throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.queryAllStudentWithFaceData());
    }

    @Override
    public int queryAllStudentCountSync() throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.queryAllStudentCount());
    }

    @Override
    public StudentData queryStudentByAccountIdSync(String accountId) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.queryStudentByAccountId(accountId));
    }

    @Override
    public List<StudentData> queryStudentByAccountIdsSync(List<String> accountIds) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.queryStudentByAccountIds(accountIds));
    }

    @Override
    public List<StudentData> queryStudentByClassIdsSync(List<String> classIds) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.queryStudentByClassIds(classIds));
    }

    @Override
    public List<StudentData> queryStudentByCarNumSync(String cardNum) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.queryStudentByCardNum(cardNum));
    }

    @Override
    public List<TeacherData> queryAllTeacherListSync() throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.queryAllTeacherList());
    }

    @Override
    public List<TeacherData> queryAllTeacherListWithFaceDataSync() throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.queryAllTeacherListWithFaceData());
    }

    @Override
    public int queryAllTeacherCountSync(RequestCallback callback) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.queryAllTeacherCount());
    }

    @Override
    public TeacherData queryTeacherByAccountIdSync(String accountId) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.queryTeacherByAccountId(accountId));
    }

    @Override
    public List<TeacherData> queryTeacherListByAccountIdsSync(List<String> accountIds) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.queryTeacherListByAccountIds(accountIds));
    }

    @Override
    public long getGradeTreeAsync(RequestScope scope, EmptyRequest request, RequestCallback<List<GradeTreeData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(commonInfoRepository.getGradeTree(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<GradeTreeData> getGradeTreeSync(EmptyRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.getGradeTree(request));
    }

    @Override
    public long getGradeChineseNameListAsync(RequestScope scope, EmptyRequest request, RequestCallback<List<GradeChineseNameData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(commonInfoRepository.getGradeChineseNameList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<GradeChineseNameData> getGradeChineseNameListSync(EmptyRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.getGradeChineseNameList(request));
    }

    @Override
    public long getClassListAsync(RequestScope scope, ClassListRequest request, RequestCallback<List<ClassData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(commonInfoRepository.getClassList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<ClassData> getClassListSync(ClassListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.getClassList(request));
    }

    @Override
    public long getClassInfoAsync(RequestScope scope, ClassInfoRequest request, RequestCallback<ClassInfoData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(commonInfoRepository.getClassInfo(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public ClassInfoData getClassInfoSync(ClassInfoRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.getClassInfo(request));
    }

    public LoginInfoData queryLoginInfoSync() throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.queryLoginInfo());
    }

    @Override
    public long getLoginInfoAsync(RequestScope scope, EmptyRequest request, RequestCallback<LoginInfoData> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(commonInfoRepository.getLoginInfo(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public LoginInfoData getLoginInfoSync(EmptyRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.getLoginInfo(request));
    }

    @Override
    public long getAcayearListAsync(RequestScope scope, EmptyRequest request, RequestCallback<List<AcayearData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(commonInfoRepository.getAcayearList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<AcayearData> getAcayearListSync(EmptyRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.getAcayearList(request));
    }

    @Override
    public long getTeacherListsAllAsync(RequestScope scope, TeacherListRequest request, RequestCallback<GetPageResult> callback) {
        CallerUtil.assertCurrent(request);
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(commonInfoRepository.getTeacherListsAll(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public GetPageResult getTeacherListsAllSync(TeacherListRequest request) throws NzBaseException {
        CallerUtil.assertCurrent(request);
        return RxCallbackScheduler.blockingGet(commonInfoRepository.getTeacherListsAll(request));
    }

    @Override
    public long getTeacherListAsync(RequestScope scope, TeacherListRequest request, RequestCallback<List<TeacherData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(commonInfoRepository.getTeacherList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<TeacherData> getTeacherListSync(TeacherListRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.getTeacherList(request));
    }

    @Override
    public long getStudentPagesAllAsync(RequestScope scope, StudentPageRequest request, RequestCallback<GetPageResult> callback) {
        CallerUtil.assertCurrent(request);
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(commonInfoRepository.getStudentPagesAll(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public GetPageResult getStudentPagesAllSync(StudentPageRequest request) throws NzBaseException {
        CallerUtil.assertCurrent(request);
        return RxCallbackScheduler.blockingGet(commonInfoRepository.getStudentPagesAll(request));
    }

    @Override
    public long getStudentPageAsync(RequestScope scope, StudentPageRequest request, RequestCallback<BasePageData<StudentData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(commonInfoRepository.getStudentPage(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public BasePageData<StudentData> getStudentPageSync(StudentPageRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.getStudentPage(request));
    }

    @Override
    public long getDictListAsync(RequestScope scope, DictRequest request, RequestCallback<List<DictData>> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(commonInfoRepository.getDictList(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public List<DictData> getDictListSync(DictRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.getDictList(request));
    }

    @Override
    public long deviceActiveAsync(RequestScope scope, DeviceActiveRequest request, RequestCallback<Boolean> callback) {
        CallerUtil.assertScope(scope);
        Disposable disposable = RxCallbackScheduler.schedule(commonInfoRepository.deviceActive(request), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    @Override
    public Boolean deviceActiveSync(DeviceActiveRequest request) throws NzBaseException {
        return RxCallbackScheduler.blockingGet(commonInfoRepository.deviceActive(request));
    }
}
