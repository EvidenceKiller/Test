package com.adl.service.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.rxjava3.disposables.Disposable;


/**
 * 订阅管理器（SDK 内部使用）
 * <p>
 * 按作用域（通常为 Activity / Fragment 实例）分组，仅在对应界面销毁时取消该组请求，
 * 不影响其他界面发起的请求。
 * </p>
 */
public class SubscriptionManager {

    private static volatile SubscriptionManager INSTANCE;
    private final ConcurrentHashMap<Object, CopyOnWriteArrayList<Disposable>> byScope;

    private SubscriptionManager() {
        byScope = new ConcurrentHashMap<>();
    }

    public static SubscriptionManager getInstance() {
        if (INSTANCE == null) {
            synchronized (SubscriptionManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SubscriptionManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 将订阅归入指定作用域（例如当前 Activity / Fragment）
     */
    public void add(Object scope, Disposable disposable) {
        if (scope == null || disposable == null) {
            return;
        }
        byScope.computeIfAbsent(scope, k -> new CopyOnWriteArrayList<>()).add(disposable);
    }

    /**
     * 取消该作用域下的全部订阅（在对应 Activity/Fragment {@code onDestroy} 中调用）
     */
    public void cancelScope(Object scope) {
        if (scope == null) {
            return;
        }
        CopyOnWriteArrayList<Disposable> list = byScope.remove(scope);
        if (list == null) {
            return;
        }
        for (Disposable disposable : list) {
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
        }
        list.clear();
    }

    /**
     * 取消指定订阅，并从所属作用域列表中移除
     */
    public void cancel(Disposable disposable) {
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        disposable.dispose();
        for (Iterator<Map.Entry<Object, CopyOnWriteArrayList<Disposable>>> it =
             byScope.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Object, CopyOnWriteArrayList<Disposable>> e = it.next();
            e.getValue().remove(disposable);
            if (e.getValue().isEmpty()) {
                it.remove();
            }
        }
    }

    /**
     * 取消所有作用域下的全部订阅（例如退出登录、进程级清理等少数场景）
     */
    public void cancelAll() {
        for (Object scope : new ArrayList<>(byScope.keySet())) {
            cancelScope(scope);
        }
    }
}
