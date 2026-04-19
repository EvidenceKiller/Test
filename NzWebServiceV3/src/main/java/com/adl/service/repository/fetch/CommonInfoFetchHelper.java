package com.adl.service.repository.fetch;

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
import com.adl.service.http.request.ClassInfoRequest;
import com.adl.service.http.request.ClassListRequest;
import com.adl.service.http.request.DeviceActiveRequest;
import com.adl.service.http.request.DictRequest;
import com.adl.service.http.request.EmptyRequest;
import com.adl.service.http.request.StudentPageRequest;
import com.adl.service.http.request.TeacherListRequest;
import com.adl.service.internal.RetrofitManager;
import com.adl.service.web.CommonInfoService;

import java.util.List;

import io.reactivex.rxjava3.functions.Function;

public final class CommonInfoFetchHelper {

    private final CommonInfoService commonInfoService;

    public CommonInfoFetchHelper() {
        this.commonInfoService = RetrofitManager.getInstance().create(CommonInfoService.class);
    }

    public Function<EmptyRequest, List<GradeTreeData>> createGetGradeTreeFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(commonInfoService.getGradeTree());
    }

    public Function<EmptyRequest, List<GradeChineseNameData>> createGetGradeChineseNameListFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(commonInfoService.getGradeChineseNameList());
    }

    public Function<ClassListRequest, List<ClassData>> createGetClassListFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(commonInfoService.getClassList(request));
    }

    public Function<ClassInfoRequest, ClassInfoData> createGetClassInfoFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(commonInfoService.getClassInfo(request));
    }

    public Function<EmptyRequest, LoginInfoData> createGetLoginInfoFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(commonInfoService.getLoginInfo());
    }

    public Function<EmptyRequest, List<AcayearData>> createGetAcayearListFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(commonInfoService.getAcayearList());
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
            return BaseFetchUtil.scheduleResponse(commonInfoService.getTeacherList(pageReq));
        };
    }

    public Function<TeacherListRequest, List<TeacherData>> createGetTeacherListFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(commonInfoService.getTeacherList(request));
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
            return BaseFetchUtil.scheduleResponse(commonInfoService.getStudentPage(pageReq));
        };
    }

    public Function<StudentPageRequest, BasePageData<StudentData>> createGetStudentPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(commonInfoService.getStudentPage(request));
    }

    public Function<DictRequest, List<DictData>> createGetDictListFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(commonInfoService.getDictList(request));
    }

    public Function<DeviceActiveRequest, Boolean> createGetDeviceActiveFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(commonInfoService.deviceActive(request));
    }
}
