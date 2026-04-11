package com.adl.service.common;

import com.adl.service.web.BaseHttpService;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/1
 * Describe   : 类描述
 */
public abstract class RequestWorker implements Runnable {

    protected RequestCallback mCallback;

    public RequestWorker(RequestCallback callback) {
        mCallback = callback;
    }

    public void begin() {
        if (mCallback != null) {
            mCallback.start();
        }
    }

    public void end() {
        if (mCallback != null) {
            mCallback.end();
        }
    }

    @Override
    public void run() {
        BaseHttpService.postUI(this::begin);
        try {
            RequestResult result = doWorking();
            if (result != null && mCallback != null) {
                if (result.isOk()) {
                    BaseHttpService.postUI(() -> mCallback.onResult(result));
                } else {
                    mCallback.showError(result);
                    BaseHttpService.postUI(() -> mCallback.onError(result));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (mCallback != null) {
                BaseHttpService.postUI(() -> mCallback.onError(RequestResult.error(e.toString())));
            }
        }

        BaseHttpService.postUI(this::end);
    }

    public abstract RequestResult doWorking() throws Exception;
}
