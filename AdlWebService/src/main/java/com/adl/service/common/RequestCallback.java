package com.adl.service.common;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/1
 * Describe   : 请求回答
 */
public abstract class RequestCallback {

    private Context mContext;
    private String mHintText;
    private boolean mShowLoading;
    private boolean mShowError;
    private RequestDialog mDialog;

    public RequestCallback() {
        this(null);
    }

    public RequestCallback(Context context) {
        this.mContext = context;
        this.mHintText = "加载中...";
        setShowErrorText(true);
    }

    public RequestCallback showDialog(boolean show) {
        mShowLoading = show;
        return this;
    }

    public RequestCallback setShowErrorText(boolean show) {
        mShowError = show;
        return this;
    }

    public RequestCallback setLoadingText(String text) {
        mHintText = text;
        return this;
    }

    public void start() {
        try {
            if (mShowLoading && mDialog == null && (mContext instanceof Activity)) {
                mDialog = new RequestDialog(mContext);
                mDialog.showText(mHintText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void end() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
        mContext = null;
    }

    // 显示默认错误信息
    public final void showError(RequestResult result) {
        if (mShowError && result.getMsg() != null && mContext != null) {
            BaseService.postUI(() -> {
                Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
            });
        }
    }

    public void onError(RequestResult error) {
        // TODO 错误处理
    }

    public abstract void onResult(RequestResult result);
}
