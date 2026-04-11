package com.adl.service.internal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * RxJava 线程调度工具（SDK 内部使用）
 * - 订阅在 IO 线程执行网络请求
 * - 观察在主线程更新 UI
 */
public final class RxSchedulers {

    private RxSchedulers() {}

    /** Observable 调度：io → main */
    public static <T> ObservableTransformer<T, T> io2Main() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /** Single 调度：io → main */
    public static <T> SingleTransformer<T, T> singleIo2Main() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /** IO 线程（纯后台，不切主线程，用于同步阻塞场景） */
    public static <T> SingleTransformer<T, T> singleIo() {
        return upstream -> upstream.subscribeOn(Schedulers.io());
    }
}
