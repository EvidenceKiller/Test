package com.adl.service.upload;

import android.text.TextUtils;

import com.adl.service.ConfigService;
import com.adl.service.common.BaseService;
import com.adl.service.common.IDefine;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.db.FileUploadDao;
import com.adl.service.db.entity.FileUploadInfoEntity;
import com.adl.service.log.NzLog;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/4
 * Describe   : 文件上传
 * 1. 视频文件
 * 2. 图片上传
 */
public class FileUploadService extends BaseService {

    private static FileUploadService _instance;
    private ExecutorService mExecutors;

    private final Object mLock = new Object();
    private UploadManager uploadManager;
    private FileUploadDao uploadDao;
    private boolean mStart;
    private List<FileUploadCallback> mCallbacks = new ArrayList<>();

    private FileUploadService() {
        mExecutors = Executors.newFixedThreadPool(2);

        Configuration config = new Configuration.Builder()
                .connectTimeout(90)                 // 链接超时。默认90秒
                .useHttps(false)                    // 是否使用https上传域名
                .useConcurrentResumeUpload(true)    // 使用并发上传，使用并发上传时，除最后一块大小不定外，其余每个块大小固定为4M，
                .concurrentTaskCount(3)             // 并发上传线程数量为3
                .responseTimeout(90)                // 服务器响应超时。默认90秒
                .zone(FixedZone.zone2)              // 设置区域，不指定会自动选择。指定不同区域的上传域名、备用域名、备用IP。
                .build();

        // 重用uploadManager，一般地，只需要创建一个uploadManager对象
        uploadManager = new UploadManager(config);

        // dao
        uploadDao = DaoManagerProxy.getInstance().getFileUploadDao();

        // 启动任务
        mStart = true;
        new Thread(() -> {

            while (mStart) {

                ConfigService configService = getConfigService();
                if (configService == null || TextUtils.isEmpty(configService.getQiNiuHost())) {
                    waitSleep(100);
                    continue;
                }

                try {

                    FileUploadInfoEntity entity = queryUpdateTask();
                    if (entity == null) {
                        waitSleep(100);
                        continue;
                    }

                    File file = new File(entity.getLocalPath());
                    if (file.exists()) {
                        NzLog.d("正在上传: " + entity.getLocalPath());
                        startCallback(file);
                        String remoteFileName = entity.getRemoteFileName();
                        String bucket = entity.getRemoteBucket();
                        if (TextUtils.isEmpty(bucket)) bucket = configService.getQiNiuBucket();
                        if (TextUtils.isEmpty(bucket)) bucket = IDefine.QiNiuPublicBucket;
                        String token = getQiNiuToken(bucket);
                        String url = syncUploadFileToQiNiuYun(file, remoteFileName, token);
                        if (url != null) {
                            entity.setRemoteUrl(url);
                            entity.setUploadStatus(FileUploadStatusDone);
                            entity.setRemark("上传完成");
                            NzLog.d("文件上传成功: " + url);
                        } else {
                            entity.setUploadStatus(FileUploadStatusNew);
                            entity.setRemark("上传失败，等待下次上传");
                        }
                        completeCallback(file, url);
                    } else {
                        entity.setUploadStatus(FileUploadStatusError);
                        entity.setRemark("文件路径不存在");
                    }

                    uploadDao.update(entity);
                    waitSleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static FileUploadService instance() {
        if (_instance == null) {
            _instance = new FileUploadService();
        }
        return _instance;
    }

    public void addUploadTask(List<UploadFileTask> list) {
        if (list == null || list.isEmpty()) return;
        mExecutors.submit(() -> {
            synchronized (mLock) {
                for (UploadFileTask task : list) {
                    FileUploadInfoEntity entity = new FileUploadInfoEntity();
                    entity.setOrgId(task.orgId);
                    entity.setLocalPath(task.file.getAbsolutePath());
                    entity.setRemoteFileName(task.remoteFileName);
                    entity.setUploadStatus(FileUploadStatusNew);
                    entity.setCreateTime(System.currentTimeMillis());
                    entity.setRemoteBucket(task.bucket);
                    uploadDao.insert(entity);
                }
            }
        });
    }

    private String syncUploadFileToQiNiuYun(File file, String name, String token) {
        try {
            ResponseInfo info = uploadManager.syncPut(file, name, token, null);
            if (info.isOK()) {
                String facePrefix = getConfigService().getFacePrefix();
                return facePrefix + info.response.getString("key");
            } else {
                NzLog.e("七牛上传: " + info.error);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 从数据库中查询需要上传的任务
    private FileUploadInfoEntity queryUpdateTask() {
        synchronized (mLock) {
            FileUploadInfoEntity entity = uploadDao.queryOneFileStatus(FileUploadStatusNew);
            if (entity != null) {
                entity.setUploadStatus(FileUploadStatusDoing);
                return entity;
            }
        }
        return null;
    }

    public void addCallback(FileUploadCallback callback) {
        if (callback != null && !mCallbacks.contains(callback)) {
            mCallbacks.add(callback);
        }
    }

    public void removeCallback(FileUploadCallback callback) {
        if (callback != null) {
            mCallbacks.remove(callback);
        }
    }

    private void startCallback(File file) {
        for (FileUploadCallback callback : mCallbacks) {
            callback.onStart(file);
        }
    }

    private void completeCallback(File file, String url) {
        for (FileUploadCallback callback : mCallbacks) {
            callback.onComplete(file, url);
        }
    }
}
