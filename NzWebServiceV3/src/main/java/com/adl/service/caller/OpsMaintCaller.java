package com.adl.service.caller;

import com.adl.service.callback.RequestCallback;
import com.adl.service.exception.NzBaseException;
import com.adl.service.web.request.DeviceQrcodeRequest;
import com.adl.service.web.request.LogAddBatchRequest;
import com.adl.service.web.request.RequestScope;
import com.adl.service.web.request.SoftwareReportRequest;
import com.adl.service.web.request.UpgradePlanByDeviceWithOutLoginRequest;
import com.adl.service.web.request.UploadDeviceInfoRequest;
import com.adl.service.web.request.UploadUpgradeLogRequest;
import com.adl.service.web.response.UpgradePlanByDeviceData;

/**
 * OpsMaintCaller。
 */
public interface OpsMaintCaller {

    /**
     * 上报设备信息(昵称及Mac)
     * <p>异步调用。</p>
     */
    long uploadDeviceInfoAsync(RequestScope scope, UploadDeviceInfoRequest request, RequestCallback<Boolean> callback);

    /**
     * 上报设备信息(昵称及Mac)
     * <p>同步调用。</p>
     */
    Boolean uploadDeviceInfoSync(UploadDeviceInfoRequest request) throws NzBaseException;

    /**
     * 设备OTA（上报升级日志）
     * <p>异步调用。</p>
     */
    long uploadUpgradeLogAsync(RequestScope scope, UploadUpgradeLogRequest request, RequestCallback<Object> callback);

    /**
     * 设备OTA（上报升级日志）
     * <p>同步调用。</p>
     */
    Object uploadUpgradeLogSync(UploadUpgradeLogRequest request) throws NzBaseException;

    /**
     * 设备OTA（获取升级计划）
     * <p>异步调用。</p>
     */
    long getUpgradePlanByDeviceAsync(RequestScope scope, RequestCallback<UpgradePlanByDeviceData> callback);

    /**
     * 设备OTA（获取升级计划）
     * <p>同步调用。</p>
     */
    UpgradePlanByDeviceData getUpgradePlanByDeviceSync() throws NzBaseException;

    /**
     * 设备OTA（无登录状态获取升级计划）
     * <p>异步调用。</p>
     */
    long getUpgradePlanByDeviceWithOutLoginAsync(RequestScope scope, UpgradePlanByDeviceWithOutLoginRequest request, RequestCallback<String> callback);

    /**
     * 设备OTA（无登录状态获取升级计划）
     * <p>同步调用。</p>
     */
    String getUpgradePlanByDeviceWithOutLoginSync(UpgradePlanByDeviceWithOutLoginRequest request) throws NzBaseException;

    /**
     * 设备状态上报（软件版本）
     * <p>异步调用。</p>
     */
    long softwareReportAsync(RequestScope scope, SoftwareReportRequest request, RequestCallback<Boolean> callback);

    /**
     * 设备状态上报（软件版本）
     * <p>同步调用。</p>
     */
    Boolean softwareReportSync(SoftwareReportRequest request) throws NzBaseException;

    /**
     * 设备二维码
     * <p>异步调用。</p>
     */
    long getDeviceQrcodeAsync(RequestScope scope, DeviceQrcodeRequest request, RequestCallback<String> callback);

    /**
     * 设备二维码
     * <p>同步调用。</p>
     */
    String getDeviceQrcodeSync(DeviceQrcodeRequest request) throws NzBaseException;

    /**
     * 设备日志上报
     * <p>异步调用。</p>
     */
    long logAddBatchAsync(RequestScope scope, LogAddBatchRequest request, RequestCallback<Object> callback);

    /**
     * 设备日志上报
     * <p>同步调用。</p>
     */
    Object logAddBatchSync(LogAddBatchRequest request) throws NzBaseException;

}
