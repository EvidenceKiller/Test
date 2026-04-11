package com.adl.service.common;

import android.text.TextUtils;

import com.adl.service.ConfigService;
import com.adl.service.internal.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 异步任务实现
 */
public abstract class BaseService implements IDefine {

    private static ConfigService mConfig;

    public BaseService() {
    }

    public static void setConfigService(ConfigService config) {
        mConfig = config;
    }

    public static ConfigService getConfigService() {
        if (mConfig == null) {
            mConfig = InnerPreferences.instance().readObject(IDefine.SpConfigService, ConfigService.class);
        }
        return mConfig;
    }

    public static void waitSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO 需优化
    // 获取七牛云token
    protected static String getQiNiuToken(String bucket) {
        try {
            InnerPreferences pf = InnerPreferences.instance();
            final String keyTime = bucket + LastQiNiuTime;
            final String keyToken = bucket + LastQiNiuToken;
            long lastTime = pf.readLong(keyTime);
            String token = pf.readString(keyToken);
            // 30 分钟过期
            if (System.currentTimeMillis() - lastTime < 5 * IDefine.Minute) {
                if (!TextUtils.isEmpty(token)) {
                    return token;
                }
            }

            //  七牛云token地址
            String url = "";
            if (getConfigService() != null) {
                url = getConfigService().getQiNiuHost() + "/uc/oss/v1/qiniu/token";
            }

            //  参数
            Map<String, Object> map = new HashMap<>();
            map.put("bucket", bucket);
            String param = InnerUtil.toJson(map);

            //  post 请求
            Request request = new Request.Builder().url(url)
                    .post(RequestBody.create(param, MediaType.parse("application/json; charset=utf-8"))).build();
            Response response = RetrofitClient.getInstance("", false).getOkHttpClient().newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String content = response.body().string();
                //  去除双引号
                token = content.replaceAll("\"", "");
                pf.putString(keyToken, token);
                pf.putLong(keyTime, System.currentTimeMillis());
                return token;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
