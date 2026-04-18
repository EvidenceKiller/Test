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

    public static <R> Single<R> repositoryAsSingle(Function<Void, R> netFunc) {
        return Single.just(applyFunc(netFunc, null));
    }

    public static <R> Single<R> repositoryAsSingle(Function<Void, R> netFunc,
                                                   Function<R, R> persistFunc) {
        return Single.fromCallable(() -> applyFunc(netFunc, null))
                .flatMap(networkData -> {
                    if (networkData == null) {
                        return Single.error(new NzNetworkException(-1, "network data is null"));
                    }
                    return Single.just(applyFunc(persistFunc, networkData));
                });
    }

    public static <R> Single<R> repositoryAsSingle(Function<Void, R> localFunc,
                                                   Function<Void, R> netFunc,
                                                   Function<R, R> persistFunc) {
        Log.i("ZXN_TEST", "BaseRepository : repositoryAsSingle");
        return Single.fromCallable(() -> applyFunc(localFunc))
                .flatMap(localData -> {
                    if (localData != null) {
                        if (localData instanceof List && !((List) localData).isEmpty()) {
                            return Single.just(localData);
                        }
                        return Single.just(localData);
                    }
                    return Single.fromCallable(() -> applyFunc(netFunc))
                            .flatMap(networkData -> {
                                if (networkData == null) {
                                    return Single.error(new NzNetworkException(-1, "network data is null"));
                                }
                                return Single.just(applyFunc(persistFunc, networkData));
                            });
                });
    }

    public static <Q extends BaseRequest, R> Single<R> repositoryAsSingle(Q request, Function<Q, R> netFunc) {
        Log.i("ZXN_TEST", "BaseRepository : repositoryAsSingle");
        return Single.just(applyFunc(netFunc, request));
    }

    public static <Q extends BaseRequest, R> Single<R> repositoryAsSingle(Q request,
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

    public static <Q extends BaseRequest, R> Single<R> repositoryAsSingle(Q request,
                                                                          Function<Q, R> localFunc,
                                                                          Function<Q, R> netFunc,
                                                                          Function<R, R> persistFunc) {
        Log.i("ZXN_TEST", "BaseRepository : repositoryAsSingle");
        return Single.fromCallable(() -> applyFunc(localFunc, request))
                .flatMap(localData -> {
                    if (localData != null) {
                        if (localData instanceof List && !((List) localData).isEmpty()) {
                            return Single.just(localData);
                        }
                        return Single.just(localData);
                    }
                    return Single.fromCallable(() -> applyFunc(netFunc, request))
                            .flatMap(networkData -> {
                                if (networkData == null) {
                                    return Single.error(new NzNetworkException(-1, "network data is null"));
                                }
                                return Single.just(applyFunc(persistFunc, networkData));
                            });
                });
    }

    public static <Q extends BaseRequest, R> Single<GetPageResult> repositoryListsAsSingle(Q request,
                                                                                           Function<Q, Void> localFunc,
                                                                                           Function<Long, List<R>> netFunc,
                                                                                           Function<List<R>, List<R>> persistFunc) {
        Log.i("ZXN_TEST", "BasePagePersistence : repositoryListsAsSingle");
        final String uuId = UUID.randomUUID().toString();
        return Completable.fromCallable(() -> applyFunc(localFunc, request))
                .andThen(Observable.<Long, Long>generate(() -> 1L, (current, emitter) -> {
                            emitter.onNext(current);
                            return current + 1L;
                        })
                        .concatMapSingle(currentPage ->
                                Single.fromCallable(() -> {
                                    List<R> pageList = applyFunc(netFunc, currentPage);
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
                        .collect(GetPageResult::new, (lastResult, currentResult) -> accumulatePageResult(uuId, lastResult, currentResult)))
                .onErrorResumeNext(throwable -> Single.error(throwable));
    }

    public static <Q extends BaseRequest, R> Single<GetPageResult> repositoryPagesAsSingle(Q request,
                                                                                           Function<Q, Void> localFunc,
                                                                                           Function<Long, BasePageData<R>> netFunc,
                                                                                           Function<List<R>, List<R>> persistFunc) {
        Log.i("ZXN_TEST", "BaseRepository : repositoryPagesAsSingle");
        return Completable.fromCallable(() -> applyFunc(localFunc, request))
                .andThen(Single.fromCallable(() -> applyFunc(netFunc, 1L)))
                .flatMap(firstPage -> {
                    final String uuId = UUID.randomUUID().toString();
                    if (firstPage == null || firstPage.getRecords() == null || firstPage.getRecords().isEmpty()) {
                        return Single.error(new NzGetPageException(createFailedPageResult(uuId, "request page 1 is empty")));
                    }
                    List<R> firstFetchedList = firstPage.getRecords();
                    List<R> firstPersistedList = persistFunc.apply(firstFetchedList);

                    GetPageResult firstResult = new GetPageResult();
                    firstResult.setUuId(uuId);
                    firstResult.setSuccess(true);
                    firstResult.setMessage("success");
                    firstResult.setServerCount(firstPage.getTotal() == null ? 0L : firstPage.getTotal());
                    firstResult.setTotalPages(firstPage.getPages() == null ? 0L : firstPage.getPages());
                    firstResult.setSuccessedPages(1L);
                    firstResult.setFetchedCount(firstFetchedList == null ? 0 : firstFetchedList.size());
                    firstResult.setPersistedCount(firstPersistedList == null ? 0 : firstPersistedList.size());

                    if (firstResult.getTotalPages() <= 1L) {
                        return Single.just(firstResult);
                    }
                    return persistAndAccumulateRemainingPages(netFunc, persistFunc, firstResult, 2L, firstResult.getTotalPages() - 1L);
                })
                .onErrorResumeNext(throwable ->
                        Single.error(new NzGetPageException(createFailedPageResult(UUID.randomUUID().toString(), throwable.getMessage()), throwable)));
    }

    private static <R> Single<GetPageResult> persistAndAccumulateRemainingPages(
            Function<Long, BasePageData<R>> netFunc,
            Function<List<R>, List<R>> persistFunc,
            GetPageResult firstResult,
            Long startPage,
            Long pageCount) {
        return Observable.rangeLong(startPage, pageCount)
                .concatMapSingle(currentPage -> Single.fromCallable(() -> {
                    BasePageData<R> page = applyFunc(netFunc, currentPage);
                    // 检查数据有效性
                    if (page == null || page.getRecords() == null || page.getRecords().isEmpty()) {
                        throw new NzGetPageException(createFailedPageResult(firstResult.getUuId(), "request page " + currentPage + " is empty"));
                    }
                    // 检查分页一致性
                    if (page.getCurrent() != currentPage || page.getPages() != firstResult.getTotalPages() || page.getTotal() != firstResult.getServerCount()) {
                        throw new NzGetPageException(createFailedPageResult(firstResult.getUuId(), "request current page " + currentPage + " does not match the pagination data on the server side "));
                    }
                    List<R> fetchedList = page.getRecords();

                    List<R> persistedList = applyFunc(persistFunc, fetchedList);
                    long fetchedCount = fetchedList == null ? 0 : fetchedList.size();
                    long persistedCount = persistedList == null ? 0 : persistedList.size();
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
}
