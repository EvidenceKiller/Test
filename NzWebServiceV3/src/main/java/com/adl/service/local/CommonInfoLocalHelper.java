package com.adl.service.local;

import android.text.TextUtils;

import com.adl.service.data.DictData;
import com.adl.service.data.LoginInfoData;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.db.entity.DictEntity;
import com.adl.service.db.entity.LoginInfoEntity;
import com.adl.service.exception.NzCommonException;
import com.adl.service.http.request.DictRequest;
import com.adl.service.http.request.StudentPageRequest;
import com.adl.service.http.request.TeacherListRequest;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.functions.Function;

public final class CommonInfoLocalHelper {

    public CommonInfoLocalHelper() {
    }

    public Function<Void, LoginInfoData> createLoginInfoFunc() {
        return (v) ->
                LoginInfoEntity.convertToData(DaoManagerProxy.getInstance().getLoginInfoDao().getLoginInfo());
    }

    public Function<TeacherListRequest, Void> createTeacherListsAllFunc() {
        return (request) -> {
            if (request == null) {
                throw new NzCommonException("request is null when getStudentPagesAll");
            }
            if (request.getForceUpdate()) {
                // TODO 强制更新
                return null;
            }
            DaoManagerProxy.getInstance().getTeacherDao().clearAll();
            return null;
        };
    }

    public Function<StudentPageRequest, Void> createStudentPagesAllFunc() {
        return (request) -> {
            if (request == null) {
                throw new NzCommonException("request is null when getStudentPagesAll");
            }
            if (request.getForceUpdate()) {
                // TODO 强制更新
                return null;
            }
            DaoManagerProxy.getInstance().getStudentDao().clearAll();
            return null;
        };
    }


    public Function<DictRequest, List<DictData>> createDictListFunc() {
        return (request) -> {
            if (request == null) {
                throw new NzCommonException("request is null when getDictList");
            }
            if (request.getForceUpdate()) {
                // TODO 强制更新
                return null;
            }
            if (TextUtils.isEmpty(request.getDictId())) {
                // 服务器逻辑dictId为空，返回空列表，缓存逻辑与服务器保持一致
                // TODO 这里返回空列表会触发网络请求，这里需要重新考虑
                return null;
            }
            List<DictData> data = (List<DictData>) MemoryCacheManager.getInstance().get(request);
            if (data != null) {
                return data;
            }
            List<DictEntity> entities = DaoManagerProxy.getInstance().getDictDao().queryDictByDictCode(request.getDictId());
            if (entities == null || entities.isEmpty()) {
                return null;
            }
            if (entities.get(0).getRecordTime() + DictEntity.EXPIRED_TIME < System.currentTimeMillis()) {
                return null;
            }

            return entities.stream()
                    .map(DictEntity::convertToData)
                    .collect(Collectors.toList());
        };
    }
}
