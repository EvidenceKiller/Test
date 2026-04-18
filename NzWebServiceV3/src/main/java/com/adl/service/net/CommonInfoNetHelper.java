package com.adl.service.net;

import com.adl.service.data.AcayearData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.BaseResponse;
import com.adl.service.data.ClassData;
import com.adl.service.data.ClassInfoData;
import com.adl.service.data.DictData;
import com.adl.service.data.GradeChineseNameData;
import com.adl.service.data.GradeTreeData;
import com.adl.service.data.LoginInfoData;
import com.adl.service.data.StudentData;
import com.adl.service.data.TeacherData;
import com.adl.service.http.request.ClassInfoRequest;
import com.adl.service.http.request.ClassListRequest;
import com.adl.service.http.request.DeviceActiveRequest;
import com.adl.service.http.request.DictRequest;
import com.adl.service.http.request.StudentPageRequest;
import com.adl.service.http.request.TeacherListRequest;
import com.adl.service.internal.RetrofitManager;
import com.adl.service.web.CommonInfoService;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

public final class CommonInfoNetHelper {

    private final CommonInfoService commonInfoService;

    public CommonInfoNetHelper() {
        this.commonInfoService = RetrofitManager.getInstance().create(CommonInfoService.class);
    }

    public Function<Void, List<GradeTreeData>> createGetGradeTreeFunc() {
        return (request) -> BaseNetUtil.scheduleResponse(commonInfoService.getGradeTree());
    }

    public Function<Void, List<GradeChineseNameData>> createGetGradeChineseNameListFunc() {
        return (request) -> BaseNetUtil.scheduleResponse(commonInfoService.getGradeChineseNameList());
    }

    public Function<ClassListRequest, List<ClassData>> createGetClassListFunc() {
        return (request) -> BaseNetUtil.scheduleResponse(commonInfoService.getClassList(request));
    }

    public Function<ClassInfoRequest, ClassInfoData> createGetClassInfoFunc() {
        return (request) -> BaseNetUtil.scheduleResponse(commonInfoService.getClassInfo(request));
    }

    public Function<Void, LoginInfoData> createGetLoginInfoFunc() {
        return (request) -> BaseNetUtil.scheduleResponse(commonInfoService.getLoginInfo());
    }

    public Function<Void, List<AcayearData>> createGetAcayearListFunc() {
        return (request) -> BaseNetUtil.scheduleResponse(commonInfoService.getAcayearList());
    }

    public Function<Long, List<TeacherData>> createGetTeacherListsAllFunc(TeacherListRequest request) {
        return (page) -> {
            TeacherListRequest pageReq = TeacherListRequest.builder()
                    .current(page)
                    .pageSize(request.getPageSize())
                    .orgId(request.getOrgId())
                    .accountType(request.getAccountType())
                    .lastUpdateTime(request.getLastUpdateTime())
                    .acayearSem(request.getAcayearSem())
                    .acayearSemCode(request.getAcayearSemCode())
                    .faceType(request.getFaceType())
                    .delFlag(request.getDelFlag())
                    .build();
            return BaseNetUtil.scheduleResponse(commonInfoService.getTeacherList(pageReq));
        };
    }

    public Function<TeacherListRequest, List<TeacherData>> createGetTeacherListFunc() {
        return (request) -> BaseNetUtil.scheduleResponse(commonInfoService.getTeacherList(request));
    }

    public Function<Long, BasePageData<StudentData>> createGetStudentPagesAllFunc(StudentPageRequest request) {
        return (page) -> {
            StudentPageRequest pageReq = StudentPageRequest.builder()
                    .current(page)
                    .pageSize(request.getPageSize())
                    .orgId(request.getOrgId())
                    .accountType(request.getAccountType())
                    .lastUpdateTime(request.getLastUpdateTime())
                    .acayearSem(request.getAcayearSem())
                    .acayearSemCode(request.getAcayearSemCode())
                    .faceType(request.getFaceType())
                    .delFlag(request.getDelFlag())
                    .build();
            return BaseNetUtil.scheduleResponse(commonInfoService.getStudentPage(pageReq));
        };
    }

    public Function<StudentPageRequest, BasePageData<StudentData>> createGetStudentPageFunc() {
        return (request) -> BaseNetUtil.scheduleResponse(commonInfoService.getStudentPage(request));
    }

    public Function<DictRequest, List<DictData>> createGetDictListFunc() {
        return (request) -> BaseNetUtil.scheduleResponse(commonInfoService.getDictList(request));
    }

    public Function<DeviceActiveRequest, Boolean> createGetDeviceActiveFunc() {
        return (request) -> BaseNetUtil.scheduleResponse(commonInfoService.deviceActive(request));
    }
}
