package com.ai.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.web.request.BaseGroup;
import com.adl.service.web.request.BaseGroupAccountInfo;
import com.adl.service.web.request.CompetitionPageRequest;
import com.adl.service.web.request.CompetitionRankDetailRequest;
import com.adl.service.web.request.DownloadGroupTeamDetailPageRequest;
import com.adl.service.web.request.DownloadGroupTeamPageRequest;
import com.adl.service.web.request.GetGroupRequest;
import com.adl.service.web.request.JoinCompetitionRankRequest;
import com.adl.service.web.request.RequestScope;
import com.adl.service.web.request.ResInfoRequest;
import com.adl.service.web.request.ResPageRequest;
import com.adl.service.web.request.ResTypeListRequest;
import com.adl.service.web.request.SaveGroupRequest;
import com.adl.service.web.request.SportMeetPageRequest;
import com.adl.service.web.request.TrainPlanInfoPageRequest;
import com.adl.service.web.request.TrainPlanOneDayProjectListRequest;
import com.adl.service.web.response.BasePageData;
import com.adl.service.web.response.CompetitionData;
import com.adl.service.web.response.CompetitionRankDetailData;
import com.adl.service.web.response.GroupData;
import com.adl.service.web.response.GroupTeamData;
import com.adl.service.web.response.GroupTeamDetailData;
import com.adl.service.web.response.ResData;
import com.adl.service.web.response.ResTypeData;
import com.adl.service.web.response.SportMeetData;
import com.adl.service.web.response.TrainPlanInfoData;
import com.adl.service.web.response.TrainPlanOneDayProjectData;
import com.adl.ts.general.R;

import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/** 演示在独立界面中调用 SDK「BusinessCaller」接口。 */
public class V3BusinessCallerTestActivity extends AppCompatActivity {

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

        createButton("saveGroupAsync").setOnClickListener(v -> saveGroupAsync());
        createButton("saveGroupSync").setOnClickListener(v -> saveGroupSync());
        createButton("getGroupAsync").setOnClickListener(v -> getGroupAsync());
        createButton("getGroupSync").setOnClickListener(v -> getGroupSync());
        createButton("getTrainPlanOneDayProjectListAsync").setOnClickListener(v -> getTrainPlanOneDayProjectListAsync());
        createButton("getTrainPlanOneDayProjectListSync").setOnClickListener(v -> getTrainPlanOneDayProjectListSync());
        createButton("getTrainPlanInfoPageAsync").setOnClickListener(v -> getTrainPlanInfoPageAsync());
        createButton("getTrainPlanInfoPageSync").setOnClickListener(v -> getTrainPlanInfoPageSync());
        createButton("joinCompetitionRankAsync").setOnClickListener(v -> joinCompetitionRankAsync());
        createButton("joinCompetitionRankSync").setOnClickListener(v -> joinCompetitionRankSync());
        createButton("getCompetitionRankDetailAsync").setOnClickListener(v -> getCompetitionRankDetailAsync());
        createButton("getCompetitionRankDetailSync").setOnClickListener(v -> getCompetitionRankDetailSync());
        createButton("getCompetitionPageAsync").setOnClickListener(v -> getCompetitionPageAsync());
        createButton("getCompetitionPageSync").setOnClickListener(v -> getCompetitionPageSync());
        createButton("getSportMeetPageAsync").setOnClickListener(v -> getSportMeetPageAsync());
        createButton("getSportMeetPageSync").setOnClickListener(v -> getSportMeetPageSync());
        createButton("downloadGroupTeamPageAsync").setOnClickListener(v -> downloadGroupTeamPageAsync());
        createButton("downloadGroupTeamPageSync").setOnClickListener(v -> downloadGroupTeamPageSync());
        createButton("downloadGroupTeamDetailPageAsync").setOnClickListener(v -> downloadGroupTeamDetailPageAsync());
        createButton("downloadGroupTeamDetailPageSync").setOnClickListener(v -> downloadGroupTeamDetailPageSync());
        createButton("getResTypeListAsync").setOnClickListener(v -> getResTypeListAsync());
        createButton("getResTypeListSync").setOnClickListener(v -> getResTypeListSync());
        createButton("getResPageAsync").setOnClickListener(v -> getResPageAsync());
        createButton("getResPageSync").setOnClickListener(v -> getResPageSync());
        createButton("getResInfoAsync").setOnClickListener(v -> getResInfoAsync());
        createButton("getResInfoSync").setOnClickListener(v -> getResInfoSync());

    }

    private SaveGroupRequest buildSaveGroupRequest() {
        return SaveGroupRequest.builder("2001", Collections.singletonList(
                new BaseGroup().setGroupId("1111").setGroupClassId("2222").setGroupName("testGroup").setGroupAccountInfos(Collections.singletonList(new BaseGroupAccountInfo()))))
                .build();
    }

    private GetGroupRequest buildGetGroupRequest() {
        return GetGroupRequest.builder()
                .build();
    }

    private TrainPlanOneDayProjectListRequest buildTrainPlanOneDayProjectListRequest() {
        return TrainPlanOneDayProjectListRequest.builder("", "")
                .build();
    }

    private TrainPlanInfoPageRequest buildTrainPlanInfoPageRequest() {
        return TrainPlanInfoPageRequest.builder()
                .build();
    }

    private JoinCompetitionRankRequest buildJoinCompetitionRankRequest() {
        return JoinCompetitionRankRequest.builder()
                .id("2041833871287713792")
                .accountId("123456789")
                .classId("123456789")
                .build();
    }

    private CompetitionRankDetailRequest buildCompetitionRankDetailRequest() {
        return CompetitionRankDetailRequest.builder()
                .build();
    }

    private CompetitionPageRequest buildCompetitionPageRequest() {
        return CompetitionPageRequest.builder()
                .build();
    }

    private SportMeetPageRequest buildSportMeetPageRequest() {
        return SportMeetPageRequest.builder()
                .build();
    }

    private DownloadGroupTeamPageRequest buildDownloadGroupTeamPageRequest() {
        return DownloadGroupTeamPageRequest.builder()
                .build();
    }

    private DownloadGroupTeamDetailPageRequest buildDownloadGroupTeamDetailPageRequest() {
        return DownloadGroupTeamDetailPageRequest.builder()
                .build();
    }

    private ResTypeListRequest buildResTypeListRequest() {
        return ResTypeListRequest.builder()
                .resType(1)
                .build();
    }

    private ResPageRequest buildResPageRequest() {
        return ResPageRequest.builder("")
                .build();
    }

    private ResInfoRequest buildResInfoRequest() {
        return ResInfoRequest.builder()
                .id("2041833871287713792")
                .build();
    }

    private void saveGroupAsync() {
        SaveGroupRequest request = buildSaveGroupRequest();
        AdlService.getService().getBusinessCaller().saveGroupAsync(RequestScope.of(this), request, new RequestCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                Log.d(TAG, "BusinessCallerTestActivity::saveGroupAsync::onSuccess : data : " + data);
                resultView.setText(String.valueOf(data));
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BusinessCallerTestActivity::saveGroupAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BusinessCallerTestActivity::saveGroupAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void saveGroupSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBusinessCaller().saveGroupSync(buildSaveGroupRequest()),
                new RequestCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean data) {
                        Log.d(TAG, "BusinessCallerTestActivity::saveGroupSync::onSuccess : data : " + data);
                        resultView.setText(String.valueOf(data));
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BusinessCallerTestActivity::saveGroupSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BusinessCallerTestActivity::saveGroupSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getGroupAsync() {
        GetGroupRequest request = buildGetGroupRequest();
        AdlService.getService().getBusinessCaller().getGroupAsync(RequestScope.of(this), request, new RequestCallback<GroupData>() {
            @Override
            public void onSuccess(GroupData data) {
                if (data != null) {
                    Log.d(TAG, "BusinessCallerTestActivity::getGroupAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BusinessCallerTestActivity::getGroupAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BusinessCallerTestActivity::getGroupAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BusinessCallerTestActivity::getGroupAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getGroupSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBusinessCaller().getGroupSync(buildGetGroupRequest()),
                new RequestCallback<GroupData>() {
                    @Override
                    public void onSuccess(GroupData data) {
                        if (data != null) {
                            Log.d(TAG, "BusinessCallerTestActivity::getGroupSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BusinessCallerTestActivity::getGroupSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BusinessCallerTestActivity::getGroupSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BusinessCallerTestActivity::getGroupSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getTrainPlanOneDayProjectListAsync() {
        TrainPlanOneDayProjectListRequest request = buildTrainPlanOneDayProjectListRequest();
        AdlService.getService().getBusinessCaller().getTrainPlanOneDayProjectListAsync(RequestScope.of(this), request, new RequestCallback<List<TrainPlanOneDayProjectData>>() {
            @Override
            public void onSuccess(List<TrainPlanOneDayProjectData> data) {
                if (data != null) {
                    Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanOneDayProjectListAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanOneDayProjectListAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanOneDayProjectListAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanOneDayProjectListAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanOneDayProjectListAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getTrainPlanOneDayProjectListSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBusinessCaller().getTrainPlanOneDayProjectListSync(buildTrainPlanOneDayProjectListRequest()),
                new RequestCallback<List<TrainPlanOneDayProjectData>>() {
                    @Override
                    public void onSuccess(List<TrainPlanOneDayProjectData> data) {
                        if (data != null) {
                            Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanOneDayProjectListSync::onSuccess : data size : " + data.size());
                            Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanOneDayProjectListSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanOneDayProjectListSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanOneDayProjectListSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanOneDayProjectListSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getTrainPlanInfoPageAsync() {
        TrainPlanInfoPageRequest request = buildTrainPlanInfoPageRequest();
        AdlService.getService().getBusinessCaller().getTrainPlanInfoPageAsync(RequestScope.of(this), request, new RequestCallback<BasePageData<TrainPlanInfoData>>() {
            @Override
            public void onSuccess(BasePageData<TrainPlanInfoData> data) {
                if (data != null) {
                    Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanInfoPageAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanInfoPageAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanInfoPageAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanInfoPageAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getTrainPlanInfoPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBusinessCaller().getTrainPlanInfoPageSync(buildTrainPlanInfoPageRequest()),
                new RequestCallback<BasePageData<TrainPlanInfoData>>() {
                    @Override
                    public void onSuccess(BasePageData<TrainPlanInfoData> data) {
                        if (data != null) {
                            Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanInfoPageSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanInfoPageSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanInfoPageSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BusinessCallerTestActivity::getTrainPlanInfoPageSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void joinCompetitionRankAsync() {
        JoinCompetitionRankRequest request = buildJoinCompetitionRankRequest();
        AdlService.getService().getBusinessCaller().joinCompetitionRankAsync(RequestScope.of(this), request, new RequestCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                Log.d(TAG, "BusinessCallerTestActivity::joinCompetitionRankAsync::onSuccess : data : " + data);
                resultView.setText(String.valueOf(data));
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BusinessCallerTestActivity::joinCompetitionRankAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BusinessCallerTestActivity::joinCompetitionRankAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void joinCompetitionRankSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBusinessCaller().joinCompetitionRankSync(buildJoinCompetitionRankRequest()),
                new RequestCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean data) {
                        Log.d(TAG, "BusinessCallerTestActivity::joinCompetitionRankSync::onSuccess : data : " + data);
                        resultView.setText(String.valueOf(data));
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BusinessCallerTestActivity::joinCompetitionRankSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BusinessCallerTestActivity::joinCompetitionRankSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getCompetitionRankDetailAsync() {
        CompetitionRankDetailRequest request = buildCompetitionRankDetailRequest();
        AdlService.getService().getBusinessCaller().getCompetitionRankDetailAsync(RequestScope.of(this), request, new RequestCallback<CompetitionRankDetailData>() {
            @Override
            public void onSuccess(CompetitionRankDetailData data) {
                if (data != null) {
                    Log.d(TAG, "BusinessCallerTestActivity::getCompetitionRankDetailAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BusinessCallerTestActivity::getCompetitionRankDetailAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BusinessCallerTestActivity::getCompetitionRankDetailAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BusinessCallerTestActivity::getCompetitionRankDetailAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getCompetitionRankDetailSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBusinessCaller().getCompetitionRankDetailSync(buildCompetitionRankDetailRequest()),
                new RequestCallback<CompetitionRankDetailData>() {
                    @Override
                    public void onSuccess(CompetitionRankDetailData data) {
                        if (data != null) {
                            Log.d(TAG, "BusinessCallerTestActivity::getCompetitionRankDetailSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BusinessCallerTestActivity::getCompetitionRankDetailSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BusinessCallerTestActivity::getCompetitionRankDetailSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BusinessCallerTestActivity::getCompetitionRankDetailSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getCompetitionPageAsync() {
        CompetitionPageRequest request = buildCompetitionPageRequest();
        AdlService.getService().getBusinessCaller().getCompetitionPageAsync(RequestScope.of(this), request, new RequestCallback<BasePageData<CompetitionData>>() {
            @Override
            public void onSuccess(BasePageData<CompetitionData> data) {
                if (data != null) {
                    Log.d(TAG, "BusinessCallerTestActivity::getCompetitionPageAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BusinessCallerTestActivity::getCompetitionPageAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BusinessCallerTestActivity::getCompetitionPageAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BusinessCallerTestActivity::getCompetitionPageAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getCompetitionPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBusinessCaller().getCompetitionPageSync(buildCompetitionPageRequest()),
                new RequestCallback<BasePageData<CompetitionData>>() {
                    @Override
                    public void onSuccess(BasePageData<CompetitionData> data) {
                        if (data != null) {
                            Log.d(TAG, "BusinessCallerTestActivity::getCompetitionPageSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BusinessCallerTestActivity::getCompetitionPageSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BusinessCallerTestActivity::getCompetitionPageSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BusinessCallerTestActivity::getCompetitionPageSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getSportMeetPageAsync() {
        SportMeetPageRequest request = buildSportMeetPageRequest();
        AdlService.getService().getBusinessCaller().getSportMeetPageAsync(RequestScope.of(this), request, new RequestCallback<BasePageData<SportMeetData>>() {
            @Override
            public void onSuccess(BasePageData<SportMeetData> data) {
                if (data != null) {
                    Log.d(TAG, "BusinessCallerTestActivity::getSportMeetPageAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BusinessCallerTestActivity::getSportMeetPageAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BusinessCallerTestActivity::getSportMeetPageAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BusinessCallerTestActivity::getSportMeetPageAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getSportMeetPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBusinessCaller().getSportMeetPageSync(buildSportMeetPageRequest()),
                new RequestCallback<BasePageData<SportMeetData>>() {
                    @Override
                    public void onSuccess(BasePageData<SportMeetData> data) {
                        if (data != null) {
                            Log.d(TAG, "BusinessCallerTestActivity::getSportMeetPageSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BusinessCallerTestActivity::getSportMeetPageSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BusinessCallerTestActivity::getSportMeetPageSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BusinessCallerTestActivity::getSportMeetPageSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void downloadGroupTeamPageAsync() {
        DownloadGroupTeamPageRequest request = buildDownloadGroupTeamPageRequest();
        AdlService.getService().getBusinessCaller().downloadGroupTeamPageAsync(RequestScope.of(this), request, new RequestCallback<List<GroupTeamData>>() {
            @Override
            public void onSuccess(List<GroupTeamData> data) {
                if (data != null) {
                    Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamPageAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamPageAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamPageAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamPageAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamPageAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void downloadGroupTeamPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBusinessCaller().downloadGroupTeamPageSync(buildDownloadGroupTeamPageRequest()),
                new RequestCallback<List<GroupTeamData>>() {
                    @Override
                    public void onSuccess(List<GroupTeamData> data) {
                        if (data != null) {
                            Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamPageSync::onSuccess : data size : " + data.size());
                            Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamPageSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamPageSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamPageSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamPageSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void downloadGroupTeamDetailPageAsync() {
        DownloadGroupTeamDetailPageRequest request = buildDownloadGroupTeamDetailPageRequest();
        AdlService.getService().getBusinessCaller().downloadGroupTeamDetailPageAsync(RequestScope.of(this), request, new RequestCallback<List<GroupTeamDetailData>>() {
            @Override
            public void onSuccess(List<GroupTeamDetailData> data) {
                if (data != null) {
                    Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamDetailPageAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamDetailPageAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamDetailPageAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamDetailPageAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamDetailPageAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void downloadGroupTeamDetailPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBusinessCaller().downloadGroupTeamDetailPageSync(buildDownloadGroupTeamDetailPageRequest()),
                new RequestCallback<List<GroupTeamDetailData>>() {
                    @Override
                    public void onSuccess(List<GroupTeamDetailData> data) {
                        if (data != null) {
                            Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamDetailPageSync::onSuccess : data size : " + data.size());
                            Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamDetailPageSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamDetailPageSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamDetailPageSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BusinessCallerTestActivity::downloadGroupTeamDetailPageSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getResTypeListAsync() {
        ResTypeListRequest request = buildResTypeListRequest();
        AdlService.getService().getBusinessCaller().getResTypeListAsync(RequestScope.of(this), request, new RequestCallback<List<ResTypeData>>() {
            @Override
            public void onSuccess(List<ResTypeData> data) {
                if (data != null) {
                    Log.d(TAG, "BusinessCallerTestActivity::getResTypeListAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "BusinessCallerTestActivity::getResTypeListAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BusinessCallerTestActivity::getResTypeListAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BusinessCallerTestActivity::getResTypeListAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BusinessCallerTestActivity::getResTypeListAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getResTypeListSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBusinessCaller().getResTypeListSync(buildResTypeListRequest()),
                new RequestCallback<List<ResTypeData>>() {
                    @Override
                    public void onSuccess(List<ResTypeData> data) {
                        if (data != null) {
                            Log.d(TAG, "BusinessCallerTestActivity::getResTypeListSync::onSuccess : data size : " + data.size());
                            Log.d(TAG, "BusinessCallerTestActivity::getResTypeListSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BusinessCallerTestActivity::getResTypeListSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BusinessCallerTestActivity::getResTypeListSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BusinessCallerTestActivity::getResTypeListSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getResPageAsync() {
        ResPageRequest request = buildResPageRequest();
        AdlService.getService().getBusinessCaller().getResPageAsync(RequestScope.of(this), request, new RequestCallback<BasePageData<ResData>>() {
            @Override
            public void onSuccess(BasePageData<ResData> data) {
                if (data != null) {
                    Log.d(TAG, "BusinessCallerTestActivity::getResPageAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BusinessCallerTestActivity::getResPageAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BusinessCallerTestActivity::getResPageAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BusinessCallerTestActivity::getResPageAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getResPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBusinessCaller().getResPageSync(buildResPageRequest()),
                new RequestCallback<BasePageData<ResData>>() {
                    @Override
                    public void onSuccess(BasePageData<ResData> data) {
                        if (data != null) {
                            Log.d(TAG, "BusinessCallerTestActivity::getResPageSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BusinessCallerTestActivity::getResPageSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BusinessCallerTestActivity::getResPageSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BusinessCallerTestActivity::getResPageSync::onError : error : " + error);
                        resultView.setText(error);
                    }
                });
    }

    private void getResInfoAsync() {
        ResInfoRequest request = buildResInfoRequest();
        AdlService.getService().getBusinessCaller().getResInfoAsync(RequestScope.of(this), request, new RequestCallback<ResData>() {
            @Override
            public void onSuccess(ResData data) {
                if (data != null) {
                    Log.d(TAG, "BusinessCallerTestActivity::getResInfoAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "BusinessCallerTestActivity::getResInfoAsync::onSuccess : data is null");
                }
            }
            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "BusinessCallerTestActivity::getResInfoAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }
            @Override
            public void onError(String error) {
                Log.d(TAG, "BusinessCallerTestActivity::getResInfoAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getResInfoSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this),
                () -> AdlService.getService().getBusinessCaller().getResInfoSync(buildResInfoRequest()),
                new RequestCallback<ResData>() {
                    @Override
                    public void onSuccess(ResData data) {
                        if (data != null) {
                            Log.d(TAG, "BusinessCallerTestActivity::getResInfoSync::onSuccess : data : " + data);
                            resultView.setText(data.toString());
                        } else {
                            Log.d(TAG, "BusinessCallerTestActivity::getResInfoSync::onSuccess : data is null");
                        }
                    }
                    @Override
                    public void onFail(int code, String msg) {
                        Log.d(TAG, "BusinessCallerTestActivity::getResInfoSync::onFail : code : " + code + ", msg : " + msg);
                        resultView.setText(msg);
                    }
                    @Override
                    public void onError(String error) {
                        Log.d(TAG, "BusinessCallerTestActivity::getResInfoSync::onError : error : " + error);
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
