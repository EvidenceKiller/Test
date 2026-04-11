package com.ai.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.web.request.LoginRequest;
import com.adl.service.web.request.LogoutRequest;
import com.adl.service.web.request.RequestScope;
import com.adl.service.web.response.LoginData;
import com.adl.ts.general.R;

import androidx.appcompat.app.AppCompatActivity;

/** 演示在独立界面中调用 SDK「LoginAuthCaller」接口。 */
public class V3LoginAuthCallerTestActivity extends AppCompatActivity {

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

        createButton("loginAsync").setOnClickListener(v -> loginAsync());
        createButton("loginSync").setOnClickListener(v -> loginSync());
        createButton("logoutAsync").setOnClickListener(v -> logoutAsync());
        createButton("logoutSync").setOnClickListener(v -> logoutSync());

    }

    private LoginRequest buildLoginRequest() {
        return LoginRequest.builder("123456", "123456").build();
    }

    private LogoutRequest buildLogoutRequest() {
        return LogoutRequest.builder("").build();
    }

    private void loginAsync() {
        LoginRequest request = buildLoginRequest();
        AdlService.getService().getLoginAuthCaller().loginAsync(RequestScope.of(this), request, new RequestCallback<LoginData>() {
            @Override
            public void onSuccess(LoginData data) {
                if (data != null) {
                    Log.d(TAG, "LoginAuthCallerTestActivity::loginAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "LoginAuthCallerTestActivity::loginAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "LoginAuthCallerTestActivity::loginAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "LoginAuthCallerTestActivity::loginAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void loginSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getLoginAuthCaller().loginSync(buildLoginRequest()),
                new RequestCallback<LoginData>() {
                    @Override
                    public void onSuccess(LoginData data) {
                        if (data != null) {
                            Log.d(TAG, "LoginAuthCallerTestActivity::loginSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "LoginAuthCallerTestActivity::loginSync::onSuccess : data is null");
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "LoginAuthCallerTestActivity::loginSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "LoginAuthCallerTestActivity::loginSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void logoutAsync() {
        LogoutRequest request = buildLogoutRequest();
        AdlService.getService().getLoginAuthCaller().logoutAsync(RequestScope.of(this), request, new RequestCallback<String>() {
            @Override
            public void onSuccess(String data) {
                Log.d(TAG, "LoginAuthCallerTestActivity::logoutAsync::onSuccess : data : " + data);
                resultView.setText(String.valueOf(data));
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "LoginAuthCallerTestActivity::logoutAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "LoginAuthCallerTestActivity::logoutAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void logoutSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getLoginAuthCaller().logoutSync(buildLogoutRequest()),
                new RequestCallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.d(TAG, "LoginAuthCallerTestActivity::logoutSync::onSuccess : data : " + data);
                        resultView.setText(String.valueOf(data));
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "LoginAuthCallerTestActivity::logoutSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "LoginAuthCallerTestActivity::logoutSync::onError : error : " + error);
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
