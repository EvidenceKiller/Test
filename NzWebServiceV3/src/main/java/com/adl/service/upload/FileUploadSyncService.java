package com.adl.service.upload;

import android.text.TextUtils;

import com.adl.service.ConfigService;
import com.adl.service.common.BaseService;
import com.adl.service.common.IDefine;
import com.adl.service.exception.NzBaseException;
import com.adl.service.exception.NzNetworkException;
import com.adl.service.exception.NzUnknownException;
import com.adl.service.log.NzLog;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import java.io.File;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/4
 * Describe   : 同步文件上传
 */
public class FileUploadSyncService extends BaseService {

    private static FileUploadSyncService _instance;

    private final UploadManager uploadManager;

    private FileUploadSyncService() {
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
    }

    public static FileUploadSyncService instance() {
        if (_instance == null) {
            _instance = new FileUploadSyncService();
        }
        return _instance;
    }

    public String syncUploadFile(File file, String remoteFileName) throws NzBaseException {
        ConfigService configService = getConfigService();
        if (configService == null || TextUtils.isEmpty(configService.getQiNiuHost())) {
            throw new NzUnknownException("上传配置错误");
        }

        if (file != null && file.exists()) {
            if (TextUtils.isEmpty(remoteFileName)) {
                throw new NzUnknownException("远端文件名为空");
            }

            String token = getQiNiuToken(IDefine.QiNiuPublicBucket);
            String url = syncUploadFileToQiNiuYun(file, remoteFileName, token);
            if (url != null) {
                return url;
            } else {
                throw new NzUnknownException("上传失败");
            }
        } else {
            throw new NzUnknownException("文件路径不存在");
        }
    }

    private String syncUploadFileToQiNiuYun(File file, String name, String token) {
        try {
            ResponseInfo info = uploadManager.syncPut(file, name, token, new UploadOptions(null, null, true, null, null));
            if (info.isOK()) {
                String facePrefix = getConfigService().getFacePrefix();
                return facePrefix + info.response.getString("key");
            } else {
                NzLog.e("七牛上传: token: " + token + ", code: " + info.statusCode + ", error: " + info.error);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
