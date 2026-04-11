package com.adl.service.upload;

import com.adl.service.common.IDefine;

import java.io.File;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/8
 * Describe   : 类描述
 */
public class UploadFileTask {

    public String orgId;
    public File file;
    public String remoteFileName;
    public String bucket = IDefine.QiNiuPublicBucket;

    public UploadFileTask(File f) {
        this.file = f;
    }

    public UploadFileTask(File f, String fileName) {
        this.file = f;
        this.remoteFileName = fileName;
    }

    public void buildSmartScreenName(String sn, String name) {
        this.remoteFileName = IDefine.QiNiuRemovePathSmartScreen + sn + "/" + name;
    }

    public void buildPadName(String name) {
        this.remoteFileName = IDefine.QiNiuRemovePathPad + name;
    }
}
