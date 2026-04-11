package com.ai.jump.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.adl.base.common.AdlToast;
import com.adl.base.config.AdlUIConfig;
import com.adl.base.config.RoleConfigJump;
import com.adl.base.element.ElementJumpTarget;
import com.adl.base.overlay.SportOverlayJump;
import com.adl.base.overlay.SportOverlayMain;
import com.adl.lib.AdlCameraPreviewView;
import com.adl.processor.SportProcessorStatus;
import com.adl.sport.focus.LongJumpMat;
import com.adl.sport.focus.LongJumpMatCallback;
import com.adl.sport.focus.LongJumpMatProcessor;
import com.adl.ts.general.R;
import com.nz.sport.layout.SportMainConfig;
import com.nz.sport.layout.SportMainSetCallback;
import com.nz.sport.layout.SportMainSetLayout;
import com.nz.sport.layout.SportModelInfo;
import com.nz.sport.layout.SportReqInfo;

/**
 * ProjectName: AdlMountGeneral
 * Author     : 南山
 * Date       : 2026/1/26
 * Describe   : 类描述
 */
public class SportLongJumpSetLayout extends SportMainSetLayout {

    private AdlCameraPreviewView mPreviewView;
    private SportOverlayJump mJumpOverlay;
    private ElementJumpTarget mJumpTarget;
    private SportLongJumpSetCallback mCallback;
    private int mDirection;
    private LongJumpMatProcessor mMatProcessor;

    public SportLongJumpSetLayout(@NonNull Context context) {
        super(context);
        loadView(context);
    }

    public SportLongJumpSetLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        loadView(context);
    }

    public SportLongJumpSetLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadView(context);
    }

    private void loadView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.sport_long_jump_set_layout, null);
        addView(view, new LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        mJumpOverlay = view.findViewById(R.id.overlay);
        mPreviewView = view.findViewById(R.id.preview_view);
        mJumpOverlay.setListener(() -> {
            mJumpTarget = mJumpOverlay.getSportRegion();
            mDirection = mJumpTarget.getDirection();
        });

        mMatProcessor = new LongJumpMatProcessor();
        mMatProcessor.open(mJumpOverlay);
        mMatProcessor.setCallback(new LongJumpMatCallback() {

            @Override
            public void onError(int code, String error) {
                AdlToast.show(error);
            }

            @Override
            public void onModelOk() {
                if (mCallback != null) mCallback.onModel(new SportModelInfo(0, "ok"));
            }

            @Override
            public void onSpeaker(String msg) {
                if (mCallback != null) mCallback.onSpeaker(msg);
            }

            @Override
            public void onMatInfo(LongJumpMat mat) {

            }
        });
        mMatProcessor.onInit();
    }

    @Override
    public void onResume() {
        // 加载页面配置文件
        RoleConfigJump uiConfig = AdlUIConfig.instance()
                .readConfig(mJumpOverlay.getConfigKey(), RoleConfigJump.class);
        mJumpOverlay.setConfig(uiConfig, SportOverlayMain.StatusSportSet);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void setConfig(SportMainConfig config) {

    }

    @Override
    public void setCallback(SportMainSetCallback callback) {
        mCallback = (SportLongJumpSetCallback) callback;
    }

    @Override
    public void toInitScene() {
        mMatProcessor.changeTo(SportProcessorStatus.MatDetect);
    }

    public void toChangeDirection() {
        mDirection = mDirection == 1 ? 2 : 1;
        mJumpTarget.setDirection(++mDirection);
    }

    @Override
    public SportReqInfo toSave() {
        return mJumpOverlay.saveConfig() ? SportReqInfo.ok() : SportReqInfo.error("保存失败");
    }
}
