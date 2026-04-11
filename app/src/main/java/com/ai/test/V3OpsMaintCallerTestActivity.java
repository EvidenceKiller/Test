package com.ai.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.web.request.DeviceQrcodeRequest;
import com.adl.service.web.request.LogAddBatchRequest;
import com.adl.service.web.request.RequestScope;
import com.adl.service.web.request.SoftwareReportRequest;
import com.adl.service.web.request.UpgradePlanByDeviceWithOutLoginRequest;
import com.adl.service.web.request.UploadDeviceInfoRequest;
import com.adl.service.web.request.UploadUpgradeLogRequest;
import com.adl.service.web.response.UpgradePlanByDeviceData;
import com.adl.ts.general.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 演示在独立界面中调用 SDK「学生列表」接口。
 */
public class V3OpsMaintCallerTestActivity extends AppCompatActivity {

    private static final String TAG = "ZXN_TEST";
    private TextView resultView;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_v3_interface_caller_test);

        resultView = findViewById(R.id.tv_result);
        gridLayout = findViewById(R.id.gl_test);
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
        findViewById(R.id.btn_clear).setOnClickListener(v -> resultView.setText(""));

        createButton("uploadDeviceInfoAsync").setOnClickListener(v -> uploadDeviceInfoAsync());
        createButton("uploadDeviceInfoSync").setOnClickListener(v -> uploadDeviceInfoSync());
        createButton("uploadUpgradeLogAsync").setOnClickListener(v -> uploadUpgradeLogAsync());
        createButton("uploadUpgradeLogSync").setOnClickListener(v -> uploadUpgradeLogSync());
        createButton("getUpgradePlanByDeviceAsync").setOnClickListener(v -> getUpgradePlanByDeviceAsync());
        createButton("getUpgradePlanByDeviceSync").setOnClickListener(v -> getUpgradePlanByDeviceSync());
        createButton("getUpgradePlanByDeviceWithOutLoginAsync").setOnClickListener(v -> getUpgradePlanByDeviceWithOutLoginAsync());
        createButton("getUpgradePlanByDeviceWithOutLoginSync").setOnClickListener(v -> getUpgradePlanByDeviceWithOutLoginSync());
        createButton("softwareReportAsync").setOnClickListener(v -> softwareReportAsync());
        createButton("softwareReportSync").setOnClickListener(v -> softwareReportSync());
        createButton("getDeviceQrcodeAsync").setOnClickListener(v -> getDeviceQrcodeAsync());
        createButton("getDeviceQrcodeSync").setOnClickListener(v -> getDeviceQrcodeSync());
        createButton("logAddBatchAsync").setOnClickListener(v -> logAddBatchAsync());
        createButton("logAddBatchSync").setOnClickListener(v -> logAddBatchSync());
    }

    private UploadDeviceInfoRequest buildUploadDeviceInfoRequest() {
        return UploadDeviceInfoRequest.builder("123456789")
                .build();
    }

    private UploadUpgradeLogRequest buildUploadUpgradeLogRequest() {
        return UploadUpgradeLogRequest.builder()
                .build();
    }

    private UpgradePlanByDeviceWithOutLoginRequest buildUpgradePlanByDeviceWithOutLoginRequest() {
        return UpgradePlanByDeviceWithOutLoginRequest.builder("123456789").build();
    }

    private SoftwareReportRequest buildSoftwareReportRequest() {
        return SoftwareReportRequest.builder()
                .build();
    }

    private DeviceQrcodeRequest buildDeviceQrcodeRequest() {
        return DeviceQrcodeRequest.builder("101101").build();
    }

    private LogAddBatchRequest buildLogAddBatchRequest() {
        return LogAddBatchRequest.builder()
                .appCode("101101")
                .content("test context")
                .productCode("789456132")
                .build();
    }

    private void uploadDeviceInfoAsync() {
        UploadDeviceInfoRequest request = buildUploadDeviceInfoRequest();
        AdlService.getService().getOpsMaintCaller().uploadDeviceInfoAsync(RequestScope.of(this), request, new RequestCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                if (data != null) {
                    Log.d(TAG, "OpsMaintCallerTestActivity::uploadDeviceInfoAsync::onSuccess : data : " + data);
                    resultView.setText(String.valueOf(data));
                } else {
                    Log.d(TAG, "OpsMaintCallerTestActivity::uploadDeviceInfoAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OpsMaintCallerTestActivity::uploadDeviceInfoAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OpsMaintCallerTestActivity::uploadDeviceInfoAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void uploadDeviceInfoSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getOpsMaintCaller().uploadDeviceInfoSync(buildUploadDeviceInfoRequest()),
                new RequestCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean data) {
                        if (data != null) {
                            Log.d(TAG, "OpsMaintCallerTestActivity::uploadDeviceInfoSync::onSuccess : data : " + data);
                            resultView.setText(String.valueOf(data));
                        } else {
                            Log.d(TAG, "OpsMaintCallerTestActivity::uploadDeviceInfoSync::onSuccess : data is null");
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "OpsMaintCallerTestActivity::uploadDeviceInfoSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "OpsMaintCallerTestActivity::uploadDeviceInfoSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void uploadUpgradeLogAsync() {
        UploadUpgradeLogRequest request = buildUploadUpgradeLogRequest();
        AdlService.getService().getOpsMaintCaller().uploadUpgradeLogAsync(RequestScope.of(this), request, new RequestCallback<Object>() {
            @Override
            public void onSuccess(Object data) {
                if (data != null) {
                    Log.d(TAG, "OpsMaintCallerTestActivity::uploadUpgradeLogAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OpsMaintCallerTestActivity::uploadUpgradeLogAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OpsMaintCallerTestActivity::uploadUpgradeLogAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OpsMaintCallerTestActivity::uploadUpgradeLogAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void uploadUpgradeLogSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getOpsMaintCaller().uploadUpgradeLogSync(buildUploadUpgradeLogRequest()),
                new RequestCallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if (data != null) {
                            Log.d(TAG, "OpsMaintCallerTestActivity::uploadUpgradeLogSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "OpsMaintCallerTestActivity::uploadUpgradeLogSync::onSuccess : data is null");
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "OpsMaintCallerTestActivity::uploadUpgradeLogSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "OpsMaintCallerTestActivity::uploadUpgradeLogSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getUpgradePlanByDeviceAsync() {
        AdlService.getService().getOpsMaintCaller().getUpgradePlanByDeviceAsync(RequestScope.of(this), new RequestCallback<UpgradePlanByDeviceData>() {
            @Override
            public void onSuccess(UpgradePlanByDeviceData data) {
                if (data != null) {
                    Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getUpgradePlanByDeviceSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getOpsMaintCaller().getUpgradePlanByDeviceSync(),
                new RequestCallback<UpgradePlanByDeviceData>() {
                    @Override
                    public void onSuccess(UpgradePlanByDeviceData data) {
                        if (data != null) {
                            Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceSync::onSuccess : data is null");
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getUpgradePlanByDeviceWithOutLoginAsync() {
        UpgradePlanByDeviceWithOutLoginRequest request = buildUpgradePlanByDeviceWithOutLoginRequest();
        AdlService.getService().getOpsMaintCaller().getUpgradePlanByDeviceWithOutLoginAsync(RequestScope.of(this), request, new RequestCallback<String>() {
            @Override
            public void onSuccess(String data) {
                if (data != null) {
                    Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceWithOutLoginAsync::onSuccess : data : " + data);
                    resultView.setText(data);
                } else {
                    Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceWithOutLoginAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceWithOutLoginAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceWithOutLoginAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getUpgradePlanByDeviceWithOutLoginSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getOpsMaintCaller().getUpgradePlanByDeviceWithOutLoginSync(buildUpgradePlanByDeviceWithOutLoginRequest()),
                new RequestCallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        if (data != null) {
                            Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceWithOutLoginSync::onSuccess : data : " + data);
                            resultView.setText(data);
                        } else {
                            Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceWithOutLoginSync::onSuccess : data is null");
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceWithOutLoginSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "OpsMaintCallerTestActivity::getUpgradePlanByDeviceWithOutLoginSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void softwareReportAsync() {
        SoftwareReportRequest request = buildSoftwareReportRequest();
        AdlService.getService().getOpsMaintCaller().softwareReportAsync(RequestScope.of(this), request, new RequestCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                if (data != null) {
                    Log.d(TAG, "OpsMaintCallerTestActivity::softwareReportAsync::onSuccess : data : " + data);
                    resultView.setText(String.valueOf(data));
                } else {
                    Log.d(TAG, "OpsMaintCallerTestActivity::softwareReportAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OpsMaintCallerTestActivity::softwareReportAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OpsMaintCallerTestActivity::softwareReportAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void softwareReportSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getOpsMaintCaller().softwareReportSync(buildSoftwareReportRequest()),
                new RequestCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean data) {
                        if (data != null) {
                            Log.d(TAG, "OpsMaintCallerTestActivity::softwareReportSync::onSuccess : data : " + data);
                            resultView.setText(String.valueOf(data));
                        } else {
                            Log.d(TAG, "OpsMaintCallerTestActivity::softwareReportSync::onSuccess : data is null");
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "OpsMaintCallerTestActivity::softwareReportSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "OpsMaintCallerTestActivity::softwareReportSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getDeviceQrcodeAsync() {
        DeviceQrcodeRequest request = buildDeviceQrcodeRequest();
        AdlService.getService().getOpsMaintCaller().getDeviceQrcodeAsync(RequestScope.of(this), request, new RequestCallback<String>() {
            @Override
            public void onSuccess(String data) {
                if (data != null) {
                    Log.d(TAG, "OpsMaintCallerTestActivity::getDeviceQrcodeAsync::onSuccess : data : " + data);
                    resultView.setText(data);
                } else {
                    Log.d(TAG, "OpsMaintCallerTestActivity::getDeviceQrcodeAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OpsMaintCallerTestActivity::getDeviceQrcodeAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OpsMaintCallerTestActivity::getDeviceQrcodeAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getDeviceQrcodeSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getOpsMaintCaller().getDeviceQrcodeSync(buildDeviceQrcodeRequest()),
                new RequestCallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        if (data != null) {
                            Log.d(TAG, "OpsMaintCallerTestActivity::getDeviceQrcodeSync::onSuccess : data : " + data);
                            resultView.setText(data);
                        } else {
                            Log.d(TAG, "OpsMaintCallerTestActivity::getDeviceQrcodeSync::onSuccess : data is null");
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "OpsMaintCallerTestActivity::getDeviceQrcodeSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "OpsMaintCallerTestActivity::getDeviceQrcodeSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void logAddBatchAsync() {
        LogAddBatchRequest request = buildLogAddBatchRequest();
        AdlService.getService().getOpsMaintCaller().logAddBatchAsync(RequestScope.of(this), request, new RequestCallback<Object>() {
            @Override
            public void onSuccess(Object data) {
                if (data != null) {
                    Log.d(TAG, "OpsMaintCallerTestActivity::logAddBatchAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OpsMaintCallerTestActivity::logAddBatchAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OpsMaintCallerTestActivity::logAddBatchAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OpsMaintCallerTestActivity::logAddBatchAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void logAddBatchSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getOpsMaintCaller().logAddBatchSync(buildLogAddBatchRequest()),
                new RequestCallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        if (data != null) {
                            Log.d(TAG, "OpsMaintCallerTestActivity::logAddBatchSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "OpsMaintCallerTestActivity::logAddBatchSync::onSuccess : data is null");
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "OpsMaintCallerTestActivity::logAddBatchSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "OpsMaintCallerTestActivity::logAddBatchSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private Button createButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setAllCaps(false);
        button.setId(View.generateViewId());

        // 设置布局参数
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0; // 配合 columnWeight 使用
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f); // 平分列宽
        button.setLayoutParams(params);
        gridLayout.addView(button);
        return button;
    }

    @Override
    protected void onDestroy() {
        AdlService.getService().cancelRequestScope(RequestScope.of(this));
        super.onDestroy();
    }
}
