package com.adl.service.repository;

import android.util.Log;

import com.adl.service.callback.GetPageResult;
import com.adl.service.data.BasePageData;
import com.adl.service.exception.NzGetPageException;
import com.adl.service.exception.NzNetworkException;
import com.adl.service.http.request.BaseRequest;

import java.util.List;
import java.util.UUID;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

/**
 * 对 {@code Single<BaseResponse<T>>} 在业务成功且含 data 时执行一次 Room 写入，再向下游传递原响应。
 * <p>
 * 各业务 Persistence 只需提供「如何从 data 得到 {@link Completable}」（无数据可写时返回 {@link Completable#complete()}）。
 * </p>
 */
class BaseRepositoryUtil {
    public static <R> Single<R> repositoryAsSingle(Function<Void, R> func) {
        Log.i("ZXN_TEST", "BaseRepository : repositoryAsSingle");
        return Single.just(applyFunc(func));
    }

    public static <Q, R> Single<R> repositoryAsSingle(Q request, Function<Q, R> func) {
        Log.i("ZXN_TEST", "BaseRepository : repositoryAsSingle");
        return Single.just(applyFunc(func, request));
    }

    public static <Q, R> Single<R> repositoryAsSingle(Q request,
                                                      Function<Q, R> netFunc,
                                                      Function<R, R> persistFunc) {
        Log.i("ZXN_TEST", "BaseRepository : repositoryAsSingle");
        return Single.fromCallable(() -> applyFunc(netFunc, request))
                .flatMap(networkData -> {
                    if (networkData == null) {
                        return Single.error(new NzNetworkException(-1, "network data is null"));
                    }
                    return Single.just(applyFunc(persistFunc, networkData));
                });
    }

    public static <Q, R> Single<R> repositoryAsSingle(Q request,
                                                      Function<Q, R> prepareFunc,
                                                      Function<Q, R> fetchFunc,
                                                      Function<R, R> persistFunc) {
        Log.i("ZXN_TEST", "BaseRepository : repositoryAsSingle");
        return Single.fromCallable(() -> applyFunc(prepareFunc, request))
                .flatMap(localData -> {
                    if (localData != null) {
                        return Single.just(localData);
                    }
                    return Single.fromCallable(() -> applyFunc(fetchFunc, request))
                            .flatMap(networkData -> {
                                if (networkData == null) {
                                    return Single.error(new NzNetworkException(-1, "network data is null"));
                                }
                                return Single.just(applyFunc(persistFunc, networkData));
                            });
                });
    }

    public static <Q, R> Single<GetPageResult> repositoryListsAsSingle(Q request,
                                                                       Function<Long, List<R>> fetchFunc,
                                                                       Function<List<R>, List<R>> persistFunc) {
        return repositoryListsAsSingle(request, createEmptyPrepareFunc(), fetchFunc, persistFunc);
    }

    public static <Q, R> Single<GetPageResult> repositoryListsAsSingle(Q request,
                                                                       Function<Q, Void> prepareFunc,
                                                                       Function<Long, List<R>> fetchFunc,
                                                                       Function<List<R>, List<R>> persistFunc) {
        Log.i("ZXN_TEST", "BasePagePersistence : repositoryListsAsSingle");
        return Completable.fromCallable(() -> applyFunc(prepareFunc, request))
                .andThen(Observable.<Long, Long>generate(() -> 1L, (current, emitter) -> {
                            emitter.onNext(current);
                            return current + 1L;
                        })
                        .concatMapSingle(currentPage ->
                                Single.fromCallable(() -> {
                                    List<R> pageList = applyFunc(fetchFunc, currentPage);
                                    GetPageResult pageResult = new GetPageResult();
                                    pageResult.setTotalPages(currentPage);
                                    if (pageList == null || pageList.isEmpty()) {
                                        return createCurrentPageResult(pageResult, 0L, 0L);
                                    }
                                    List<R> persistedList = applyFunc(persistFunc, pageList);
                                    long fetchedCount = pageList == null ? 0 : pageList.size();
                                    long persistedCount = persistedList == null ? 0 : persistedList.size();
                                    return createCurrentPageResult(pageResult, fetchedCount, persistedCount);
                                }))
                        .takeWhile(stat -> stat.getFetchedCount() > 0)
                        .collect(GetPageResult::new, (lastResult, currentResult) -> accumulatePageResult(UUID.randomUUID().toString(), lastResult, currentResult)))
                .onErrorResumeNext(throwable -> Single.error(throwable));
    }

    public static <Q, R> Single<GetPageResult> repositoryPagesAsSingle(Q request,
                                                                       Function<Long, BasePageData<R>> fetchFunc,
                                                                       Function<BasePageData<R>, BasePageData<R>> persistFunc) {
        return repositoryPagesAsSingle(request, createEmptyPrepareFunc(), fetchFunc, persistFunc);
    }

    public static <Q, R> Single<GetPageResult> repositoryPagesAsSingle(Q request,
                                                                       Function<Q, Void> prepareFunc,
                                                                       Function<Long, BasePageData<R>> fetchFunc,
                                                                       Function<BasePageData<R>, BasePageData<R>> persistFunc) {
        Log.i("ZXN_TEST", "BaseRepository : repositoryPagesAsSingle");
        return Completable.fromCallable(() -> applyFunc(prepareFunc, request))
                .andThen(Single.fromCallable(() -> applyFunc(fetchFunc, 1L)))
                .flatMap(firstPageData -> {
                    final String uuId = UUID.randomUUID().toString();
                    if (firstPageData == null || firstPageData.getRecords() == null || firstPageData.getRecords().isEmpty()) {
                        return Single.error(new NzGetPageException(createFailedPageResult(uuId, "request page 1 is empty")));
                    }
                    BasePageData<R> firstPersistedPageData = persistFunc.apply(firstPageData);

                    GetPageResult firstResult = new GetPageResult();
                    firstResult.setUuId(uuId);
                    firstResult.setSuccess(true);
                    firstResult.setMessage("success");
                    firstResult.setServerCount(firstPageData.getTotal() == null ? 0L : firstPageData.getTotal());
                    firstResult.setTotalPages(firstPageData.getPages() == null ? 0L : firstPageData.getPages());
                    firstResult.setSuccessedPages(1L);
                    firstResult.setFetchedCount(firstPageData.getRecords() == null ? 0 : firstPageData.getRecords().size());
                    firstResult.setPersistedCount(firstPersistedPageData.getRecords() == null ? 0 : firstPersistedPageData.getRecords().size());

                    if (firstResult.getTotalPages() <= 1L) {
                        return Single.just(firstResult);
                    }
                    return persistAndAccumulateRemainingPages(fetchFunc, persistFunc, firstResult, 2L, firstResult.getTotalPages() - 1L);
                })
                .onErrorResumeNext(throwable ->
                        Single.error(new NzGetPageException(createFailedPageResult(UUID.randomUUID().toString(), throwable.getMessage()), throwable)));
    }

    private static <R> Single<GetPageResult> persistAndAccumulateRemainingPages(
            Function<Long, BasePageData<R>> fetchFunc,
            Function<BasePageData<R>, BasePageData<R>> persistFunc,
            GetPageResult firstResult,
            Long startPage,
            Long pageCount) {
        return Observable.rangeLong(startPage, pageCount)
                .concatMapSingle(currentPage -> Single.fromCallable(() -> {
                    BasePageData<R> pageData = applyFunc(fetchFunc, currentPage);
                    // 检查数据有效性
                    if (pageData == null || pageData.getRecords() == null || pageData.getRecords().isEmpty()) {
                        throw new NzGetPageException(createFailedPageResult(firstResult.getUuId(), "request page " + currentPage + " is empty"));
                    }
                    // 检查分页一致性
                    if (pageData.getCurrent() != currentPage || pageData.getPages() != firstResult.getTotalPages() || pageData.getTotal() != firstResult.getServerCount()) {
                        throw new NzGetPageException(createFailedPageResult(firstResult.getUuId(), "request current page " + currentPage + " does not match the pagination data on the server side "));
                    }
                    BasePageData<R> persistedPageData = applyFunc(persistFunc, pageData);
                    long fetchedCount = pageData.getRecords() == null ? 0 : pageData.getRecords().size();
                    long persistedCount = persistedPageData.getRecords() == null ? 0 : persistedPageData.getRecords().size();
                    return createCurrentPageResult(firstResult, fetchedCount, persistedCount);
                }))
                .reduce(firstResult, (lastResult, currentResult) -> accumulatePageResult(firstResult.getUuId(), lastResult, currentResult));
    }

    private static GetPageResult createCurrentPageResult(GetPageResult firstPageResult, long fetchedCount, long persistedCount) {
        GetPageResult thisResult = new GetPageResult();
        thisResult.setSuccess(true);
        thisResult.setMessage("success");
        thisResult.setServerCount(firstPageResult.getServerCount());
        thisResult.setTotalPages(firstPageResult.getTotalPages());
        thisResult.setFetchedCount(fetchedCount);
        thisResult.setPersistedCount(persistedCount);
        return thisResult;
    }

    private static GetPageResult createFailedPageResult(String uuId, String message) {
        GetPageResult result = new GetPageResult();
        result.setUuId(uuId);
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    private static GetPageResult accumulatePageResult(String uuId, GetPageResult lastResult, GetPageResult currentResult) throws NzGetPageException {
        if (!currentResult.isSuccess()) {
            throw new NzGetPageException(createFailedPageResult(currentResult.getUuId(), "accumulate page meet some error"));
        }
        lastResult.setUuId(uuId);
        lastResult.setSuccess(true);
        lastResult.setMessage(currentResult.getMessage());
        if (currentResult.getFetchedCount() > 0L) {
            lastResult.setSuccessedPages(lastResult.getSuccessedPages() + 1L);
            lastResult.setFetchedCount(lastResult.getFetchedCount() + currentResult.getFetchedCount());
            lastResult.setPersistedCount(lastResult.getPersistedCount() + currentResult.getPersistedCount());
        }
        lastResult.setTotalPages(Math.max(lastResult.getTotalPages(), currentResult.getTotalPages()));
        return lastResult;
    }

    private static <Q, R> R applyFunc(Function<Q, R> func, Q q) {
        try {
            return func.apply(q);
        } catch (Throwable throwable) {
            if (throwable instanceof RuntimeException) {
                throw (RuntimeException) throwable;
            }
            throw new RuntimeException(throwable);
        }
    }

    private static <R> R applyFunc(Function<Void, R> func) {
        return applyFunc(func, null);
    }

    private static <Q> Function<Q, Void> createEmptyPrepareFunc() {
        return (q) -> {return null;};
    }
}
