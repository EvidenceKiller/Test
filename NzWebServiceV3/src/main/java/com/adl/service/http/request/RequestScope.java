package com.adl.service.http.request;

import android.app.Activity;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * 异步请求作用域，仅允许由 {@link Activity} 或 {@link Fragment} 创建，
 * 用于绑定请求生命周期并在界面销毁时取消对应订阅。
 */
public final class RequestScope {

    private final Object owner;

    private RequestScope(@NonNull Object owner) {
        this.owner = Objects.requireNonNull(owner);
    }

    /**
     * 以当前 {@link Activity} 为作用域（通常为 {@code Activity.this}）。
     */
    @NonNull
    public static RequestScope of(@NonNull Activity activity) {
        return new RequestScope(activity);
    }

    /**
     * 以当前 {@link Fragment} 为作用域（通常为 {@code Fragment.this}）。
     */
    @NonNull
    public static RequestScope of(@NonNull Fragment fragment) {
        return new RequestScope(fragment);
    }

    /**
     * 供 SDK 内部作为订阅分组键使用（与 {@link #of(Activity)} / {@link #of(Fragment)} 传入的实例一致）。
     */
    @NonNull
    public Object owner() {
        return owner;
    }
}
