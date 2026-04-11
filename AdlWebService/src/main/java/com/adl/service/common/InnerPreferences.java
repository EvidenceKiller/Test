package com.adl.service.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 存储标识
 */
public final class InnerPreferences {

    private static InnerPreferences _instance = new InnerPreferences();
    private SharedPreferences mPf;
    private final Gson mGson = new Gson();

    private InnerPreferences() {

    }

    public Gson getGson() {
        return mGson;
    }

    public static InnerPreferences instance() {
        return _instance;
    }

    public void init(Context context) {
        mPf = context.getSharedPreferences("_web_service", Context.MODE_PRIVATE);
    }

    public void putString(String key, String value) {
        if (mPf != null) {
            SharedPreferences.Editor editor = mPf.edit();
            if (editor != null) {
                editor.putString(key, value).apply();
            }
        }
    }

    public void putInt(String key, int value) {
        if (mPf != null) {
            SharedPreferences.Editor editor = mPf.edit();
            if (editor != null) {
                editor.putInt(key, value).apply();
            }
        }
    }

    public void putLong(String key, long value) {
        if (mPf != null) {
            SharedPreferences.Editor editor = mPf.edit();
            if (editor != null) {
                editor.putLong(key, value).apply();
            }
        }
    }

    public void putObject(String key, Object value) {
        if (mPf != null) {
            SharedPreferences.Editor editor = mPf.edit();
            if (editor != null) {
                String strJson = mGson.toJson(value);
                editor.putString(key, strJson).apply();
            }
        }
    }

    public String readString(String key) {
        return mPf != null ? mPf.getString(key, "") : "";
    }

    public int readInt(String key) {
        return mPf != null ? mPf.getInt(key, -1) : -1;
    }

    public long readLong(String key) {
        return mPf != null ? mPf.getLong(key, -1L) : -1L;
    }

    public <T> T readObject(String key, Class<T> clazz) {
        if (mPf == null) return null;
        String strJson = mPf.getString(key, "");
        if (TextUtils.isEmpty(strJson)) {
            return null;
        } else {
            return mGson.fromJson(strJson, clazz);
        }
    }
}
