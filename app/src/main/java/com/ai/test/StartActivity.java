package com.ai.test;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import com.adl.auth.common.AdlAppCode;
import com.adl.auth.core.AdlAuthCallback;
import com.adl.auth.core.AdlAuthFactory;
import com.adl.base.activity.NextWriter;
import com.adl.base.log.AdlLogger;
import com.adl.service.AdlService;
import com.adl.ts.general.R;
import com.yanzhenjie.permission.AndPermission;

public class StartActivity extends BaseActivity {

    private String testAuthHost_V2 = "http://172.16.2.102:18005/gateway/api/device/auth";
    private String testBusHost_V2 = "http://172.16.0.206:8082/gateway/api";
    // v3 host
    private String testBusHost_V3 = "http://172.16.2.102:9082/v3/gateway/api/";

//    private static String testHost = "http://112.19.167.50:18005/gateway/api/device/auth";
//    private static String testBusHost = "http://112.19.167.50:18082/gateway/api";

    //生产环境
    private static String host = "http://globalauth.nezhasport.com/gateway/api/device/auth";
    private static String busHost = "https://ssp.nezhasport.com/gateway/api";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_start);

        if (checkPermissions(Permissions)) {
            next();
        } else {
            AndPermission.with(this)
                    .runtime()
                    .permission(Permissions)
                    .onGranted(data -> {
                        next();
                    })
                    .onDenied(data -> {
                        AdlLogger.d("--->");
                    }).start();
        }
    }

    private void next() {

        final boolean readConfig = true;
        final boolean debug = true;
        AdlAuthFactory authFactory = AdlAuthFactory.create(getApplicationContext());
        authFactory.setAuthHost(testAuthHost_V2);
        authFactory.setBusinessHost(testBusHost_V2);
        authFactory.setBusinessHostV3(testBusHost_V3);
        authFactory.setUseV3Host(true);
        authFactory.setChipType(3);// 1.高通， 2.天山， 3.自动识别
        authFactory.setDebug(debug);
        authFactory.setDebugApp(AdlAppCode.SmartScreenApp);
        authFactory.setReadConfig(false);

        authFactory.startAuth(this, new AdlAuthCallback() {

            @Override
            public void onSucceed() {

                String token = authFactory.getAccessToken();
                String appCode = authFactory.getAppCode();
                String app = authFactory.getProductCode();
                String regCode = authFactory.getRegisterCode();

                AdlService.getService()
                        .setSdcard("com.adl.sport")
                        .setHost(testBusHost_V3)
                        .setQiNiuHost("http://172.16.0.206:8082/gateway/api")
                        .setQiNiuBucket("adl-publicres")
                        .setFacePrefix("https://f.nezhasport.com/")
                        .setDeviceToken(token)
                        .setDebug(debug)
                        .setReadConfig(readConfig)
                        .build();


                NextWriter.with(StartActivity.this, MainActivity.class).closeCurrent(true).go();
            }

            @Override
            public void onSetting() {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        });
    }

}
