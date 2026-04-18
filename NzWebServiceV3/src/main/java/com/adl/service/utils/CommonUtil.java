package com.adl.service.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CommonUtil {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT < 23) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return null != networkInfo && networkInfo.isConnected();
        } else {
            Network network = cm.getActiveNetwork();
            if (network != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
                return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
            }
        }
        return false;
    }

    //  List深度复制，List<E> 需要 E 对象实现接口 Serializable，否则会报错。
    public static <E> List<E> deepCopy(List<E> src) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            @SuppressWarnings("unchecked")
            List<E> dest = (List<E>) in.readObject();
            return dest;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<E>();
        }
    }

    public static <T> boolean isNotEmptyList(List<T> list) {
        return list != null && !list.isEmpty();
    }

    public static <T> boolean isEmptyList(List<T> list) {
        return list == null || list.isEmpty();
    }

    private static final List<String> acceptLanguages = Arrays.asList("zh-CN", "zh-TW", "en-US");

    public static String getLocaleLanguage() {
        Locale currentLocale = Locale.getDefault();

        String language = currentLocale.getLanguage();//zh,en
        String regional = currentLocale.getCountry();//CN,TW,HK
        String script = currentLocale.getScript();//Hans 简体中文 Hant 繁体中文

        //  英文
        if ("en".equals(language)) {
            return acceptLanguages.get(2);
        }
        //  繁体中文
        if ("zh".equals(language) && ("TW".equals(regional) || "HK".equals(regional))) {
            return acceptLanguages.get(1);
        }
        //  繁体中文
        if ("zh".equals(language) && ("CN".equals(regional))) {
            if ("Hant".equals(script)) {
                return acceptLanguages.get(1);
            }
        }

        //  默认简体中文
        return acceptLanguages.get(0);
    }
}
