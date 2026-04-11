package com.ai.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.web.request.RequestScope;
import com.adl.service.web.request.SunshineRunningCompetitionRecordPageRequest;
import com.adl.service.web.request.SunshineRunningSportDetailPageRequest;
import com.adl.service.web.request.TeachCourseButtonClickCountRequest;
import com.adl.service.web.request.TeachCourseDataDatesRequest;
import com.adl.service.web.request.TeachCourseRequest;
import com.adl.service.web.response.BasePageData;
import com.adl.service.web.response.ClassroomCompetitionRecordData;
import com.adl.service.web.response.SunshineRunningSportDetailData;
import com.adl.service.web.response.TeachCourseDetailData;
import com.adl.ts.general.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 演示在独立界面中调用 SDK「学生列表」接口。
 */
public class V3BigScreenH5CallerTestActivity extends AppCompatActivity {

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

        createButton("getTeachCourseDetailListAsync").setOnClickListener(v -> getTeachCourseDetailListAsync());
        createButton("getTeachCourseDetailListSync").setOnClickListener(v -> getTeachCourseDetailListSync());
        createButton("getTeachCourseDataDatesAsync").setOnClickListener(v -> getTeachCourseDataDatesAsync());
        createButton("getTeachCourseDataDatesSync").setOnClickListener(v -> getTeachCourseDataDatesSync());
        createButton("getTeachCourseTimesAsync").setOnClickListener(v -> getTeachCourseTimesAsync());
        createButton("getTeachCourseTimesSync").setOnClickListener(v -> getTeachCourseTimesSync());
        createButton("getTeachCourseButtonClickCountAsync").setOnClickListener(v -> getTeachCourseButtonClickCountAsync());
        createButton("getTeachCourseButtonClickCountSync").setOnClickListener(v -> getTeachCourseButtonClickCountSync());
        createButton("getSunshineRunningSportDetailPageAsync").setOnClickListener(v -> getSunshineRunningSportDetailPageAsync());
        createButton("getSunshineRunningSportDetailPageSync").setOnClickListener(v -> getSunshineRunningSportDetailPageSync());
        createButton("getSunshineRunningCompetitionRecordPageAsync").setOnClickListener(v -> getSunshineRunningCompetitionRecordPageAsync());
        createButton("getSunshineRunningCompetitionRecordPageSync").setOnClickListener(v -> getSunshineRunningCompetitionRecordPageSync());
    }

    private TeachCourseRequest buildTeachCourseRequest() {
        return TeachCourseRequest.builder()
                .build();
    }

    private TeachCourseDataDatesRequest buildTeachCourseDataDatesRequest() {
        return TeachCourseDataDatesRequest.builder()
                .build();
    }

    private TeachCourseButtonClickCountRequest buildTeachCourseButtonClickCountRequest() {
        return TeachCourseButtonClickCountRequest.builder("123", "123456789").build();
    }

    private SunshineRunningSportDetailPageRequest buildSunshineRunningSportDetailPageRequest() {
        return SunshineRunningSportDetailPageRequest.builder()
                .build();
    }

    private SunshineRunningCompetitionRecordPageRequest buildSunshineRunningCompetitionRecordPageRequest() {
        return SunshineRunningCompetitionRecordPageRequest.builder()
                .orgId("1858352135200641024")
                .classId("12345679")
                .build();
    }

    private void getTeachCourseDetailListAsync() {
        TeachCourseRequest request = buildTeachCourseRequest();
        AdlService.getService().getBigScreenH5Caller().getTeachCourseDetailListAsync(RequestScope.of(this), request, new RequestCallback<List<TeachCourseDetailData>>() {
            @Override
            public void onSuccess(List<TeachCourseDetailData> data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDetailListAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDetailListAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDetailListAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDetailListAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getTeachCourseDetailListSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenH5Caller().getTeachCourseDetailListSync(buildTeachCourseRequest()),
                new RequestCallback<List<TeachCourseDetailData>>() {
                    @Override
                    public void onSuccess(List<TeachCourseDetailData> data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDetailListSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDetailListSync::onSuccess : data is null");
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDetailListSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDetailListSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getTeachCourseDataDatesAsync() {
        TeachCourseDataDatesRequest request = buildTeachCourseDataDatesRequest();
        AdlService.getService().getBigScreenH5Caller().getTeachCourseDataDatesAsync(RequestScope.of(this), request, new RequestCallback<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDataDatesAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDataDatesAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDataDatesAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDataDatesAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDataDatesAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getTeachCourseDataDatesSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenH5Caller().getTeachCourseDataDatesSync(buildTeachCourseDataDatesRequest()),
                new RequestCallback<List<String>>() {
                    @Override
                    public void onSuccess(List<String> data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDataDatesSync::onSuccess : data size : " + data.size());
                            Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDataDatesSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDataDatesSync::onSuccess : data is null");
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDataDatesSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseDataDatesSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getTeachCourseTimesAsync() {
        TeachCourseRequest request = buildTeachCourseRequest();
        AdlService.getService().getBigScreenH5Caller().getTeachCourseTimesAsync(RequestScope.of(this), request, new RequestCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseTimesAsync::onSuccess : data : " + data);
                    resultView.setText(String.valueOf(data));
                } else {
                    Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseTimesAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseTimesAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseTimesAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getTeachCourseTimesSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenH5Caller().getTeachCourseTimesSync(buildTeachCourseRequest()),
                new RequestCallback<Integer>() {
                    @Override
                    public void onSuccess(Integer data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseTimesSync::onSuccess : data : " + data);
                            resultView.setText(String.valueOf(data));
                        } else {
                            Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseTimesSync::onSuccess : data is null");
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseTimesSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseTimesSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getTeachCourseButtonClickCountAsync() {
        TeachCourseButtonClickCountRequest request = buildTeachCourseButtonClickCountRequest();
        AdlService.getService().getBigScreenH5Caller().getTeachCourseButtonClickCountAsync(RequestScope.of(this), request, new RequestCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseButtonClickCountAsync::onSuccess : data : " + data);
                    resultView.setText(String.valueOf(data));
                } else {
                    Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseButtonClickCountAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseButtonClickCountAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseButtonClickCountAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getTeachCourseButtonClickCountSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenH5Caller().getTeachCourseButtonClickCountSync(buildTeachCourseButtonClickCountRequest()),
                new RequestCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseButtonClickCountSync::onSuccess : data : " + data);
                            resultView.setText(String.valueOf(data));
                        } else {
                            Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseButtonClickCountSync::onSuccess : data is null");
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseButtonClickCountSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenH5CallerTestActivity::getTeachCourseButtonClickCountSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getSunshineRunningSportDetailPageAsync() {
        SunshineRunningSportDetailPageRequest request = buildSunshineRunningSportDetailPageRequest();
        AdlService.getService().getBigScreenH5Caller().getSunshineRunningSportDetailPageAsync(RequestScope.of(this), request, new RequestCallback<BasePageData<SunshineRunningSportDetailData>>() {
            @Override
            public void onSuccess(BasePageData<SunshineRunningSportDetailData> data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningSportDetailPageAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningSportDetailPageAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningSportDetailPageAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningSportDetailPageAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getSunshineRunningSportDetailPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenH5Caller().getSunshineRunningSportDetailPageSync(buildSunshineRunningSportDetailPageRequest()),
                new RequestCallback<BasePageData<SunshineRunningSportDetailData>>() {
                    @Override
                    public void onSuccess(BasePageData<SunshineRunningSportDetailData> data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningSportDetailPageSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningSportDetailPageSync::onSuccess : data is null");
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningSportDetailPageSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningSportDetailPageSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getSunshineRunningCompetitionRecordPageAsync() {
        SunshineRunningCompetitionRecordPageRequest request = buildSunshineRunningCompetitionRecordPageRequest();
        AdlService.getService().getBigScreenH5Caller().getSunshineRunningCompetitionRecordPageAsync(RequestScope.of(this), request, new RequestCallback<BasePageData<ClassroomCompetitionRecordData>>() {
            @Override
            public void onSuccess(BasePageData<ClassroomCompetitionRecordData> data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningCompetitionRecordPageAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningCompetitionRecordPageAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningCompetitionRecordPageAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningCompetitionRecordPageAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getSunshineRunningCompetitionRecordPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenH5Caller().getSunshineRunningCompetitionRecordPageSync(buildSunshineRunningCompetitionRecordPageRequest()),
                new RequestCallback<BasePageData<ClassroomCompetitionRecordData>>() {
                    @Override
                    public void onSuccess(BasePageData<ClassroomCompetitionRecordData> data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningCompetitionRecordPageSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningCompetitionRecordPageSync::onSuccess : data is null");
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningCompetitionRecordPageSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }

                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenH5CallerTestActivity::getSunshineRunningCompetitionRecordPageSync::onError : error : " + error);
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
