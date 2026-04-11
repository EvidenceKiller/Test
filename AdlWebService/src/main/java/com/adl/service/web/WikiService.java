package com.adl.service.web;

import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.OrgResInfoEntity;
import com.adl.service.entity.WikiDetailEntity;
import com.adl.service.entity.WikiEntity;
import com.adl.service.entity.WikiTypeEntity;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/1
 * Describe   : 用户数据查询
 * 1. 获取百科列表
 * 2. 获取运动百科详情
 */
public class WikiService extends BaseHttpService {

    ////////// 获取百科列表
    public static WikiTypeEntity getWikiTypeList() {
        try {
            d("From Http 获取百科类型");

            // 增量更新学生信息
            //http://172.16.0.206:8082/gateway/api/oper/terminal/cs/wiki/type/v1/getWikiTypeList
            String url = wrapperUrl("/oper/terminal/cs/wiki/type/v1/getWikiTypeList");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create("{}", jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<WikiTypeEntity>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static WikiEntity getWikiList(String typeId) {
        try {
            d("From Http 获取百科");
            Map<String, Object> map = new HashMap<>();
            map.put("wikiTypeId", typeId);
            String param = InnerUtil.toJson(map);
            // 增量更新学生信息
            //http://172.16.0.206:8082/gateway/api/oper/terminal/cs/wiki/v1/getWikiList
            String url = wrapperUrl("/oper/terminal/cs/wiki/v1/getWikiList");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<WikiEntity>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WikiEntity getWikiListByPage(String typeId,int current, int pageSize) {
        try {
            d("From Http 获取百科");
            Map<String, Object> map = new HashMap<>();
            map.put("wikiTypeId", typeId);
            map.put("current", current);// 当前页
            map.put("pageSize", pageSize); // 每页大小
            String param = InnerUtil.toJson(map);
            // 增量更新学生信息
            //http://172.16.0.206:8082/gateway/api/oper/terminal/cs/wiki/v1/getWikiList
            String url = wrapperUrl("/oper/terminal/cs/wiki/v1/getWikiList");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<WikiEntity>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WikiDetailEntity getWikiDetail(String wikiId) {
        try {
            d("From Http 获取百科详情");
            Map<String, Object> map = new HashMap<>();
            map.put("wikiId", wikiId);
            String param = InnerUtil.toJson(map);
            // 增量更新学生信息
            //http://172.16.0.206:8082/gateway/api/oper/terminal/cs/wiki/v1/getWikiDetail
            String url = wrapperUrl("/oper/terminal/cs/wiki/v1/getWikiDetail");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<WikiDetailEntity>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取校本和备课助手详情信息
     * @param resId
     * @return
     */
    public static OrgResInfoEntity getOrgResInfo(String resId) {
        try {
            d("From Http 获取百科详情");
            Map<String, Object> map = new HashMap<>();
            map.put("id", resId);
            String param = InnerUtil.toJson(map);
            // 增量更新学生信息
            //http://172.16.0.206:8082/gateway/api/oper/terminal/org/res/v1/info
            //gateway/api/busi/terminal/org/res/v1/info
            String url = wrapperUrl("/busi/terminal/org/res/v1/info");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToType(result.getContent().toString(), new TypeToken<OrgResInfoEntity>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}