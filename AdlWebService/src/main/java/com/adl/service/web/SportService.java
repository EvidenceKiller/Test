package com.adl.service.web;

import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.DictEntity;
import com.adl.service.entity.DictExcelEntity;
import com.adl.service.entity.PageInfo;
import com.adl.service.entity.SceneEntity;
import com.adl.service.entity.SceneSportEntity;
import com.adl.service.entity.SceneSportInfoEntity;
import com.adl.service.entity.SportDetailEntity;
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
 * Describe   : 运动服务
 * 1. 场景查询
 * 2. 运动列表
 * 3. 运动指标
 */
public class SportService extends BaseHttpService {

    ////////// 同步查询场景列表
    public static List<SceneEntity> getSceneList(String appCode, boolean skuStatus) {
        try {
            d("From Http 查询场景列表");

            Map<String, Object> map = new HashMap<>();
            map.put("appCode", appCode);        //  应用编码
            map.put("skuStatus", skuStatus);    //  是否查询运动

            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/oper/terminal/scene/v1/getSceneList");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<SceneEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 同步查询运动列表
    public static List<SceneSportEntity> getSportSkuList(String appCode, String sceneId, int current) {
        try {
            d("From Http 查询运动列表");

            Map<String, Object> map = new HashMap<>();
            map.put("appCode", appCode);    //  应用编码
            map.put("sceneId", sceneId);    //  场景Id
            map.put("delFlag", false);    //  空-全部 true-已删除 false-未删除
            map.put("enabled", true);     //  true-启用 false-禁用
            map.put("current", current);    //  当前页
            map.put("pageSize", 500);

            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/oper/terminal/sportSku/v1/getSportSkuList");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<SceneSportEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 同步查询运动详情
    public static SceneSportInfoEntity getSportSkuDetail(String sportSkuId) {
        try {
            d("From Http 查询运动详情");

            Map<String, Object> map = new HashMap<>();
            map.put("sportSkuId", sportSkuId);    //  项目id

            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/oper/terminal/sportSku/v1/getSportSkuDetail");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToObject(result.getContent().toString(), SceneSportInfoEntity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 字典查询
    public static List<DictEntity> getDicts(String dictId) {
        try {
            d("From Http 字典查询");

            Map<String, Object> map = new HashMap<>();
            map.put("dictId", dictId);

            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/uc/web/dict/getDicts");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<DictEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////////// 获取趣味运动配置
    public static List<DictExcelEntity> getDictMap(String sportSkuId) {
        try {
            d("From Http 获取趣味运动配置");

            Map<String, Object> map = new HashMap<>();
            map.put("sportSkuId", sportSkuId);

            String param = InnerUtil.toJson(map);

            // 请求
            String url = wrapperUrl("/oper/terminal/sportSku/v1/getDictMap");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<DictExcelEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<SportDetailEntity> getSportDetailList(String date) {
        return getSportDetailList(date, 200);
    }

    public static List<SportDetailEntity> getSportDetailList(String date, int pageSize) {
        try {
            d("From Http 获取运动历史记录");
            List<SportDetailEntity> outList = new ArrayList<>();
            for (int index = 1; index < 20; index++) {
                PageInfo<SportDetailEntity> info = getSportDetailList(date, index, pageSize);
                if (info == null || info.getRecords() == null || info.getRecords().size() < pageSize) {
                    if (info != null && info.getRecords() != null) {
                        outList.addAll(info.getRecords());
                    }
                    break;
                }
                outList.addAll(info.getRecords());
            }
            return outList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PageInfo<SportDetailEntity> getSportDetailList(String date, int pageIndex, int pageSize) {
        try {
            d("From Http 获取运动历史记录, current=" + pageIndex + ", pageSize=" + pageSize);

            Map<String, Object> map = new HashMap<>();
            map.put("date", date);
            map.put("current", pageIndex);
            map.put("pageSize", pageSize);
            String param = InnerUtil.toJson(map);

            // 返回值
            PageInfo<SportDetailEntity> pageInfo = new PageInfo<>();

            // 请求
            String url = wrapperUrl("/busi/terminal/sunshineRunning/sportDetailList");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString(), pageInfo);
                List<SportDetailEntity> list = InnerUtil.jsonToType(str, new TypeToken<ArrayList<SportDetailEntity>>() {
                });
                pageInfo.setRecords(list);
                return pageInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
