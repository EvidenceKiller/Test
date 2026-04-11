package com.adl.service.outer;

import com.adl.service.common.BaseService;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/4
 * Describe   : 文件上传
 * 1. 视频文件
 * 2. 图片文件
 * 3. 日志文件
 */
public class UploadCaller extends BaseService {

    private static UploadCaller _instance;

    private UploadCaller() {

    }

    public static UploadCaller instance() {
        if (_instance == null) {
            _instance = new UploadCaller();
        }
        return _instance;
    }


}
