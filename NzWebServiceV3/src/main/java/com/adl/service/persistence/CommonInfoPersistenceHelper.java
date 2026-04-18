package com.adl.service.persistence;

import android.text.TextUtils;

import com.adl.service.common.FileDownManager;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.db.entity.DictEntity;
import com.adl.service.db.entity.LoginInfoEntity;
import com.adl.service.db.entity.StudentEntity;
import com.adl.service.db.entity.TeacherEntity;
import com.adl.service.exception.NzEmptyDataException;
import com.adl.service.http.request.StudentPageRequest;
import com.adl.service.http.request.TeacherListRequest;
import com.adl.service.data.BasePageData;
import com.adl.service.data.BaseResponse;
import com.adl.service.data.DictData;
import com.adl.service.data.LoginInfoData;
import com.adl.service.data.StudentData;
import com.adl.service.data.TeacherData;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

public final class CommonInfoPersistenceHelper {

    public static Function<Void, Completable> createDictListPreparer(String dictCode) {
        return (v) -> {
            if (TextUtils.isEmpty(dictCode)) {
                DaoManagerProxy.getInstance().getDictDao().clearAll();
            } else {
                DaoManagerProxy.getInstance().getDictDao().clearByDictCode(dictCode);
            }
            return Completable.complete();
        };
    }

    public static Function<List<DictData>, Completable> createDictListPersister() {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                return Completable.error(new NzEmptyDataException("dict data is empty"));
            }
            List<DictEntity> entityList = dataList.stream()
                    .map(data -> DictEntity.convertToEntity(data))
                    .collect(Collectors.toList());
            DaoManagerProxy.getInstance().getDictDao().insertAll(entityList);
            return Completable.complete();
        };
    }

    public static Function<List<DictData>, Completable> createGetDictListFunc() {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                return Completable.error(new NzEmptyDataException("dict data is empty"));
            }
            List<DictEntity> entityList = dataList.stream()
                    .map(data -> DictEntity.convertToEntity(data))
                    .collect(Collectors.toList());
            DaoManagerProxy.getInstance().getDictDao().insertAll(entityList);
            return Completable.complete();
        };
    }

    public static Function<LoginInfoData, Completable> createLoginPersister() {
        return (data) -> {
            if (data == null) {
                return Completable.error(new NzEmptyDataException("login info data is empty"));
            }
            DaoManagerProxy.getInstance().getLoginInfoDao().saveLoginInfoWithClear(LoginInfoEntity.convertToEntity(data));
            return Completable.complete();
        };
    }

    public static Function<Void, Completable> createStudentPagePreparer() {
        return (v) -> {
            DaoManagerProxy.getInstance().getStudentDao().clearAll();
            return Completable.complete();
        };
    }

    public static Function<Long, Single<BaseResponse<BasePageData<StudentData>>>> createStudentPageRequester(
            StudentPageRequest request, Function<StudentPageRequest, Single<BaseResponse<BasePageData<StudentData>>>> function) {
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
            return function.apply(pageReq);
        };
    }

    public static Function<List<StudentData>, List<StudentData>> createStudentPagePersister() {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                return dataList;
            }
            Map<Boolean, List<StudentEntity>> partitioned = dataList.stream()
                    .map(data -> StudentEntity.convertToEntity(data))
                    .collect(Collectors.partitioningBy(data -> "0".equals(data.getDelFlag())));
            DaoManagerProxy.getInstance().getStudentDao().insertAll(partitioned.get(true));
            DaoManagerProxy.getInstance().getStudentDao().clear(partitioned.get(false));
            FileDownManager.instance().clearByUrls(partitioned.get(false).stream().map(StudentEntity::getUserFaceImgUrl).collect(Collectors.toList()));
            FileDownManager.instance().clearByUrls(partitioned.get(false).stream().map(StudentEntity::getThumbnailUserFaceImgUrl).collect(Collectors.toList()));
            return dataList;
        };
    }

    public static Function<Void, Completable> createTeacherListPreparer() {
        return (v) -> {
            DaoManagerProxy.getInstance().getTeacherDao().clearAll();
            return Completable.complete();
        };
    }

    public static Function<Long, Single<BaseResponse<List<TeacherData>>>> createTeacherListRequester(
            TeacherListRequest request, Function<TeacherListRequest, Single<BaseResponse<List<TeacherData>>>> function) {
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
            return function.apply(pageReq);
        };
    }

    public static Function<List<TeacherData>, List<TeacherData>> createTeacherListPersister() {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                return dataList;
            }
            Map<Boolean, List<TeacherEntity>> partitioned = dataList.stream()
                    .map(data -> TeacherEntity.convertToEntity(data))
                    .collect(Collectors.partitioningBy(data -> !data.getDelFlag()));
            DaoManagerProxy.getInstance().getTeacherDao().insertAll(partitioned.get(true));
            DaoManagerProxy.getInstance().getTeacherDao().clear(partitioned.get(false));
            return dataList;
        };
    }
}
