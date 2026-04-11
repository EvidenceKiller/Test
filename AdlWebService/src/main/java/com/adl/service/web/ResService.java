package com.adl.service.web;

import android.text.TextUtils;

import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.ResInfoEntity;
import com.adl.service.entity.WikiTypeEntity;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

public class ResService extends BaseHttpService {


    /**
     * 获取资源类型列表
     * @param resType
     * @return
     */
    public static List<WikiTypeEntity.WikiTypeInfo> getResList(int resType) {
        try {
            d("From Http 获取教师资源列表");
            Map<String, Object> map = new HashMap<>();
            map.put("resType", resType);
            String param = InnerUtil.toJson(map);
            //http://172.16.0.206:8082/gateway/api/busi/terminal/org/res/v1/list/type
            String url = wrapperUrl("/busi/terminal/org/res/v1/list/type");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<ArrayList<WikiTypeEntity.WikiTypeInfo>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取资源列表
     *
     * @param createNum
     * @param resType
     * @return
     */
    public static List<ResInfoEntity> getResListByPage(String createNum, int resType, String resTypeId, String appCode, int current, int pageSize) {
        try {
            d("From Http 获取教师资源列表");
            Map<String, Object> map = new HashMap<>();
            map.put("createNum", createNum);
            map.put("resType", resType);
            map.put("appCode", appCode);
            map.put("current", current);// 当前页
            if (!TextUtils.isEmpty(resTypeId)){
                map.put("resTypeId", resTypeId);
            }
            map.put("pageSize", pageSize); // 每页大小
            String param = InnerUtil.toJson(map);
            //http://172.16.0.206:8082/gateway/api/busi/terminal/org/res/v1/list
            String url = wrapperUrl("/busi/terminal/org/res/v1/list");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<List<ResInfoEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
