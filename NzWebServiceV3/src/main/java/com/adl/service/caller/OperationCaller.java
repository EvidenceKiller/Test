package com.adl.service.caller;

import com.adl.service.callback.GetPageResult;
import com.adl.service.callback.RequestCallback;
import com.adl.service.data.BannerDetailData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.DeviceFocusData;
import com.adl.service.data.DictMapData;
import com.adl.service.data.SceneData;
import com.adl.service.data.SportSkuData;
import com.adl.service.data.SportSkuDetailData;
import com.adl.service.exception.NzBaseException;
import com.adl.service.http.request.BannerDetailListRequest;
import com.adl.service.http.request.DeviceFocusListRequest;
import com.adl.service.http.request.DictMapRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.http.request.SceneListRequest;
import com.adl.service.http.request.SportSkuDetailRequest;
import com.adl.service.http.request.SportSkuPageRequest;
import com.adl.service.http.request.UploadDeviceNameRequest;

import java.util.List;
import java.util.Map;

/**
 * OperationCaller。
 */
public interface OperationCaller {

    void reLoadAllSceneDataSync(Map<String, List<String>> map) throws NzBaseException;

    /**
     * 获取Banner详情列表
     * <p>异步调用。</p>
     */
    long getBannerDetailListAsync(RequestScope scope, BannerDetailListRequest request, RequestCallback<List<BannerDetailData>> callback);

    /**
     * 获取Banner详情列表
     * <p>同步调用。</p>
     */
    List<BannerDetailData> getBannerDetailListSync(BannerDetailListRequest request) throws NzBaseException;

    long getSportSkuPagesAllAsync(RequestScope scope, SportSkuPageRequest request, RequestCallback<GetPageResult> callback);

    GetPageResult getSportSkuPagesAllSync(SportSkuPageRequest request) throws NzBaseException;

    /**
     * 获取项目库列表
     * <p>异步调用。</p>
     */
    long getSportSkuPageAsync(RequestScope scope, SportSkuPageRequest request, RequestCallback<BasePageData<SportSkuData>> callback);

    /**
     * 获取项目库列表
     * <p>同步调用。</p>
     */
    BasePageData<SportSkuData> getSportSkuPageSync(SportSkuPageRequest request) throws NzBaseException;

    /**
     * 获取项目详情
     * <p>异步调用。</p>
     */
    long getSportSkuDetailAsync(RequestScope scope, SportSkuDetailRequest request, RequestCallback<SportSkuDetailData> callback);

    /**
     * 获取项目详情
     * <p>同步调用。</p>
     */
    SportSkuDetailData getSportSkuDetailSync(SportSkuDetailRequest request) throws NzBaseException;

    /**
     * 获取模板内容
     * <p>异步调用。</p>
     */
    long getDictMapAsync(RequestScope scope, DictMapRequest request, RequestCallback<List<DictMapData>> callback);

    /**
     * 获取模板内容
     * <p>同步调用。</p>
     */
    List<DictMapData> getDictMapSync(DictMapRequest request) throws NzBaseException;

    /**
     * 获取场景列表
     * <p>异步调用。</p>
     */
    long getSceneListAsync(RequestScope scope, SceneListRequest request, RequestCallback<List<SceneData>> callback);

    /**
     * 获取场景列表
     * <p>同步调用。</p>
     */
    List<SceneData> getSceneListSync(SceneListRequest request) throws NzBaseException;

    /**
     * 修改设备名称
     * <p>异步调用。</p>
     */
    long uploadDeviceNameAsync(RequestScope scope, UploadDeviceNameRequest request, RequestCallback<Boolean> callback);

    /**
     * 修改设备名称
     * <p>同步调用。</p>
     */
    Boolean uploadDeviceNameSync(UploadDeviceNameRequest request) throws NzBaseException;

    /**
     * 获取设备专注信息列表
     * <p>异步调用。</p>
     */
    long getDeviceFocusListAsync(RequestScope scope, DeviceFocusListRequest request, RequestCallback<List<DeviceFocusData>> callback);

    /**
     * 获取设备专注信息列表
     * <p>同步调用。</p>
     */
    List<DeviceFocusData> getDeviceFocusListSync(DeviceFocusListRequest request) throws NzBaseException;

}
