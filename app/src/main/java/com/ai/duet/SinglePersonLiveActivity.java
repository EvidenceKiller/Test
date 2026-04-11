package com.ai.duet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.adl.base.file.AdlFileHelper;
import com.adl.base.log.AdlLogger;
import com.adl.base.overlay.SportOverlayMultiRegion;
import com.adl.base.speech.AdlTextSpeaker;
import com.adl.base.speech.SpeechCallBack;
import com.adl.lib.AdlCamera;
import com.adl.lib.AdlCameraConfig;
import com.adl.lib.AdlCameraFrameProcessor;
import com.adl.lib.AdlCameraPreviewView;
import com.adl.lib.pose.AdlPoseInfo;
import com.adl.sport.general.EuError;
import com.adl.sport.general.invertedrow.InvertedRowCallBack;
import com.adl.sport.general.invertedrow.InvertedRowConfig;
import com.adl.sport.general.invertedrow.InvertedRowProcessor;
import com.adl.sport.general.invertedrow.InvertedRowStatus;
import com.ai.test.BaseActivity;
import com.adl.ts.general.R;
import com.ai.test.SoundOrderConst;
import com.ai.test.SoundUtil;

import java.io.File;

public class SinglePersonLiveActivity extends BaseActivity {
    TextView scoreTv;
    TextView infoTv;
    Button buttonInit;
    Button buttonStart;
    Button buttonEnd;
    SportOverlayMultiRegion overlay;
    AdlCameraPreviewView adlCameraPreviewView;
    InvertedRowProcessor invertedRowProcessor;
    AdlCamera camera;
    AdlCameraFrameProcessor cameraFrameProcessor;
    int count = 0;
    int invalidCount = 0;
    RadioGroup radioGroup;
    RadioButton radioButton120;
    RadioButton radioButton130;
    RadioButton radioButton140;
    RadioButton radioButton150;
    boolean initDown = false;
    ToggleButton toggle45;
    ToggleButton toggleShoulder;
    ToggleButton toggleToe;
    ToggleButton toggleRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_person_live);
        buttonStart = findViewById(R.id.bnt_start);
        buttonEnd = findViewById(R.id.bnt_end);
        buttonInit = findViewById(R.id.bnt_init);
        adlCameraPreviewView = findViewById(R.id.camera_preview);
        overlay = findViewById(R.id.overlay);
        scoreTv = findViewById(R.id.score_tv);
        infoTv = findViewById(R.id.info_tv);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton120 = findViewById(R.id.radioButton120);
        radioButton130 = findViewById(R.id.radioButton130);
        radioButton140 = findViewById(R.id.radioButton140);
        radioButton150 = findViewById(R.id.radioButton150);
        if (radioButton120 != null) {
            radioButton120.setChecked(true);
        }


        toggle45 = findViewById(R.id.toggle_45);
        toggleShoulder = findViewById(R.id.toggle_shoulder);
        toggleToe = findViewById(R.id.toggle_toe);
        toggleRecord = findViewById(R.id.toggle_record);

        camera = AdlCameraConfig.builder()
                .setCameraType(AdlCameraConfig.CameraType.CameraX)
                .setPreviewSize(1920, 1080)
                .setPreviewFps(20)
                .build();


        cameraFrameProcessor = (yuv, info) -> {
            invertedRowProcessor.onFrame(yuv, info);
        };

        adlCameraPreviewView.addFrameProcessor(cameraFrameProcessor);
        adlCameraPreviewView.startPreview(camera);

        InvertedRowConfig config = new InvertedRowConfig();
        config.enableRecordVideo = true;
        config.poleHeight = InvertedRowConfig.PoleHeight.LOW_120;
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
//                runOnUiThread(() -> {
//                    AdlTextSpeaker.speak("10秒倒计时开始");
//                });
            }

            @Override
            public void cancel() {
                runOnUiThread(() -> {
                    AdlTextSpeaker.speak("运动结束");
                    invertedRowProcessor.changeStatus(InvertedRowStatus.Standby);
                    invertedRowProcessor.onReset();
                });

            }

            @Override
            public void onInit(EuError error) {
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
                    AdlTextSpeaker.speak("运动开始");
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

        buttonStart.setOnClickListener(view -> {
            if (initDown) {
                File root = AdlFileHelper.createSportRootDir();
                File videos = AdlFileHelper.createDir(root, "InvertedRowVideo");
                invertedRowProcessor.startRecording(new File(videos, "/" + System.currentTimeMillis() + ".mp4"));
                invertedRowProcessor.changeStatus(InvertedRowStatus.Sporting);
            }else {
                runOnUiThread(() -> {
                    AdlTextSpeaker.speak("场地尚未构建，请先构建场地");
                });
            }
        });

        buttonInit.setOnClickListener(view -> {
            invertedRowProcessor.changeStatus(InvertedRowStatus.InitPlayGround);
        });

        buttonEnd.setOnClickListener(view -> {
            reset();
            invertedRowProcessor.stopRecording();
            invertedRowProcessor.changeStatus(InvertedRowStatus.Standby);
        });


        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            InvertedRowConfig.PoleHeight poleHeight = InvertedRowConfig.PoleHeight.LOW_120;
            if (checkedId == R.id.radioButton120) {
                poleHeight = InvertedRowConfig.PoleHeight.LOW_120;
            } else if (checkedId == R.id.radioButton130) {
                poleHeight = InvertedRowConfig.PoleHeight.LOW_130;
            } else if (checkedId == R.id.radioButton140) {
                poleHeight = InvertedRowConfig.PoleHeight.MID_140;
            } else if (checkedId == R.id.radioButton150) {
                poleHeight = InvertedRowConfig.PoleHeight.HIGH_150;
            }

            config.poleHeight = poleHeight;
            invertedRowProcessor.setConfig(config);
        });

        // 设置初始状态
        toggle45.setChecked(config.checkInverted_45);
        toggleShoulder.setChecked(config.checkArmAngle);
        toggleToe.setChecked(config.checkToeTip);
        toggleRecord.setChecked(config.enableRecordVideo);

        // 添加点击事件监听器
        toggle45.setOnCheckedChangeListener((buttonView, isChecked) -> {
            config.checkInverted_45 = isChecked;
            invertedRowProcessor.setConfig(config);
        });

        toggleShoulder.setOnCheckedChangeListener((buttonView, isChecked) -> {
            config.checkArmAngle = isChecked;
            invertedRowProcessor.setConfig(config);
        });

        toggleToe.setOnCheckedChangeListener((buttonView, isChecked) -> {
            config.checkToeTip = isChecked;
            invertedRowProcessor.setConfig(config);
        });

        toggleRecord.setOnCheckedChangeListener((buttonView, isChecked) -> {
            config.enableRecordVideo = isChecked;
            invertedRowProcessor.setConfig(config);
        });

    }

    private void reset() {
        invalidCount = 0;
        count = 0;
        scoreTv.setText("成功数：" + count + "  失败数：" + invalidCount);
        invertedRowProcessor.onReset();
        invertedRowProcessor.changeStatus(InvertedRowStatus.Standby);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        invertedRowProcessor.onRelease();

    }
}
