package com.adl.service.log;

import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 轻量日志核心实现。
 */
class Logger {

    public enum Level {
        DEBUG, INFO, WARN, ERROR
    }

    private static volatile Logger instance;

    private static final Level LOG_LEVEL = LogConfig.LOG_LEVEL;
    private static final String TAG = LogConfig.TAG;
    private static final boolean ENABLE_CONSOLE = LogConfig.ENABLE_CONSOLE;
    private static final boolean ENABLE_FILE = LogConfig.ENABLE_FILE;
    private static final int MAX_QUEUE_SIZE = LogConfig.MAX_QUEUE_SIZE;
    private static final long FLUSH_INTERVAL_MS = LogConfig.FLUSH_INTERVAL_MS;
    private static final String FILE_PATTERN = LogConfig.FILE_PATTERN;
    private static final long MAX_FILE_SIZE_BYTES = LogConfig.MAX_FILE_SIZE_BYTES;
    private static final int MAX_BACKUP_COUNT = LogConfig.MAX_BACKUP_COUNT;

    private final BlockingQueue<LogEntry> logQueue;
    private final ExecutorService logExecutor;
    private final AtomicBoolean isRunning = new AtomicBoolean(true);
    private final LogFileWriter fileWriter;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);

    private static class LogEntry {
        final long timestamp;
        final Level level;
        final String message;
        final Throwable throwable;

        LogEntry(long timestamp, Level level, String message, Throwable throwable) {
            this.timestamp = timestamp;
            this.level = level;
            this.message = message;
            this.throwable = throwable;
        }
    }

    private Logger() {
        this.logQueue = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE);
        this.fileWriter = new LogFileWriter(FILE_PATTERN, MAX_FILE_SIZE_BYTES, MAX_BACKUP_COUNT);
        this.logExecutor = Executors.newSingleThreadExecutor(r -> {
            Thread thread = new Thread(r, "NzLog-Writer");
            thread.setDaemon(true);
            return thread;
        });
        this.logExecutor.submit(this::processLogQueue);
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void shutdown() {
        if (isRunning.compareAndSet(true, false)) {
            try {
                logExecutor.shutdown();
                if (!logExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                    logExecutor.shutdownNow();
                }
            } catch (InterruptedException e) {
                logExecutor.shutdownNow();
                Thread.currentThread().interrupt();
            } finally {
                fileWriter.flush();
                fileWriter.close();
            }
        }
    }

    public void debug(String message) {
        log(Level.DEBUG, message, null);
    }

    public void debug(String format, Object... args) {
        log(Level.DEBUG, safeFormat(format, args), null);
    }

    public void info(String message) {
        log(Level.INFO, message, null);
    }

    public void info(String format, Object... args) {
        log(Level.INFO, safeFormat(format, args), null);
    }

    public void warn(String message) {
        log(Level.WARN, message, null);
    }

    public void warn(String message, Throwable throwable) {
        log(Level.WARN, message, throwable);
    }

    public void warn(String format, Object... args) {
        log(Level.WARN, safeFormat(format, args), null);
    }

    public void error(String message) {
        log(Level.ERROR, message, null);
    }

    public void error(String message, Throwable throwable) {
        log(Level.ERROR, message, throwable);
    }

    public void error(String format, Object... args) {
        log(Level.ERROR, safeFormat(format, args), null);
    }

    private void log(Level level, String message, Throwable throwable) {
        if (level.ordinal() < LOG_LEVEL.ordinal()) {
            return;
        }

        LogEntry entry = new LogEntry(System.currentTimeMillis(), level, message, throwable);

        try {
            if (!logQueue.offer(entry, 100, TimeUnit.MILLISECONDS) && ENABLE_CONSOLE) {
                directConsoleOutput(entry);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            if (ENABLE_CONSOLE) {
                directConsoleOutput(entry);
            }
        }
    }

    private void directConsoleOutput(LogEntry entry) {
        String logMessage = formatLogMessage(entry);
        switch (entry.level) {
            case DEBUG:
                Log.d(TAG, logMessage);
                break;
            case INFO:
                Log.i(TAG, logMessage);
                break;
            case WARN:
                Log.w(TAG, logMessage);
                break;
            case ERROR:
                Log.e(TAG, logMessage, entry.throwable);
                break;
            default:
                break;
        }
    }

    private void processLogQueue() {
        StringBuilder buffer = new StringBuilder();
        long lastFlushTime = System.currentTimeMillis();

        while (isRunning.get() || !logQueue.isEmpty()) {
            try {
                LogEntry entry = logQueue.poll(100, TimeUnit.MILLISECONDS);
                if (entry != null) {
                    if (ENABLE_CONSOLE) {
                        directConsoleOutput(entry);
                    }
                    if (ENABLE_FILE) {
                        buffer.append(formatLogMessage(entry)).append('\n');
                        if (entry.throwable != null) {
                            buffer.append(Log.getStackTraceString(entry.throwable)).append('\n');
                        }
                    }
                }

                long currentTime = System.currentTimeMillis();
                if (buffer.length() > 0 &&
                        (currentTime - lastFlushTime >= FLUSH_INTERVAL_MS || buffer.length() > 8192)) {
                    fileWriter.write(buffer.toString());
                    buffer.setLength(0);
                    lastFlushTime = currentTime;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                Log.e(TAG, "日志处理异常", e);
            }
        }

        if (buffer.length() > 0) {
            fileWriter.write(buffer.toString());
        }
    }

    private String formatLogMessage(LogEntry entry) {
        return String.format(Locale.CHINA, "%s [%s] %s", dateFormat.format(new Date(entry.timestamp)), entry.level.name(), entry.message);
    }

    private String safeFormat(String format, Object... args) {
        try {
            return String.format(Locale.CHINA, format, args);
        } catch (Exception ignore) {
            return format;
        }
    }
}
