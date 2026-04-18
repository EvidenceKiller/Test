package com.adl.service.local;

import android.util.Log;

import com.adl.service.data.BaseResponse;
import com.adl.service.exception.NzEmptyResponseException;
import com.adl.service.exception.NzNetworkException;
import com.adl.service.http.request.BaseRequest;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

/**
 * 对 {@code Single<BaseResponse<T>>} 在业务成功且含 data 时执行一次 Room 写入，再向下游传递原响应。
 * <p>
 * 各业务 Persistence 只需提供「如何从 data 得到 {@link Completable}」（无数据可写时返回 {@link Completable#complete()}）。
 * </p>
 */
class BaseLocalUtil {

    public static <T> T createEmptyLocalFunc(BaseResponse<T> response) throws NzNetworkException, NzEmptyResponseException {
        if (response == null) {
            throw new NzEmptyResponseException("response is null");
        }
        if (!response.getSucceed().booleanValue() || response.getCode() != 200) {
            throw new NzNetworkException(response.getCode(), response.getMsg());
        }
        return response.getData();
    }

    public static Function<Void, Completable> createEmptyLocalFunc() {
        return (v) -> Completable.complete();
    }
}
