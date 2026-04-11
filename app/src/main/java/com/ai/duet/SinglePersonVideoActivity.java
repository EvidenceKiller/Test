package com.ai.duet;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;

import com.adl.base.common.AdlVideoReader;
import com.adl.base.common.VideoReaderCallback;
import com.adl.base.overlay.SportOverlayMultiRegion;
import com.adl.base.speech.AdlTextSpeaker;
import com.adl.lib.AdlCameraPreviewInfo;
import com.adl.lib.pose.AdlPoseInfo;
import com.adl.sport.general.EuError;
import com.adl.sport.general.invertedrow.InvertedRowCallBack;
import com.adl.sport.general.invertedrow.InvertedRowConfig;
import com.adl.sport.general.invertedrow.InvertedRowProcessor;
import com.adl.sport.general.invertedrow.InvertedRowStatus;
import com.adl.ts.general.R;
import com.ai.test.BaseActivity;
import com.ai.test.SoundOrderConst;
import com.ai.test.SoundUtil;

public class SinglePersonVideoActivity extends BaseActivity {
    SportOverlayMultiRegion overlay;
    SurfaceView surfaceView;
    Button bntPlay;
    Button bntChange;
    Button bntNext;
    Button bntReset;
    Button buttonInit;
    TextView scoreTv;
    TextView infoTv;
    AdlVideoReader videoReader;
    AdlVideoReader.PlayMode playMode = AdlVideoReader.PlayMode.Auto;
    InvertedRowProcessor invertedRowProcessor;
    String path = "/sdcard/zy.sport.cache/InvertedRowVideo/斜身引体多计数.mp4";
    int count = 0;
    int invalidCount = 0;
    boolean initDown = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_person_video);
        surfaceView = findViewById(R.id.surface_view);
        overlay = findViewById(R.id.overlay);
        scoreTv = findViewById(R.id.score_tv);
        infoTv = findViewById(R.id.info_tv);
        bntPlay = findViewById(R.id.bnt_start);
        bntChange = findViewById(R.id.bnt_change);
        bntNext = findViewById(R.id.bnt_next);
        bntReset = findViewById(R.id.bnt_reset);
        buttonInit = findViewById(R.id.bnt_init);
        bntChange.setOnClickListener(view -> {
            if (playMode == AdlVideoReader.PlayMode.Auto) {
                playMode = AdlVideoReader.PlayMode.OneFrame;
                bntChange.setText("单帧模式");
            } else {
                playMode = AdlVideoReader.PlayMode.Auto;
                bntChange.setText("自动模式");
            }
            videoReader.setPlayMode(playMode);
        });

        bntNext.setOnClickListener(view -> {
            videoReader.nextFrame();
        });

        InvertedRowConfig config = new InvertedRowConfig();
        config.poleHeight = InvertedRowConfig.PoleHeight.LOW_130;
        config.checkCountDown = false;
        invertedRowProcessor = new InvertedRowProcessor(config);
        invertedRowProcessor.open(overlay);
        invertedRowProcessor.setCallback(new InvertedRowCallBack() {

            @Override
            public void invalidMotion(String errMsg) {
                invalidCount++;
                runOnUiThread(() -> {
                    AdlTextSpeaker.speak(errMsg);
                    scoreTv.setText("成功数：" + count + "  失败数：" + invalidCount + "  违规原因：" + errMsg);
                });
            }

            @Override
            public void onComplete() {
                count++;
                runOnUiThread(() -> {
                    SoundUtil.getInstance().playSound(SoundOrderConst.SPORT_COUNT);
                    scoreTv.setText("成功数：" + count + "  失败数：" + invalidCount);
                });

            }

            @Override
            public void callCountDown() {
                // 10秒没有斜身完成开始倒计时
                runOnUiThread(() -> {
                    AdlTextSpeaker.speak("10秒倒计时开始");
                });
            }

            @Override
            public void cancel() {
                runOnUiThread(() -> {
                    scoreTv.setText("运动结束-------- ");
                    invertedRowProcessor.changeStatus(InvertedRowStatus.Standby);
                    invertedRowProcessor.onReset();
                });
            }

            @Override
            public void onInit(EuError error) {
//                if (!error.errorNeedCancel) {
//                    AdlLogger.d("场地构建成功");
//                    invertedRowProcessor.changeStatus(InvertedRowStatus.Sporting);
//                }
                if (!error.errorNeedCancel) {
                    runOnUiThread(() -> {
                        AdlTextSpeaker.speak("场地构建成功");
                        initDown = true;
                    });
                    invertedRowProcessor.changeStatus(InvertedRowStatus.Standby);
                } else {
                    runOnUiThread(() -> {
                        AdlTextSpeaker.speak("场地构建失败" + error.errorMsg);
                        initDown = false;
                    });
                    invertedRowProcessor.changeStatus(InvertedRowStatus.Standby);
                    invertedRowProcessor.onReset();
                }
            }

            @Override
            public void onPrepare() {
                runOnUiThread(() -> {
                    scoreTv.setText("检测完毕 可以开始------");
                });
                invertedRowProcessor.startSporting();

            }

            @Override
            public void onFrame(AdlPoseInfo poseInfo) {

            }

            @Override
            public void onBodyAngle(float bodyAngle, double leftElbowAngle, double rightElbowAngle, double leftToeAngle, double rightToeAngle) {
                runOnUiThread(() -> {
                    infoTv.setText("身体角度：" + String.valueOf(bodyAngle) + "°\n" + "左手肘角度：" + String.valueOf(leftElbowAngle) + "°\n" + "右手肘角度：" + String.valueOf(rightElbowAngle) + "°\n"
                            + "左脚角尖:" + String.valueOf(leftToeAngle) + "°" + " --右脚角尖：" + String.valueOf(rightToeAngle) + "°");
                });

            }
        });

        videoReader = new AdlVideoReader();
        videoReader.setPlayMode(playMode);
        videoReader.setSurface(surfaceView.getHolder().getSurface());
        videoReader.enableRender(true);
        videoReader.openFile(path);
        videoReader.setCallback(new VideoReaderCallback() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onFrame(byte[] yuv, int width, int height, long frameTime) {
                AdlCameraPreviewInfo info = new AdlCameraPreviewInfo(width, height);
                info.setImageId(String.valueOf(frameTime));
                info.setFrameTimestamp(frameTime);
                invertedRowProcessor.onFrame(yuv, info);
            }

        });


        bntPlay.setOnClickListener(view -> {
            if (videoReader.isPlaying()) {
                bntPlay.setText("播放");
                videoReader.onPause();
            } else {
                videoReader.onStart();
                if (initDown) {
                    invertedRowProcessor.changeStatus(InvertedRowStatus.Sporting);
                }else {
                    runOnUiThread(() -> {
                        AdlTextSpeaker.speak("场地尚未构建，请先构建场地");
                    });
                }
//                invertedRowProcessor.onReset();
//                invertedRowProcessor.changeStatus(InvertedRowStatus.InitPlayGround);
                bntPlay.setText("暂停");
            }
        });
//        videoReader.onReset(path);
        bntReset.setOnClickListener(view -> {
            if (videoReader.isPlaying()) {
                bntPlay.setText("播放");
                videoReader.onPause();
            } else {
                videoReader.onStart();
                bntPlay.setText("暂停");
            }
            videoReader.onReset(path);
            invalidCount = 0;
            count = 0;
            invertedRowProcessor.changeStatus(InvertedRowStatus.Standby);
            invertedRowProcessor.onReset();
        });

        buttonInit.setOnClickListener(view -> {
//            File root = AdlFileHelper.createSportRootDir();
//            File videos = AdlFileHelper.createDir(root, "InvertedRowVideo");
//
//            invertedRowProcessor.startRecording(new File(videos, "/" + System.currentTimeMillis() + ".mp4"));
            invertedRowProcessor.changeStatus(InvertedRowStatus.InitPlayGround);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        invertedRowProcessor.onRelease();
        invertedRowProcessor = null;
        videoReader.onStop();
    }
}