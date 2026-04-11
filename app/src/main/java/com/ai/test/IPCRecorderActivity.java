package com.ai.test;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.adl.base.common.AdlExecutor;
import com.adl.base.file.AdlFileHelper;
import com.adl.lib.AdlCamera;
import com.adl.lib.AdlCameraConfig;
import com.adl.lib.AdlCameraPreviewView;
import com.adl.lib.camera.hk.IPCDVideoPlayer;
import com.adl.lib.camera.hk.IPCDVideoRecorder;
import com.adl.lib.camera.hk.NsCameraHKSource;
import com.adl.ts.general.R;

import java.io.File;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/8
 * Describe   : 类描述
 */
public class IPCRecorderActivity extends BaseActivity {

    Button bntStart;
    AdlCameraPreviewView previewView;
    AdlCamera camera1;

    IPCDVideoRecorder dVideoRecorder;

    IPCDVideoPlayer dVideoPlayer;

    TextView tvDebug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_ipc_recorder);

        previewView = findViewById(R.id.preview_view);
        bntStart = findViewById(R.id.bnt_start);
        dVideoPlayer = findViewById(R.id.hk_player);
        tvDebug = findViewById(R.id.tv_debug);
        dVideoPlayer.setVideoPath("/sdcard/com.adl.sport/common/test_1759892372650_172_16_30_200.mp4");
        dVideoPlayer.setDescPath("/sdcard/com.adl.sport/common/test_1759892372650_172_16_30_200.desc");

        bntStart.setOnClickListener(view -> {

            if (camera1 instanceof NsCameraHKSource) {
                NsCameraHKSource camera = (NsCameraHKSource) camera1;

                dVideoRecorder.attachHkPreview(camera.getSubHandle(), camera.getIp());
                dVideoRecorder.stopRecord();

                File dir = AdlFileHelper.createCommonDir();
                dVideoRecorder.startRecord(dir, "test_" + System.currentTimeMillis(), new IPCDVideoRecorder.OnRecordCallback() {
                    @Override
                    public void onRecording(long duration) {

                    }

                    @Override
                    public void onStart(File file) {

                    }

                });
            }
        });

        findViewById(R.id.bnt_stop).setOnClickListener(view -> {
            if (dVideoRecorder != null) {
                dVideoRecorder.stopRecord();
            }
        });

        findViewById(R.id.bnt_marker).setOnClickListener(view -> {
            if (dVideoRecorder != null) {
                dVideoRecorder.markFeature("发令信号");
            }
        });

        findViewById(R.id.bnt_marker_1).setOnClickListener(view -> {
            if (dVideoRecorder != null) {
                dVideoRecorder.markFeature("过线信号");
            }
        });

        findViewById(R.id.bnt_play_video).setOnClickListener(view -> {
            //dVideoPlayer.play();
        });

        findViewById(R.id.bnt_pause_video).setOnClickListener(view -> {
            //dVideoPlayer.pause();
        });

        dVideoRecorder = new IPCDVideoRecorder();

        AdlExecutor.postUIDelayed(() -> {
            camera1 = AdlCameraConfig.builder()
                    .setCameraType(AdlCameraConfig.CameraType.HK)
                    .setConnect("172.16.30.200", 8000, "admin", "zy123456")
                    .setStreamChannel(2)
                    .setAutoDecode(true)
                    .build();
            previewView.addFrameProcessor(dVideoRecorder);
            previewView.startPreview(camera1, new AdlCameraPreviewView.OnViewStatusListener() {
                @Override
                public void onAvailable() {

                }

                @Override
                public void onCameraOpen() {

                }

                @Override
                public void onDestroyed() {

                }

                @Override
                public void onStreamLoss() {
                    tvDebug.setText("IPC  断流了");
                }

                @Override
                public void onOpenFail() {
                    tvDebug.setText("摄像头打开失败");
                }
            });
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        previewView.stopPreview();
        dVideoRecorder.stopRecord();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        previewView.release();
        dVideoRecorder.release();
    }

}
