package com.adl.service.caller;

import com.adl.service.callback.RequestCallback;
import com.adl.service.exception.NzBaseException;
import com.adl.service.http.request.ClassInfoRequest;
import com.adl.service.http.request.ClassListRequest;
import com.adl.service.http.request.DeviceActiveRequest;
import com.adl.service.http.request.DictRequest;
import com.adl.service.http.request.EmptyRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.http.request.StudentPageRequest;
import com.adl.service.http.request.TeacherListRequest;
import com.adl.service.data.AcayearData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.ClassInfoData;
import com.adl.service.data.ClassData;
import com.adl.service.data.DictData;
import com.adl.service.data.GradeChineseNameData;
import com.adl.service.data.GradeTreeData;
import com.adl.service.data.LoginInfoData;
import com.adl.service.callback.GetPageResult;
import com.adl.service.data.StudentData;
import com.adl.service.data.TeacherData;

import java.util.List;

/**
 * CommonInfoCaller。
 */
public interface CommonInfoCaller {

    void reloadCommonInfoData(boolean forceUpdate, int faceType, long pageSize) throws NzBaseException;

    List<StudentData> queryAllStudentSync() throws NzBaseException;

    List<StudentData> queryAllStudentWithFaceDataSync() throws NzBaseException;

    int queryAllStudentCountSync() throws NzBaseException;

    StudentData queryStudentByAccountIdSync(String accountId) throws NzBaseException;

    List<StudentData> queryStudentByAccountIdsSync(List<String> accountIds) throws NzBaseException;

    List<StudentData> queryStudentByClassIdsSync(List<String> classIds) throws NzBaseException;

    List<StudentData> queryStudentByCarNumSync(String cardNum) throws NzBaseException;

    List<TeacherData> queryAllTeacherListSync() throws NzBaseException;

    List<TeacherData> queryAllTeacherListWithFaceDataSync() throws NzBaseException;

    int queryAllTeacherCountSync(RequestCallback callback) throws NzBaseException;

    TeacherData queryTeacherByAccountIdSync(String accountId) throws NzBaseException;

    List<TeacherData> queryTeacherListByAccountIdsSync(List<String> accountIds) throws NzBaseException;

    /**
     * 获取机构年级树
     * <p>异步调用。</p>
     */
    long getGradeTreeAsync(RequestScope scope, EmptyRequest request, RequestCallback<List<GradeTreeData>> callback);

    /**
     * 获取机构年级树
     * <p>同步调用。</p>
     */
    List<GradeTreeData> getGradeTreeSync(EmptyRequest request) throws NzBaseException;

    /**
     * 获取年级列表
     * <p>异步调用。</p>
     */
    long getGradeChineseNameListAsync(RequestScope scope, EmptyRequest request, RequestCallback<List<GradeChineseNameData>> callback);

    /**
     * 获取年级列表
     * <p>同步调用。</p>
     */
    List<GradeChineseNameData> getGradeChineseNameListSync(EmptyRequest request) throws NzBaseException;

    /**
     * 获取班级列表
     * <p>异步调用。</p>
     */
    long getClassListAsync(RequestScope scope, ClassListRequest request, RequestCallback<List<ClassData>> callback);

    /**
     * 获取班级列表
     * <p>同步调用。</p>
     */
    List<ClassData> getClassListSync(ClassListRequest request) throws NzBaseException;

    /**
     * 获取班级信息
     * <p>异步调用。</p>
     */
    long getClassInfoAsync(RequestScope scope, ClassInfoRequest request, RequestCallback<ClassInfoData> callback);

    /**
     * 获取班级信息
     * <p>同步调用。</p>
     */
    ClassInfoData getClassInfoSync(ClassInfoRequest request) throws NzBaseException;

    LoginInfoData queryLoginInfoSync() throws NzBaseException;

    /**
     * 获取登录用户信息
     * <p>异步调用。</p>
     */
    long getLoginInfoAsync(RequestScope scope, EmptyRequest request, RequestCallback<LoginInfoData> callback);

    /**
     * 获取登录用户信息
     * <p>同步调用。</p>
     */
    LoginInfoData getLoginInfoSync(EmptyRequest request) throws NzBaseException;

    /**
     * 获取学年学期列表
     * <p>异步调用。</p>
     */
    long getAcayearListAsync(RequestScope scope, EmptyRequest request, RequestCallback<List<AcayearData>> callback);

    /**
     * 获取学年学期列表
     * <p>同步调用。</p>
     */
    List<AcayearData> getAcayearListSync(EmptyRequest request) throws NzBaseException;

    long getTeacherListsAllAsync(RequestScope scope, TeacherListRequest request, RequestCallback<GetPageResult> callback);

    GetPageResult getTeacherListsAllSync(TeacherListRequest request) throws NzBaseException;

    /**
     * 获取教师用户列表（全量）
     * <p>异步调用。</p>
     */
    long getTeacherListAsync(RequestScope scope, TeacherListRequest request, RequestCallback<List<TeacherData>> callback);

    /**
     * 获取教师用户列表（全量）
     * <p>同步调用。</p>
     */
    List<TeacherData> getTeacherListSync(TeacherListRequest request) throws NzBaseException;

    long getStudentPagesAllAsync(RequestScope scope, StudentPageRequest request, RequestCallback<GetPageResult> callback);

    GetPageResult getStudentPagesAllSync(StudentPageRequest request) throws NzBaseException;

    /**
     * 获取学生增量数据
     * <p>异步调用。</p>
     */
    long getStudentPageAsync(RequestScope scope, StudentPageRequest request, RequestCallback<BasePageData<StudentData>> callback);

    /**
     * 获取学生增量数据
     * <p>同步调用。</p>
     */
    BasePageData<StudentData> getStudentPageSync(StudentPageRequest request) throws NzBaseException;

    /**
     * 获取系统字典
     * <p>异步调用。</p>
     */
    long getDictListAsync(RequestScope scope, DictRequest request, RequestCallback<List<DictData>> callback);

    /**
     * 获取系统字典
     * <p>同步调用。</p>
     */
    List<DictData> getDictListSync(DictRequest request) throws NzBaseException;

    /**
     * 设备激活
     * <p>异步调用。</p>
     */
    long deviceActiveAsync(RequestScope scope, DeviceActiveRequest request, RequestCallback<Boolean> callback);

    /**
     * 设备激活
     * <p>同步调用。</p>
     */
    Boolean deviceActiveSync(DeviceActiveRequest request) throws NzBaseException;
}
