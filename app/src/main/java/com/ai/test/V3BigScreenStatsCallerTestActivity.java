package com.ai.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.http.request.BestRankTopRequest;
import com.adl.service.http.request.CompetitionRankRequest;
import com.adl.service.http.request.MorePeopleRecordRankRequest;
import com.adl.service.http.request.MorePeopleVictoryRankRequest;
import com.adl.service.http.request.OverviewRequest;
import com.adl.service.http.request.PersonRankRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.http.request.SportRankRequest;
import com.adl.service.http.request.SumScoreRankRequest;
import com.adl.service.http.request.WarRecordRankRequest;
import com.adl.service.data.BestRankTopData;
import com.adl.service.data.CompetitionRankData;
import com.adl.service.data.MorePeopleRecordRankData;
import com.adl.service.data.MorePeopleVictoryRankData;
import com.adl.service.data.OverviewData;
import com.adl.service.data.PersonRankData;
import com.adl.service.data.SportRankKingData;
import com.adl.service.data.SportSkuRankData;
import com.adl.service.data.SumScoreRankData;
import com.adl.service.data.WarRecordRankData;
import com.adl.ts.general.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/** 演示在独立界面中调用 SDK「BigScreenStatsCaller」接口。 */
public class V3BigScreenStatsCallerTestActivity extends AppCompatActivity {

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

        createButton("getWarRecordRankAsync").setOnClickListener(v -> getWarRecordRankAsync());
        createButton("getWarRecordRankSync").setOnClickListener(v -> getWarRecordRankSync());
        createButton("getPersonRankAsync").setOnClickListener(v -> getPersonRankAsync());
        createButton("getPersonRankSync").setOnClickListener(v -> getPersonRankSync());
        createButton("getMorePeopleVictoryRankAsync").setOnClickListener(v -> getMorePeopleVictoryRankAsync());
        createButton("getMorePeopleVictoryRankSync").setOnClickListener(v -> getMorePeopleVictoryRankSync());
        createButton("getMorePeopleRecordRankAsync").setOnClickListener(v -> getMorePeopleRecordRankAsync());
        createButton("getMorePeopleRecordRankSync").setOnClickListener(v -> getMorePeopleRecordRankSync());
        createButton("getOverviewAsync").setOnClickListener(v -> getOverviewAsync());
        createButton("getOverviewSync").setOnClickListener(v -> getOverviewSync());
        createButton("getCompetitionRankAsync").setOnClickListener(v -> getCompetitionRankAsync());
        createButton("getCompetitionRankSync").setOnClickListener(v -> getCompetitionRankSync());
        createButton("sumScoreRankAsync").setOnClickListener(v -> sumScoreRankAsync());
        createButton("sumScoreRankSync").setOnClickListener(v -> sumScoreRankSync());
        createButton("getSportSkuRankAsync").setOnClickListener(v -> getSportSkuRankAsync());
        createButton("getSportSkuRankSync").setOnClickListener(v -> getSportSkuRankSync());
        createButton("getSportRankKingAsync").setOnClickListener(v -> getSportRankKingAsync());
        createButton("getSportRankKingSync").setOnClickListener(v -> getSportRankKingSync());
        createButton("getPhysicalTrainingRankAsync").setOnClickListener(v -> getPhysicalTrainingRankAsync());
        createButton("getPhysicalTrainingRankSync").setOnClickListener(v -> getPhysicalTrainingRankSync());
        createButton("getExerciseSumTimeRankAsync").setOnClickListener(v -> getExerciseSumTimeRankAsync());
        createButton("getExerciseSumTimeRankSync").setOnClickListener(v -> getExerciseSumTimeRankSync());
        createButton("getBestRankTopAsync").setOnClickListener(v -> getBestRankTopAsync());
        createButton("getBestRankTopSync").setOnClickListener(v -> getBestRankTopSync());

    }

    private WarRecordRankRequest buildWarRecordRankRequest() {
        return WarRecordRankRequest.builder()
                .sportSkuId("4501010005")
                .rankNumber(5L)
                .sportSceneCodes(Collections.singletonList("1003"))
                .build();
    }

    private PersonRankRequest buildPersonRankRequest() {
        return PersonRankRequest.builder("123456789", "0001010001")
                .timeType(1)
                .build();
    }

    private MorePeopleVictoryRankRequest buildMorePeopleVictoryRankRequest() {
        return MorePeopleVictoryRankRequest.builder("0001010001")
                .maxRank(100)
                .top(10)
                .build();
    }

    private MorePeopleRecordRankRequest buildMorePeopleRecordRankRequest() {
        return MorePeopleRecordRankRequest.builder()
                .sportSkuId("0001010001")
                .orgId("1858352135200641024")
                .rankNumber(100L)
                .build();
    }

    private OverviewRequest buildOverviewRequest() {
        return OverviewRequest.builder()
                .build();
    }

    private CompetitionRankRequest buildCompetitionRankRequest() {
        return CompetitionRankRequest.builder()
                .competitionId("123456789")
                .rankType("1")
                .sportSkuId("0001010001")
                .build();
    }

    private SumScoreRankRequest buildSumScoreRankRequest() {
        return SumScoreRankRequest.builder("0001010001", 5)
                .build();
    }

    private SportRankRequest buildSportRankRequest() {
        return SportRankRequest.builder("101101", "5")
                .rankNumber(5L)
                .sportSceneCodes(Arrays.asList("1003", "1002", "1001"))
                .build();
    }

    private SportRankRequest buildSportRankKing() {
        return SportRankRequest.builder("101101", "5")
                .rankNumber(7L)
                .sportSceneCodes(Arrays.asList("1003", "1002", "1001"))
                .dayType("3")
                .build();
    }

    private SportRankRequest buildPhysicalTrainingRankRequest() {
        return SportRankRequest.builder("101101", "5")
                .rankNumber(20L)
                .sportSkuId("4301010001")
                .sportSceneCodes(Arrays.asList("1003", "1002", "1001"))
                .dayType("3")
                .build();
    }

    private SportRankRequest buildExerciseSumTimeRankRequest() {
        return SportRankRequest.builder("101101", "5")
                .rankNumber(7L)
                .sportSceneCodes(Arrays.asList("1003", "1002", "1001", "3004"))
                .dayType("3")
                .build();
    }

    private BestRankTopRequest buildBestRankTopRequest() {
        return BestRankTopRequest.builder("0001010001", 5)
                .build();
    }

    private void getWarRecordRankAsync() {
        WarRecordRankRequest request = buildWarRecordRankRequest();
        AdlService.getService().getBigScreenStatsCaller().getWarRecordRankAsync(RequestScope.of(this), request, new RequestCallback<List<WarRecordRankData>>() {
            @Override
            public void onSuccess(List<WarRecordRankData> data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getWarRecordRankAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getWarRecordRankAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getWarRecordRankAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getWarRecordRankAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getWarRecordRankAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getWarRecordRankSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenStatsCaller().getWarRecordRankSync(buildWarRecordRankRequest()),
                new RequestCallback<List<WarRecordRankData>>() {
                    @Override
                    public void onSuccess(List<WarRecordRankData> data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getWarRecordRankSync::onSuccess : data size : " + data.size());
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getWarRecordRankSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getWarRecordRankSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getWarRecordRankSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getWarRecordRankSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getPersonRankAsync() {
        PersonRankRequest request = buildPersonRankRequest();
        AdlService.getService().getBigScreenStatsCaller().getPersonRankAsync(RequestScope.of(this), request, new RequestCallback<PersonRankData>() {
            @Override
            public void onSuccess(PersonRankData data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getPersonRankAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getPersonRankAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getPersonRankAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getPersonRankAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getPersonRankSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenStatsCaller().getPersonRankSync(buildPersonRankRequest()),
                new RequestCallback<PersonRankData>() {
                    @Override
                    public void onSuccess(PersonRankData data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getPersonRankSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getPersonRankSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getPersonRankSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getPersonRankSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getMorePeopleVictoryRankAsync() {
        MorePeopleVictoryRankRequest request = buildMorePeopleVictoryRankRequest();
        AdlService.getService().getBigScreenStatsCaller().getMorePeopleVictoryRankAsync(RequestScope.of(this), request, new RequestCallback<List<MorePeopleVictoryRankData>>() {
            @Override
            public void onSuccess(List<MorePeopleVictoryRankData> data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleVictoryRankAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleVictoryRankAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleVictoryRankAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleVictoryRankAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleVictoryRankAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getMorePeopleVictoryRankSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenStatsCaller().getMorePeopleVictoryRankSync(buildMorePeopleVictoryRankRequest()),
                new RequestCallback<List<MorePeopleVictoryRankData>>() {
                    @Override
                    public void onSuccess(List<MorePeopleVictoryRankData> data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleVictoryRankSync::onSuccess : data size : " + data.size());
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleVictoryRankSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleVictoryRankSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleVictoryRankSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleVictoryRankSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getMorePeopleRecordRankAsync() {
        MorePeopleRecordRankRequest request = buildMorePeopleRecordRankRequest();
        AdlService.getService().getBigScreenStatsCaller().getMorePeopleRecordRankAsync(RequestScope.of(this), request, new RequestCallback<List<MorePeopleRecordRankData>>() {
            @Override
            public void onSuccess(List<MorePeopleRecordRankData> data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleRecordRankAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleRecordRankAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleRecordRankAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleRecordRankAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleRecordRankAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getMorePeopleRecordRankSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenStatsCaller().getMorePeopleRecordRankSync(buildMorePeopleRecordRankRequest()),
                new RequestCallback<List<MorePeopleRecordRankData>>() {
                    @Override
                    public void onSuccess(List<MorePeopleRecordRankData> data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleRecordRankSync::onSuccess : data size : " + data.size());
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleRecordRankSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleRecordRankSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleRecordRankSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getMorePeopleRecordRankSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getOverviewAsync() {
        OverviewRequest request = buildOverviewRequest();
        AdlService.getService().getBigScreenStatsCaller().getOverviewAsync(RequestScope.of(this), request, new RequestCallback<OverviewData>() {
            @Override
            public void onSuccess(OverviewData data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getOverviewAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getOverviewAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getOverviewAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getOverviewAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getOverviewSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenStatsCaller().getOverviewSync(buildOverviewRequest()),
                new RequestCallback<OverviewData>() {
                    @Override
                    public void onSuccess(OverviewData data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getOverviewSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getOverviewSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getOverviewSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getOverviewSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getCompetitionRankAsync() {
        CompetitionRankRequest request = buildCompetitionRankRequest();
        AdlService.getService().getBigScreenStatsCaller().getCompetitionRankAsync(RequestScope.of(this), request, new RequestCallback<CompetitionRankData>() {
            @Override
            public void onSuccess(CompetitionRankData data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getCompetitionRankAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getCompetitionRankAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getCompetitionRankAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getCompetitionRankAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getCompetitionRankSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenStatsCaller().getCompetitionRankSync(buildCompetitionRankRequest()),
                new RequestCallback<CompetitionRankData>() {
                    @Override
                    public void onSuccess(CompetitionRankData data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getCompetitionRankSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getCompetitionRankSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getCompetitionRankSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getCompetitionRankSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void sumScoreRankAsync() {
        SumScoreRankRequest request = buildSumScoreRankRequest();
        AdlService.getService().getBigScreenStatsCaller().sumScoreRankAsync(RequestScope.of(this), request, new RequestCallback<List<SumScoreRankData>>() {
            @Override
            public void onSuccess(List<SumScoreRankData> data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::sumScoreRankAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::sumScoreRankAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::sumScoreRankAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::sumScoreRankAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::sumScoreRankAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void sumScoreRankSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenStatsCaller().sumScoreRankSync(buildSumScoreRankRequest()),
                new RequestCallback<List<SumScoreRankData>>() {
                    @Override
                    public void onSuccess(List<SumScoreRankData> data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::sumScoreRankSync::onSuccess : data size : " + data.size());
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::sumScoreRankSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::sumScoreRankSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::sumScoreRankSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::sumScoreRankSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getSportSkuRankAsync() {
        SportRankRequest request = buildSportRankRequest();
        AdlService.getService().getBigScreenStatsCaller().getSportSkuRankAsync(RequestScope.of(this), request, new RequestCallback<List<SportSkuRankData>>() {
            @Override
            public void onSuccess(List<SportSkuRankData> data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportSkuRankAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportSkuRankAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportSkuRankAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportSkuRankAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportSkuRankAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getSportSkuRankSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenStatsCaller().getSportSkuRankSync(buildSportRankRequest()),
                new RequestCallback<List<SportSkuRankData>>() {
                    @Override
                    public void onSuccess(List<SportSkuRankData> data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportSkuRankSync::onSuccess : data size : " + data.size());
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportSkuRankSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportSkuRankSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportSkuRankSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportSkuRankSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getSportRankKingAsync() {
        SportRankRequest request = buildSportRankKing();
        AdlService.getService().getBigScreenStatsCaller().getSportRankKingAsync(RequestScope.of(this), request, new RequestCallback<List<SportRankKingData>>() {
            @Override
            public void onSuccess(List<SportRankKingData> data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportRankKingAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportRankKingAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportRankKingAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportRankKingAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportRankKingAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getSportRankKingSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenStatsCaller().getSportRankKingSync(buildSportRankKing()),
                new RequestCallback<List<SportRankKingData>>() {
                    @Override
                    public void onSuccess(List<SportRankKingData> data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportRankKingSync::onSuccess : data size : " + data.size());
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportRankKingSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportRankKingSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportRankKingSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getSportRankKingSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getPhysicalTrainingRankAsync() {
        SportRankRequest request = buildPhysicalTrainingRankRequest();
        AdlService.getService().getBigScreenStatsCaller().getPhysicalTrainingRankAsync(RequestScope.of(this), request, new RequestCallback<List<SportRankKingData>>() {
            @Override
            public void onSuccess(List<SportRankKingData> data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getPhysicalTrainingRankAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getPhysicalTrainingRankAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getPhysicalTrainingRankAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getPhysicalTrainingRankAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getPhysicalTrainingRankAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getPhysicalTrainingRankSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenStatsCaller().getPhysicalTrainingRankSync(buildPhysicalTrainingRankRequest()),
                new RequestCallback<List<SportRankKingData>>() {
                    @Override
                    public void onSuccess(List<SportRankKingData> data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getPhysicalTrainingRankSync::onSuccess : data size : " + data.size());
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getPhysicalTrainingRankSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getPhysicalTrainingRankSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getPhysicalTrainingRankSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getPhysicalTrainingRankSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getExerciseSumTimeRankAsync() {
        SportRankRequest request = buildExerciseSumTimeRankRequest();
        AdlService.getService().getBigScreenStatsCaller().getExerciseSumTimeRankAsync(RequestScope.of(this), request, new RequestCallback<List<SportRankKingData>>() {
            @Override
            public void onSuccess(List<SportRankKingData> data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getExerciseSumTimeRankAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getExerciseSumTimeRankAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getExerciseSumTimeRankAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getExerciseSumTimeRankAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getExerciseSumTimeRankAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getExerciseSumTimeRankSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenStatsCaller().getExerciseSumTimeRankSync(buildExerciseSumTimeRankRequest()),
                new RequestCallback<List<SportRankKingData>>() {
                    @Override
                    public void onSuccess(List<SportRankKingData> data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getExerciseSumTimeRankSync::onSuccess : data size : " + data.size());
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getExerciseSumTimeRankSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getExerciseSumTimeRankSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getExerciseSumTimeRankSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getExerciseSumTimeRankSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getBestRankTopAsync() {
        BestRankTopRequest request = buildBestRankTopRequest();
        AdlService.getService().getBigScreenStatsCaller().getBestRankTopAsync(RequestScope.of(this), request, new RequestCallback<List<BestRankTopData>>() {
            @Override
            public void onSuccess(List<BestRankTopData> data) {
                if (data != null) {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getBestRankTopAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getBestRankTopAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BigScreenStatsCallerTestActivity::getBestRankTopAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getBestRankTopAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BigScreenStatsCallerTestActivity::getBestRankTopAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getBestRankTopSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBigScreenStatsCaller().getBestRankTopSync(buildBestRankTopRequest()),
                new RequestCallback<List<BestRankTopData>>() {
                    @Override
                    public void onSuccess(List<BestRankTopData> data) {
                        if (data != null) {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getBestRankTopSync::onSuccess : data size : " + data.size());
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getBestRankTopSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BigScreenStatsCallerTestActivity::getBestRankTopSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getBestRankTopSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BigScreenStatsCallerTestActivity::getBestRankTopSync::onError : error : " + error);
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
