package com.adl.service.repository.fetch;

import com.adl.service.data.BannerDetailData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.DeviceFocusData;
import com.adl.service.data.DictMapData;
import com.adl.service.data.SceneData;
import com.adl.service.data.SportSkuData;
import com.adl.service.data.SportSkuDetailData;
import com.adl.service.http.request.BannerDetailListRequest;
import com.adl.service.http.request.DeviceFocusListRequest;
import com.adl.service.http.request.DictMapRequest;
import com.adl.service.http.request.SceneListRequest;
import com.adl.service.http.request.SportSkuDetailRequest;
import com.adl.service.http.request.SportSkuPageRequest;
import com.adl.service.http.request.UploadDeviceNameRequest;
import com.adl.service.internal.RetrofitManager;
import com.adl.service.web.OperationService;

import java.util.List;

import io.reactivex.rxjava3.functions.Function;

public final class OperationFetchHelper {

    private final OperationService operationService;

    public OperationFetchHelper() {
        this.operationService = RetrofitManager.getInstance().create(OperationService.class);
    }

    public Function<BannerDetailListRequest, List<BannerDetailData>> createGetBannerDetailListFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(operationService.getBannerDetailList(request));
    }

    public Function<Long, BasePageData<SportSkuData>> createGetSportSkuPagesAllFunc(SportSkuPageRequest request) {
        return (page) -> {
            SportSkuPageRequest pageReq = SportSkuPageRequest.builder()
                    .current(page)
                    .pageSize(request.getPageSize())
                    .sportNature(request.getSportNature())
                    .sportType(request.getSportType())
                    .keyword(request.getKeyword())
                    .templateFlag(request.getTemplateFlag())
                    .ids(request.getIds())
                    .appCodes(request.getAppCodes())
                    .appCode(request.getAppCode())
                    .sceneId(request.getSceneId())
                    .delFlag(request.getDelFlag())
                    .enabled(request.getEnabled())
                    .build();
            return BaseFetchUtil.scheduleResponse(operationService.getSportSkuPage(pageReq));
        };
    }

    public Function<SportSkuPageRequest, BasePageData<SportSkuData>> createGetSportSkuPageFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(operationService.getSportSkuPage(request));
    }

    public Function<SportSkuDetailRequest, SportSkuDetailData> createGetSportSkuDetailFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(operationService.getSportSkuDetail(request));
    }

    public Function<DictMapRequest, List<DictMapData>> createGetDictMapFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(operationService.getDictMap(request));
    }

    public Function<SceneListRequest, List<SceneData>> createGetSceneListFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(operationService.getSceneList(request));
    }

    public Function<UploadDeviceNameRequest, Boolean> createUploadDeviceNameFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(operationService.uploadDeviceName(request));
    }

    public Function<DeviceFocusListRequest, List<DeviceFocusData>> createGetDeviceFocusListFunc() {
        return (request) -> BaseFetchUtil.scheduleResponse(operationService.getDeviceFocusList(request));
    }
}
