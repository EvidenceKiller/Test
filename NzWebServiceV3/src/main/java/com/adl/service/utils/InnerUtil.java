package com.adl.service.utils;

import com.adl.service.AdlService;
import com.adl.service.exception.NzBaseException;
import com.adl.service.exception.NzCommonException;
import com.adl.service.internal.RetrofitClient;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Closeable;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Request;
import okhttp3.Response;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
public class InnerUtil {

    private static final Pattern URL_PATTERN = Pattern.compile("https?://(?:www\\.)?(?:(?:[\\w-]+\\.)+[\\w-]+|\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(:\\d+)?");
    private static final String SERVICE_HOST = "https://www.nezhasport.com";

    private static SimpleDateFormat YMD_HMS_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private static SimpleDateFormat YMD_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static String getIpHost() {
        Matcher matcher = URL_PATTERN.matcher(AdlService.getService().getHost());
        if (matcher.find()) {
            return matcher.group(0);
        }
        return SERVICE_HOST;
    }

    /**
     * TODO 临时实现
     * 获取服务端时间
     */
    public static long getServerTime() throws NzBaseException {
        try {
            Request request = new Request.Builder().url(InnerUtil.getIpHost() + "/time").build();
            Response response = RetrofitClient.getInstance("", false).getOkHttpClient().newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                com.alibaba.fastjson.JSONObject jo = JSON.parseObject(response.body().string());
                // 服务器返回时间为秒
                return (long) (jo.getDoubleValue("timestamp") * 1000L);
            } else {
                throw new NzCommonException("Get Server time meet some error : " + response.message());
            }
        } catch (IOException e) {
            throw new NzCommonException("Get Server time meet some error", e);
        }
    }

    public static String formatByTimeCode(long time) {
        return YMD_HMS_FORMAT.format(new Date(time));
    }

    public static String formatNow() {
        return YMD_HMS_FORMAT.format(new Date());
    }

    public static String formatYMDNow() {
        return YMD_FORMAT.format(new Date());
    }

    public static String md5(String text) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(text.getBytes());
            result = toHexString(digest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static String toHexString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        String hexStr;
        for (byte b : digest) {
            hexStr = Integer.toHexString(b & 0xFF);
            if (hexStr.length() == 1) {
                hexStr = "0" + hexStr;
            }
            sb.append(hexStr);
        }

        return sb.toString();
    }

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String dateToStr(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String toJson(Object obj) {
        return new Gson().toJson(obj);
    }

    public static <T> T jsonToObject(String json, Class<T> cls) {
        if (json != null && cls != null) {
            try {
                return new Gson().fromJson(json, cls);
            } catch (Exception var3) {
                var3.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static <T> T jsonToType(String json, TypeToken typeToken) {
        if (json != null && typeToken != null) {
            try {
                return new Gson().fromJson(json, typeToken.getType());
            } catch (Exception var3) {
                var3.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    // 分页参数解析 兼容 "data":{"records":xxx} , "data":{"list":xxx} , "data":xxx
    public static String readRecords(String json) {
        try {
            JSONObject repObj = new JSONObject(json);
            if (repObj.has("records")) {
                return repObj.optString("records");
            }

            if (repObj.has("list")) {
                return repObj.optString("list");
            }
        } catch (JSONException ignored) {
        }

        try {
            new JSONArray(json);
            return json;
        } catch (JSONException ignored) {
        }
        return null;
    }
}
