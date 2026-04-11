package com.adl.service.upload;

import java.io.File;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 类描述
 */
public interface FileUploadCallback {

    void onStart(File file);

    void onComplete(File file, String url);

}
