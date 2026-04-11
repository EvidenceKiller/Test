package com.adl.service.common;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 */
public final class InnerLogger {

    private String TAG = "AdlService";
    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private final Object mLock = new Object();
    private StringBuilder mBuilder;
    private int maxSize = 2 * 1024;
    private boolean mDebug;
    private static InnerLogger _logger = new InnerLogger();

    private InnerLogger() {

    }

    public static InnerLogger logger() {
        return _logger;
    }

    public void setDebug(boolean debug) {
        mDebug = debug;
    }

    public void d(String msg) {
        if (mDebug) Log.d(TAG, "Adl--> " + msg);
        write(" D: " + msg);
    }

    public void e(String msg) {
        if (mDebug) Log.e(TAG, "Adl--> " + msg);
        write(" E: " + msg);
    }

    public void w(String msg) {
        if (mDebug) Log.w(TAG, "Adl--> " + msg);
        write(" W: " + msg);
    }

    private void write(String msg) {
        synchronized (mLock) {
            if (mBuilder == null) {
                mBuilder = new StringBuilder();
            }

            StringBuilder builder = mBuilder;
            if (builder.length() > 0) {
                builder.append("\n");
            }
            builder.append(InnerUtil.formatNow());
            builder.append(":");
            builder.append(msg);

            // flush
            flush(false);
        }
    }

    private void flush(boolean focus) {
        if (mBuilder == null) return;
        synchronized (mLock) {
            if (focus || mBuilder.length() > maxSize) {
                final String str = mBuilder.toString() + "\n";
                mExecutor.submit(() -> {
                    try {
                        String fileName = InnerUtil.dateToStr(new Date(), "yyyyMMdd") + "_service.log";
                        File file = FileUtil.createLog(fileName);
                        byte[] data = str.getBytes();
                        FileOutputStream out = new FileOutputStream(file, true);
                        out.write(data);
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                mBuilder = null;
            }
        }
    }
}
