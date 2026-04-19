package com.adl.service.repository.persist;

import com.adl.service.data.BasePageData;
import com.adl.service.data.CompetitionData;
import com.adl.service.data.SportMeetData;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.db.entity.CompetitionEntity;
import com.adl.service.db.entity.SportMeetEntity;
import com.adl.service.db.entity.SportMeetGroupDetailEntity;
import com.adl.service.db.entity.SportMeetGroupTeamEntity;
import com.adl.service.exception.NzCommonException;
import com.adl.service.http.request.SportMeetPageRequest;
import com.adl.service.utils.CommonUtil;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.functions.Function;

public final class BusinessPersistHelper {

    public BusinessPersistHelper() {
    }

    public Function<BasePageData<CompetitionData>, BasePageData<CompetitionData>> createPersistCompetitionPageFunc() {
        return (pageData) -> {
            if (pageData == null || pageData.getRecords() == null || pageData.getRecords().isEmpty()) {
                throw new NzCommonException("competition page data is empty");
            }
            List<CompetitionEntity> entityList = pageData.getRecords().stream()
                    .map(data -> CompetitionEntity.convertToEntity(data))
                    .collect(Collectors.toList());
            DaoManagerProxy.getInstance().getCompetitionDao().insertCompetitionList(entityList);
            return pageData;
        };
    }

    public Function<BasePageData<SportMeetData>, BasePageData<SportMeetData>> createPersistSportMeetPageFunc(SportMeetPageRequest request) {
        return (pageData) -> {
            if (pageData == null || pageData.getRecords() == null || pageData.getRecords().isEmpty()) {
                throw new NzCommonException("sport meet page data is empty");
            }
            List<SportMeetData> dataList = pageData.getRecords();
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
                            f.getCompetitionType() == 2 && (CommonUtil.isNotEmptyList(f.getAppCodes()) && f.getAppCodes().contains(request.getAppCode())));
                    SportMeetEntity entity = SportMeetEntity.convertToEntity(data, request.getAppCode());
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
            return pageData;
        };
    }
}
