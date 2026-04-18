package com.adl.service.persistence;

import android.util.Log;

import com.adl.service.callback.GetPageResult;
import com.adl.service.data.BasePageData;
import com.adl.service.data.BaseResponse;
import com.adl.service.exception.NzEmptyDataException;
import com.adl.service.exception.NzGetPageException;

import java.util.List;
import java.util.UUID;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

/**
 * 网络成功后按需写入本地，再向下游传递原响应。
 * 具体落库逻辑由调用方通过 {@code persistIfSuccess} 注入。
 */
public final class BasePagePersistence {

    private BasePagePersistence() {
    }

    public static <T> Single<BaseResponse<BasePageData<T>>> persistOnePageAsSingle(
            Function<Void, Single<BaseResponse<BasePageData<T>>>> requestFunc,
            Function<List<T>, List<T>> persistFunc) {
        return persistOnePageAsSingle(BasePersistence.createBaseEmptyPreparer(), requestFunc, persistFunc);
    }

    /**
     * 网络 {@link Single} 成功后，若业务成功且含列表数据则插入本地库，再向下游传递原响应。
     */
    public static <T> Single<BaseResponse<BasePageData<T>>> persistOnePageAsSingle(
            Function<Void, Completable> prepareFunc,
            Function<Void, Single<BaseResponse<BasePageData<T>>>> requestFunc,
            Function<List<T>, List<T>> persistFunc) {
        Log.i("ZXN_TEST", "BasePagePersistence : persistOnePageAsSingle");
        return BasePersistence.persistAsSingle(prepareFunc, requestFunc, data -> {
            if (data == null || data.getSize() == 0) {
                return Completable.error(new NzEmptyDataException("request data is null before persist"));
            }
            if (data.getRecords() == null || data.getRecords().isEmpty()) {
                return Completable.error(new NzEmptyDataException("request page data is null before persist"));
            }
            return Completable.fromAction(() -> persistFunc.apply(data.getRecords()));
        });
    }

    public static <T> Single<BaseResponse<List<T>>> persistOneListAsSingle(
            Function<Void, Single<BaseResponse<List<T>>>> requestFunc,
            Function<List<T>, List<T>> persistFunc) {
        return persistOneListAsSingle(BasePersistence.createBaseEmptyPreparer(), requestFunc, persistFunc);
    }

    public static <T> Single<BaseResponse<List<T>>> persistOneListAsSingle(
            Function<Void, Completable> prepareFunc,
            Function<Void, Single<BaseResponse<List<T>>>> requestFunc,
            Function<List<T>, List<T>> persistFunc) {
        Log.i("ZXN_TEST", "BasePagePersistence : persistOneListAsSingle");
        return BasePersistence.persistAsSingle(prepareFunc, requestFunc, data -> {
            if (data == null || data.size() == 0) {
                return Completable.error(new NzEmptyDataException("request data is null before persist"));
            }
            return Completable.fromAction(() -> persistFunc.apply(data));
        });
    }

    public static <T> Single<GetPageResult> persistAllListsAsSingle(
            Function<Long, Single<BaseResponse<List<T>>>> pageRequestFunc,
            Function<List<T>, List<T>> persistFunc) {
        return persistAllListsAsSingle(BasePersistence.createBaseEmptyPreparer(), pageRequestFunc, persistFunc);
    }

    /**
     * 按分页循环请求直至拉完全部数据：每一页成功后写入本地，并汇总所有数据。
     * <p>
     * 后端返回分页数据有两种方式，这个函数处理返回List的方式
     * 始终从第 1 页开始请求，直到拉取完所有页。
     * </p>
     *
     * @param pageRequestFunc 分页请求函数，接收页码返回 Single
     * @param persistFunc     每页成功后的持久化逻辑
     * @return 包含所有分页记录的汇总 {@link List}
     */
    public static <T> Single<GetPageResult> persistAllListsAsSingle(
            Function<Void, Completable> prepareFunc,
            Function<Long, Single<BaseResponse<List<T>>>> pageRequestFunc,
            Function<List<T>, List<T>> persistFunc) {
        Log.i("ZXN_TEST", "BasePagePersistence : persistAllListsAsSingle");
        final String uuId = UUID.randomUUID().toString();
        return Completable.defer(() -> prepareFunc.apply(null))
                .andThen(Observable.<Long, Long>generate(() -> 1L, (current, emitter) -> {
                            emitter.onNext(current);
                            return current + 1L;
                        })
                        .concatMapSingle(currentPage -> pageRequestFunc.apply(currentPage)
                                .flatMap(resp -> {
                                    if (!resp.getSucceed()) {
                                        return Single.error(new NzGetPageException(createFailedPageResult(uuId, "request page " + currentPage + " failed: " + resp.getMsg())));
                                    }
                                    List<T> fetchedList = resp.getData();
                                    GetPageResult pageResult = new GetPageResult();
                                    pageResult.setTotalPages(currentPage);
                                    if (fetchedList == null || fetchedList.isEmpty()) {
                                        return Single.just(createCurrentPageResult(pageResult, 0L, 0L));
                                    }
                                    return Single.fromCallable(() -> {
                                        try {
                                            List<T> persistedList = persistFunc.apply(fetchedList);
                                            long fetchedCount = fetchedList == null ? 0 : fetchedList.size();
                                            long persistedCount = persistedList == null ? 0 : persistedList.size();
                                            return createCurrentPageResult(pageResult, fetchedCount, persistedCount);
                                        } catch (Throwable e) {
                                            throw new NzGetPageException(createFailedPageResult(uuId, "persist page " + currentPage + " failed"), e);
                                        }
                                    });
                                }))
                        .takeWhile(stat -> stat.getFetchedCount() > 0)
                        .collect(GetPageResult::new, (lastResult, currentResult) -> accumulatePageResult(uuId, lastResult, currentResult)))
                .onErrorResumeNext(throwable -> Single.error(throwable));
    }

    public static <T> Single<GetPageResult> persistAllPagesAsSingle(
            Function<Long, Single<BaseResponse<BasePageData<T>>>> pageRequestFunc,
            Function<List<T>, List<T>> persistFunc) {
        return persistAllPagesAsSingle(BasePersistence.createBaseEmptyPreparer(), pageRequestFunc, persistFunc);
    }

    /**
     * 按分页循环请求直至拉完全部数据：每一页成功后写入本地，并汇总所有数据。
     * <p>
     * 后端返回分页数据有两种方式，这个函数处理返回BasePageData的方式
     * 始终从第 1 页开始请求，直到拉取完所有页。
     * </p>
     *
     * @param pageRequestFunc 分页请求函数，接收页码返回 Single
     * @param persistFunc     每页成功后的持久化逻辑
     * @return 包含所有分页记录的汇总 {@link List}
     */
    public static <T> Single<GetPageResult> persistAllPagesAsSingle(
            Function<Void, Completable> prepareFunc,
            Function<Long, Single<BaseResponse<BasePageData<T>>>> pageRequestFunc,
            Function<List<T>, List<T>> persistFunc) {
        Log.i("ZXN_TEST", "BasePagePersistence : persistAllPagesAsSingle");
        final String uuId = UUID.randomUUID().toString();
        return Completable.defer(() -> prepareFunc.apply(null))
                .andThen(Single.defer(() -> pageRequestFunc.apply(1L)))
                .flatMap(firstResp -> {
                    if (!firstResp.getSucceed() || firstResp.getData() == null) {
                        return Single.error(new NzGetPageException(createFailedPageResult(uuId, "request page 1 failed: " + firstResp.getMsg())));
                    }

                    BasePageData<T> firstPage = firstResp.getData();
                    List<T> firstFetchedList = firstPage.getRecords();
                    List<T> firstPersistedList = persistFunc.apply(firstFetchedList);

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
                    return persistAndAccumulateRemainingPages(pageRequestFunc, persistFunc, firstResult, 2L, firstResult.getTotalPages() - 1L);
                })
                .onErrorResumeNext(throwable ->
                        Single.error(new NzGetPageException(createFailedPageResult(uuId, throwable.getMessage()), throwable)));
    }

    private static <T> Single<GetPageResult> persistAndAccumulateRemainingPages(
            Function<Long, Single<BaseResponse<BasePageData<T>>>> pageRequestFunc,
            Function<List<T>, List<T>> persistFunc,
            GetPageResult firstResult,
            Long startPage,
            Long pageCount) {
        return Observable.rangeLong(startPage, pageCount)
                .concatMapSingle(currentPage -> pageRequestFunc.apply(currentPage)
                        .flatMap(resp -> {
                            if (!resp.getSucceed() || resp.getData() == null) {
                                return Single.error(new NzGetPageException(createFailedPageResult(firstResult.getUuId(), "request page " + currentPage + " failed: " + resp.getMsg())));
                            }
                            if (resp.getData().getCurrent() != currentPage
                                    || resp.getData().getPages() != firstResult.getTotalPages()
                                    || resp.getData().getTotal() != firstResult.getServerCount()) {
                                throw new NzGetPageException(createFailedPageResult(firstResult.getUuId(), "request current page " + currentPage + " does not match the pagination data on the server side "));
                            }
                            List<T> fetchedList = resp.getData().getRecords();
                            return Single.fromCallable(() -> {
                                try {
                                    List<T> persistedList = persistFunc.apply(fetchedList);
                                    long fetchedCount = fetchedList == null ? 0 : fetchedList.size();
                                    long persistedCount = persistedList == null ? 0 : persistedList.size();
                                    return createCurrentPageResult(firstResult, fetchedCount, persistedCount);
                                } catch (Throwable e) {
                                    throw new NzGetPageException(createFailedPageResult(firstResult.getUuId(), "persist page " + currentPage + " failed"), e);
                                }
                            });
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

    public static GetPageResult accumulatePageResult(String uuId, GetPageResult lastResult, GetPageResult currentResult) throws NzGetPageException {
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
}
