package com.ai.jump;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.adl.base.common.AdlExecutor;
import com.adl.base.common.AdlToast;
import com.adl.base.file.AdlFileHelper;
import com.adl.base.speech.AdlTextSpeaker;
import com.adl.sport.focus.JumpResultBean2;
import com.adl.sport.focus.JumpSubStatus;
import com.adl.sport.focus.LongJumpConfig;
import com.adl.sport.focus.LongJumpModelFactory;
import com.adl.ts.general.R;
import com.ai.jump.lib.SportLongJumpCallback;
import com.ai.jump.lib.SportLongJumpLayout;
import com.ai.jump.lib.SportLongJumpResult;
import com.ai.test.BaseActivity;
import com.jf.pose.ModeBodyType;
import com.jf.pose.ModeFPType;
import com.jf.pose.ModePoseType;
import com.nz.sport.layout.SportReqInfo;
import com.nz.sport.layout.SportResultInfo;
import com.nz.sport.layout.SportUserInfo;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/8
 * Describe   : 类描述
 */
public class LongJumpActivity extends BaseActivity {

    TextView mLogTv;
    ImageView preview;
    SportLongJumpLayout longJumpLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_long_jump);
        longJumpLayout = findViewById(R.id.long_jump_layout);
        mLogTv = findViewById(R.id.info_tv);
        preview = findViewById(R.id.preview);

        // 配置
        LongJumpConfig config = new LongJumpConfig();
        config.cameraFps = 24;
        config.debug = true;
        config.enablePrescription = true;
        config.readVideo = true;
        config.waitTakeoffCount = 6;
        config.enableTakeOffOverLine = true;
        config.enableSaveTakeOffOverLine = true;
        config.takeOffOverLineDx = 6;
        config.enableFallDetectAgain = true; // 骨骼处理
        config.fallTimeOut = 3 * 1000;

        config.enableRtOverLine = true;
        config.rtOverLineDx = 16;

        config.enableSaveStageI = true;
        config.enableSaveStageII = true;
        config.enableSaveStageIII = true;
        config.enableSaveFallYuv = true;

        // 高精度计算
        config.measureByJava = true;
        config.measureByReverse = true;

        // 昆仑
        config.klBodyModeType = ModeBodyType.ModeGeneralBody;
        config.klBodyModeFPType = ModeFPType.FP16;
        config.klPoseModeType = ModePoseType.ModeGeneral;
        config.klPoseModeFPType = ModeFPType.FP16;

        // 天山
        config.bodyModelId = LongJumpModelFactory.TsBodyModelId;// 人框
        config.poseModelId = LongJumpModelFactory.TsPoseModelId;// 骨骼

        // 起跳调试
        config.showCrossover = true;
        config.showSkeleton = true;
        config.showHintRect = true;
        config.drawAllBody = true;
        config.crossoverType = 2;

        config.crossoverWRate = 0.30f;
        config.crossoverHRate = 0.75f;
        config.crossoverXRate = 0.35f;

        config.stageICacheSize = (int) (24 * 1.2f);
        config.waitFallFrame = (int) (24 * 3.0f);
        config.useLowMemory = false;

        config.afterFallFrame = 30; // 落地后帧数
        config.enableCheckCollapse = true;// 倒地
        config.enableCheckRollback = true;// 后退
        config.enableCheckHandBrace = true; // 手触地

        config.enableFallCheckOut = true; // 界外落地后再次落地到垫子上
        config.enablePadFootJump = false; // 垫脚跳
        config.enableOneFootJump = true; // 单脚跳
        config.enableRunUpJump = true; // 助跑跳
        config.enableNearEndCheckOut = true; // 摄像头近端是否检测落地
        config.enableDefaultFirstFrame = true; // 默认第一帧
        config.runUpJumpTimeOut = 600;

        config.sportCode = "2000"; // 运动编码

        longJumpLayout.setConfig(config);
        longJumpLayout.setCallback(new SportLongJumpCallback() {

            @Override
            public void onChange(JumpSubStatus status) {
                runOnUiThread(() -> {
                    if (status == JumpSubStatus.WaitTakeOff) {
                        AdlTextSpeaker.speakAfterStop("请开始运动");
                    } else if (status == JumpSubStatus.JumpingTracker) {

                    } else if (status == JumpSubStatus.FallWait) {
                        AdlTextSpeaker.speakAfterStop("结果计算中");
                    }
                });
            }

            @Override
            public void onResult(SportResultInfo bean) {
                AdlExecutor.postIODelayed(() -> {

                    SportLongJumpResult result = (SportLongJumpResult) bean;
                    JumpResultBean2 resultBean = result.resultBean;
                    float distance = resultBean.getDistance();

                    if (!TextUtils.isEmpty(resultBean.getError())) {
                        AdlTextSpeaker.speak("异常: " + resultBean.getError() + ", 最终成绩:" + distance + "厘米");
                    } else {
                        AdlTextSpeaker.speak("成绩:" + distance + "厘米");
                    }

                    AdlExecutor.postUI(() -> {

                        StringBuilder str = new StringBuilder();
                        str.append("\n 距离=" + (int) distance + "cm");
                        str.append("\n\n");
                        str.append(" 警告信息: " + resultBean.getError());
                        str.append("\n\n");
                        str.append("\n 上摆幅度=" + resultBean.getPrepareFrontSwayAngle());
                        str.append("\n 预蹲后摆=" + resultBean.getPrepareBackSwayAngle());
                        str.append("\n 预蹲屈髋=" + resultBean.getPrepareBendHipAngle());
                        str.append("\n 预蹲屈膝=" + resultBean.getPrepareBendKneeAngle());
                        str.append("\n\n 起跳");
                        str.append("\n 起跳角=" + resultBean.getTakeOffAngle());
                        str.append("\n 前摆姿态=" + resultBean.getTakeOffFrontSwayPostureAngle());
                        str.append("\n 前摆幅度=" + resultBean.getTakeOffFrontSwayAngle());
                        str.append("\n 起跳伸髋=" + resultBean.getTakeOffStretchHipAngle());
                        str.append("\n 起跳伸膝=" + resultBean.getTakeOffStretchKneeAngle());
                        str.append("\n 起跳蹬地=" + resultBean.getTakeOffTuckLegAngle());
                        str.append("\n\n 腾空");
                        str.append("\n 手臂位置=" + resultBean.getSoarArmAngle());
                        str.append("\n 腾空收腹=" + resultBean.getSoarShrinkAbdomenAngle());
                        str.append("\n 腾空收腿=" + resultBean.getSoarShrinkLegAngle());
                        str.append("\n 腾空时间=" + resultBean.getSoarDuration());
                        str.append("\n\n 落地");
                        str.append("\n 落地收腹=" + resultBean.getFallShrinkAbdomenAngle());
                        str.append("\n 落地伸腿=" + resultBean.getFallExtendLegAngle());
                        str.append("\n 落地姿态=" + resultBean.getFallPoseAngle());
                        str.append("\n 落地角度=" + resultBean.getFallAngle());
                        str.append("\n 缓冲屈膝=" + resultBean.getFallSpoolerBendKneeAngle());

                        mLogTv.setText(str.toString());

                        // 文件名,前摆角度=;后摆角度=;起跳姿势=;起跳角=;起跳展体=;收腿时间,角度=;振臂幅度=;腿部折叠=;收腿程度=;落地膝角=;跳远距离=
                        StringBuilder str2 = new StringBuilder();
                        //str2.append(mCurFile.getName());
                        str2.append(",");

                        /////////////////////////////////////////////////////////////
                        // 前摆
                        str2.append("上摆幅度=");
                        str2.append(resultBean.getPrepareFrontSwayAngle());
                        str2.append(";");
                        // 预蹲后摆
                        str2.append("预蹲后摆=");
                        str2.append(resultBean.getPrepareBackSwayAngle());
                        str2.append(";");
                        // 预蹲屈髋
                        str2.append("预蹲屈髋=");
                        str2.append(resultBean.getPrepareBendHipAngle());
                        str2.append(";");
                        // 预蹲屈膝
                        str2.append("预蹲屈膝=");
                        str2.append(resultBean.getPrepareBendKneeAngle());
                        str2.append(";");

                        /////////////////////////////////////////////////////////////
                        // 起跳角
                        str2.append("起跳角=");
                        str2.append(resultBean.getTakeOffAngle());
                        str2.append(";");
                        // 前摆姿态
                        str2.append("前摆姿态=");
                        str2.append(resultBean.getTakeOffFrontSwayPostureAngle());
                        str2.append(";");
                        // 前摆幅度
                        str2.append("前摆幅度=");
                        str2.append(resultBean.getTakeOffFrontSwayAngle());
                        str2.append(";");
                        // 起跳伸髋
                        str2.append("起跳伸髋=");
                        str2.append(resultBean.getTakeOffStretchHipAngle());
                        str2.append(";");
                        // 起跳伸膝
                        str2.append("起跳伸膝=");
                        str2.append(resultBean.getTakeOffStretchKneeAngle());
                        str2.append(";");
                        // 起跳蹬地
                        str2.append("起跳蹬地=");
                        str2.append(resultBean.getTakeOffTuckLegAngle());
                        str2.append(";");

                        /////////////////////////////////////////////////////////////
                        // 手臂位置
                        str2.append("手臂位置=");
                        str2.append(resultBean.getSoarArmAngle());
                        str2.append(";");
                        // 腾空收腹
                        str2.append("腾空收腹=");
                        str2.append(resultBean.getSoarShrinkAbdomenAngle());
                        str2.append(";");
                        // 腾空收腿
                        str2.append("腾空收腿=");
                        str2.append(resultBean.getSoarShrinkLegAngle());
                        str2.append(";");
                        // 腾空时间
                        str2.append("腾空时间=");
                        str2.append(resultBean.getSoarDuration());
                        str2.append(";");

                        /////////////////////////////////////////////////////////////
                        // 落地收腹
                        str2.append("落地收腹=");
                        str2.append(resultBean.getFallShrinkAbdomenAngle());
                        str2.append(";");
                        // 落地伸腿
                        str2.append("落地伸腿=");
                        str2.append(resultBean.getFallExtendLegAngle());
                        str2.append(";");
                        // 落地姿态
                        str2.append("落地姿态=");
                        str2.append(resultBean.getFallPoseAngle());
                        str2.append(";");
                        // 落地角度
                        str2.append("落地角度=");
                        str2.append(resultBean.getFallAngle());
                        str2.append(";");
                        // 缓冲屈膝
                        str2.append("缓冲屈膝=");
                        str2.append(resultBean.getFallSpoolerBendKneeAngle());
                        str2.append(";");

                        // 跳远距离
                        str2.append("跳远距离=");
                        str2.append(distance);
                        str2.append("cm\n");

                        File root = AdlFileHelper.createSportRootDir();
                        try {
                            File file = new File(root, "JumpVideoOut.txt");
                            FileWriter out = new FileWriter(file, true);
                            out.write(str2.toString());
                            out.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String path = resultBean.getFallPath();
                        if (!TextUtils.isEmpty(path)) {
                            Bitmap src = BitmapFactory.decodeFile(path);
                            preview.setImageBitmap(src);
                        }

                    });

                }, 500);
            }
        });

        findViewById(R.id.bnt_start).setOnClickListener(view -> {
            longJumpLayout.setCheckIn(Arrays.asList(new SportUserInfo("11111", "zhouwei")));
            SportReqInfo reqInfo = longJumpLayout.toSporting();
            if (!reqInfo.isOk()) {
                AdlToast.show(reqInfo.msg);
            }
        });

        findViewById(R.id.bnt_stop).setOnClickListener(view -> {
            longJumpLayout.toStop();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        longJumpLayout.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        longJumpLayout.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        longJumpLayout.onRelease();
    }
}
