package com.ai.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.adl.service.AdlService;
import com.adl.service.callback.RequestCallback;
import com.adl.service.http.request.BaseDirection;
import com.adl.service.http.request.BaseSort;
import com.adl.service.http.request.ClassInfoRequest;
import com.adl.service.http.request.ClassListRequest;
import com.adl.service.http.request.DeviceActiveRequest;
import com.adl.service.http.request.DictRequest;
import com.adl.service.http.request.RequestScope;
import com.adl.service.http.request.StudentPageRequest;
import com.adl.service.http.request.TeacherListRequest;
import com.adl.service.data.AcayearData;
import com.adl.service.data.BasePageData;
import com.adl.service.data.ClassInfoData;
import com.adl.service.data.ClassData;
import com.adl.service.data.DictData;
import com.adl.service.data.GradeChineseNameData;
import com.adl.service.data.GradeTreeData;
import com.adl.service.data.LoginInfoData;
import com.adl.service.data.StudentData;
import com.adl.service.data.TeacherData;
import com.adl.ts.general.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 演示在独立界面中调用 SDK「学生列表」接口。
 */
public class V3CommonInfoCallerTestActivity extends AppCompatActivity {

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

        createButton("getGradeTreeAsync").setOnClickListener(v -> getGradeTreeAsync());
        createButton("getGradeTreeSync").setOnClickListener(v -> getGradeTreeSync());

        createButton("getGradeChineseNameListAsync").setOnClickListener(v -> getGradeChineseNameListAsync());
        createButton("getGradeChineseNameListSync").setOnClickListener(v -> getGradeChineseNameListSync());

        createButton("getClassListAsync").setOnClickListener(v -> getClassListAsync());
        createButton("getClassListSync").setOnClickListener(v -> getClassListSync());

        createButton("getClassInfoAsync").setOnClickListener(v -> getClassInfoAsync());
        createButton("getClassInfoSync").setOnClickListener(v -> getClassInfoSync());

        createButton("getLoginInfoAsync").setOnClickListener(v -> getLoginInfoAsync());
        createButton("getLoginInfoSync").setOnClickListener(v -> getLoginInfoSync());

        createButton("getAcayearListAsync").setOnClickListener(v -> getAcayearListAsync());
        createButton("getAcayearListSync").setOnClickListener(v -> getAcayearListSync());

        createButton("getTeacherListAsync").setOnClickListener(v -> getTeacherListAsync());
        createButton("getTeacherListSync").setOnClickListener(v -> getTeacherListSync());

        createButton("getStudentPageAsync").setOnClickListener(v -> getStudentPageAsync());
        createButton("getStudentPageSync").setOnClickListener(v -> getStudentPageSync());

        createButton("getDictsAsync").setOnClickListener(v -> getDictsAsync());
        createButton("getDictsSync").setOnClickListener(v -> getDictsSync());

        createButton("deviceActiveAsync").setOnClickListener(v -> deviceActiveAsync());
        createButton("deviceActiveSync").setOnClickListener(v -> deviceActiveSync());
    }

    private void getGradeTreeAsync() {
        AdlService.getService().getCommonInfoCaller().getGradeTreeAsync(RequestScope.of(this), new RequestCallback<List<GradeTreeData>>() {
            @Override
            public void onSuccess(List<GradeTreeData> data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getGradeTreeAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "CommonInfoCallerTestActivity::getGradeTreeAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getGradeTreeAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getGradeTreeAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getGradeTreeAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getGradeTreeSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            return AdlService.getService().getCommonInfoCaller().getGradeTreeSync();
        }, new RequestCallback<List<GradeTreeData>>() {
            @Override
            public void onSuccess(List<GradeTreeData> data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getGradeTreeSync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "CommonInfoCallerTestActivity::getGradeTreeSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getGradeTreeSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getGradeTreeSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getGradeTreeSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getGradeChineseNameListAsync() {
        AdlService.getService().getCommonInfoCaller().getGradeChineseNameListAsync(RequestScope.of(this), new RequestCallback<List<GradeChineseNameData>>() {
            @Override
            public void onSuccess(List<GradeChineseNameData> data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getGradeChineseNameListAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "CommonInfoCallerTestActivity::getGradeChineseNameListAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getGradeChineseNameListAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getGradeChineseNameListAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getGradeChineseNameListAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getGradeChineseNameListSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            return AdlService.getService().getCommonInfoCaller().getGradeChineseNameListSync();
        }, new RequestCallback<List<GradeChineseNameData>>() {
            @Override
            public void onSuccess(List<GradeChineseNameData> data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getGradeChineseNameListSync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "CommonInfoCallerTestActivity::getGradeChineseNameListSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getGradeChineseNameListSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getGradeChineseNameListSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getGradeChineseNameListSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getClassListAsync() {
        ClassListRequest request = ClassListRequest.builder().build();
        AdlService.getService().getCommonInfoCaller().getClassListAsync(RequestScope.of(this), request, new RequestCallback<List<ClassData>>() {
            @Override
            public void onSuccess(List<ClassData> data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getClassListAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "CommonInfoCallerTestActivity::getClassListAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getClassListAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getClassListAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getClassListAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getClassListSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            ClassListRequest request = ClassListRequest.builder().build();
            return AdlService.getService().getCommonInfoCaller().getClassListSync(request);
        }, new RequestCallback<List<ClassData>>() {
            @Override
            public void onSuccess(List<ClassData> data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getClassListSync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "CommonInfoCallerTestActivity::getClassListSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getClassListSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getClassListSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getClassListSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getClassInfoAsync() {
        List<BaseSort> sorts = new ArrayList<>();
        sorts.add(new BaseSort().setColumn("classNum").setDirection(BaseDirection.DESC));
        sorts.add(new BaseSort().setColumn("className").setDirection(BaseDirection.ASC));
        ClassInfoRequest request = ClassInfoRequest.builder("2011254439800274944")
                .sorts(sorts)
                .build();
        AdlService.getService().getCommonInfoCaller().getClassInfoAsync(RequestScope.of(this), request, new RequestCallback<ClassInfoData>() {
            @Override
            public void onSuccess(ClassInfoData data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getClassInfoAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getClassInfoAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getClassInfoAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getClassInfoAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getClassInfoSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            List<BaseSort> sorts = new ArrayList<>();
            sorts.add(new BaseSort().setColumn("classNum").setDirection(BaseDirection.DESC));
            sorts.add(new BaseSort().setColumn("className").setDirection(BaseDirection.ASC));
            ClassInfoRequest request = ClassInfoRequest.builder("2011254439800274944")
                    .sorts(sorts)
                    .build();
            return AdlService.getService().getCommonInfoCaller().getClassInfoSync(request);
        }, new RequestCallback<ClassInfoData>() {
            @Override
            public void onSuccess(ClassInfoData data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getClassInfoSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getClassInfoSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getClassInfoSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getClassInfoSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getLoginInfoAsync() {
        AdlService.getService().getCommonInfoCaller().getLoginInfoAsync(RequestScope.of(this), new RequestCallback<LoginInfoData>() {
            @Override
            public void onSuccess(LoginInfoData data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getLoginInfoAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getLoginInfoAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getLoginInfoAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getLoginInfoAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getLoginInfoSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            return AdlService.getService().getCommonInfoCaller().getLoginInfoSync();
        }, new RequestCallback<LoginInfoData>() {
            @Override
            public void onSuccess(LoginInfoData data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getLoginInfoSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getLoginInfoSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getLoginInfoSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getLoginInfoSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getAcayearListAsync() {
        AdlService.getService().getCommonInfoCaller().getAcayearListAsync(RequestScope.of(this), new RequestCallback<List<AcayearData>>() {
            @Override
            public void onSuccess(List<AcayearData> data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getAcayearListAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "CommonInfoCallerTestActivity::getAcayearListAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getAcayearListAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getAcayearListAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getAcayearListAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getAcayearListSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> AdlService.getService().getCommonInfoCaller().getAcayearListSync(), new RequestCallback<List<AcayearData>>() {
            @Override
            public void onSuccess(List<AcayearData> data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getAcayearListSync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "CommonInfoCallerTestActivity::getAcayearListSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getAcayearListSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getAcayearListSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getAcayearListSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getTeacherListAsync() {
        TeacherListRequest request = TeacherListRequest.builder()
                .build();
        AdlService.getService().getCommonInfoCaller().getTeacherListAsync(RequestScope.of(this), request, new RequestCallback<List<TeacherData>>() {
            @Override
            public void onSuccess(List<TeacherData> data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getTeacherListAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "CommonInfoCallerTestActivity::getTeacherListAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getTeacherListAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getTeacherListAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getTeacherListAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getTeacherListSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            TeacherListRequest request = TeacherListRequest.builder()
                    .build();
            return AdlService.getService().getCommonInfoCaller().getTeacherListSync(request);
        }, new RequestCallback<List<TeacherData>>() {
            @Override
            public void onSuccess(List<TeacherData> data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getTeacherListSync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "CommonInfoCallerTestActivity::getTeacherListSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getTeacherListSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getTeacherListSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getTeacherListSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getStudentPageAsync() {
        StudentPageRequest request = StudentPageRequest.builder()
                .faceType(2)
                .build();
        AdlService.getService().getCommonInfoCaller().getStudentPageAsync(RequestScope.of(this), request, new RequestCallback<BasePageData<StudentData>>() {
            @Override
            public void onSuccess(BasePageData<StudentData> data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getStudentPageAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getStudentPageAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getStudentPageAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getStudentPageAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getStudentPageSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            StudentPageRequest request = StudentPageRequest.builder()
                    .build();
            return AdlService.getService().getCommonInfoCaller().getStudentPageSync(request);
        }, new RequestCallback<BasePageData<StudentData>>() {
            @Override
            public void onSuccess(BasePageData<StudentData> data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getStudentPageSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getStudentPageSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getStudentPageSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getStudentPageSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getDictsAsync() {
        DictRequest request = DictRequest.builder().build();
        AdlService.getService().getCommonInfoCaller().getDictListAsync(RequestScope.of(this), request, new RequestCallback<List<DictData>>() {
            @Override
            public void onSuccess(List<DictData> data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getDictsAsync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "CommonInfoCallerTestActivity::getDictsAsync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getDictsAsync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getDictsAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getDictsAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void getDictsSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            DictRequest request = DictRequest.builder().build();
            return AdlService.getService().getCommonInfoCaller().getDictListSync(request);
        }, new RequestCallback<List<DictData>>() {
            @Override
            public void onSuccess(List<DictData> data) {
                if (data != null) {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getDictsSync::onSuccess : data size : " + data.size());
                    Log.d(TAG, "CommonInfoCallerTestActivity::getDictsSync::onSuccess : data : " + data);
                    resultView.setText(data.toString());
                } else {
                    Log.d(TAG, "CommonInfoCallerTestActivity::getDictsSync::onSuccess : data is null");
                }
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getDictsSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::getDictsSync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void deviceActiveAsync() {
        DeviceActiveRequest request = DeviceActiveRequest.builder("", "").build();
        AdlService.getService().getCommonInfoCaller().deviceActiveAsync(RequestScope.of(this), request, new RequestCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                Log.d(TAG, "CommonInfoCallerTestActivity::deviceActiveAsync::onSuccess : data : " + data);
                resultView.setText(String.valueOf(data));
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::deviceActiveAsync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::deviceActiveAsync::onError : error : " + error);
                resultView.setText(error);
            }
        });
    }

    private void deviceActiveSync() {
        AdlService.getService().executeSyncOnIo(RequestScope.of(this), () -> {
            DeviceActiveRequest request = DeviceActiveRequest.builder("", "").build();
            return AdlService.getService().getCommonInfoCaller().deviceActiveSync(request);
        }, new RequestCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean data) {
                Log.d(TAG, "CommonInfoCallerTestActivity::deviceActiveSync::onSuccess : data : " + data);
                resultView.setText(String.valueOf(data));
            }

            @Override
            public void onFail(int code, String msg) {
                Log.d(TAG, "CommonInfoCallerTestActivity::deviceActiveSync::onFail : code : " + code + ", msg : " + msg);
                resultView.setText(msg);
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "CommonInfoCallerTestActivity::deviceActiveSync::onError : error : " + error);
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
