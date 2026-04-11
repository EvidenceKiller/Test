package com.adl.service.web;

import android.text.TextUtils;

import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.SportCompetitionEntity;
import com.adl.service.entity.SportCompetitionInfoEntity;
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
 * Describe   : 赛事
 * 1. 赛事列表
 * 2. 竞技挑战赛事详情
 * 3. 加入赛事
 */
public class SportCompetitionService extends BaseHttpService {

    ////////// 同步查询赛事列表
    public static List<SportCompetitionEntity> getCompetitionList(String appCode, String onlineType, String competitionEnabled, int current) {
        try {
            d("From Http 查询赛事列表");

            Map<String, Object> map = new HashMap<>();
            map.put("appCode", appCode);                            //  应用编码
            if (!TextUtils.isEmpty(onlineType)) {
                map.put("onlineType", onlineType);                  //  赛事类型 1-竞速赛 2-闯关赛
            }
            if (!TextUtils.isEmpty(competitionEnabled)) {
                map.put("competitionEnabled", competitionEnabled);  //  赛事状态 1-未开始 2-进行中 3-已结束
            }
            map.put("current", current);// 当前页
            map.put("pageSize", 500);
            String param = InnerUtil.toJson(map);

            // 请求
            String url;
            if (Platform.GX.equals(getConfigService().getPlatform())) {
                url = wrapperUrl("/busi/terminal/gx/competition/v1/getCompetitionList");
            } else {
                url = wrapperUrl("/busi/terminal/k12/competition/v1/getCompetitionList");
            }

            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<SportCompetitionEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 同步查询竞技挑战赛事详情
    public static SportCompetitionInfoEntity getCompetitionRankDetail(String id, String accountId) {
        try {
            d("From Http 查询竞技挑战赛事详情");

            Map<String, Object> map = new HashMap<>();
            if (!TextUtils.isEmpty(id)) {
                map.put("id", id);                  //  赛事Id
            }

            if (!TextUtils.isEmpty(accountId)) {
                map.put("accountId", accountId);    //  accountId
            }
            String param = InnerUtil.toJson(map);

            // 请求
            String url;
            if (Platform.GX.equals(getConfigService().getPlatform())) {
                url = wrapperUrl("/busi/terminal/gx/competition/v1/getCompetitionRankDetail");
            } else {
                url = wrapperUrl("/busi/terminal/k12/competition/v1/getCompetitionRankDetail");
            }

            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToObject(result.getContent().toString(), SportCompetitionInfoEntity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    ////////// 加入赛事
    public static RequestResult joinCompetitionRank(String id, String accountId, String classId) {
        try {
            d("From Http 加入赛事");

            Map<String, Object> map = new HashMap<>();
            map.put("id", id);                  //  赛事Id
            map.put("accountId", accountId);    //  人员ID
            map.put("classId", classId);        //  人员对应班级ID

            String param = InnerUtil.toJson(map);

            // 请求
            String url;
            if (Platform.GX.equals(getConfigService().getPlatform())) {
                url = wrapperUrl("/busi/terminal/gx/competition/v1/joinCompetitionRank");
            } else {
                url = wrapperUrl("/busi/terminal/k12/competition/v1/joinCompetitionRank");
            }

            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            return result;
        } catch (Exception e) {
            return RequestResult.error(e.toString());
        }
    }
}
