package com.ai.test;

import android.graphics.Color;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.adl.base.common.AdlAudioReader;
import com.adl.base.common.AdlToast;
import com.adl.base.common.AdlVideoReader;
import com.adl.base.common.VideoReaderCallback;
import com.adl.base.config.RoleConfigSolidBall;
import com.adl.base.file.AdlFileHelper;
import com.adl.base.overlay.OnRenderScene;
import com.adl.base.overlay.SportOverlaySolidBall;
import com.adl.base.view.WaveLine;
import com.adl.lib.AdlCameraPreviewInfo;
import com.adl.mount.core.TsTaskDrawer;
import com.adl.mount.core.TsTaskSingleBody;
import com.adl.mount.sport.TsConfig;
import com.adl.mount.sport.TsSportEngine;
import com.adl.mount.sport.TsSportRegion;
import com.adl.ts.general.R;

import java.io.File;
import java.util.Random;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/8
 * Describe   : 类描述
 */
public class SingleActivity extends BaseActivity {

    //    SportOverlayRopeSkip overlayVessel;
    Button bntPlay;
    Button bntReset;
    Button bntChange;
    Button bntNext;
    //    SurfaceView surfaceView;
    AdlVideoReader videoReader;
    AdlVideoReader.PlayMode playMode = AdlVideoReader.PlayMode.Auto;
    TextView infoTv;

    TsSportRegion sportRegion;
    //    EuSingleBase euBase;
    TsConfig tsConfig;
    TsTaskDrawer drawer;

    TsSportEngine sportEngine;

    ListView listView;
    FileAdapter mAdapter;

    AdlAudioReader audioReader;

//    WaveLine waveLine;
//    Random random = new Random();

    SportOverlaySolidBall ballOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_situp);

//        surfaceView = findViewById(R.id.surface_view);
//        listView = findViewById(R.id.list_view);
        bntPlay = findViewById(R.id.bnt_start);
        bntReset = findViewById(R.id.bnt_reset);
        infoTv = findViewById(R.id.info_tv);
//        overlayVessel = findViewById(R.id.overlay);
        bntChange = findViewById(R.id.bnt_change);
        bntNext = findViewById(R.id.bnt_next);
        ballOverlay = findViewById(R.id.solid_ball_overlay);
        ballOverlay.setDeviceType(1);
        ballOverlay.setDirection(1);

//        waveLine = findViewById(R.id.wave);
//        waveLine.setBackgroundColor(Color.GRAY);

//        overlayVessel.enableDrawSkeleton(true);
//        overlayVessel.enableDrawBox(false);
//        overlayVessel.enableDraw(true);
//        overlayVessel.enableDebug(false);

//        audioReader = new AdlAudioReader();
//        audioReader.setCallback(new AdlAudioReader.OnAudioCallback() {
//            @Override
//            public void onPrepared() {
//                audioReader.onStart();
//            }
//
//            @Override
//            public void onAudio(long position) {
//
//            }
//
//            @Override
//            public void onEnd() {
//
//            }
//        });

//        mAdapter = new FileAdapter(this, file -> {
//            videoReset(file);
//        });
//        listView.setAdapter(mAdapter);

        bntPlay.setOnClickListener(view -> {

//            audioReader.openRes(R.raw.s_90_1_min);

//            // 设置运动区域
//            if (sportRegion == null) {
//                List<Point> list = overlayVessel.getSportRegion().getPreviewPoints();
//                sportRegion = new TsSportRegion(list);
//            }
//
//            if (videoReader.isPlaying()) {
//                bntPlay.setText("播放");
//                videoReader.onPause();
//            } else {
//                videoReader.onStart();
//                bntPlay.setText("暂停");
//            }


//            long startTime = System.currentTimeMillis();
//            waveLine.start(60, startTime);
//            new Thread(() -> {
//
//                long dt = System.currentTimeMillis() - startTime;
//                while (dt < 60 * 1000) {
//
//                    dt = System.currentTimeMillis() - startTime;
//                    if (dt < 10000) {
//
//                    } else {
//                        waveLine.insert((Math.abs(random.nextInt()) + 300) % 300);
//                    }
//
//                    try {
//                        Thread.sleep(250);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();

        });

        findViewById(R.id.bnt_close).setOnClickListener(view -> finish());

        // 运动配置
        tsConfig = new TsConfig();
        tsConfig.debug = true;
        tsConfig.bodyModelId = 1003;// 人框
        tsConfig.poseModelId = 2004;// 骨骼

        // 绘制
        drawer = new TsTaskDrawer();

        // 运动实现
//        EuCallback euCallback = new EuCallback() {
//            @Override
//            public void onLogger(String msg) {
//
//            }
//
//            @Override
//            public void onSpeaker(String msg) {
//                super.onSpeaker(msg);
//            }
//        };
//
//        euBase = new EuSingleBase(euCallback) {
//            @Override
//            public void onSkeleton(AdlPoseSkeleton skeleton) {
//                AdlLogger.d("骨骼处理----->" + skeleton.getKey());
//            }
//        };

        // 单引擎
//        sportEngine = TsSportEngine.instance();
//        sportEngine.init(TsYuvFrameRecCache.pool().prepare(24), 4);
//        sportEngine.open(tsConfig);// 配置项，必须
//        sportEngine.setSportLifecycle(new TsSportLifecycleSingle(euBase));
//        TsModelResPool.loadModel(Arrays.asList( // 加载模型，必须
//                new TsModelRes(tsConfig.bodyModelId, 6),
//                new TsModelRes(tsConfig.poseModelId, 6)));

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

        // 加载页面配置文件
//        RoleConfigRopeSkip uiConfig = AdlUIConfig.instance()
//                .readConfig(overlayVessel.getConfigKey(), RoleConfigRopeSkip.class);
//        overlayVessel.setConfig(uiConfig, OnRenderScene.StatusSportSet);
//
//        // view初始化完成 -> 下一步
//        overlayVessel.setListener(() -> {
//            // 配置，绘制 绑定
//            sportEngine.open(tsConfig, overlayVessel, drawer);
//        });

        ballOverlay.setConfig(new RoleConfigSolidBall(), OnRenderScene.StatusSportSet);

        bntChange.setOnClickListener(view -> {
//            if (playMode == AdlVideoReader.PlayMode.Auto) {
//                videoReader.setPlayMode(playMode = AdlVideoReader.PlayMode.OneFrame);
//                bntChange.setText("单帧模式");
//            } else {
//                videoReader.setPlayMode(playMode = AdlVideoReader.PlayMode.Auto);
//                bntChange.setText("自动模式");
//            }
        });
        bntNext.setOnClickListener(view -> {
//            videoReader.nextFrame();
        });

//        AdlFileHelper.scanFileInRoot("testVideo", list -> {
//            mAdapter.loadData(list);
//        });
    }

    private void videoReset(File file) {
        if (file == null) {
            AdlToast.show("请选择文件");
            return;
        }
        videoReader.onReset(file.getAbsolutePath());
        bntPlay.setText("播放");
        infoTv.setText(file.getName());
        reset();
    }

    private void reset() {

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
