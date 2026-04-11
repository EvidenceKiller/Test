package com.ai.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.adl.base.element.ElementFpsCounter;
import com.adl.base.log.AdlLogger;
import com.adl.base.overlay.SportOverlayListener;
import com.adl.base.overlay.SportOverlayVessel;
import com.adl.base.recorder.NsYuvRecorder;
import com.adl.lib.AdlCameraConfig;
import com.adl.lib.AdlCameraFrameProcessor;
import com.adl.lib.AdlCameraPreviewView;
import com.adl.lib.camera.hk.NsCameraHKRecorder;
import com.adl.lib.pose.AdlObject;
import com.adl.mount.core.TsBodyTracker;
import com.adl.mount.core.TsModelRes;
import com.adl.mount.core.TsModelResPool;
import com.adl.mount.core.TsTaskBodyFilter;
import com.adl.mount.core.TsTaskDrawer;
import com.adl.mount.core.TsTaskSingleBody;
import com.adl.mount.impl.TsSingleProcessor;
import com.adl.mount.impl.TsSportFrameSingle;
import com.adl.mount.impl.TsSportLifecycleSingle;
import com.adl.mount.sport.TsConfig;
import com.adl.mount.sport.TsSportEngine;
import com.adl.mount.sport.TsSportRegion;
import com.adl.mount.sport.TsYuvFrameRecCache;
import com.adl.ts.general.R;

import java.util.Arrays;
import java.util.List;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/4/9
 * Describe   : 单路流 + 模型测试
 */
public class Performance1Activity extends BaseActivity {

    FrameLayout rootLayout;
    AdlCameraPreviewView previewView;
    SportOverlayVessel overlayVessel;
    AdlCameraFrameProcessor frameProcessor;
    ElementFpsCounter cameraFpsCounter;
    boolean previewing;

    // 摄像头
    Spinner spResolution;
    Spinner spFps;
    AdlCameraConfig zyConfig;
    RadioButton typePad;
    RadioButton typeIpc;
    EditText ipEd;
    EditText nameEd;
    EditText pwdEd;

    CheckBox model1000;
    CheckBox model2000;
    CheckBox model3002;
    CheckBox model3003;

    // 视频录制
    RadioButton typeRecord1;
    NsYuvRecorder mRecorder;
    NsCameraHKRecorder hkRecorder;
    boolean recording;
    TsSportEngine sportEngine;
    boolean startAi;
    TsTaskBodyFilter bodyFilter;

    // 按钮
    Button bntPreview;
    Button bntStart;
    Button bntRecording;
    Button bntReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_performance);

        rootLayout = findViewById(R.id.preview_layout);
        previewView = findViewById(R.id.preview_view);
        overlayVessel = findViewById(R.id.overlay);
        spResolution = findViewById(R.id.sp_resolution);
        spFps = findViewById(R.id.sp_fps);
        typePad = findViewById(R.id.type_pad);
        typeIpc = findViewById(R.id.type_ipc);
        ipEd = findViewById(R.id.ed_ip);
        nameEd = findViewById(R.id.ed_name);
        pwdEd = findViewById(R.id.ed_pwd);
        typeRecord1 = findViewById(R.id.type_record_1);

        model1000 = findViewById(R.id.model_1000);
        model2000 = findViewById(R.id.model_2000);
        model3002 = findViewById(R.id.model_3002);
        model3003 = findViewById(R.id.model_3003);

        bntPreview = findViewById(R.id.bnt_preview);
        bntRecording = findViewById(R.id.bnt_recording);
        bntReset = findViewById(R.id.bnt_reset);
        bntStart = findViewById(R.id.bnt_start);

        mRecorder = NsYuvRecorder.create();
        hkRecorder = NsCameraHKRecorder.instance();

        cameraFpsCounter = new ElementFpsCounter("Camera FPS:");
        overlayVessel.enableDrawBox(true);
        overlayVessel.enableDebug(true);
        overlayVessel.enableDraw(true);
        overlayVessel.enableDrawSkeleton(true);
        overlayVessel.addElement(cameraFpsCounter);

        typePad.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                findViewById(R.id.layout_1).setVisibility(View.VISIBLE);
                findViewById(R.id.layout_2).setVisibility(View.GONE);
            } else {
                findViewById(R.id.layout_1).setVisibility(View.GONE);
                findViewById(R.id.layout_2).setVisibility(View.VISIBLE);
            }
        });

        typeIpc.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                findViewById(R.id.layout_1).setVisibility(View.GONE);
                findViewById(R.id.layout_2).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.layout_1).setVisibility(View.VISIBLE);
                findViewById(R.id.layout_2).setVisibility(View.GONE);
            }
        });

        typeRecord1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                findViewById(R.id.sp_bitrate).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.sp_bitrate).setVisibility(View.GONE);
            }
        });

        TsConfig config = new TsConfig();
        config.debug = true;
        config.bodyModelId = 1009;
        config.poseModelId = 2004;
        config.drawAllBody = true;
        config.drawSkeleton = true;
        TsTaskDrawer drawer = new TsTaskDrawer();
        overlayVessel.addElement(drawer);

        sportEngine = TsSportEngine.instance();
        TsModelResPool.loadModel(Arrays.asList(new TsModelRes(1009, 6),
                new TsModelRes(2004, 6)));
        sportEngine.init(TsYuvFrameRecCache.defCache(), 5);
        sportEngine.setSportLifecycle(new TsSportLifecycleSingle(new TsSingleProcessor() {

            @Override
            public void onInit() {

            }

            @Override
            public void onReset() {

            }

            @Override
            public void onRelease() {

            }

            @Override
            public String version() {
                return null;
            }

            @Override
            public void onAnalyzer(TsSportFrameSingle frame) {

            }
        }));

        bodyFilter = new TsTaskBodyFilter() {
            @Override
            public AdlObject onFilter(List<AdlObject> list, TsSportRegion region) {
                return null;
            }
        };
        overlayVessel.setListener(() -> {
            sportEngine.open(config, overlayVessel, drawer);
            sportEngine.toSporting();
        });

        bntStart.setOnClickListener(view -> {
            startAi = true;
        });

        frameProcessor = (yuv, info) -> {
            // 摄像头fps统计
            cameraFpsCounter.updateFps();

            if (startAi) {
                sportEngine.onFrame(yuv, info, new TsTaskSingleBody()
                        .setBodyFilter(bodyFilter));
            }
        };

        bntPreview.setOnClickListener(view -> {

            if (previewing) {
                stopPreview();
                stopRecording();
            }

            String resStr = (String) spResolution.getSelectedItem();
            String fpsStr = (String) spFps.getSelectedItem();

            String[] ss = resStr.split("x");
            int fps = Integer.parseInt(fpsStr);
            int width = Integer.parseInt(ss[0]);
            int height = Integer.parseInt(ss[1]);

            if (typePad.isChecked()) {
                zyConfig = AdlCameraConfig.builder()
                        .setCameraType(AdlCameraConfig.CameraType.CameraX)
                        .setCameraId(AdlCameraConfig.CameraId.Back)
                        .setPreviewFps(fps)
                        .setPreviewSize(width, height);
            } else {
                String ip = readString(ipEd);
                String name = readString(nameEd);
                String pwd = readString(pwdEd);
                zyConfig = AdlCameraConfig.builder()
                        .setCameraType(AdlCameraConfig.CameraType.HK)
                        .setConnect(ip, 8000, name, pwd);
            }

            previewView.addFrameProcessor(frameProcessor);
            previewView.startPreview(zyConfig.build());
            previewing = true;
        });

        // 视频录制
        bntRecording.setOnClickListener(view -> {
            if (recording) {
                stopRecording();
            }
            startRecording();
        });

        bntReset.setOnClickListener(view -> {
            startAi = false;
            stopRecording();
            stopPreview();
        });

        findViewById(R.id.bnt_close).setOnClickListener(view -> {
            finish();
        });

        typeIpc.performClick();
    }

    private void stopPreview() {
        previewView.stopPreview();
        previewing = false;
    }

    private void startRecording() {
        if (typeRecord1.isChecked()) {

        }
    }

    private void stopRecording() {
        mRecorder.onStop();
        recording = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopRecording();
        stopPreview();
    }
}
