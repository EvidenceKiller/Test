package com.adl.service.persistence;

import android.util.Log;

import com.adl.service.exception.NzNetworkException;
import com.adl.service.data.BaseResponse;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

/**
 * 对 {@code Single<BaseResponse<T>>} 在业务成功且含 data 时执行一次 Room 写入，再向下游传递原响应。
 * <p>
 * 各业务 Persistence 只需提供「如何从 data 得到 {@link Completable}」（无数据可写时返回 {@link Completable#complete()}）。
 * </p>
 */
public final class BasePersistence {

    private BasePersistence() {
    }

    public static <T> Single<BaseResponse<T>> persistAsSingle(
            Function<Void, Single<BaseResponse<T>>> requestFunc,
            Function<T, Completable> persistFunc) {
        return persistAsSingle(createBaseEmptyPreparer(), requestFunc, persistFunc);
    }

    /**
     * @param prepareFunc  最开始执行的函数，通常为清理数据库
     * @param requestFunc 网络请求函数
     * @param persistFunc 仅当 {@code response.isSuccess() && response.getData()!=null} 时调用，对 {@code data} 执行插入；无需写入时返回 {@link Completable#complete()}
     */
    public static <T> Single<BaseResponse<T>> persistAsSingle(
            Function<Void, Completable> prepareFunc,
            Function<Void, Single<BaseResponse<T>>> requestFunc,
            Function<T, Completable> persistFunc) {
        Log.i("ZXN_TEST", "BasePersistence : persistAfterFetch");
        return Completable.defer(() -> prepareFunc.apply(null))
                .andThen(Single.defer(() -> requestFunc.apply(null)))
                .flatMap(response -> {
                    if (!response.getSucceed() || response.getData() == null) {
                        return Single.error(new NzNetworkException(response.getCode(), response.getMsg()));
                    }
                    T data = response.getData();
                    return persistFunc.apply(data).andThen(Single.just(response));
                });
    }

    public static Function<Void, Completable> createBaseEmptyPreparer() {
        return (v) -> Completable.complete();
    }

    public static <Q, T> Function<Void, Single<BaseResponse<T>>> createBaseRequester(
            Q request, Function<Q, Single<BaseResponse<T>>> function) {
        return (v) -> function.apply(request);
    }
}
