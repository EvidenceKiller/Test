package com.adl.service.internal;


import com.adl.service.callback.RequestCallback;
import com.adl.service.exception.NzBaseException;
import com.adl.service.exception.NzEmptyDataException;
import com.adl.service.exception.NzEmptyResponseException;
import com.adl.service.exception.NzExceptionMapper;
import com.adl.service.exception.NzNetworkException;
import com.adl.service.exception.NzUnknownException;
import com.adl.service.web.response.BaseResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * RxJava 调度与 {@link BaseResponse} 处理封装（SDK 内部使用，不对外暴露）。
 * <p>
 * 包含：异步回调调度（{@link #schedule} / {@link #scheduleBaseResponse}），以及同步阻塞解包（{@link #blockingGetData} / {@link #requireSuccessData}）。
 * </p>
 */
public final class RxCallbackScheduler {

    private RxCallbackScheduler() {
    }

    /**
     * 将 {@code Single<BaseResponse<T>>} 转换为 {@code RequestCallback<T>}。
     * <p>
     * 规则：
     * <ul>
     *   <li>{@code base.isSuccess()==true} => {@code onSuccess(base.getData())}</li>
     *   <li>{@code base.isSuccess()==false} => {@code onFail(base.getCode())}</li>
     * </ul>
     * </p>
     */
    public static <T> Disposable scheduleBaseResponse(
            Single<BaseResponse<T>> single,
            RequestCallback<T> callback
    ) {
        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            if (callback == null) {
                                return;
                            }
                            if (response != null && response.getSucceed().booleanValue()) {
                                callback.onSuccess(response.getData());
                            } else if (response != null) {
                                callback.onFail(response.getCode(), response.getMsg());
                            } else {
                                callback.onError("Response is null");
                            }
                        },
                        error -> {
                            if (callback != null) {
                                callback.onError(NzExceptionMapper.toMessage(error));
                            }
                        }
                );
    }

    /**
     * 将 RxJava Single 转换为普通回调，自动处理线程切换和订阅管理
     *
     * @param single   RxJava Single 源
     * @param callback 业务回调接口
     * @return Disposable 订阅对象（可用于取消）
     */
    public static <T> Disposable schedule(
            Single<T> single,
            RequestCallback<T> callback
    ) {
        return single
                .subscribeOn(Schedulers.io())           // 在 IO 线程执行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程回调
                .subscribe(
                        data -> {
                            if (callback != null) {
                                // 统一约定：如果响应是 BaseResponse 且业务失败，则走 onFail。
                                if (data == null) {
                                    callback.onError("Response data is null");
                                } else {
                                    if (data instanceof BaseResponse) {
                                        BaseResponse<?> response = (BaseResponse<?>) data;
                                        if (!response.getSucceed().booleanValue()) {
                                            callback.onFail(response.getCode(), response.getMsg());
                                            return;
                                        }
                                    }
                                    callback.onSuccess(data);
                                }

                            }
                        },
                        error -> {
                            if (callback != null) {
                                // 统一异常映射，避免业务层到处散落 error.getMessage()。
                                callback.onError(NzExceptionMapper.toMessage(error));
                            }
                        }
                );
    }

    /**
     * 阻塞获取 Single，校验响应成功且 data 非空后返回 data；否则抛出 {@link NzNetworkException} 或 {@link NzUnknownException}；
     * 其它异常经 {@link NzExceptionMapper} 映射。
     */
    public static <T> T blockingGetData(Single<BaseResponse<T>> single) throws NzBaseException {
        try {
            return requireSuccessData(single.blockingGet());
        } catch (Throwable t) {
            throw NzExceptionMapper.map(t);
        }
    }

    /**
     * 对已有 {@link BaseResponse} 做成功与 data 校验，成功则返回 data。
     */
    public static <T> T requireSuccessData(BaseResponse<T> response) throws NzBaseException {
        if (response == null) {
            throw new NzEmptyResponseException("Response is null");
        }
        if (response.getCode() != 200 || !response.getSucceed().booleanValue()) {
            throw new NzNetworkException(response.getCode(), response.getMsg());
        }
        T data = response.getData();
        if (data == null) {
            throw new NzEmptyDataException("Response data is null");
        }
        return data;
    }
}
