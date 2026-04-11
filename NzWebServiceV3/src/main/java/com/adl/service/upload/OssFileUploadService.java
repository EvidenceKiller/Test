package com.adl.service.upload;

import com.adl.base.log.AdlLogger;
import com.adl.service.AdlService;
import com.adl.service.common.BaseService;
import com.adl.service.common.OssType;
import com.adl.service.log.NzLog;
import com.ssp.oss.sdk.callback.OssSwitchCallback;
import com.ssp.oss.sdk.callback.UploadCallback;
import com.ssp.oss.sdk.core.OssClient;
import com.ssp.oss.sdk.core.OssClientConfig;
import com.ssp.oss.sdk.model.ErrorCode;
import com.ssp.oss.sdk.model.OssConfig;
import com.ssp.oss.sdk.model.OssConfigList;
import com.ssp.oss.sdk.model.SyncUploadResult;
import com.ssp.oss.sdk.model.UploadRequest;

import java.io.File;

/**
 * Author     : HB
 * Date       : 2026/1/27
 * Describe   : OSS方式上传文件，后端平台提供的文件上传服务，可设置上传至七牛或minio
 * 1. 视频文件
 * 2. 图片上传
 */
public class OssFileUploadService extends BaseService {
    private static OssFileUploadService _instance;

    private OssFileUploadService() {
        initOssClient();
    }

    private void initOssClient() {
        String token = AdlService.getService().getDeviceToken();
        AdlLogger.d("init Oss token: "+token);
        OssClientConfig config = OssClientConfig.builder()
                // 必填:OSS配置获取接口
                .configApiUrl(AdlService.getService().getOssConfigHost())

                // 必填:临时凭证获取接口
                .tokenApiUrl(AdlService.getService().getOssTokenHost())

                // 必填:Authorization认证头(用于config和token两个接口)
                .addApiHeader("Authorization", "Bearer " + token)

                // 可选:最大并发上传数(默认1)
                .maxConcurrentTasks(3)

                // 可选:连接超时时间(默认30秒)
                .connectionTimeout(30000)

                // 可选:Socket超时时间(默认30秒)
                .socketTimeout(30000)

                // 可选:调试模式(打印详细日志)
                .debugMode(true)

                .build();

        boolean isInit = OssClient.init(AdlService.getService().getContext(), config);
        AdlLogger.d("OssClient 初始化" + (isInit ? "成功" : "失败"));
    }

    public String getPrefix() {
        OssConfigList ossConfigList = getOssConfigList();
        if (ossConfigList == null) {
            NzLog.e("未找到对应的OSS配置");
            return "";
        }
        OssConfig config = ossConfigList.findConfig(OssClient.getCurrentOssType());
        if (config != null) {
            NzLog.d("该配置下的前缀：" + config.getCdnBaseUrl());
            return config.getCdnBaseUrl();
        }
        return "";
    }

    /**
     * @param localFilePath
     * @param uploadFilePrefix
     * @param uploadFileName
     * @param callback
     * @return requestId, 用于取消上传
     */
    public String uploadFile(String localFilePath, String uploadFilePrefix, String uploadFileName, UploadCallback callback) {
        File file = new File(localFilePath);
        UploadRequest request = UploadRequest.builder(file)
                .pathPrefix(uploadFilePrefix)  // 必需：存储路径前缀
                .fileName(uploadFileName)     // 必需：自定义文件名(可选)
                .build();
        //ossClient未初始化成功或已销毁，重新初始化
        if (OssClient.getInstance() == null) {
            initOssClient();
            return null;
        }
        String requestId = OssClient.getInstance().uploadFile(request, callback);
        return requestId;
    }

    /**
     * 同步上传文件
     *
     * @param localFilePath
     * @param uploadFilePrefix
     * @param uploadFileName
     * @return url
     */
    public SyncUploadResult syncUploadFile(String localFilePath, String uploadFilePrefix, String uploadFileName) {
        File file = new File(localFilePath);
        UploadRequest request = UploadRequest.builder(file)
                .pathPrefix(uploadFilePrefix)  // 必需：存储路径前缀
                .fileName(uploadFileName)     // 必需：自定义文件名(可选)
                .build();
        if (OssClient.getInstance() == null) {
            initOssClient();
            return SyncUploadResult.failure(ErrorCode.OSS_ERROR, "OSSClient未初始化成功或已销毁", 0);
        }
        return OssClient.getInstance().uploadFileSync(request);
    }

    public void cancelUpload(String requestId) {
        if (OssClient.getInstance() == null) {
            initOssClient();
            return;
        }
        OssClient.getInstance().cancelUpload(requestId);
    }

    /**
     * 获取可用的OSS配置列表
     *
     * @return
     */
    public OssConfigList getOssConfigList() {
        return OssClient.getAvailableConfigs();
    }

    /**
     * 获取当前使用的OSS
     *
     * @return
     */
    public String getCurrentOssType() {
        if (OssClient.getInstance() == null) {
            initOssClient();
            return null;
        }
        return OssClient.getInstance().getCurrentOssType();
    }

    /**
     * 切换OSS
     * minio,qiniu
     *
     * @param ossType
     */
    public void switchOssSync(OssType ossType) {
        // 切换到指定的OSS(例如从七牛云切换到MinIO)
        try {
            OssConfig ossConfig = getOssConfigList().findConfig(ossType.value());
            if (ossConfig == null) {
                NzLog.e("未找到对应的OSS配置：" + ossType.value());
                return;
            }
            // 切换OSS(会自动获取新的凭证)
            OssClient.switchOss(ossType.value());
            NzLog.d("已切换到 " + ossType.value());
        } catch (Exception e) {
            e.printStackTrace();
            NzLog.e("切换失败：" + e.getMessage());
        }
    }

    /**
     * 异步切换OSS
     * minio,qiniu
     *
     * @param ossType
     * @param callback
     */
    public void switchOssAsync(OssType ossType, OssSwitchCallback callback) {
        OssConfig ossConfig = getOssConfigList().findConfig(ossType.value());
        if (ossConfig == null) {
            callback.onError(ossType.value(), "未找到对应的OSS配置：" + ossType.value(), null);
            NzLog.e("未找到对应的OSS配置：" + ossType.value());
            return;
        }
        OssClient.switchOssAsync(ossType.value(), callback);
    }

    public static OssFileUploadService instance() {
        if (_instance == null) {
            _instance = new OssFileUploadService();
        }
        return _instance;
    }


    public void release() {
        if (OssClient.getInstance() != null) {
            OssClient.getInstance().destroy();
        }
    }

}
