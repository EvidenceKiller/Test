package com.adl.service.outer;

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
import com.adl.service.web.SportMeetingService;
import com.adl.service.web.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/5
 * Describe   : 运动会
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

    //  同步运动会列表【运动列表、编排列表、编排人员一个接口返回，无法获取测试人员的运动结果sportResult】
    public void syncMeetData(List<String> appCodes, String orgId, RequestCallback callback) {
        postTask(new RequestWorker(callback) {

            @Override
            public RequestResult doWorking() {
                try {
                    AdlService adlService = AdlService.getService();
                    int requestIndex;
                    boolean isRequest;

                    // 服务器系统时间
                    long serverTime = UserService.currentSystemTime();
                    if (serverTime <= 0) {
                        return RequestResult.okContent("同步完成");
                    }
                    d("服务器时间: " + InnerUtil.formatByTimeCode(serverTime));

                    adlService.getMeetDao().clearAll();
                    adlService.getMeetGroupTeamDao().clearAll();
                    adlService.getMeetGroupDetailsDao().clearAll();

                    // 1未开始 2进行中 3已结束
                    List<String> status = Arrays.asList("1", "2");
                    ////////////// 1.同步运动会列表 ////////////
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
                                //  逻辑删除标识位 0 删除 1 可用
                                Iterator<SportMeetingEntity> iterator = meetTempList.iterator();
                                while (iterator.hasNext()) {
                                    SportMeetingEntity entity = iterator.next();
                                    entity.setManualData(appCode);

                                    if (entity.isDelFlag()) {
                                        iterator.remove();
                                    } else {
                                        List<MeetSkuEntity> skuList = entity.getSkuList();
                                        if (CommonUtil.isNotEmptyList(skuList)) {
                                            // competitionType:比赛方式 1集体 2个人
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

                    ////////////// 2.同步运动会编排 ////////////
                    for (SportMeetingEntity entity : meetList) {
                        //  获取分组编排
                        List<MeetGroupTeamEntity> oldGroupTeams = entity.getGroupTeams();
                        if (CommonUtil.isEmptyList(oldGroupTeams)) continue;

                        // competitionType:比赛方式 1集体 2个人
                        List<MeetGroupTeamEntity> groupTeams = CommonUtil.deepCopy(oldGroupTeams).stream().filter(f -> f.getCompetitionType() == 2).collect(Collectors.toList());
                        oldGroupTeams.clear();

                        for (MeetGroupTeamEntity groupTeam : groupTeams) {
                            groupTeam.setManualData(entity.getAppCode(), entity.getId());

                            //  获取人员信息
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
