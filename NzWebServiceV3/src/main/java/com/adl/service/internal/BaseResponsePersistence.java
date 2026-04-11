package com.adl.service.internal;

import com.adl.service.web.response.BaseResponse;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

/**
 * 对 {@code Single<BaseResponse<T>>} 在业务成功且含 data 时执行一次 Room 写入，再向下游传递原响应。
 * <p>
 * 各业务 Persistence 只需提供「如何从 data 得到 {@link Completable}」（无数据可写时返回 {@link Completable#complete()}）。
 * </p>
 */
public final class BaseResponsePersistence {

    private BaseResponsePersistence() {
    }

    /**
     * @param network          网络请求 Single
     * @param persistIfSuccess 仅当 {@code response.isSuccess() && response.getData()!=null} 时调用，对 {@code data} 执行插入；无需写入时返回 {@link Completable#complete()}
     */
    public static <T> Single<BaseResponse<T>> persistAfterFetch(
            Single<BaseResponse<T>> network,
            Function<T, Completable> persistIfSuccess) {
        return network.flatMap(response -> {
            if (!response.getSucceed().booleanValue() || response.getData() == null) {
                return Single.just(response);
            }
            T data = response.getData();
            return persistIfSuccess.apply(data).andThen(Single.just(response));
        });
    }
}
