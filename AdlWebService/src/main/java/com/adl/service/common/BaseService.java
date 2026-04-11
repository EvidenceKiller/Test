package com.adl.service.common;

import android.os.Handler;
import android.os.Looper;

import com.adl.service.outer.ConfigService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 异步任务实现
 */
public abstract class BaseService implements IDefine {

    private static Handler uiHandler;
    private static ExecutorService mExecutors;
    private static ConfigService mConfig;

    static {
        uiHandler = new Handler(Looper.getMainLooper());
        mExecutors = Executors.newFixedThreadPool(5);
    }

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

    public static void postUI(Runnable runnable) {
        if (runnable != null) {
            uiHandler.post(runnable);
        }
    }

    public static void postTask(Runnable runnable) {
        if (runnable != null) {
            mExecutors.execute(runnable);
        }
    }

    public static void d(String msg) {
        InnerLogger.logger().d(msg);
    }

    public static void e(String msg) {
        InnerLogger.logger().e(msg);
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

    // 访问异常处理
    public static RequestResult parserException(Exception e) {
        e.printStackTrace();
        e(e.getMessage());
        return RequestResult.error(e.toString());
    }

}
