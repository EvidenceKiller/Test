package com.ai.test;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.adl.base.config.AdlUIConfig;
import com.adl.base.config.RoleConfigRopeSkip;
import com.adl.base.element.ElementFpsCounter;
import com.adl.base.log.AdlLogger;
import com.adl.base.overlay.OnRenderScene;
import com.adl.base.overlay.SportOverlayRopeSkip;
import com.adl.lib.AdlCamera;
import com.adl.lib.AdlCameraConfig;
import com.adl.lib.AdlCameraFrameProcessor;
import com.adl.lib.AdlCameraPreviewView;
import com.adl.lib.pose.AdlPoseSkeleton;
import com.adl.mount.core.TsTaskDrawer;
import com.adl.mount.sport.TsConfig;
import com.adl.mount.sport.TsSportRegion;
import com.adl.sport.general.EuCallback;
import com.adl.sport.general.EuSingleBase;
import com.adl.ts.general.R;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/8
 * Describe   : 类描述
 */
public class HKCameraPreviewActivity extends BaseActivity {

    SportOverlayRopeSkip overlayVessel;
    Button bntPlay;
    Button bntReset;
    AdlCameraPreviewView previewView;
    TextView infoTv;

    AdlCamera adlCamera;
    AdlCameraFrameProcessor frameProcessor;
    ElementFpsCounter fpsCounter;
    AdlCamera hikCamera = null;
    int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_camera);
        int streamChannel = 2;
        hikCamera = AdlCameraConfig.builder().setCameraType(AdlCameraConfig.CameraType.HK)
                .setStreamChannel(streamChannel)
                .setPreviewSize(1920, 1080)
                .setConnect("172.16.30.202", 8000, "admin", "zy132456")
            .build();

        previewView = findViewById(R.id.preview_view);
        bntPlay = findViewById(R.id.bnt_start);
        bntReset = findViewById(R.id.bnt_reset);
        infoTv = findViewById(R.id.info_tv);
        overlayVessel = findViewById(R.id.overlay);

        overlayVessel.enableDrawSkeleton(true);
        overlayVessel.enableDrawBox(false);
        overlayVessel.enableDraw(true);
        overlayVessel.enableDebug(false);

        fpsCounter = new ElementFpsCounter("Camera");
        fpsCounter.setPosition(20, 300);
        overlayVessel.addElement(fpsCounter);

        bntPlay.setOnClickListener(view -> {
            if (type == 0) {
                startCameraPreview(type);
            } else {
                startCameraPreview(type);
            }
            if (type == 0) {
                type = 1;
            } else {
                type = 0;
            }
        });

        findViewById(R.id.bnt_close).setOnClickListener(view -> finish());

        frameProcessor = (yuv, info) -> {

        };
        previewView.addFrameProcessor(frameProcessor);
    }

    private void startCameraPreview(int type) {

        long startTime = System.currentTimeMillis();
        AdlCamera camera = null;
        if (type == 0) {
            camera = getCamera();
        } else {
            camera = hikCamera;
        }

        startPreview(camera);
    }


    // 摄像头预览大小
    public static int PreviewWidth = 1920;
    public static int PreviewHeight = 1080;
    public AdlCamera getCamera() {
        AdlCameraConfig config = AdlCameraConfig.builder()
                .setCameraType(AdlCameraConfig.CameraType.Old)
                .setCameraId(AdlCameraConfig.CameraId.Back)
                .setPreviewSize(PreviewWidth, PreviewHeight);

        config.setDisplayOrientation(0);
        return config.build();
    }

    private void startPreview(AdlCamera camera) {
        previewView.startPreview(
                camera,
                new AdlCameraPreviewView.OnViewStatusListenerSample() {
                    @Override
                    public void onAvailable() {
                        super.onAvailable();
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        previewView.stopPreview();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        previewView.release();
        overlayVessel.release();
    }

}
