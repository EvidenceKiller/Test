package com.adl.service.repository.persist;

import com.adl.service.repository.prepare.CacheTag;
import com.adl.service.repository.prepare.MemoryCacheManager;
import com.adl.service.common.FileDownManager;
import com.adl.service.data.BasePageData;
import com.adl.service.data.DictData;
import com.adl.service.data.LoginInfoData;
import com.adl.service.data.StudentData;
import com.adl.service.data.TeacherData;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.db.entity.DictEntity;
import com.adl.service.db.entity.LoginInfoEntity;
import com.adl.service.db.entity.StudentEntity;
import com.adl.service.db.entity.TeacherEntity;
import com.adl.service.exception.NzCommonException;
import com.adl.service.http.request.DictRequest;
import com.adl.service.utils.InnerUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.functions.Function;

public final class CommonInfoPersistHelper {

    public CommonInfoPersistHelper() {
    }

    public static Function<LoginInfoData, LoginInfoData> createPersistLoginInfoFunc() {
        return (data) -> {
            if (data == null) {
                throw new NzCommonException("login info data is empty");
            }
            DaoManagerProxy.getInstance().getLoginInfoDao().saveLoginInfoWithClear(LoginInfoEntity.convertToEntity(data));
            return data;
        };
    }

    public static Function<List<TeacherData>, List<TeacherData>> createPersistTeacherList() {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                throw new NzCommonException("teacher page data is empty");
            }
            Map<Boolean, List<TeacherEntity>> partitioned = dataList.stream()
                    .map(data -> TeacherEntity.convertToEntity(data))
                    .collect(Collectors.partitioningBy(data -> !data.getDelFlag()));
            DaoManagerProxy.getInstance().getTeacherDao().insertAll(partitioned.get(true));
            DaoManagerProxy.getInstance().getTeacherDao().clear(partitioned.get(false));
            return dataList;
        };
    }

    public Function<BasePageData<StudentData>, BasePageData<StudentData>> createPersistStudentPageFunc() {
        return (pageData) -> {
            if (pageData == null || pageData.getRecords() == null || pageData.getRecords().isEmpty()) {
                throw new NzCommonException("student page data is empty");
            }
            Map<Boolean, List<StudentEntity>> partitioned = pageData.getRecords().stream()
                    .map(data -> StudentEntity.convertToEntity(data))
                    .collect(Collectors.partitioningBy(data -> "0".equals(data.getDelFlag())));
            DaoManagerProxy.getInstance().getStudentDao().insertAll(partitioned.get(true));
            DaoManagerProxy.getInstance().getStudentDao().clear(partitioned.get(false));
            FileDownManager.instance().clearByUrls(partitioned.get(false).stream().map(StudentEntity::getUserFaceImgUrl).collect(Collectors.toList()));
            FileDownManager.instance().clearByUrls(partitioned.get(false).stream().map(StudentEntity::getThumbnailUserFaceImgUrl).collect(Collectors.toList()));
            return pageData;
        };
    }

    public Function<List<DictData>, List<DictData>> createPersistDictListFunc(DictRequest request) {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                throw new NzCommonException("dict data is empty");
            }
            MemoryCacheManager.getInstance().put(CacheTag.CACHE_TAG_DICT_LIST + InnerUtil.toJson(request), dataList, DictEntity.EXPIRED_TIME);
            DaoManagerProxy.getInstance().getDictDao().clearByDictCode(dataList.get(0).getDictCode());
            List<DictEntity> entityList = dataList.stream()
                    .map(data -> DictEntity.convertToEntity(data))
                    .collect(Collectors.toList());
            DaoManagerProxy.getInstance().getDictDao().insertAll(entityList);
            return dataList;
        };
    }

}
