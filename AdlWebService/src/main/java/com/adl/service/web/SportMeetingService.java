package com.adl.service.web;

import android.text.TextUtils;

import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.MeetGroupDetailsEntity;
import com.adl.service.entity.MeetGroupTeamEntity;
import com.adl.service.entity.SportMeetingEntity;
import com.adl.service.web.constants.Platform;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 运动会
 * 1. 运动会列表
 * 2. 运动会项目编排列表
 */
public class SportMeetingService extends BaseHttpService {

    public static List<SportMeetingEntity> getSportMeetList(String appCode, String orgId, String state, boolean showGroup, int current){
        return getSportMeetList(appCode, orgId, state, showGroup, current, 5);
    }
    ////////// 同步查询运动会列表
    public static List<SportMeetingEntity> getSportMeetList(String appCode, String orgId, String state, boolean showGroup, int current, int pageSize) {
        try {
            d("From Http 查询运动会列表");

            Map<String, Object> map = new HashMap<>();
            map.put("appCode", appCode);
            map.put("orgId", orgId);
            if (!TextUtils.isEmpty(state)) {
                map.put("state", state);    //  1未开始 2进行中 3已结束 空全部
            }
            map.put("showGroup", showGroup);
            map.put("current", current);
            map.put("pageSize", pageSize);

            String param = InnerUtil.toJson(map);

            // 请求
            String url;
            if (Platform.GX.equals(getConfigService().getPlatform())) {
                url = wrapperUrl("/busi/terminal/gx/sportMeet/v1/getSportMeetList");
            } else {
                url = wrapperUrl("/busi/terminal/k12/sportMeet/v1/getSportMeetList");
            }

            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<SportMeetingEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 同步查询运动会项目编排列表
    public static List<MeetGroupTeamEntity> downloadGroupTeam(
            String meetId,                  // 运动会id不能为空
            String meetSportSkuId,          // 运动会项目id
            String competitionRound,        // 比赛轮次
            boolean showDetail              // 是否显示详情,false不显示 true显示 默认不显示
    ) {
        try {
            d("From Http 查询运动会项目编排列表");

            Map<String, Object> map = new HashMap<>();
            map.put("meetId", meetId);
            if (!TextUtils.isEmpty(meetSportSkuId)) {
                map.put("meetSportSkuId", meetSportSkuId);
            }
            if (!TextUtils.isEmpty(competitionRound)) {
                map.put("competitionRound", competitionRound);
            }
            map.put("showDetail", showDetail);

            String param = InnerUtil.toJson(map);

            // 请求
            String url;
            if (Platform.GX.equals(getConfigService().getPlatform())) {
                url = wrapperUrl("/busi/terminal/gx/sportMeet/group/downloadGroupTeam");
            } else {
                url = wrapperUrl("/busi/terminal/k12/sportMeet/group/downloadGroupTeam");
            }

            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<MeetGroupTeamEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 同步查询运动会项目编排列表详情
    public static List<MeetGroupDetailsEntity> downloadGroupTeamDetail(
            String meetId,                  // 运动会id不能为空
            String meetSportSkuId,          // 运动会项目id
            String competitionType,         // 比赛方式 1集体 2个人
            String competitionRound,        // 比赛轮次
            String teamUnitCode             // 分组单位
    ) {
        try {
            d("From Http 查询运动会项目编排列表详情");

            Map<String, Object> map = new HashMap<>();
            map.put("meetId", meetId);
            if (!TextUtils.isEmpty(meetSportSkuId)) {
                map.put("meetSportSkuId", meetSportSkuId);
            }
            if (!TextUtils.isEmpty(competitionType)) {
                map.put("competitionType", competitionType);
            }
            if (!TextUtils.isEmpty(competitionRound)) {
                map.put("competitionRound", competitionRound);
            }
            map.put("teamUnitCode", teamUnitCode);

            String param = InnerUtil.toJson(map);

            // 请求
            String url;
            if (Platform.GX.equals(getConfigService().getPlatform())) {
                url = wrapperUrl("/busi/terminal/gx/sportMeet/group/downloadGroupTeamDetail");
            } else {
                url = wrapperUrl("/busi/terminal/k12/sportMeet/group/downloadGroupTeamDetail");
            }

            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<MeetGroupDetailsEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
