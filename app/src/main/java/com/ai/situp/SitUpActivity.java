package com.ai.situp;

import android.os.Bundle;
import android.view.SurfaceView;

import com.adl.algorithm.BodyModelType;
import com.adl.algorithm.PoseModelType;
import com.adl.base.common.AdlJsonUtil;
import com.adl.base.common.AdlVideoReader;
import com.adl.base.common.VideoReaderCallback;
import com.adl.base.config.RoleConfigSitUp;
import com.adl.base.overlay.SportOverlaySingleRegion;
import com.adl.base.overlay.SportOverlaySitUp;
import com.adl.lib.AdlCameraPreviewInfo;
import com.adl.lib.pose.AdlPoseSkeleton;
import com.adl.mount.core.TsModelRes;
import com.adl.mount.core.TsModelResPool;
import com.adl.mount.core.TsTaskDrawer;
import com.adl.mount.core.TsTaskSingleBody;
import com.adl.mount.impl.TsSportLifecycleSingle;
import com.adl.mount.sport.TsSportEngine;
import com.adl.mount.sport.TsSportRegion;
import com.adl.mount.sport.TsYuvFrameRecCache;
import com.adl.sport.general.situp.EuSiteUpCallback;
import com.adl.sport.general.situp.EuSiteUpOnceInfo;
import com.adl.sport.general.situp.SitUpConfig;
import com.adl.ts.general.R;
import com.ai.test.BaseActivity;

import java.util.Arrays;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/8
 * Describe   : 类描述
 */
public class SitUpActivity extends BaseActivity {

    SurfaceView surfaceView;
    AdlVideoReader videoReader;
    AdlVideoReader.PlayMode playMode = AdlVideoReader.PlayMode.Auto;
    SportOverlaySingleRegion overlay;

    TsSportEngine sportEngine;
    SitUpConfig tsConfig;
    TsTaskDrawer tsTaskDrawer;

    TsSportRegion sportRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_situp);

        overlay = findViewById(R.id.overlay);
        initOverlay();
        overlay.enableDrawSkeleton(true);
        overlay.enableDebug(true);

        sportRegion = TsSportRegion.defCenterRegion(1920, 1080);

        // 运动配置
        tsConfig = new SitUpConfig();
        tsConfig.debug = false;
        tsConfig.bodyModelId = BodyModelType.SitUpLH;// 人框
        tsConfig.poseModelId = PoseModelType.SitUp;// 骨骼
        tsConfig.sportRegion = sportRegion;

        // 运动实现
//        euSiteUp = new EuSiteUp(new EuSiteUpCallback() {
//
//            @Override
//            public void onInterrupt() {
//
//            }
//
//            @Override
//            public void onShoulderLowest(AdlPoseSkeleton skeleton) {
//
//            }
//
//            @Override
//            public void onHipHighest(AdlPoseSkeleton skeleton) {
//
//            }
//
//            @Override
//            public void onSiteUpOnce(EuSiteUpOnceInfo info) {
//
//            }
//
//        });
//        euSiteUp.setConfig(tsConfig);


        // 基础绘制
        tsTaskDrawer = new TsTaskDrawer();

        // 单引擎
//        sportEngine = TsSportEngine.instance();
//        sportEngine.init(TsYuvFrameRecCache.pool().prepare(24), 4);
//        sportEngine.open(tsConfig);// 配置项，必须
//        sportEngine.setSportLifecycle(new TsSportLifecycleSingle(euSiteUp));
//        TsModelResPool.loadModel(Arrays.asList( // 加载模型，必须
//                new TsModelRes(tsConfig.bodyModelId, 2),
//                new TsModelRes(tsConfig.poseModelId, 2)));
//
//        // 处方实现
//        prescription = new SiteUpPrescription(sportEngine.cache());
//
//        videoReader = new AdlVideoReader();
//        videoReader.setPlayMode(playMode);
//        videoReader.setSurface(surfaceView.getHolder().getSurface());
//        videoReader.setCallback(new VideoReaderCallback() {
//            @Override
//            public void onError(Exception e) {
//
//            }
//
//            @Override
//            public void onFrame(byte[] yuv, int width, int height, long frameTime) {
//                AdlCameraPreviewInfo info = new AdlCameraPreviewInfo(width, height);
//                info.setImageId(String.valueOf(frameTime));
//                info.setFrameTimestamp(frameTime);
//                sportEngine.onFrame(yuv, info, new TsTaskSingleBody().setRegion(sportRegion));
//            }
//        });
//
//        // 初始化
//        overlay.setListener(() -> {
//            sportEngine.open(tsConfig, overlay, tsTaskDrawer);
//        });
//        euSiteUp.setCache(sportEngine.cache());
//        sportEngine.toSporting();
    }

    private void initOverlay() {
//        RoleConfigSitUp config = AdlJsonUtil.jsonToObject(sitUp, RoleConfigSitUp.class);
//        if (config == null) config = RoleConfigSitUp();

        overlay.setConfig(new RoleConfigSitUp(), SportOverlaySitUp.StatusSportSet);
        overlay.enableDrawSkeleton(true);
        overlay.enableDrawBox(true);
        overlay.enableDraw(true);
        overlay.enableDebug(true);
        overlay.enableTouch(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        sportEngine.toSporting();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        sportEngine.toNone();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        sportEngine.onRelease();
    }
}
