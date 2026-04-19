package com.adl.service.repository.prepare;

import android.text.TextUtils;

import com.adl.service.db.DaoManagerProxy;
import com.adl.service.exception.NzCommonException;
import com.adl.service.http.request.CompetitionPageRequest;
import com.adl.service.http.request.SportMeetPageRequest;

import io.reactivex.rxjava3.functions.Function;

public final class BusinessPrepareHelper {

    public BusinessPrepareHelper() {
    }

    public Function<CompetitionPageRequest, Void> createPrepareCompetitionPageFunc() {
        return (request) -> {
            DaoManagerProxy.getInstance().getCompetitionDao().clearAll();
            return null;
        };
    }

    public Function<SportMeetPageRequest, Void> createPrepareSportMeetPageFunc() {
        return (request) -> {
            if (request == null) {
                throw new NzCommonException("request is null when getSportMeetPage");
            }
            if (TextUtils.isEmpty(request.getAppCode())) {
                DaoManagerProxy.getInstance().getSportMeetDao().clearAll();
                DaoManagerProxy.getInstance().getSportMeetGroupTeamDao().clearAll();
                DaoManagerProxy.getInstance().getSportMeetGroupDetailsDao().clearAll();
            } else {
                DaoManagerProxy.getInstance().getSportMeetDao().clearByAppCode(request.getAppCode());
                DaoManagerProxy.getInstance().getSportMeetGroupTeamDao().clearByAppCode(request.getAppCode());
                DaoManagerProxy.getInstance().getSportMeetGroupDetailsDao().clearByAppCode(request.getAppCode());
            }
            return null;
        };
    }
}
