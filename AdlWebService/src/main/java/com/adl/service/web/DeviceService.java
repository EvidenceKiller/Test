package com.adl.service.web;

import android.text.TextUtils;

import com.adl.auth.common.AdlUpgradeInfo;
import com.adl.service.common.InnerUtil;
import com.adl.service.common.RequestResult;
import com.adl.service.entity.DeviceFocusEntity;
import com.adl.service.entity.DeviceInfoEntity;
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
 * Date       : 2024/7/1
 * Describe   : 设备相关
 * 1. 获取设备信息
 */
public class DeviceService extends BaseHttpService {

    ////////// 获取设备信息【设备别称 （如传入则更新设备别称）】
    public static DeviceInfoEntity getDeviceInfo() {
        try {
            d("From Http 获取banner");

            Map<String, Object> map = new HashMap<>();
            String param = InnerUtil.toJson(map);

            // 请求/terminal/device/info/mine
            String url = wrapperUrl("/busi/terminal/device/info/mine");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return InnerUtil.jsonToObject(result.getContent().toString(), DeviceInfoEntity.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDeviceQrcode(String appCode) {
        try {
            d("From Http 获取设备二维码");

            Map<String, Object> map = new HashMap<>();
            map.put("appCode", appCode);
            String param = InnerUtil.toJson(map);

            // http://172.16.2.102:8082/gateway/api/busi/terminal/home/device/qrcode
            String url = wrapperUrl("/busi/terminal/home/device/qrcode");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                return result.getContent().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean uploadDeviceName(String deviceName, String id, String macAddress) {
        try {
            d("From Http 上传设备信息");
            Map<String, Object> map = new HashMap<>();
            map.put("deviceName", deviceName);
            map.put("id", id);
            map.put("macAddress", macAddress);
            String param = InnerUtil.toJson(map);

            // /terminal/device/upload/deviceInfo
            String url = wrapperUrl("/busi/terminal/device/upload/deviceInfo");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk() && result.getContent() != null) {
                return InnerUtil.jsonToObject(result.getContent().toString(), Boolean.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取专注模式
     *
     * @param appCode
     * @param deviceId
     * @return
     */
    public static List<DeviceFocusEntity> getDeviceFocus(String appCode, String deviceId) {
        try {
            d("From Http 获取专注模式");
            Map<String, Object> map = new HashMap<>();
            map.put("appCode", appCode);//
            map.put("deviceId", deviceId);// registerCode
            String param = InnerUtil.toJson(map);

            // 请求  http://172.16.0.206:8082/gateway/api/busi/terminal/device/getDeviceFocus
            String url = wrapperUrl("/busi/terminal/device/getDeviceFocus");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<DeviceFocusEntity>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取升级信息
     *
     * @param appCode
     * @param productCode
     * @return
     */
    public static List<AdlUpgradeInfo> getUpgradeInfo(String appCode, String productCode) {
        try {
            d("From Http 获取升级计划");
            Map<String, Object> map = new HashMap<>();
            map.put("appCode", appCode);
            if (!TextUtils.isEmpty(productCode)) {
                map.put("productCode", productCode);// productCode
            }
            String param = InnerUtil.toJson(map);

            // 请求  http://172.16.0.206:8082/gateway/api/dc/upgradePlan/getUpgradePlanByDevice
            String url = wrapperUrl("/dc/upgradePlan/getUpgradePlanByDevice");
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, jsonType)).build();
            RequestResult result = parserResponse(getHttpClient().newCall(request).execute());
            if (result.isOk()) {
                String str = InnerUtil.readRecords(result.getContent().toString());
                return InnerUtil.jsonToType(str, new TypeToken<ArrayList<AdlUpgradeInfo>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}