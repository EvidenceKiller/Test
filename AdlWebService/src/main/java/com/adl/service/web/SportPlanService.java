package com.adl.service.web;

import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.ClassInfoEntity;
import com.adl.service.entity.SportPlanEntity;
import com.adl.service.entity.SportPlanStudentEntity;
import com.adl.service.entity.StandardConfigEntity;
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
 * Describe   : 组织测试
 * 1. 运动计划
 * 2. 运动计划下学生
 * 3. 运动标准
 */
public class SportPlanService extends BaseHttpService {

    // 运动计划
    public static List<SportPlanEntity> querySportPlan(String standardType, int status, int current) {

        try {
            d("From Http 查询运动计划");

            Map<String, Object> map = new HashMap<>();
//            map.put("acayearCode", yearCode);// 学年标识码
            map.put("standardType", standardType);// 测试类型 体测：TC、中考：ZK
            map.put("status", status); // 计划状态0全部 1未开始 2进行中 3已结束
            map.put("current", current);// 当前页
            map.put("pageSize", 500);
            String param = InnerUtil.toJson(map);

            // 获取组织测试列表
            String url;
            if (Platform.GX.equals(getConfigService().getPlatform())) {
                url = wrapperUrl("/busi/terminal/gx/organizeTest/getPlanList");
            } else {
                url = wrapperUrl("/busi/terminal/k12/organizeTest/getPlanList");
            }

            // 请求
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<SportPlanEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<SportPlanEntity> querySportPlan(String standardType, int status, int current, int pageSize) {

        try {
            d("From Http 查询运动计划");

            Map<String, Object> map = new HashMap<>();
//            map.put("acayearCode", yearCode);// 学年标识码
            map.put("standardType", standardType);// 测试类型 体测：TC、中考：ZK
            map.put("status", status); // 计划状态0全部 1未开始 2进行中 3已结束
            map.put("current", current);// 当前页
            map.put("pageSize", pageSize);
            String param = InnerUtil.toJson(map);

            // 获取组织测试列表
            String url;
            if (Platform.GX.equals(getConfigService().getPlatform())) {
                url = wrapperUrl("/busi/terminal/gx/organizeTest/getPlanList");
            } else {
                url = wrapperUrl("/busi/terminal/k12/organizeTest/getPlanList");
            }

            // 请求
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<SportPlanEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取计划详情
     *
     * @param planId
     * @return
     */
    public static SportPlanEntity getSportPlanDetail(String planId) {

        try {
            d("From Http 获取计划详情");

            Map<String, Object> map = new HashMap<>();
            map.put("planId", planId);
            String param = InnerUtil.toJson(map);

            // 获取组织测试列表
            String url;
            if (Platform.GX.equals(getConfigService().getPlatform())) {
                url = wrapperUrl("/busi/terminal/gx/organizeTest/planInfo");
            } else {
                url = wrapperUrl("/busi/terminal/k12/organizeTest/planInfo");
            }

            // 请求
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToObject(result.getContent().toString(), SportPlanEntity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 运动计划下学生
    public static List<SportPlanStudentEntity> querySportPlanStudent(String planId, int pageIndex) {

        try {
            d("From Http 查询运动计划下学生信息");

            Map<String, Object> map = new HashMap<>();
            map.put("planId", planId);// 计划id
            map.put("current", pageIndex);// 当前页
            map.put("pageSize", 500);
            String param = InnerUtil.toJson(map);

            // 获取计划下学生列表
            String url;
            if (Platform.GX.equals(getConfigService().getPlatform())) {
                url = wrapperUrl("/busi/terminal/gx/organizeTest/getPlanStudents");
            } else {
                url = wrapperUrl("/busi/terminal/k12/organizeTest/getPlanStudents");
            }

            // 请求
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<SportPlanStudentEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Boolean checkStudent(String planId, String accountId) {

        try {
            d("From Http 查询学生是否在运动计划下");

            Map<String, Object> map = new HashMap<>();
            map.put("planId", planId);// 计划id
            map.put("accountId", accountId);// 当前页
            String param = InnerUtil.toJson(map);

            // 获取计划下学生列表
            String url;
            if (Platform.GX.equals(getConfigService().getPlatform())) {
                url = wrapperUrl("/busi/terminal/gx/organizeTest/checkStudent");
            } else {
                url = wrapperUrl("/busi/terminal/k12/organizeTest/checkStudent");
            }

            // 请求
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<Boolean>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ClassInfoEntity> querySportPlanClass(String planId) {

        try {
            d("From Http 查询运动计划下班级信息");

            Map<String, Object> map = new HashMap<>();
            map.put("planId", planId);// 计划id
            String param = InnerUtil.toJson(map);

            // 获取计划下学生列表
            String url;
            if (Platform.GX.equals(getConfigService().getPlatform())) {
                url = wrapperUrl("/busi/terminal/gx/organizeTest/getPlanClassList");
            } else {
                url = wrapperUrl("/busi/terminal/k12/organizeTest/getPlanClassList");
            }

            // 请求
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<ArrayList<ClassInfoEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 运动标准
    public static List<StandardConfigEntity> queryStandardConfig(String standardId, String sportProjectCode) {

        try {
            d("From Http 查询运动标准");

            Map<String, Object> map = new HashMap<>();
            map.put("standardId", standardId);// 标准id
            map.put("sportProjectCode", sportProjectCode);// 项目code
            String param = InnerUtil.toJson(map);

            // 运动标准
            String url;
            if (Platform.GX.equals(getConfigService().getPlatform())) {
                url = wrapperUrl("/busi/terminal/gx/organizeTest/getStandardConfig");
            } else {
                url = wrapperUrl("/busi/terminal/k12/organizeTest/getStandardConfig");
            }

            // 请求
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<StandardConfigEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
