package com.adl.service.callback;

/**
 * 通用请求回调接口（SDK 对外暴露，app 模块使用）
 * <p>
 * 用于替代 RxJava，提供简单的异步回调机制
 * </p>
 *
 * @param <T> 响应数据类型
 */
public interface RequestCallback<T> {
    
    /**
     * 请求成功时的回调
     *
     * @param data 响应数据
     */
    void onSuccess(T data);
    
    /**
     * 请求失败时的回调
     *
     * @param error 错误信息
     */
    void onError(String error);

    /**
     * 业务失败回调（当响应是 {@code BaseResponse} 且 {@code isSuccess()==false} 时触发）。
     *
     * @param code 业务错误码
     */
    void onFail(int code, String msg);
}
