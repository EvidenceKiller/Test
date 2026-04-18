package com.adl.service.persistence;

import android.text.TextUtils;

import com.adl.service.utils.CommonUtil;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.db.entity.CompetitionEntity;
import com.adl.service.db.entity.SportMeetEntity;
import com.adl.service.db.entity.SportMeetGroupDetailEntity;
import com.adl.service.db.entity.SportMeetGroupTeamEntity;
import com.adl.service.http.request.CompetitionPageRequest;
import com.adl.service.http.request.SportMeetPageRequest;
import com.adl.service.data.BasePageData;
import com.adl.service.data.BaseResponse;
import com.adl.service.data.CompetitionData;
import com.adl.service.data.SportMeetData;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

public final class BusinessPersistenceHelper {

    private BusinessPersistenceHelper() {
    }

    public static Function<Void, Completable> createSportMeetPagePreparer(String appCode) {
        return (v) -> {
            if (TextUtils.isEmpty(appCode)) {
                DaoManagerProxy.getInstance().getSportMeetDao().clearAll();
                DaoManagerProxy.getInstance().getSportMeetGroupTeamDao().clearAll();
                DaoManagerProxy.getInstance().getSportMeetGroupDetailsDao().clearAll();
            } else {
                DaoManagerProxy.getInstance().getSportMeetDao().clearByAppCode(appCode);
                DaoManagerProxy.getInstance().getSportMeetGroupTeamDao().clearByAppCode(appCode);
                DaoManagerProxy.getInstance().getSportMeetGroupDetailsDao().clearByAppCode(appCode);
            }
            return Completable.complete();
        };
    }

    /**
     * 为 Plan 分页请求构建通用的 PageRequester
     */
    public static Function<Long, Single<BaseResponse<BasePageData<SportMeetData>>>> createSportMeetPageRequester(
            SportMeetPageRequest request, Function<SportMeetPageRequest, Single<BaseResponse<BasePageData<SportMeetData>>>> function) {
        return (page) -> {
            SportMeetPageRequest pageReq = SportMeetPageRequest.builder()
                    .current(page)
                    .pageSize(request.getPageSize())
                    .appCode(request.getAppCode())
                    .classIds(request.getClassIds())
                    .orgId(request.getOrgId())
                    .state(request.getState())
                    .keyword(request.getKeyword())
                    .id(request.getId())
                    .showGroup(request.getShowGroup())
                    .checkTime(request.getCheckTime())
                    .orderByField(request.getOrderByField())
                    .lastUpdateTime(request.getLastUpdateTime())
                    .build();
            return function.apply(pageReq);
        };
    }

    /**
     * 为 Plan 数据构建通用的 Persister
     */
    public static Function<List<SportMeetData>, List<SportMeetData>> createSportMeetPagePersister(String appCode) {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                return dataList;
            }
            List<SportMeetEntity> persistList = new LinkedList<>();
            Iterator<SportMeetData> iterator = dataList.iterator();
            while (iterator.hasNext()) {
                SportMeetData data = iterator.next();

                //  逻辑删除标识位 0 删除 1 可用
                if (data.getDelFlag()) {
                    iterator.remove();
                } else {
                    data.fliterSkuList(f ->
                            // competitionType:比赛方式 1集体 2个人
                            f.getCompetitionType() == 2 && (CommonUtil.isNotEmptyList(f.getAppCodes()) && f.getAppCodes().contains(appCode)));
                    SportMeetEntity entity = SportMeetEntity.convertToEntity(data, appCode);
                    persistList.add(entity);
                }
            }

            for (SportMeetEntity persistItem : persistList) {
                //  获取分组编排
                List<SportMeetGroupTeamEntity> oldGroupTeams = persistItem.getGroupTeams();
                if (CommonUtil.isEmptyList(oldGroupTeams)) {
                    continue;
                }

                // competitionType:比赛方式 1集体 2个人
                List<SportMeetGroupTeamEntity> groupTeams = CommonUtil.deepCopy(oldGroupTeams).stream().filter(f -> f.getCompetitionType() == 2).collect(Collectors.toList());
                oldGroupTeams.clear();

                for (SportMeetGroupTeamEntity groupTeam : groupTeams) {
                    groupTeam.setAppCode(persistItem.getAppCode());
                    groupTeam.setMeetId(persistItem.getId());

                    //  获取人员信息
                    List<SportMeetGroupDetailEntity> oldDetails = groupTeam.getDetails();
                    if (CommonUtil.isEmptyList(oldDetails)) {
                        continue;
                    }

                    List<SportMeetGroupDetailEntity> details = CommonUtil.deepCopy(oldDetails).stream().map(detail -> {
                        detail.setAppCode(persistItem.getAppCode());
                        detail.setMeetId(persistItem.getId());
                        detail.setMeetSportSkuId(groupTeam.getMeetSportSkuId());
                        detail.setCompetitionRound(groupTeam.getCompetitionRound());
                        return detail;
                    }).collect(Collectors.toList());
                    oldDetails.clear();

                    DaoManagerProxy.getInstance().getSportMeetGroupDetailsDao().insertAll(details);
                }

                DaoManagerProxy.getInstance().getSportMeetGroupTeamDao().insertAll(groupTeams);
            }

            DaoManagerProxy.getInstance().getSportMeetDao().insertAll(persistList);
            return dataList;
        };
    }

    public static Function<Void, Completable> createCompetitionPagePreparer() {
        return (v) -> {
            DaoManagerProxy.getInstance().getCompetitionDao().clearAll();
            return Completable.complete();
        };
    }

    public static Function<Long, Single<BaseResponse<BasePageData<CompetitionData>>>> createCompetitionPageRequester(
            CompetitionPageRequest request, Function<CompetitionPageRequest, Single<BaseResponse<BasePageData<CompetitionData>>>> function) {
        return (page) -> {
            CompetitionPageRequest pageReq = CompetitionPageRequest.builder()
                    .current(page)
                    .pageSize(request.getPageSize())
                    .onlineType(request.getOnlineType())
                    .orgId(request.getOrgId())
                    .competitionLevel(request.getCompetitionLevel())
                    .appCode(request.getAppCode())
                    .startTime(request.getStartTime())
                    .endTime(request.getEndTime())
                    .competitionEnabled(request.getCompetitionEnabled())
                    .keyword(request.getKeyword())
                    .acayearSemCode(request.getAcayearSemCode())
                    .orgType(request.getOrgType())
                    .terminalFlag(request.getTerminalFlag())
                    .accountId(request.getAccountId())
                    .sortField(request.getSortField())
                    .build();
            return function.apply(pageReq);
        };
    }

    public static Function<List<CompetitionData>, List<CompetitionData>> createCompetitionPagePersister() {
        return (dataList) -> {
            if (dataList == null || dataList.isEmpty()) {
                return dataList;
            }
            List<CompetitionEntity> entityList = dataList.stream()
                    .map(data -> CompetitionEntity.convertToEntity(data))
                    .collect(Collectors.toList());
            DaoManagerProxy.getInstance().getCompetitionDao().insertCompetitionList(entityList);
            return dataList;
        };
    }
}
