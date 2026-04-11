package com.ai.jump.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.adl.base.common.AdlDevicePlatform;
import com.adl.base.common.AdlVideoAsyncRecorder;
import com.adl.base.config.AdlUIConfig;
import com.adl.base.config.RoleConfigJump;
import com.adl.base.file.AdlFileHelper;
import com.adl.base.overlay.SportOverlayJump;
import com.adl.base.overlay.SportOverlayMain;
import com.adl.lib.AdlCamera;
import com.adl.lib.AdlCameraConfig;
import com.adl.lib.AdlCameraFrameProcessor;
import com.adl.lib.AdlCameraPreviewInfo;
import com.adl.lib.AdlCameraPreviewView;
import com.adl.lib.pose.AdlPoseSkeleton;
import com.adl.processor.SportProcessorStatus;
import com.adl.sport.focus.JumpResultBean2;
import com.adl.sport.focus.JumpSubStatus;
import com.adl.sport.focus.LongJumpCallback;
import com.adl.sport.focus.LongJumpConfig;
import com.adl.sport.focus.LongJumpProcessor;
import com.adl.sport.focus.OnBeforeCallbackSample;
import com.adl.ts.general.R;
import com.nz.sport.layout.SportErrorInfo;
import com.nz.sport.layout.SportMainCallback;
import com.nz.sport.layout.SportMainConfig;
import com.nz.sport.layout.SportMainLayout;
import com.nz.sport.layout.SportReqInfo;
import com.nz.sport.layout.SportRuleInfo;
import com.nz.sport.layout.SportUserInfo;

import java.io.File;
import java.util.List;

/**
 * ProjectName: AdlMountGeneral
 * Author     : 南山
 * Date       : 2026/1/19
 * Describe   : 类描述
 */
public class SportLongJumpLayout extends SportMainLayout {

    private final int imageWidth = 1920;
    private final int imageHeight = 1080;
    private AdlCameraPreviewView mPreviewView;
    private SportOverlayJump mJumpOverlay;

    private AdlCamera mAdlCamera;
    private AdlVideoAsyncRecorder mRecorder;
    private File mVideoPath;
    private volatile boolean mEnableRecording;

    private LongJumpProcessor mProcessor;
    private LongJumpConfig mConfig;
    private AdlCameraFrameProcessor mFrameProcessor;
    private SportLongJumpCallback mCallBack;
    private SportUserInfo mUserInfo;

    public SportLongJumpLayout(@NonNull Context context) {
        super(context);
    }

    public SportLongJumpLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context) {
        super.initView(context);
        View view = LayoutInflater.from(context).inflate(R.layout.sport_long_jump_layout, null);
        addView(view, new LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        mPreviewView = view.findViewById(R.id.preview_view);
        mJumpOverlay = view.findViewById(R.id.overlay);

        mProcessor = new LongJumpProcessor();
        mProcessor.open(mJumpOverlay);
        mProcessor.setBeforeCallback(new OnBeforeCallbackSample() {

            @Override
            public void onOverLine(String info) {
                if (mCallBack != null) mCallBack.onBreakRule(new SportRuleInfo(-1, info));
            }

            @Override
            public void onOverLineByOneFrame(String imageId, String info) {
                if (mCallBack != null) mCallBack.onBreakRule(new SportRuleInfo(-2, info));
            }

            @Override
            public void onPadFootJump(String imageId, String info) {
                if (mCallBack != null) mCallBack.onBreakRule(new SportRuleInfo(-3, info));
            }

            @Override
            public void onOneFootJump(String imageId, String info) {
                if (mCallBack != null) mCallBack.onBreakRule(new SportRuleInfo(-4, info));
            }

            @Override
            public void onRunUpJump(String info) {
                if (mCallBack != null) mCallBack.onBreakRule(new SportRuleInfo(-5, info));
            }

        });
        mProcessor.setJumpCallback(new LongJumpCallback() {

            @Override
            public void onSpeaker(String msg) {
                if (mCallBack != null) mCallBack.onSpeaker(msg);
            }

            @Override
            public void onChange(JumpSubStatus status) {
                if (mCallBack != null) mCallBack.onChange(status);
            }

            @Override
            public void onError(int code, String error) {
                if (mCallBack != null) mCallBack.onError(new SportErrorInfo(-2, error));
            }

            @Override
            public void onFallDone(AdlPoseSkeleton startKey, AdlPoseSkeleton fallKey,
                                   float distance, JumpResultBean2 resultBean) {
                SportLongJumpResult result = new SportLongJumpResult();
                result.startKey = startKey;
                result.fallKey = fallKey;
                result.distance = distance;
                result.resultBean = resultBean;
                if (mCallBack != null) mCallBack.onResult(result);
            }
        });
        mFrameProcessor = SportLongJumpLayout.this::onFrame;

        // 视频录制
        mRecorder = new AdlVideoAsyncRecorder();
    }

    @Override
    public void setConfig(SportMainConfig config) {
        mProcessor.setConfig(mConfig = (LongJumpConfig) config);
        mProcessor.onInit();
    }

    @Override
    public void setCallback(SportMainCallback callback) {
        mCallBack = (SportLongJumpCallback) callback;
    }

    @Override
    public void onResume() {

        // 加载页面配置文件
        RoleConfigJump uiConfig = AdlUIConfig.instance()
                .readConfig(mJumpOverlay.getConfigKey(), RoleConfigJump.class);
        mJumpOverlay.setConfig(uiConfig, SportOverlayMain.StatusSportDoing);

        // 针对不同光线配置摄像头参数
        if (!mConfig.readVideo) {
            mAdlCamera = AdlCameraConfig.builder()
                    .setPreviewSize(imageWidth, imageHeight)
                    .setCameraType(mPlatform == AdlDevicePlatform.TShan ? AdlCameraConfig.CameraType.UVC : AdlCameraConfig.CameraType.CameraX)
                    .setCameraId(AdlCameraConfig.CameraId.Back)
                    .setPreviewFps(30)
                    .build();
            mPreviewView.addFrameProcessor(mFrameProcessor);
            mPreviewView.startPreview(mAdlCamera);
        }
    }

    // 外部debug支持
    public void onFrame(byte[] yuv, AdlCameraPreviewInfo info) {

        // 视频录制
        if (mEnableRecording) mRecorder.inputNv21(yuv);

        // 处理数据
        mProcessor.onFrame(yuv, info);
    }

    @Override
    public void onPause() {
        mPreviewView.stopPreview();
        mPreviewView.removeFrameProcessor(mFrameProcessor);
    }

    @Override
    public void setCheckIn(List<SportUserInfo> checkList) {
        if (checkList == null || checkList.size() == 0) return;
        mUserInfo = checkList.get(0);
    }

    @Override
    public void toCheckFace() {

    }

    @Override
    public void toInitScene() {
        mProcessor.changeTo(SportProcessorStatus.MatDetect);
    }

    @Override
    public void toReset() {
        mProcessor.onReset();
    }

    @Override
    public SportReqInfo toPreparing() {
        return SportReqInfo.ok();
    }

    @Override
    public SportReqInfo toSporting() {

        if (mUserInfo == null) {
            return SportReqInfo.error("请先设置用户信息");
        }

        // 存储目录
        File rootDir = AdlFileHelper.createUserDir(mUserInfo.userName, mConfig.sportCode);
        mProcessor.setRootDir(rootDir, mUserInfo.userId);

        // 状态切换
        mProcessor.onReset();
        mProcessor.changeTo(SportProcessorStatus.Sporting);

        // 视频录制
        if (!mConfig.readVideo) {
            File videoDir = AdlFileHelper.createAppVideoDir();
            String fileName = AdlFileHelper.currentDayOfTime() + ".mp4";
            mVideoPath = new File(videoDir, fileName);
            mRecorder.onStart(mVideoPath, imageWidth, imageHeight);
            mEnableRecording = true;
        } else {
            mEnableRecording = false;
        }

        return SportReqInfo.ok();
    }

    @Override
    public void toStop() {
        mProcessor.changeTo(SportProcessorStatus.Idle);
        mRecorder.onStop();
    }

    @Override
    public void onRelease() {
        mPreviewView.stopPreview();
        mPreviewView.release();
        mProcessor.onRelease();
        mRecorder.onRelease();
    }
}
