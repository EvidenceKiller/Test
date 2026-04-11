package com.adl.service.web;

import android.text.TextUtils;

import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.SportIndicatorsEntity;
import com.adl.service.entity.SportRecordEntity;
import com.adl.service.entity.SportRecordResultEntity;
import com.adl.service.entity.TeacherSportLocalEntity;
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
 * Describe   : 运动记录服务
 * 1. 运动记录上报
 * 2. 运动指标上报
 */
public class SportRecordService extends BaseHttpService {

    ////////// 运动记录上报
    public static RequestResult uploadRecord(SportRecordEntity sportRecord) {
        try {
            d("From Http 运动记录上报");

            String param = InnerUtil.toJson(sportRecord);

            // 请求
            String url = wrapperUrl("/busi/terminal/sport/result/upload");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            return parserResponse(getHttpClient().newCall(request).execute());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 运动记录更新
    public static RequestResult updateRecord(SportRecordEntity sportRecord) {
        try {
            d("From Http 运动记录更新");

            String param = InnerUtil.toJson(sportRecord);

            // 请求
            String url = wrapperUrl("/busi/terminal/sport/result/update");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            return parserResponse(getHttpClient().newCall(request).execute());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 运动指标上报
    public static RequestResult uploadIndicators(SportIndicatorsEntity<?, ?> sportIndicators) {
        try {
            d("From Http 运动指标上报");

            String param = InnerUtil.toJson(sportIndicators);

            // 请求
            String url = wrapperUrl("/busi/terminal/sport/result/uploadIndicators");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            return parserResponse(getHttpClient().newCall(request).execute());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static RequestResult uploadTeacherData(TeacherSportLocalEntity sportRecord) {
        try {
            d("From Http 运动记录上报");

            String param = InnerUtil.toJson(sportRecord);

            // 请求
            String url = wrapperUrl("/busi/terminal/sport/teacher/result/upload");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            return parserResponse(getHttpClient().newCall(request).execute());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<SportRecordResultEntity> getSportResultRecord(String sportSkuId, String sceneCode, String appCode, String orgId, String classId, String ptPlanId, int current, int pageSize) {

        try {
            d("From Http 查询运动记录成绩");
            Map<String, Object> map = new HashMap<>();
//            map.put("acayearCode", yearCode);// 学年标识码
            map.put("sportSkuId", sportSkuId);// 测试类型 体测：TC、中考：ZK
            map.put("sceneCode", sceneCode); // 计划状态0全部 1未开始 2进行中 3已结束
            map.put("current", current);// 当前页
            if (!TextUtils.isEmpty(appCode)) {
                map.put("appCode", appCode);
            }
            map.put("pageSize", pageSize);
            map.put("orgId", orgId);
            if (!TextUtils.isEmpty(ptPlanId)) {
                map.put("ptPlanId", ptPlanId);
            }
            if (!TextUtils.isEmpty(classId)) {
                map.put("classId", classId);
            }
            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/busi/terminal/sport/result/record");

            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<SportRecordResultEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
