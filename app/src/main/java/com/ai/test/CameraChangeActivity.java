package com.ai.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.adl.base.log.AdlLogger;
import com.adl.base.overlay.SportOverlayRopeSkip;
import com.adl.lib.AdlCamera;
import com.adl.lib.AdlCameraConfig;
import com.adl.lib.AdlCameraFrameProcessor;
import com.adl.lib.AdlCameraPreviewInfo;
import com.adl.lib.AdlCameraPreviewView;
import com.adl.lib.camera.hk.NsCameraHKSource;
import com.adl.ts.general.R;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/8
 * Describe   : 类描述
 */
public class CameraChangeActivity extends BaseActivity {

    SportOverlayRopeSkip overlayVessel;
    Button bntChange;
    Button bntDisplay;
    AdlCameraPreviewView previewView;
    AdlCameraPreviewView previewView2;
    AdlCameraFrameProcessor frameProcessor1;
    AdlCameraFrameProcessor frameProcessor2;
    int cameraIndex = 0;

    AdlCamera camera1;
    AdlCamera camera2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_camera_change);

        previewView = findViewById(R.id.preview_view);
        previewView2 = findViewById(R.id.preview_view_right);
        bntChange = findViewById(R.id.bnt_change);
        bntDisplay = findViewById(R.id.bnt_display);
        overlayVessel = findViewById(R.id.overlay);

        overlayVessel.enableDrawSkeleton(true);
        overlayVessel.enableDrawBox(false);
        overlayVessel.enableDraw(true);
        overlayVessel.enableDebug(false);

        frameProcessor1 = new AdlCameraFrameProcessor() {
            @Override
            public void onFrame(byte[] yuv, AdlCameraPreviewInfo info) {
                AdlLogger.d("视频流----->1=" + info.getImageId());
            }
        };
        frameProcessor2 = new AdlCameraFrameProcessor() {
            @Override
            public void onFrame(byte[] yuv, AdlCameraPreviewInfo info) {
                AdlLogger.d("视频流----->2=" + info.getImageId());
            }
        };

        bntChange.setOnClickListener(view -> {

            previewView.stopPreview();
            previewView2.stopPreview();

            cameraIndex++;
            String ip;
            String ip2;
            if (cameraIndex % 2 == 0) {
                ip = "172.16.0.230";
                ip2 = "172.16.1.66";
            } else {
                ip = "172.16.1.66";
                ip2 = "172.16.0.230";
            }

            camera1 = AdlCameraConfig.builder()
                    .setCameraType(AdlCameraConfig.CameraType.HK)
                    .setConnect(ip, 8000, "admin", "zy123456")
                    .setAutoDecode(true)
                    .build();

            previewView.startPreview(camera1);
            previewView.addFrameProcessor(frameProcessor1);
            previewView.setVisibility(View.INVISIBLE);

            camera2 = AdlCameraConfig.builder()
                    .setCameraType(AdlCameraConfig.CameraType.HK)
                    .setConnect(ip2, 8000, "admin", "zy123456")
                    .setAutoDecode(true)
                    .build();
            previewView2.startPreview(camera2);
            previewView2.addFrameProcessor(frameProcessor2);
            previewView2.setVisibility(View.INVISIBLE);
        });

        bntDisplay.setOnClickListener(view -> {

            if (camera1 instanceof NsCameraHKSource) {
                //((NsCameraHKSource) camera1).setEnableDecode(true);
                previewView.setVisibility(View.VISIBLE);
            }

            if (camera2 instanceof NsCameraHKSource) {
                //((NsCameraHKSource) camera2).setEnableDecode(true);
                previewView2.setVisibility(View.VISIBLE);
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
        previewView2.stopPreview();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        previewView.release();
        previewView2.release();
        overlayVessel.release();
    }

}
