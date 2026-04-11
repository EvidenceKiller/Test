package com.adl.service.web;

import android.text.TextUtils;

import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.PersonRankEntity;
import com.adl.service.entity.SportOverviewEntity;
import com.adl.service.entity.SumScoreRankEntity;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

public class SportRankService extends BaseHttpService {

    public static PersonRankEntity getPersonRank(String accountId, String sportSkuId, String sportSkuSubType) {
        try {
            d("From Http 获取个人运动排名");

            Map<String, Object> map = new HashMap<>();

            if (!TextUtils.isEmpty(accountId)) {
                map.put("accountId", accountId);    //  accountId
            }
            if (!TextUtils.isEmpty(sportSkuId)) {
                map.put("sportSkuId", sportSkuId);
            }
            if (!TextUtils.isEmpty(sportSkuSubType)) {
                map.put("sportSkuSubType", sportSkuSubType);
            }
            String param = InnerUtil.toJson(map);

            // 请求/terminal/statistics/sport/personRank
            String url = wrapperUrl("/busi/terminal/statistics/sport/personRank");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToObject(result.getContent().toString(), PersonRankEntity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SumScoreRankEntity> getSumScoreRank(String sportSkuId, String topLimit) {
        try {
            d("From Http 获取项目成绩排名");

            Map<String, Object> map = new HashMap<>();

            if (!TextUtils.isEmpty(sportSkuId)) {
                map.put("sportSkuId", sportSkuId);
            }
            if (!TextUtils.isEmpty(topLimit)) {
                map.put("topLimit", topLimit);
            }
            String param = InnerUtil.toJson(map);

            ///terminal/statistics/sport/sumScoreRank
            String url = wrapperUrl("/busi/terminal/statistics/sport/sumScoreRank");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<ArrayList<SumScoreRankEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static SportOverviewEntity getSportOverview() {
        try {
            d("From Http 获取运动数据总览");

            Map<String, Object> map = new HashMap<>();
            String param = InnerUtil.toJson(map);

            //terminal/home/sport/overview
            String url = wrapperUrl("/busi/terminal/home/sport/overview");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToObject(result.getContent().toString(), SportOverviewEntity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
