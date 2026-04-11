package com.ai.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.web.request.CheckStudentInPlanRequest;
import com.adl.service.web.request.PlanClassListRequest;
import com.adl.service.web.request.PlanListRequest;
import com.adl.service.web.request.PlanStudentPageRequest;
import com.adl.service.web.request.RecordDetailPageRequest;
import com.adl.service.web.request.RequestScope;
import com.adl.service.web.request.StandardConfigPageRequest;
import com.adl.service.web.response.BasePageData;
import com.adl.service.web.response.PlanClassData;
import com.adl.service.web.response.PlanInfoData;
import com.adl.service.web.response.PlanData;
import com.adl.service.web.response.PlanStudentData;
import com.adl.service.web.response.RecordDetailData;
import com.adl.service.web.response.StandardConfigPageData;
import com.adl.ts.general.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/** 演示在独立界面中调用 SDK「OrgTestCaller」接口。 */
public class V3OrgTestCallerTestActivity extends AppCompatActivity {

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

        createButton("getStandardConfigPageAsync").setOnClickListener(v -> getStandardConfigPageAsync());
        createButton("getStandardConfigPageSync").setOnClickListener(v -> getStandardConfigPageSync());
        createButton("getRecordDetailPageAsync").setOnClickListener(v -> getRecordDetailPageAsync());
        createButton("getRecordDetailPageSync").setOnClickListener(v -> getRecordDetailPageSync());
        createButton("getPlanStudentPageAsync").setOnClickListener(v -> getPlanStudentPageAsync());
        createButton("getPlanStudentPageSync").setOnClickListener(v -> getPlanStudentPageSync());
        createButton("getPlanPageAsync").setOnClickListener(v -> getPlanPageAsync());
        createButton("getPlanPageSync").setOnClickListener(v -> getPlanPageSync());
        createButton("getPlanInfoAsync").setOnClickListener(v -> getPlanInfoAsync());
        createButton("getPlanInfoSync").setOnClickListener(v -> getPlanInfoSync());
        createButton("getPlanClassListAsync").setOnClickListener(v -> getPlanClassListAsync());
        createButton("getPlanClassListSync").setOnClickListener(v -> getPlanClassListSync());
        createButton("getCheckStudentAsync").setOnClickListener(v -> checkStudentInPlanAsync());
        createButton("getCheckStudentSync").setOnClickListener(v -> checkStudentInPlanSync());
    }


    private StandardConfigPageRequest buildStandardConfigPageRequest() {
        return StandardConfigPageRequest.builder("")
                .build();
    }

    private RecordDetailPageRequest buildRecordDetailPageRequest() {
        return RecordDetailPageRequest.builder()
                .build();
    }

    private PlanStudentPageRequest buildPlanStudentPageRequest() {
        return PlanStudentPageRequest.builder("fc7a820389f744b5a094c775ba3037db")
                .build();
    }

    private PlanListRequest buildPlanListRequest() {
        return PlanListRequest.builder()
                .build();
    }

    private PlanClassListRequest buildPlanClassListRequest() {
        return PlanClassListRequest.builder()
                .build();
    }

    private CheckStudentInPlanRequest buildCheckStudentInPlanRequest() {
        return CheckStudentInPlanRequest.builder("fc7a820389f744b5a094c775ba3037db")
                .build();
    }

    private void getStandardConfigPageAsync() {
        StandardConfigPageRequest request = buildStandardConfigPageRequest();
        AdlService.getService().getOrgTestCaller().getStandardConfigPageAsync(RequestScope.of(this), request, new RequestCallback<List<StandardConfigPageData>>() {
            @Override
            public void onSuccess(List<StandardConfigPageData> data) {
                if (data != null) {
                    Log.d(TAG, "OrgTestCallerTestActivity::getStandardConfigPageAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "OrgTestCallerTestActivity::getStandardConfigPageAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OrgTestCallerTestActivity::getStandardConfigPageAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OrgTestCallerTestActivity::getStandardConfigPageAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OrgTestCallerTestActivity::getStandardConfigPageAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getStandardConfigPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            StandardConfigPageRequest request = buildStandardConfigPageRequest();
            return AdlService.getService().getOrgTestCaller().getStandardConfigPageSync(request);
        }, new RequestCallback<List<StandardConfigPageData>>() {
            @Override
            public void onSuccess(List<StandardConfigPageData> data) {
                if (data != null) {
                    Log.d(TAG, "OrgTestCallerTestActivity::getStandardConfigPageSync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "OrgTestCallerTestActivity::getStandardConfigPageSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OrgTestCallerTestActivity::getStandardConfigPageSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OrgTestCallerTestActivity::getStandardConfigPageSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OrgTestCallerTestActivity::getStandardConfigPageSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getRecordDetailPageAsync() {
        RecordDetailPageRequest request = buildRecordDetailPageRequest();
        AdlService.getService().getOrgTestCaller().getRecordDetailPageAsync(RequestScope.of(this), request, new RequestCallback<BasePageData<RecordDetailData>>() {
            @Override
            public void onSuccess(BasePageData<RecordDetailData> data) {
                if (data != null) {
                    Log.d(TAG, "OrgTestCallerTestActivity::getRecordDetailPageAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OrgTestCallerTestActivity::getRecordDetailPageAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OrgTestCallerTestActivity::getRecordDetailPageAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OrgTestCallerTestActivity::getRecordDetailPageAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getRecordDetailPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            RecordDetailPageRequest request = buildRecordDetailPageRequest();
            return AdlService.getService().getOrgTestCaller().getRecordDetailPageSync(request);
        }, new RequestCallback<BasePageData<RecordDetailData>>() {
            @Override
            public void onSuccess(BasePageData<RecordDetailData> data) {
                if (data != null) {
                    Log.d(TAG, "OrgTestCallerTestActivity::getRecordDetailPageSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OrgTestCallerTestActivity::getRecordDetailPageSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OrgTestCallerTestActivity::getRecordDetailPageSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OrgTestCallerTestActivity::getRecordDetailPageSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getPlanStudentPageAsync() {
        PlanStudentPageRequest request = buildPlanStudentPageRequest();
        AdlService.getService().getOrgTestCaller().getPlanStudentPageAsync(RequestScope.of(this), request, new RequestCallback<BasePageData<PlanStudentData>>() {
            @Override
            public void onSuccess(BasePageData<PlanStudentData> data) {
                if (data != null) {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanStudentPageAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanStudentPageAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanStudentPageAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanStudentPageAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getPlanStudentPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            PlanStudentPageRequest request = buildPlanStudentPageRequest();
            return AdlService.getService().getOrgTestCaller().getPlanStudentPageSync(request);
        }, new RequestCallback<BasePageData<PlanStudentData>>() {
            @Override
            public void onSuccess(BasePageData<PlanStudentData> data) {
                if (data != null) {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanStudentPageSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanStudentPageSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanStudentPageSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanStudentPageSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getPlanPageAsync() {
        PlanListRequest request = buildPlanListRequest();
        AdlService.getService().getOrgTestCaller().getPlanPageAsync(RequestScope.of(this), request, new RequestCallback<BasePageData<PlanData>>() {
            @Override
            public void onSuccess(BasePageData<PlanData> data) {
                if (data != null) {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanPageAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanPageAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanPageAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanPageAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getPlanPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            PlanListRequest request = buildPlanListRequest();
            return AdlService.getService().getOrgTestCaller().getPlanPageSync(request);
        }, new RequestCallback<BasePageData<PlanData>>() {
            @Override
            public void onSuccess(BasePageData<PlanData> data) {
                if (data != null) {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanPageSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanPageSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanPageSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanPageSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getPlanInfoAsync() {
        AdlService.getService().getOrgTestCaller().getPlanInfoAsync(RequestScope.of(this), new RequestCallback<PlanInfoData>() {
            @Override
            public void onSuccess(PlanInfoData data) {
                if (data != null) {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanInfoAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanInfoAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanInfoAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanInfoAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getPlanInfoSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> AdlService.getService().getOrgTestCaller().getPlanInfoSync(), new RequestCallback<PlanInfoData>() {
            @Override
            public void onSuccess(PlanInfoData data) {
                if (data != null) {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanInfoSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanInfoSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanInfoSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanInfoSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getPlanClassListAsync() {
        PlanClassListRequest request = buildPlanClassListRequest();
        AdlService.getService().getOrgTestCaller().getPlanClassListAsync(RequestScope.of(this), request, new RequestCallback<List<PlanClassData>>() {
            @Override
            public void onSuccess(List<PlanClassData> data) {
                if (data != null) {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanClassListAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanClassListAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanClassListAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanClassListAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanClassListAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getPlanClassListSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            PlanClassListRequest request = buildPlanClassListRequest();
            return AdlService.getService().getOrgTestCaller().getPlanClassListSync(request);
        }, new RequestCallback<List<PlanClassData>>() {
            @Override
            public void onSuccess(List<PlanClassData> data) {
                if (data != null) {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanClassListSync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanClassListSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OrgTestCallerTestActivity::getPlanClassListSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanClassListSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OrgTestCallerTestActivity::getPlanClassListSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void checkStudentInPlanAsync() {
        CheckStudentInPlanRequest request = buildCheckStudentInPlanRequest();
        AdlService.getService().getOrgTestCaller().checkStudentInPlanAsync(RequestScope.of(this), request, new RequestCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                if (data != null) {
                    Log.d(TAG, "OrgTestCallerTestActivity::getCheckStudentAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OrgTestCallerTestActivity::getCheckStudentAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OrgTestCallerTestActivity::getCheckStudentAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OrgTestCallerTestActivity::getCheckStudentAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void checkStudentInPlanSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            CheckStudentInPlanRequest request = buildCheckStudentInPlanRequest();
            return AdlService.getService().getOrgTestCaller().checkStudentInPlanSync(request);
        }, new RequestCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                if (data != null) {
                    Log.d(TAG, "OrgTestCallerTestActivity::getCheckStudentSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "OrgTestCallerTestActivity::getCheckStudentSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "OrgTestCallerTestActivity::getCheckStudentSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "OrgTestCallerTestActivity::getCheckStudentSync::onError : error : " + error);
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
