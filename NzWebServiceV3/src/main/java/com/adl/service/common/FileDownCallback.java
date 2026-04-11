package com.adl.service.common;

import java.io.File;

public interface FileDownCallback {

    void onProgress(long total, long read);

    void onEnd(File file);

    void onError(String error);
}
