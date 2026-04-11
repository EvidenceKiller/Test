package com.adl.service.web;

import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.WeatherEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/1
 * Describe   :
 * 1. 上传日志
 */
public class CommonService extends BaseHttpService {

    ////////// 上传日志
    public static RequestResult addBatch(
            String appCode,
            int expireTime,
            String ipAddress,
            String level,
            int logType,
            List<String> logUrls,
            String productCode
    ) {
        try {
            d("From Http addBatch");

            Map<String, Object> map = new HashMap<>();
            map.put("appCode", appCode);            // 应用编码
            map.put("expireTime", expireTime);      // 日志过期时间，单位秒，范围在86400到86400*60即1-60天之间,如果不传默认存储1天
            map.put("ipAddress", ipAddress);        // ip地址
            map.put("level", level);                // 日志级别，终端定义
            map.put("logType", logType);            // 日志类型
            map.put("logUrls", logUrls);            // 日志文件地址
            map.put("productCode", productCode);    // 产品编码
            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/dc/device/log/addBatch");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            return parserResponse(getHttpClient().newCall(request).execute());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 获取天气信息
    public static WeatherEntity getWeatherInfo(String location) {
        try {
            d("From Http 获取天气信息");
            // 请求
            String url = wrapperUrl("/busi/terminal/home/weather/now?location=" + location);
            Request request = new Request.Builder().url(url).get().build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToObject(result.getContent().toString(), WeatherEntity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}