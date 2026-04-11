package com.adl.service.log;

import android.util.Log;

import com.adl.service.common.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 文件日志写入器
 * 负责将日志写入文件，支持文件滚动和清理
 */
class LogFileWriter {
    
    private static final String TAG = "FileLogWriter";
    
    // 当前日志文件
    private File currentLogFile;
    private FileOutputStream currentOutputStream;
    private final AtomicLong currentFileSize = new AtomicLong(0);
    
    // 配置
    private final String filePattern;
    private final long maxFileSize;
    private final int maxBackupCount;
    
    // 日期格式化
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
    private String currentDate;
    
    public LogFileWriter(String filePattern, long maxFileSize, int maxBackupCount) {
        this.filePattern = filePattern;
        this.maxFileSize = maxFileSize;
        this.maxBackupCount = maxBackupCount;
        this.currentDate = dateFormat.format(new Date());
    }
    
    /**
     * 写入日志到文件
     */
    public synchronized void write(String logContent) {
        if (logContent == null || logContent.isEmpty()) {
            return;
        }
        
        try {
            // 检查是否需要创建新文件（日期变化或文件大小超限）
            checkAndCreateNewFile();
            
            // 写入文件
            byte[] data = logContent.getBytes(StandardCharsets.UTF_8);
            currentOutputStream.write(data);
            currentOutputStream.flush();
            
            // 更新文件大小
            currentFileSize.addAndGet(data.length);
            
        } catch (IOException e) {
            Log.e(TAG, "写入日志文件失败", e);
            closeCurrentFile();
        }
    }
    
    /**
     * 检查并创建新文件
     */
    private void checkAndCreateNewFile() throws IOException {
        String today = dateFormat.format(new Date());
        
        // 检查日期是否变化
        boolean dateChanged = !today.equals(currentDate);
        
        // 检查文件大小是否超限
        boolean sizeExceeded = currentFileSize.get() >= maxFileSize;
        
        // 如果日期变化或文件大小超限，创建新文件
        if (dateChanged || sizeExceeded || currentOutputStream == null) {
            closeCurrentFile();
            createNewFile(today);
            currentDate = today;
        }
    }
    
    /**
     * 创建新日志文件
     */
    private void createNewFile(String date) throws IOException {
        // 使用现有的FileUtil创建日志文件
        String fileName = filePattern.replace("%d{yyyyMMdd}", date);
        
        // 使用现有的FileUtil.createLog方法
        currentLogFile = FileUtil.createLog(fileName);
        
        // 创建父目录（如果不存在）
        File parentDir = currentLogFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        
        // 打开输出流
        currentOutputStream = new FileOutputStream(currentLogFile, true);
        currentFileSize.set(currentLogFile.length());
        
        // 清理旧日志文件
        cleanupOldLogs();
        
        Log.d(TAG, "创建新日志文件: " + currentLogFile.getAbsolutePath());
    }
    
    /**
     * 清理旧日志文件
     */
    private void cleanupOldLogs() {
        try {
            File logDir = currentLogFile.getParentFile();
            if (logDir == null || !logDir.exists()) {
                return;
            }
            
            File[] logFiles = logDir.listFiles((dir, name) -> 
                name.endsWith(".log") && name.contains("_adl_service"));
            
            if (logFiles == null || logFiles.length <= maxBackupCount) {
                return;
            }
            
            // 按最后修改时间排序（旧的在前面）
            Arrays.sort(logFiles, (f1, f2) ->
                Long.compare(f1.lastModified(), f2.lastModified()));
            
            // 删除最旧的文件
            int filesToDelete = logFiles.length - maxBackupCount;
            for (int i = 0; i < filesToDelete; i++) {
                if (logFiles[i].delete()) {
                    Log.d(TAG, "删除旧日志文件: " + logFiles[i].getName());
                }
            }
            
        } catch (Exception e) {
            Log.w(TAG, "清理旧日志文件失败", e);
        }
    }
    
    /**
     * 关闭当前文件
     */
    private void closeCurrentFile() {
        if (currentOutputStream != null) {
            try {
                currentOutputStream.close();
            } catch (IOException e) {
                Log.w(TAG, "关闭日志文件流失败", e);
            }
            currentOutputStream = null;
        }
        currentLogFile = null;
        currentFileSize.set(0);
    }
    
    /**
     * 获取当前日志文件路径
     */
    public String getCurrentLogFilePath() {
        return currentLogFile != null ? currentLogFile.getAbsolutePath() : null;
    }
    
    /**
     * 获取当前日志文件大小
     */
    public long getCurrentFileSize() {
        return currentFileSize.get();
    }
    
    /**
     * 关闭写入器
     */
    public synchronized void close() {
        closeCurrentFile();
    }
    
    /**
     * 强制刷写
     */
    public synchronized void flush() {
        if (currentOutputStream != null) {
            try {
                currentOutputStream.flush();
            } catch (IOException e) {
                Log.w(TAG, "刷写日志文件失败", e);
            }
        }
    }
}