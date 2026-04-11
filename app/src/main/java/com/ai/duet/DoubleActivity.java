package com.ai.duet;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;

import com.adl.base.bean.Quadrilateral;
import com.adl.base.common.AdlVideoReader;
import com.adl.base.common.VideoReaderCallback;
import com.adl.base.config.AdlUIConfig;
import com.adl.base.config.RoleConfigMulti;
import com.adl.base.overlay.OnRenderScene;
import com.adl.base.overlay.SportOverlayMultiRegion;
import com.adl.mount.core.TsModelRes;
import com.adl.mount.core.TsModelResPool;
import com.adl.mount.core.TsTaskDoubleBody;
import com.adl.mount.impl.TsSportLifecycleDouble;
import com.adl.mount.sport.TsConfigDouble;
import com.adl.mount.sport.TsSportEngine;
import com.adl.mount.sport.TsSportRegion;
import com.adl.mount.sport.TsYuvFrameRecCache;
import com.adl.ts.general.R;
import com.ai.test.BaseActivity;

import java.util.Arrays;
import java.util.List;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/7
 * Describe   : 类描述
 */
public class DoubleActivity extends BaseActivity {

    String path = "/sdcard/testVideo/双人视频.mp4";
    SurfaceView surfaceView;
    AdlVideoReader videoReader;
    AdlVideoReader.PlayMode playMode = AdlVideoReader.PlayMode.Auto;
    Button bntPlay;
    Button bntChange;
    Button bntNext;

    SportOverlayMultiRegion overlay;
    TextView scoreTv;

    TsSportEngine sportEngine;
    TsSportLifecycleDouble sportLifecycleDouble;
    TsConfigDouble config;
    DoubleDrawer jumpDrawer;
    DoubleBallImpl ballImpl;

    TsSportRegion leftRegion;
    TsSportRegion rightRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_duet);

        surfaceView = findViewById(R.id.surface_view);
        overlay = findViewById(R.id.overlay);
        scoreTv = findViewById(R.id.score_tv);
        bntPlay = findViewById(R.id.bnt_start);
        bntChange = findViewById(R.id.bnt_change);
        bntNext = findViewById(R.id.bnt_next);

        overlay.enableDebug(true);
        overlay.enableDrawBox(false);
        overlay.enableDrawSkeleton(true);

        findViewById(R.id.bnt_close).setOnClickListener(view -> finish());

        findViewById(R.id.bnt_reset).setOnClickListener(view -> {
            overlay.clearScreen();
            videoReader.onReset(path);
            bntPlay.setText("播放");
        });

        bntPlay.setOnClickListener(view -> {

            // 运动区域
            List<Quadrilateral> list = overlay.getPreviewRegion();
            if (list != null && list.size() == 2) {
                Quadrilateral lq = list.get(0);
                Quadrilateral rq = list.get(1);

                leftRegion = new TsSportRegion(lq.p1, lq.p2, lq.p3, lq.p4);
                rightRegion = new TsSportRegion(rq.p1, rq.p2, rq.p3, rq.p4);
            }

            if (videoReader.isPlaying()) {
                bntPlay.setText("播放");
                videoReader.onPause();
            } else {
                videoReader.onStart();
                bntPlay.setText("暂停");
            }

        });

        // 运动配置
        config = new TsConfigDouble();
        config.bodyModelId = 1003;// 人框
        config.poseModelId = 2004;// 骨骼
        config.debug = true;
        config.readVideo = true;
        config.drawBody = true;
        config.drawAllBody = true;
        config.drawSkeleton = true;

        // 单引擎
        sportEngine = TsSportEngine.instance();
        sportEngine.init(TsYuvFrameRecCache.pool().prepare(25), 4); // 初始化缓存等， 必须
        sportEngine.open(config);// 配置项，必须
        TsModelResPool.loadModel(Arrays.asList(
                new TsModelRes(config.bodyModelId, 8),
                new TsModelRes(config.poseModelId, 8)));

        // 运动绘制模块
        jumpDrawer = new DoubleDrawer();
        ballImpl = new DoubleBallImpl();
        // * 必须设置
        sportLifecycleDouble = new TsSportLifecycleDouble(ballImpl);
        sportEngine.setSportLifecycle(sportLifecycleDouble);

        // 加载页面配置文件
        RoleConfigMulti uiConfig = AdlUIConfig.instance().readConfig("_double_person", RoleConfigMulti.class);
        overlay.setConfig(uiConfig, OnRenderScene.StatusSportSet);

        videoReader = new AdlVideoReader();
        videoReader.setPlayMode(playMode);
        videoReader.setSurface(surfaceView.getHolder().getSurface());
        videoReader.setCallback(new VideoReaderCallback() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onFrame(byte[] yuv, int width, int height, long frameTime) {
                sportEngine.onFrame(yuv, width, height, frameTime,
                        new TsTaskDoubleBody().setRegion(leftRegion, rightRegion));
            }
        });

        // 配置，绘制 绑定
        overlay.setListener(() -> {
            sportEngine.open(config, overlay, jumpDrawer);
            sportEngine.toSporting();
            sportLifecycleDouble.toSporting();
        });

        bntChange.setOnClickListener(view -> {
            if (playMode == AdlVideoReader.PlayMode.Auto) {
                videoReader.setPlayMode(playMode = AdlVideoReader.PlayMode.OneFrame);
                bntChange.setText("单帧模式");
            } else {
                videoReader.setPlayMode(playMode = AdlVideoReader.PlayMode.Auto);
                bntChange.setText("自动模式");
            }
        });
        bntNext.setOnClickListener(view -> {
            videoReader.nextFrame();
        });

        videoReader.onReset(path);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sportEngine.toNone();
        sportLifecycleDouble.toIdle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sportEngine.onRelease();
        videoReader.release();
    }
}
