package com.adl.service.caller;

import com.adl.service.callback.RequestCallback;
import com.adl.service.exception.NzBaseException;
import com.adl.service.web.request.AddBatchPageRequest;
import com.adl.service.web.request.CitizenPageRequest;
import com.adl.service.web.request.ClassInfoRequest;
import com.adl.service.web.request.ClassListRequest;
import com.adl.service.web.request.DeviceActiveRequest;
import com.adl.service.web.request.DictsRequest;
import com.adl.service.web.request.RequestScope;
import com.adl.service.web.request.StudentPageRequest;
import com.adl.service.web.request.TeacherListRequest;
import com.adl.service.web.response.AcayearData;
import com.adl.service.web.response.BasePageData;
import com.adl.service.web.response.CitizenPageData;
import com.adl.service.web.response.ClassInfoData;
import com.adl.service.web.response.ClassListData;
import com.adl.service.web.response.DictsData;
import com.adl.service.web.response.GradeChineseNameData;
import com.adl.service.web.response.GradeTreeData;
import com.adl.service.web.response.LoginInfoData;
import com.adl.service.web.response.StudentData;
import com.adl.service.web.response.TeacherData;

import java.util.List;

/**
 * CommonInfoCaller。
 */
public interface CommonInfoCaller {

    /**
     * 获取机构年级树
     * <p>异步调用。</p>
     */
    long getGradeTreeAsync(RequestScope scope, RequestCallback<List<GradeTreeData>> callback);

    /**
     * 获取机构年级树
     * <p>同步调用。</p>
     */
    List<GradeTreeData> getGradeTreeSync() throws NzBaseException;

    /**
     * 获取年级列表
     * <p>异步调用。</p>
     */
    long getGradeChineseNameListAsync(RequestScope scope, RequestCallback<List<GradeChineseNameData>> callback);

    /**
     * 获取年级列表
     * <p>同步调用。</p>
     */
    List<GradeChineseNameData> getGradeChineseNameListSync() throws NzBaseException;

    /**
     * 获取班级列表
     * <p>异步调用。</p>
     */
    long getClassListAsync(RequestScope scope, ClassListRequest request, RequestCallback<List<ClassListData>> callback);

    /**
     * 获取班级列表
     * <p>同步调用。</p>
     */
    List<ClassListData> getClassListSync(ClassListRequest request) throws NzBaseException;

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

    /**
     * 获取登录用户信息
     * <p>异步调用。</p>
     */
    long getLoginInfoAsync(RequestScope scope, RequestCallback<LoginInfoData> callback);

    /**
     * 获取登录用户信息
     * <p>同步调用。</p>
     */
    LoginInfoData getLoginInfoSync() throws NzBaseException;

    /**
     * 获取学年学期列表
     * <p>异步调用。</p>
     */
    long getAcayearListAsync(RequestScope scope, RequestCallback<List<AcayearData>> callback);

    /**
     * 获取学年学期列表
     * <p>同步调用。</p>
     */
    List<AcayearData> getAcayearListSync() throws NzBaseException;

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
     * 获取市民用户列表
     * <p>异步调用。</p>
     */
    long getCitizenPageAsync(RequestScope scope, CitizenPageRequest request, RequestCallback<CitizenPageData> callback);

    /**
     * 获取市民用户列表
     * <p>同步调用。</p>
     */
    CitizenPageData getCitizenPageSync(CitizenPageRequest request) throws NzBaseException;

    /**
     * 获取系统字典
     * <p>异步调用。</p>
     */
    long getDictsAsync(RequestScope scope, DictsRequest request, RequestCallback<List<DictsData>> callback);

    /**
     * 获取系统字典
     * <p>同步调用。</p>
     */
    List<DictsData> getDictsSync(DictsRequest request) throws NzBaseException;

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

    /**
     * 添加通用日志
     * <p>异步调用。</p>
     */
    long addBatchPageAsync(RequestScope scope, AddBatchPageRequest request, RequestCallback<Object> callback);

    /**
     * 添加通用日志
     * <p>同步调用。</p>
     */
    Object addBatchPageSync(AddBatchPageRequest request) throws NzBaseException;

}
