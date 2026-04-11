package com.adl.service.utils;


import com.adl.service.AdlService;
import com.adl.service.log.NzLog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HttpCacheUtil {
    private static final String TAG = "HttpCacheUtil";
    private static final String cacheDic = "http_cache";
    private static String cachePath = AdlService.getService().getContext().getCacheDir().getAbsolutePath() + File.separator + cacheDic;

    /**
     * 获取缓存HTTP数据的KEY
     *
     * @param url
     * @param params
     * @return
     */
    public static String getCacheKey(String url, String... params) {
        //  1. Key= md5(url+参数+包名 +机构ID), value=返回有效json
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        for (String param : params) {
            sb.append(param);
        }
        String pkgName = AdlService.getService().getContext().getPackageName();
        sb.append(pkgName);
        sb.append(AdlService.getService().getOrgId());
        return sha256(sb.toString());
    }


    /**
     * 保存缓存数据
     *
     * @param key
     * @param value
     */
    public static void saveHttpCache(String key, String value) {
        try {
            File cacheFile = new File(cachePath);
            if (!cacheFile.exists()) {
                cacheFile.mkdirs();
            }
            File file = new File(cacheFile, key);
            try (BufferedWriter fout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
                fout.write(value);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            NzLog.e("saveCache error;" + e.getMessage());
        }
    }

    /**
     * 获取缓存数据
     *
     * @param key
     * @return
     */
    public static String getHttpCache(String key) {
        File file = new File(cachePath, key);
        int bufferSize = 1024;
        if (file.exists()) {
            StringBuilder content = new StringBuilder();
            char[] buffer = new char[bufferSize];
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file),
                            StandardCharsets.UTF_8
                    )
            )) {
                int charsRead;
                while ((charsRead = br.read(buffer)) != -1) {
                    content.append(buffer, 0, charsRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return content.toString();
        }
        return null;
    }

    /**
     * 清空缓存
     */
    public static void clearDiskCache() {
        try {
            File[] files = new File(cachePath).listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取缓存大小
     * @return
     */
    private long getDiskCacheSize() {
        long totalSize = 0;
        File[] files = new File(cachePath).listFiles();
        if (files != null) {
            for (File file : files) {
                totalSize += file.length();
            }
        }
        return totalSize;
    }

    private static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("SHA-256计算失败", e);
        }
    }


}
