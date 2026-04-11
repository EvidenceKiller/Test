package com.adl.service.log;

/**
 * 日志常量配置。
 */
final class LogConfig {

    private LogConfig() {
    }

    public static final Logger.Level LOG_LEVEL = Logger.Level.DEBUG;
    public static final String TAG = "AdlService";
    public static final boolean ENABLE_CONSOLE = true;
    public static final boolean ENABLE_FILE = true;
    public static final int MAX_QUEUE_SIZE = 1000;
    public static final long FLUSH_INTERVAL_MS = 5000L;
    public static final String FILE_PATTERN = "%d{yyyyMMdd}_service.log";
    public static final long MAX_FILE_SIZE_BYTES = 10L * 1024 * 1024;
    public static final int MAX_BACKUP_COUNT = 500;
}
