package com.adl.service.log;

/**
 * 对外统一静态日志入口，调用方式：NzLog.i(...).
 */
public final class NzLog {

    private NzLog() {
    }

    public static void d(String message) {
        Logger.getInstance().debug(message);
    }

    public static void d(String format, Object... args) {
        Logger.getInstance().debug(format, args);
    }

    public static void i(String message) {
        Logger.getInstance().info(message);
    }

    public static void i(String format, Object... args) {
        Logger.getInstance().info(format, args);
    }

    public static void w(String message) {
        Logger.getInstance().warn(message);
    }

    public static void w(String message, Throwable throwable) {
        Logger.getInstance().warn(message, throwable);
    }

    public static void w(String format, Object... args) {
        Logger.getInstance().warn(format, args);
    }

    public static void e(String message) {
        Logger.getInstance().error(message);
    }

    public static void e(String message, Throwable throwable) {
        Logger.getInstance().error(message, throwable);
    }

    public static void e(String format, Object... args) {
        Logger.getInstance().error(format, args);
    }

    public static void shutdown() {
        Logger.getInstance().shutdown();
    }
}
