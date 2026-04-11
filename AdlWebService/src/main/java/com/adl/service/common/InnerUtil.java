package com.adl.service.common;

import com.adl.service.entity.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Closeable;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
public class InnerUtil {

    static SimpleDateFormat DFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    static SimpleDateFormat YMDFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static String formatByTimeCode(long time) {
        return DFormat.format(new Date(time));
    }

    public static String formatNow() {
        return DFormat.format(new Date());
    }

    public static String formatYMDNow() {
        return YMDFormat.format(new Date());
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

    public static String readRecords(String json, PageInfo info) {
        try {
            JSONObject repObj = new JSONObject(json);
            info.setCurrent(repObj.optInt("current"));
            info.setPages(repObj.optInt("pages"));
            info.setSize(repObj.optInt("size"));
            info.setTotal(repObj.optInt("total"));

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
