package com.adl.service.repository.persist;

import com.adl.service.data.BasePageData;
import com.adl.service.data.PlanData;
import com.adl.service.data.PlanStudentData;
import com.adl.service.data.StandardConfigData;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.db.entity.PlanEntity;
import com.adl.service.db.entity.PlanStudentEntity;
import com.adl.service.db.entity.StandardConfigEntity;
import com.adl.service.exception.NzCommonException;
import com.adl.service.http.request.PlanPageRequest;
import com.adl.service.http.request.PlanStudentPageRequest;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.functions.Function;

public final class OrgTestPersistHelper {

    public OrgTestPersistHelper() {
    }

    public Function<BasePageData<PlanStudentData>, BasePageData<PlanStudentData>> createPersistPlanStudentPageFunc(PlanStudentPageRequest request) {
        return (pageData) -> {
            if (pageData == null || pageData.getRecords() == null || pageData.getRecords().isEmpty()) {
                throw new NzCommonException("plan student page data is empty");
            }
            List<PlanStudentEntity> entityList = pageData.getRecords().stream()
                    .map(data -> PlanStudentEntity.convertToEntity(data))
                    .collect(Collectors.toList());
            entityList.forEach(plan -> plan.setPlanId(request.getPlanId()));
            DaoManagerProxy.getInstance().getPlanStudentDao().insertAll(entityList);
            return pageData;
        };
    }

    public Function<BasePageData<PlanData>, BasePageData<PlanData>> createPersistPlanPageFunc(PlanPageRequest request) {
        return (pageData) -> {
            if (pageData == null || pageData.getRecords() == null || pageData.getRecords().isEmpty()) {
                throw new NzCommonException("plan page data is empty");
            }
            // 将 PlanData 转换为 PlanEntity
            List<PlanEntity> entityList = pageData.getRecords().stream()
                    .map(data -> PlanEntity.convertToEntity(data))
                    .collect(Collectors.toList());

            DaoManagerProxy.getInstance().getPlanDao().insertPlanList(entityList);
            return pageData;
        };
    }

    public Function<List<StandardConfigData>, List<StandardConfigData>> createPersistStandardConfigPageFunc() {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                return dataList;
            }
            List<StandardConfigEntity> entityList = dataList.stream()
                    .map(data -> StandardConfigEntity.convertToEntity(data))
                    .collect(Collectors.toList());
            DaoManagerProxy.getInstance().getStandardConfigDao().insertAll(entityList);
            return dataList;
        };
    }
}
