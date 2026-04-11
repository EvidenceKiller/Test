package com.adl.service.http.cache;

import android.content.Context;

import com.adl.service.common.InnerLogger;
import com.adl.service.entity.CacheEntry;
import com.google.gson.Gson;

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

public class HttpFileCache implements IHttpCache {

    private final String TAG = "HttpCacheUtil";
    private final String cacheDic = "http_cache";
    private String cachePath;
    private File cacheFile;
    private final Gson gson = new Gson();

    public HttpFileCache(Context context) {
        cachePath = context.getCacheDir().getAbsolutePath() + File.separator + cacheDic;
        cacheFile = new File(cachePath);
        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }
    }

    @Override
    public void save(String key, CacheEntry cacheEntry) {
        new Thread(() -> {
            try {
                File file = new File(cacheFile, key);
                try (BufferedWriter fout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
                    fout.write(gson.toJson(cacheEntry));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
                InnerLogger.logger().e("saveCache error;" + e.getMessage());
            }
        }).start();
    }

    @Override
    public CacheEntry get(String key) {
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
                return gson.fromJson(content.toString(), CacheEntry.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public void remove(String key) {
        try {
            File file = new File(cachePath, key);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        try {
            File[] files = cacheFile.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        } catch (Exception e) {
            // 忽略清理错误
        }
    }

    @Override
    public long getCacheSize() {
        long totalSize = 0;
        File[] files = cacheFile.listFiles();
        if (files != null) {
            for (File file : files) {
                totalSize += file.length();
            }
        }
        return totalSize;
    }
}
