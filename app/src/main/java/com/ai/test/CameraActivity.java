package com.ai.test;

import android.graphics.Point;
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
import com.adl.mount.core.TsModelRes;
import com.adl.mount.core.TsModelResPool;
import com.adl.mount.core.TsTaskDrawer;
import com.adl.mount.core.TsTaskSingleBody;
import com.adl.mount.impl.TsSportLifecycleSingle;
import com.adl.mount.sport.TsConfig;
import com.adl.mount.sport.TsSportEngine;
import com.adl.mount.sport.TsSportRegion;
import com.adl.mount.sport.TsYuvFrameRecCache;
import com.adl.sport.general.EuCallback;
import com.adl.sport.general.EuSingleBase;
import com.adl.ts.general.R;

import java.util.Arrays;
import java.util.List;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/8
 * Describe   : 类描述
 */
public class CameraActivity extends BaseActivity {

    SportOverlayRopeSkip overlayVessel;
    Button bntPlay;
    Button bntReset;
    AdlCameraPreviewView previewView;
    TextView infoTv;

    TsSportRegion sportRegion;
    EuSingleBase euBase;
    TsConfig tsConfig;
    TsTaskDrawer drawer;
    AdlCamera adlCamera;
    AdlCameraFrameProcessor frameProcessor;
    ElementFpsCounter fpsCounter;

//    TsSportEngine sportEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_camera);

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

            previewView.stopPreview();

            // 设置运动区域
//            if (sportRegion == null) {
//                List<Point> list = overlayVessel.getSportRegion().getPreviewPoints();
//                sportRegion = new TsSportRegion(list);
//            }

            adlCamera = AdlCameraConfig.builder()
                    .setCameraType(AdlCameraConfig.CameraType.HK)
                    .setConnect("192.168.169.155", 8000, "admin", "zy132456")
//                    .setPreviewSize(1920, 1080)
                    .setPreviewFps(25)
                    .setPreviewEnable(false)
                    .build();
            previewView.addFrameProcessor(frameProcessor);
            previewView.startPreview(adlCamera);
        });

        findViewById(R.id.bnt_close).setOnClickListener(view -> finish());

        // 运动配置
        tsConfig = new TsConfig();
        tsConfig.debug = true;
        tsConfig.bodyModelId = 1006;// 人框
        tsConfig.poseModelId = 2004;// 骨骼

        // 绘制
        drawer = new TsTaskDrawer();

        // 运动实现
        EuCallback euCallback = new EuCallback() {
            @Override
            public void onLogger(String msg) {

            }

            @Override
            public void onSpeaker(String msg) {
                super.onSpeaker(msg);
            }
        };

        euBase = new EuSingleBase(euCallback) {
            @Override
            public void onSkeleton(AdlPoseSkeleton skeleton) {
                AdlLogger.d("骨骼处理----->" + skeleton.getKey());
            }
        };

//        // 单引擎
//        sportEngine = TsSportEngine.instance();
//        sportEngine.init(TsYuvFrameRecCache.pool().prepare(24), 4);
//        sportEngine.open(tsConfig);// 配置项，必须
//        sportEngine.setSportLifecycle(new TsSportLifecycleSingle(euBase));
//        TsModelResPool.loadModel(Arrays.asList( // 加载模型，必须
//                new TsModelRes(tsConfig.bodyModelId, 6),
//                new TsModelRes(tsConfig.poseModelId, 6)));

        frameProcessor = (yuv, info) -> {
            fpsCounter.updateFps();
            AdlLogger.d("摄像头流 ----->" + info.getWidth() + ", " + info.getHeight());
//            sportEngine.onFrame(yuv, info, new TsTaskSingleBody()
//                    .setRegion(sportRegion));
        };

        // 加载页面配置文件
        RoleConfigRopeSkip uiConfig = AdlUIConfig.instance()
                .readConfig(overlayVessel.getConfigKey(), RoleConfigRopeSkip.class);
        overlayVessel.setConfig(uiConfig, OnRenderScene.StatusSportSet);

        // view初始化完成 -> 下一步
        overlayVessel.setListener(() -> {
            // 配置，绘制 绑定
//            sportEngine.open(tsConfig, overlayVessel, drawer);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        sportEngine.toSporting();
    }

    @Override
    protected void onPause() {
        super.onPause();
        previewView.stopPreview();
//        sportEngine.toNone();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        sportEngine.onRelease();
        previewView.release();
        overlayVessel.release();
    }

}
