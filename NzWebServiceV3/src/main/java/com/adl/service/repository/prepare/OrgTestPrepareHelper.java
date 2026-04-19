package com.adl.service.repository.prepare;

import com.adl.service.db.DaoManagerProxy;
import com.adl.service.http.request.PlanStudentPageRequest;

import io.reactivex.rxjava3.functions.Function;

public final class OrgTestPrepareHelper {

    public OrgTestPrepareHelper() {
    }

    public Function<Void, Void> createPrepareStandardConfigDataFunc() {
        return (v) -> {
            DaoManagerProxy.getInstance().getStandardConfigDao().clearAll();
            return null;
        };
    }

    public Function<Void, Void> createClearPlanStudentPagesAllFunc() {
        return (v) -> {
            DaoManagerProxy.getInstance().getPlanStudentDao().clearAll();
            return null;
        };
    }


    public Function<PlanStudentPageRequest, Void> createPreparePlanStudentPagesAllFunc() {
        return (request) -> {
            DaoManagerProxy.getInstance().getPlanStudentDao().clearByPlanId(request.getPlanId());
            return null;
        };
    }

    public Function<Void, Void> createClearPlanPagesAllFunc() {
        return (v) -> {
            DaoManagerProxy.getInstance().getPlanStudentDao().clearAll();
            return null;
        };
    }
}
