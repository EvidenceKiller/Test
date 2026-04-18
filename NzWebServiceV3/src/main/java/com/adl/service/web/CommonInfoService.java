package com.adl.service.web;

import com.adl.service.http.request.ClassInfoRequest;
import com.adl.service.http.request.ClassListRequest;
import com.adl.service.http.request.DeviceActiveRequest;
import com.adl.service.http.request.DictRequest;
import com.adl.service.http.request.StudentPageRequest;
import com.adl.service.http.request.TeacherListRequest;
import com.adl.service.data.AcayearData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.BaseResponse;
import com.adl.service.data.ClassInfoData;
import com.adl.service.data.ClassData;
import com.adl.service.data.DictData;
import com.adl.service.data.GradeChineseNameData;
import com.adl.service.data.GradeTreeData;
import com.adl.service.data.LoginInfoData;
import com.adl.service.data.StudentData;
import com.adl.service.data.TeacherData;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Retrofit API（v3new 生成）
 */
public interface CommonInfoService {
    /**
     * 获取机构年级树
     */
    @POST("tis/device/base/org/grade/getGradeTree")
    BaseResponse<List<GradeTreeData>> getGradeTree();

    /**
     * 获取年级列表
     */
    @POST("tis/device/base/org/grade/getGradeChineseNameList")
    BaseResponse<List<GradeChineseNameData>> getGradeChineseNameList();

    /**
     * 获取班级列表
     */
    @POST("tis/device/base/org/class/getClassList")
    BaseResponse<List<ClassData>> getClassList(@Body ClassListRequest request);

    /**
     * 获取班级信息
     */
    @POST("tis/device/base/org/class/getClassInfo")
    BaseResponse<ClassInfoData> getClassInfo(@Body ClassInfoRequest request);

    /**
     * 获取登录用户信息
     */
    @POST("tis/device/base/org/account/getLoginInfo")
    BaseResponse<LoginInfoData> getLoginInfo();

    /**
     * 获取学年学期列表
     */
    @POST("tis/device/base/org/acayear/getAcayearList")
    BaseResponse<List<AcayearData>> getAcayearList();

    /**
     * 获取教师用户列表（全量）
     */
    @POST("tis/device/base/account/getTeacherList")
    BaseResponse<List<TeacherData>> getTeacherList(@Body TeacherListRequest request);

    /**
     * 获取学生增量数据
     */
    @POST("tis/device/base/account/getStudentList")
    BaseResponse<BasePageData<StudentData>> getStudentPage(@Body StudentPageRequest request);

    /**
     * 获取系统字典
     */
    @POST("tis/device/base/sys/dict/getDicts")
    BaseResponse<List<DictData>> getDictList(@Body DictRequest request);

    /**
     * 设备激活
     */
    @POST("tis/device/base/sys/device/active")
    BaseResponse<Boolean> deviceActive(@Body DeviceActiveRequest request);
}
