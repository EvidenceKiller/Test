package com.adl.service;

import android.content.Context;
import android.text.TextUtils;

import com.adl.base.file.AdlFileHelper;
import com.adl.service.callback.RequestCallback;
import com.adl.service.caller.BigScreenH5Caller;
import com.adl.service.caller.BigScreenStatsCaller;
import com.adl.service.caller.BusinessCaller;
import com.adl.service.caller.CommonInfoCaller;
import com.adl.service.caller.LoginAuthCaller;
import com.adl.service.caller.OperationCaller;
import com.adl.service.caller.OrgTestCaller;
import com.adl.service.caller.SportInfoCaller;
import com.adl.service.common.BaseService;
import com.adl.service.common.FileDownManager;
import com.adl.service.utils.FileUtil;
import com.adl.service.common.IDefine;
import com.adl.service.common.InnerPreferences;
import com.adl.service.common.NzConfig;
import com.adl.service.common.NzConfigBusiness;
import com.adl.service.common.NzConfigNode;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.internal.RetrofitManager;
import com.adl.service.internal.RxCallbackScheduler;
import com.adl.service.internal.SubscriptionManager;
import com.adl.service.log.NzLog;
import com.adl.service.upload.OssFileUploadService;
import com.adl.service.http.request.RequestScope;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 类描述
 */
public final class AdlService {

    /** 懒加载、双重检查锁定的单例 */
    private static volatile AdlService _instance;
    private ConfigService mConfig;
    private Context mContext;
    private String orgId;
    private NzConfig mNzConfig;

    private AdlService(Context context) {
        mContext = context;
        mConfig = new ConfigService();

        //  设置默认参数
        setSdcard("zy.sport.cache");
        setHost("http://112.19.167.50:18082/gateway/api");
        setQiNiuHost(IDefine.DefaultHost);
        setQiNiuBucket(IDefine.QiNiuPublicBucket);

        // preferences 存储
        InnerPreferences pf = InnerPreferences.instance();
        pf.init(context);
        DaoManagerProxy.init(context);
    }

    public static AdlService create(Context context) {
        if (_instance == null) {
            synchronized (AdlService.class) {
                if (_instance == null) {
                    _instance = new AdlService(context.getApplicationContext());
                }
            }
        }
        return _instance;
    }

    public static AdlService getService() {
        return _instance;
    }

    public Context getContext() {
        return mContext;
    }


    public AdlService setDebug(boolean debug) {
        mConfig.setDebug(debug);
        return this;
    }

    public AdlService setReadConfig(boolean readConfig) {
        mConfig.setReadConfig(readConfig);
        return this;
    }

    public AdlService setSdcard(String root) {
        mConfig.setSdcardRoot(root);
        return this;
    }

    public AdlService setHost(String host) {
        mConfig.setHost(host);
        return this;
    }

    public AdlService setQiNiuHost(String host) {
        mConfig.setQiNiuHost(host);
        return this;
    }

    public AdlService setOssConfigHost(String host) {
        mConfig.setOssConfigHost(host);
        return this;
    }

    public String getOssConfigHost() {
        return mConfig.getOssConfigHost();
    }

    public String getOssTokenHost() {
        return mConfig.getOssTokenHost();
    }

    public AdlService setOssTokenHost(String host) {
        mConfig.setOssTokenHost(host);
        return this;
    }

    public AdlService setQiNiuBucket(String bucket) {
        mConfig.setQiNiuBucket(bucket);
        return this;
    }

    public AdlService setFacePrefix(String prefix) {
        mConfig.setFacePrefix(prefix);
        return this;
    }

    public AdlService setDeviceToken(String token) {
        InnerPreferences.instance().putString(IDefine.SpToken, token);
        return this;
    }

    public AdlService setOrgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getSdcard() {
        return mConfig.getSdcardRoot();
    }

    public String getHost() {
        return mConfig.getHost();
    }

    public String getQiNiuHost() {
        return mConfig.getQiNiuHost();
    }

    public String getQiNiuBucket() {
        return mConfig.getQiNiuBucket();
    }

    public String getFacePrefix() {
        return mConfig.getFacePrefix();
    }

    public String getDeviceToken() {
        return InnerPreferences.instance().readString(IDefine.SpToken);
    }

    public CommonInfoCaller getCommonInfoCaller() {
        return RetrofitManager.getInstance().getCommonInfoCaller();
    }

    public SportInfoCaller getSportInfoCaller() {
        return RetrofitManager.getInstance().getSportInfoCaller();
    }

    public OrgTestCaller getOrgTestCaller() {
        return RetrofitManager.getInstance().getOrgTestCaller();
    }

    public OperationCaller getOperationCaller() {
        return RetrofitManager.getInstance().getOperationCaller();
    }

    public BusinessCaller getBusinessCaller() {
        return RetrofitManager.getInstance().getBusinessCaller();
    }

    public LoginAuthCaller getLoginAuthCaller() {
        return RetrofitManager.getInstance().getLoginAuthCaller();
    }

    public BigScreenStatsCaller getBigScreenStatsCaller() {
        return RetrofitManager.getInstance().getBigScreenStatsCaller();
    }

    public BigScreenH5Caller getBigScreenH5Caller() {
        return RetrofitManager.getInstance().getBigScreenH5Caller();
    }

    // ===================================================
    //  生命周期管理（按 Activity/Fragment 作用域取消）
    // ===================================================

    /**
     * 取消指定作用域下由本 SDK 发起、且尚未完成的异步请求。
     * <p>
     * 请在发起请求时相同的 {@link RequestScope} 创建方式（例如 {@code RequestScope.of(this)}）
     * 在对应 {@code Activity#onDestroy()} / {@code Fragment#onDestroy()} 中调用。
     * </p>
     */
    public void cancelRequestScope(RequestScope scope) {
        if (scope == null) {
            return;
        }
        SubscriptionManager.getInstance().cancelScope(scope.owner());
    }

    /**
     * 取消所有作用域下的全部未完成请求（例如退出登录、应用级重置等少数场景）
     */
    public void cancelAllRequestScope() {
        SubscriptionManager.getInstance().cancelAll();
    }

    // ===================================================
    //  同步阻塞接口的线程封装（RxJava 仅在 SDK 内使用）
    // ===================================================

    /**
     * 在 IO 线程执行阻塞任务（例如同步接口），在主线程回调。
     * <p>
     * 典型用法：必须在子线程调用的 {@code *Sync} 方法，通过本方法统一调度，无需在业务层引入 RxJava。
     * </p>
     *
     * @param scope    作用域，仅能通过 {@link RequestScope#of(android.app.Activity)} 或 {@link RequestScope#of(androidx.fragment.app.Fragment)} 创建
     * @param callable 在 IO 线程执行，通常返回某个 {@code *Sync} 调用的结果
     * @param callback 在主线程回调
     * @return 可用于追踪的内部请求标识（与回调方式一致）
     */
    public <T> long executeSyncOnIo(RequestScope scope,
                                    Callable<T> callable,
                                    RequestCallback<T> callback) {
        if (scope == null) {
            throw new IllegalArgumentException("scope must not be null (use RequestScope.of(activity|fragment))");
        }
        Disposable disposable = RxCallbackScheduler.schedule(Single.fromCallable(callable), callback);
        SubscriptionManager.getInstance().add(scope.owner(), disposable);
        return disposable.hashCode();
    }

    public void build() {
        // 全局配置对象
        InnerPreferences.instance().putObject(IDefine.SpConfigService, mConfig);
        // 服务统一配置
        BaseService.setConfigService(mConfig);
        // 根目录
        FileUtil.setSportRoot(AdlFileHelper.SportRoot);

        // 人脸管理
        FileDownManager.instance().init(mConfig);

        RetrofitManager.getInstance().init(mConfig.getHost(), mConfig.isDebug());

        new Thread(() -> {

            // TODO 读取本地配置, add by wei.zhou, 2026.02.02
            mNzConfig = mConfig.isReadConfig() ? readNzConfig() : null;
            if (mNzConfig != null && mNzConfig.isUseLocalize()) {
                String ec = mNzConfig.getEffectiveConfig();
                NzLog.d("~~~~~~~~~ 本地化配置 ~~~~~~~~~~");
                NzConfigNode eConfig = null;
                if (ec.equalsIgnoreCase("saasDebug")) {
                    eConfig = mNzConfig.getSaasDebug();
                } else if (ec.equalsIgnoreCase("saasRelease")) {
                    eConfig = mNzConfig.getSaasRelease();
                } else if (ec.equalsIgnoreCase("localDebug")) {
                    eConfig = mNzConfig.getLocalDebug();
                } else if (ec.equalsIgnoreCase("localRelease")) {
                    eConfig = mNzConfig.getLocalRelease();
                }
                if (eConfig == null) {
                    NzLog.d("错误: 本地配置解析错误!!!");
                } else {
                    NzConfigBusiness business = eConfig.getBusiness();
                    String busHost = business.getBusHost();
                    String busV3Host = business.getBusHostV3();
                    String busType = business.getBusType(); // k12,gx
                    String ossType = business.getOssType(); // qn, minio

                    NzLog.d("useLocalize:" + mNzConfig.isUseLocalize());
                    NzLog.d("busHost:" + busHost);
                    NzLog.d("busV3Host:" + busV3Host);
                    NzLog.d("busType:" + busType);
                    NzLog.d("ossType:" + ossType);

                    if ("qn".equalsIgnoreCase(ossType)) {
                        String qnHost = business.getQnHost();
                        String qnBucket = business.getQnBucket();
                        String qnPrefix = business.getQnPrefix();
                        setQiNiuHost(qnHost);
                        setQiNiuBucket(qnBucket);
                        setFacePrefix(qnPrefix);
                    } else {
                        String ossConfigHost = business.getOssConfigHost();
                        String ossTokenHost = business.getOssTokenHost();
                        String ossBucket = business.getOssBucket();
                        NzLog.d("ossConfigHost:" + ossConfigHost);
                        NzLog.d("ossTokenHost:" + ossTokenHost);
                        NzLog.d("ossBucket:" + ossBucket);
                        setOssConfigHost(ossConfigHost);
                        setOssTokenHost(ossTokenHost);
                    }

                    setDebug(business.isEnableDebug());
                    setHost(busHost);
                }
                NzLog.d("~~~~~~~~~ 本地化配置 ~~~~~~~~~~");
            }

            // 如果oss 配置未空, 使用测试环境
            if (TextUtils.isEmpty(getOssConfigHost())) {
                setOssConfigHost("http://172.16.0.206:8082/gateway/api/busi/oss/v1/config");
            }
            if (TextUtils.isEmpty(getOssTokenHost())) {
                setOssTokenHost("http://172.16.0.206:8082/gateway/api/busi/oss/v1/token");
            }

            NzLog.d("host:" + getHost());
            NzLog.d("ossConfigHost:" + getOssConfigHost());
            NzLog.d("ossTokenHost:" + getOssTokenHost());
            NzLog.d("facePrefix:" + getFacePrefix());

            String prefix = OssFileUploadService.instance().getPrefix();
            if (!TextUtils.isEmpty(prefix) && TextUtils.isEmpty(AdlService.getService().getFacePrefix())) {
                if (!prefix.endsWith("/")) {
                    prefix = prefix + "/";
                }
                AdlService.getService().setFacePrefix(prefix);
            }
        }).start();

        ////////////// 服务初始化 /////////////
        // 初始化文件上传服务
        //FileUploadService.instance();
    }

    private NzConfig readNzConfig() {
        try {
            File root = AdlFileHelper.createSportRootDir();
            File config = AdlFileHelper.createDir(root, "config");
            File cf = new File(config, "nzConfig.json");
            if (!cf.exists()) {
                InputStream input = mContext.getAssets().open("nzConfig.json");
                FileOutputStream out = new FileOutputStream(cf);
                byte[] buf = new byte[input.available()];
                input.read(buf);
                out.write(buf);
                AdlFileHelper.close(input);
                AdlFileHelper.close(out);
            }
            String json = AdlFileHelper.readStream(new FileInputStream(cf));
            if (!TextUtils.isEmpty(json)) {
                return new Gson().fromJson(json, NzConfig.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void release() {
        OssFileUploadService.instance().release();
        NzLog.shutdown();
    }
}
