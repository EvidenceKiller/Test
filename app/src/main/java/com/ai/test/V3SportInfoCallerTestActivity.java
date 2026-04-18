package com.ai.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.http.request.AccountRecordPageRequest;
import com.adl.service.http.request.OrgRecordPageRequest;
import com.adl.service.http.request.ReportStudentCompetitionRequest;
import com.adl.service.http.request.ReportStudentMeetRequest;
import com.adl.service.http.request.ReportStudentPlanRequest;
import com.adl.service.http.request.ReportStudentSportRequest;
import com.adl.service.http.request.ReportTeacherSportRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.data.AccountRecordData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.OrgRecordData;
import com.adl.ts.general.R;

import androidx.appcompat.app.AppCompatActivity;

/** 演示在独立界面中调用 SDK「SportInfoCaller」接口。 */
public class V3SportInfoCallerTestActivity extends AppCompatActivity {

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

        createButton("updateStudentSportAsync").setOnClickListener(v -> updateStudentSportAsync());
        createButton("updateStudentSportSync").setOnClickListener(v -> updateStudentSportSync());
        createButton("reportTeacherSportAsync").setOnClickListener(v -> reportTeacherSportAsync());
        createButton("reportTeacherSportSync").setOnClickListener(v -> reportTeacherSportSync());
        createButton("reportStudentTrainAsync").setOnClickListener(v -> reportStudentTrainAsync());
        createButton("reportStudentTrainSync").setOnClickListener(v -> reportStudentTrainSync());
        createButton("reportStudentSportAsync").setOnClickListener(v -> reportStudentSportAsync());
        createButton("reportStudentSportSync").setOnClickListener(v -> reportStudentSportSync());
        createButton("reportStudentPlanAsync").setOnClickListener(v -> reportStudentPlanAsync());
        createButton("reportStudentPlanSync").setOnClickListener(v -> reportStudentPlanSync());
        createButton("reportStudentMeetAsync").setOnClickListener(v -> reportStudentMeetAsync());
        createButton("reportStudentMeetSync").setOnClickListener(v -> reportStudentMeetSync());
        createButton("reportStudentCompetitionAsync").setOnClickListener(v -> reportStudentCompetitionAsync());
        createButton("reportStudentCompetitionSync").setOnClickListener(v -> reportStudentCompetitionSync());
        createButton("reportStudentAllSportAsync").setOnClickListener(v -> reportStudentAllSportAsync());
        createButton("reportStudentAllSportSync").setOnClickListener(v -> reportStudentAllSportSync());
        createButton("getOrgRecordPageAsync").setOnClickListener(v -> getOrgRecordPageAsync());
        createButton("getOrgRecordPageSync").setOnClickListener(v -> getOrgRecordPageSync());
        createButton("getAccountRecordPageAsync").setOnClickListener(v -> getAccountRecordPageAsync());
        createButton("getAccountRecordPageSync").setOnClickListener(v -> getAccountRecordPageSync());
    }

    private ReportStudentSportRequest buildReportStudentSportRequest() {
        return ReportStudentSportRequest.builder(
                "9901060001", "1965665142485622785", "101101", 0, 10L, "1776082153044", "1776082213054",
                "50", "1001", "4301010001", "temp", 60000L)
                .build();
    }

    private ReportTeacherSportRequest buildReportTeacherSportRequest() {
        return ReportTeacherSportRequest.builder(
                "1965665142485622785", "101101", "test1111", 0, 10L, "1776082153044", "1776082213054",
                "50", "1001", "4301010001", "temp", 60000L)
                .build();
    }

    private ReportStudentMeetRequest buildReportStudentMeetRequest() {
        return ReportStudentMeetRequest.builder(
                "1965665142485622785", "101101", 0, 10L, "1776082153044", "1776082213054",
                "50", "1001", "4301010001", "temp", 60000L)
                .build();
    }

    private ReportStudentPlanRequest buildReportStudentPlanRequest() {
        return ReportStudentPlanRequest.builder(
                "1965665142485622785", "101101", 0, 10L, "1776082153044", "1776082213054",
                "50", "1001", "4301010001", "temp", 60000L)
                .build();
    }

    private ReportStudentCompetitionRequest buildReportStudentCompetitionRequest() {
        return ReportStudentCompetitionRequest.builder(
                "1965665142485622785", "101101", 0, 10L, "1776082153044", "1776082213054",
                "50", "1001", "4301010001", "temp", 60000L)
                .build();
    }

    private AccountRecordPageRequest buildAccountRecordPageRequest() {
        AccountRecordPageRequest request = AccountRecordPageRequest.builder().foreceUpdate(true).pageSize(10L).semesterId("101101").build();
        return request;
    }

    private RequestCallback<Boolean> booleanCallback(String methodName) {
        return new RequestCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                Log.d(TAG, "SportInfoCallerTestActivity::" + methodName + "::onSuccess : data : " + data);
                resultView.setText(String.valueOf(data));
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "SportInfoCallerTestActivity::" + methodName + "::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "SportInfoCallerTestActivity::" + methodName + "::onError : error : " + error);
                resultView.setText(error);
            }
        };
    }

    private void updateStudentSportAsync() {
        AdlService.getService().getSportInfoCaller().updateStudentSportAsync(
                RequestScope.of(this), buildReportStudentSportRequest(), booleanCallback("updateStudentSportAsync"));
    }

    private void updateStudentSportSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getSportInfoCaller().updateStudentSportSync(buildReportStudentSportRequest()),
                booleanCallback("updateStudentSportSync"));
    }

    private void reportTeacherSportAsync() {
        AdlService.getService().getSportInfoCaller().reportTeacherSportAsync(
                RequestScope.of(this), buildReportTeacherSportRequest(), booleanCallback("reportTeacherSportAsync"));
    }

    private void reportTeacherSportSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getSportInfoCaller().reportTeacherSportSync(buildReportTeacherSportRequest()),
                booleanCallback("reportTeacherSportSync"));
    }

    private void reportStudentTrainAsync() {
        AdlService.getService().getSportInfoCaller().reportStudentTrainAsync(
                RequestScope.of(this), buildReportStudentSportRequest(), booleanCallback("reportStudentTrainAsync"));
    }

    private void reportStudentTrainSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getSportInfoCaller().reportStudentTrainSync(buildReportStudentSportRequest()),
                booleanCallback("reportStudentTrainSync"));
    }

    private void reportStudentSportAsync() {
        AdlService.getService().getSportInfoCaller().reportStudentSportAsync(
                RequestScope.of(this), buildReportStudentSportRequest(), booleanCallback("reportStudentSportAsync"));
    }

    private void reportStudentSportSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getSportInfoCaller().reportStudentSportSync(buildReportStudentSportRequest()),
                booleanCallback("reportStudentSportSync"));
    }

    private void reportStudentPlanAsync() {
        AdlService.getService().getSportInfoCaller().reportStudentPlanAsync(
                RequestScope.of(this), buildReportStudentPlanRequest(), booleanCallback("reportStudentPlanAsync"));
    }

    private void reportStudentPlanSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getSportInfoCaller().reportStudentPlanSync(buildReportStudentPlanRequest()),
                booleanCallback("reportStudentPlanSync"));
    }

    private void reportStudentMeetAsync() {
        AdlService.getService().getSportInfoCaller().reportStudentMeetAsync(
                RequestScope.of(this), buildReportStudentMeetRequest(), booleanCallback("reportStudentMeetAsync"));
    }

    private void reportStudentMeetSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getSportInfoCaller().reportStudentMeetSync(buildReportStudentMeetRequest()),
                booleanCallback("reportStudentMeetSync"));
    }

    private void reportStudentCompetitionAsync() {
        AdlService.getService().getSportInfoCaller().reportStudentCompetitionAsync(
                RequestScope.of(this), buildReportStudentCompetitionRequest(), booleanCallback("reportStudentCompetitionAsync"));
    }

    private void reportStudentCompetitionSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getSportInfoCaller().reportStudentCompetitionSync(buildReportStudentCompetitionRequest()),
                booleanCallback("reportStudentCompetitionSync"));
    }

    private void reportStudentAllSportAsync() {
        AdlService.getService().getSportInfoCaller().reportStudentAllSportAsync(
                RequestScope.of(this), buildReportStudentSportRequest(), booleanCallback("reportStudentAllSportAsync"));
    }

    private void reportStudentAllSportSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getSportInfoCaller().reportStudentAllSportSync(buildReportStudentSportRequest()),
                booleanCallback("reportStudentAllSportSync"));
    }

    private void getOrgRecordPageAsync() {
        AdlService.getService().getSportInfoCaller().getOrgRecordPageAsync(
                RequestScope.of(this), OrgRecordPageRequest.builder().build(), new RequestCallback<BasePageData<OrgRecordData>>() {
                    @Override
                    public void onSuccess(BasePageData<OrgRecordData> data) {
                        Log.d(TAG, "SportInfoCallerTestActivity::getOrgRecordPageAsync::onSuccess : data : " + data);
                        resultView.setText(String.valueOf(data));
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "SportInfoCallerTestActivity::getOrgRecordPageAsync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "SportInfoCallerTestActivity::getOrgRecordPageAsync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getOrgRecordPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getSportInfoCaller().getOrgRecordPageSync(OrgRecordPageRequest.builder().build()),
                new RequestCallback<BasePageData<OrgRecordData>>() {
                    @Override
                    public void onSuccess(BasePageData<OrgRecordData> data) {
                        Log.d(TAG, "SportInfoCallerTestActivity::getOrgRecordPageSync::onSuccess : data : " + data);
                        resultView.setText(String.valueOf(data));
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "SportInfoCallerTestActivity::getOrgRecordPageSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "SportInfoCallerTestActivity::getOrgRecordPageSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getAccountRecordPageAsync() {
        AdlService.getService().getSportInfoCaller().getAccountRecordPageAsync(
                RequestScope.of(this), buildAccountRecordPageRequest(), new RequestCallback<BasePageData<AccountRecordData>>() {
                    @Override
                    public void onSuccess(BasePageData<AccountRecordData> data) {
                        Log.d(TAG, "SportInfoCallerTestActivity::getAccountRecordPageAsync::onSuccess : data : " + data);
                        resultView.setText(String.valueOf(data));
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "SportInfoCallerTestActivity::getAccountRecordPageAsync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "SportInfoCallerTestActivity::getAccountRecordPageAsync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getAccountRecordPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getSportInfoCaller().getAccountRecordPageSync(buildAccountRecordPageRequest()),
                new RequestCallback<BasePageData<AccountRecordData>>() {
                    @Override
                    public void onSuccess(BasePageData<AccountRecordData> data) {
                        Log.d(TAG, "SportInfoCallerTestActivity::getAccountRecordPageSync::onSuccess : data : " + data);
                        resultView.setText(String.valueOf(data));
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "SportInfoCallerTestActivity::getAccountRecordPageSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "SportInfoCallerTestActivity::getAccountRecordPageSync::onError : error : " + error);
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
