package com.adl.service.common;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 类描述
 */
public interface FileListDownCallback {

    void onStart();

    void onProgress(int total, int downSuccess, int downFail);

    void onDownSuccess(FileDownManager.FileDownTask task);

    void onComplete();

}
