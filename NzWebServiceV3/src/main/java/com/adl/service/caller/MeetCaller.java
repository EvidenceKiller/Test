package com.adl.service.caller;

import com.adl.service.AdlService;
import com.adl.service.common.BaseService;
import com.adl.service.common.CommonUtil;
import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestCallback;
import com.adl.service.common.RequestResult;
import com.adl.service.common.RequestWorker;
import com.adl.service.entity.MeetGroupDetailsEntity;
import com.adl.service.entity.MeetGroupTeamEntity;
import com.adl.service.entity.MeetSkuEntity;
import com.adl.service.entity.SportMeetingEntity;
import com.adl.service.web.BaseHttpService;
import com.adl.service.web.SportMeetingService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 运动会数据同步（含 Dao）。
 */
public class MeetCaller extends BaseService {

    private static MeetCaller _instance;

    private MeetCaller() {

    }

    public static MeetCaller instance() {
        if (_instance == null) {
            _instance = new MeetCaller();
        }
        return _instance;
    }

    public void syncMeetData(List<String> appCodes, String orgId, RequestCallback callback) {
        postTask(new RequestWorker(callback) {

            @Override
            public RequestResult doWorking() {
                try {
                    AdlService adlService = AdlService.getService();
                    int requestIndex;
                    boolean isRequest;

                    long serverTime = BaseHttpService.currentSystemTime();
                    if (serverTime <= 0) {
                        return RequestResult.okContent("同步完成");
                    }
                    d("服务器时间: " + InnerUtil.formatByTimeCode(serverTime));

                    adlService.getMeetDao().clearAll();
                    adlService.getMeetGroupTeamDao().clearAll();
                    adlService.getMeetGroupDetailsDao().clearAll();

                    List<String> status = Arrays.asList("1", "2");
                    ArrayList<SportMeetingEntity> meetList = new ArrayList<>();
                    for (String appCode : appCodes) {
                        requestIndex = 1;
                        isRequest = true;
                        int statusIndex = 0;

                        while (isRequest) {
                            List<SportMeetingEntity> meetTempList = SportMeetingService.getSportMeetList(
                                    appCode, orgId, status.get(statusIndex), true, requestIndex
                            );
                            if (CommonUtil.isNotEmptyList(meetTempList)) {
                                Iterator<SportMeetingEntity> iterator = meetTempList.iterator();
                                while (iterator.hasNext()) {
                                    SportMeetingEntity entity = iterator.next();
                                    entity.setManualData(appCode);

                                    if (entity.isDelFlag()) {
                                        iterator.remove();
                                    } else {
                                        List<MeetSkuEntity> skuList = entity.getSkuList();
                                        if (CommonUtil.isNotEmptyList(skuList)) {
                                            List<MeetSkuEntity> filterList = skuList.stream()
                                                    .filter(f -> f.getCompetitionType() == 2 && (CommonUtil.isNotEmptyList(f.getAppCodes()) && f.getAppCodes().contains(appCode)))
                                                    .collect(Collectors.toList());
                                            entity.setSkuList(filterList);
                                        }
                                    }
                                }

                                meetList.addAll(meetTempList);
                                requestIndex++;
                            } else {
                                if (statusIndex < status.size() - 1) {
                                    statusIndex++;
                                    requestIndex = 1;
                                } else {
                                    isRequest = false;
                                }
                            }
                        }
                    }

                    for (SportMeetingEntity entity : meetList) {
                        List<MeetGroupTeamEntity> oldGroupTeams = entity.getGroupTeams();
                        if (CommonUtil.isEmptyList(oldGroupTeams)) continue;

                        List<MeetGroupTeamEntity> groupTeams = CommonUtil.deepCopy(oldGroupTeams).stream().filter(f -> f.getCompetitionType() == 2).collect(Collectors.toList());
                        oldGroupTeams.clear();

                        for (MeetGroupTeamEntity groupTeam : groupTeams) {
                            groupTeam.setManualData(entity.getAppCode(), entity.getId());

                            List<MeetGroupDetailsEntity> oldDetails = groupTeam.getDetails();
                            if (CommonUtil.isEmptyList(oldDetails)) continue;

                            List<MeetGroupDetailsEntity> details = CommonUtil.deepCopy(oldDetails);
                            oldDetails.clear();

                            for (MeetGroupDetailsEntity detail : details) {
                                detail.setManualData(entity.getAppCode(), entity.getId(), groupTeam.getMeetSportSkuId(), groupTeam.getCompetitionRound());
                            }

                            adlService.getMeetGroupDetailsDao().insertAll(details);
                        }

                        adlService.getMeetGroupTeamDao().insertAll(groupTeams);
                    }

                    adlService.getMeetDao().insertAll(meetList);

                    return RequestResult.okContent("同步完成");
                } catch (Exception e) {
                    return parserException(e);
                }
            }
        });
    }
}
