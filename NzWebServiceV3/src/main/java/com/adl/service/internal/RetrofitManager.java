package com.adl.service.internal;

import com.adl.service.caller.BigScreenH5Caller;
import com.adl.service.caller.BigScreenStatsCaller;
import com.adl.service.caller.BusinessCaller;
import com.adl.service.caller.CommonInfoCaller;
import com.adl.service.caller.LoginAuthCaller;
import com.adl.service.caller.OperationCaller;
import com.adl.service.caller.OpsMaintCaller;
import com.adl.service.caller.OrgTestCaller;
import com.adl.service.caller.SportInfoCaller;
import com.adl.service.caller.impl.BigScreenH5CallerImpl;
import com.adl.service.caller.impl.BigScreenStatsCallerImpl;
import com.adl.service.caller.impl.BusinessCallerImpl;
import com.adl.service.caller.impl.CommonInfoCallerImpl;
import com.adl.service.caller.impl.LoginAuthCallerImpl;
import com.adl.service.caller.impl.OperationCallerImpl;
import com.adl.service.caller.impl.OpsMaintCallerImpl;
import com.adl.service.caller.impl.OrgTestCallerImpl;
import com.adl.service.caller.impl.SportInfoCallerImpl;
import com.adl.service.web.service.BigScreenH5Service;
import com.adl.service.web.service.BigScreenStatsService;
import com.adl.service.web.service.BusinessService;
import com.adl.service.web.service.CommonInfoService;
import com.adl.service.web.service.LoginAuthService;
import com.adl.service.web.service.OperationService;
import com.adl.service.web.service.OpsMaintService;
import com.adl.service.web.service.OrgTestService;
import com.adl.service.web.service.SportInfoService;

/**
 * Retrofit 与 Caller 统一管理器（单例）。
 */
public final class RetrofitManager {

    private static volatile RetrofitManager instance;

    private String host;
    private boolean debug;

    private CommonInfoCaller commonInfoCaller;
    private SportInfoCaller sportInfoCaller;
    private OrgTestCaller orgTestCaller;
    private OperationCaller operationCaller;
    private BusinessCaller businessCaller;
    private LoginAuthCaller loginAuthCaller;
    private BigScreenStatsCaller bigScreenStatsCaller;
    private BigScreenH5Caller bigScreenH5Caller;
    private OpsMaintCaller opsMaintCaller;

    private RetrofitManager() {
    }

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    public synchronized void init(String host, boolean debug) {
        boolean needRebuild = this.commonInfoCaller == null
                || this.sportInfoCaller == null
                || this.orgTestCaller == null
                || this.operationCaller == null
                || this.businessCaller == null
                || this.loginAuthCaller == null
                || this.bigScreenStatsCaller == null
                || this.bigScreenH5Caller == null
                || this.opsMaintCaller == null
                || !host.equals(this.host)
                || debug != this.debug;
        if (!needRebuild) {
            return;
        }

        this.host = host;
        this.debug = debug;
        RetrofitClient.reset();
        RetrofitClient retrofitClient = RetrofitClient.getInstance(host, debug);

        CommonInfoService commonInfoService = retrofitClient.create(CommonInfoService.class);
        SportInfoService sportInfoService = retrofitClient.create(SportInfoService.class);
        OrgTestService orgTestService = retrofitClient.create(OrgTestService.class);
        OperationService operationService = retrofitClient.create(OperationService.class);
        BusinessService businessService = retrofitClient.create(BusinessService.class);
        LoginAuthService loginAuthService = retrofitClient.create(LoginAuthService.class);
        BigScreenStatsService bigScreenStatsService = retrofitClient.create(BigScreenStatsService.class);
        BigScreenH5Service bigScreenH5Service = retrofitClient.create(BigScreenH5Service.class);
        OpsMaintService opsMaintService = retrofitClient.create(OpsMaintService.class);

        this.commonInfoCaller = new CommonInfoCallerImpl(commonInfoService);
        this.sportInfoCaller = new SportInfoCallerImpl(sportInfoService);
        this.orgTestCaller = new OrgTestCallerImpl(orgTestService);
        this.operationCaller = new OperationCallerImpl(operationService);
        this.businessCaller = new BusinessCallerImpl(businessService);
        this.loginAuthCaller = new LoginAuthCallerImpl(loginAuthService);
        this.bigScreenStatsCaller = new BigScreenStatsCallerImpl(bigScreenStatsService);
        this.bigScreenH5Caller = new BigScreenH5CallerImpl(bigScreenH5Service);
        this.opsMaintCaller = new OpsMaintCallerImpl(opsMaintService);
    }

    public CommonInfoCaller getCommonInfoCaller() {
        return commonInfoCaller;
    }

    public SportInfoCaller getSportInfoCaller() {
        return sportInfoCaller;
    }

    public OrgTestCaller getOrgTestCaller() {
        return orgTestCaller;
    }

    public OperationCaller getOperationCaller() {
        return operationCaller;
    }

    public BusinessCaller getBusinessCaller() {
        return businessCaller;
    }

    public LoginAuthCaller getLoginAuthCaller() {
        return loginAuthCaller;
    }

    public BigScreenStatsCaller getBigScreenStatsCaller() {
        return bigScreenStatsCaller;
    }

    public BigScreenH5Caller getBigScreenH5Caller() {
        return bigScreenH5Caller;
    }

    public OpsMaintCaller getOpsMaintCaller() {
        return opsMaintCaller;
    }
}
