package com.ai.ropeskip;

import androidx.appcompat.app.AppCompatActivity;

import com.adl.algorithm.BodyModelType;
import com.adl.algorithm.PoseModelType;
import com.adl.base.common.AdlVideoReader;
import com.adl.base.common.VideoReaderCallback;
import com.adl.base.config.AdlUIConfig;
import com.adl.base.config.RoleConfigRopeSkip;
import com.adl.base.log.AdlLogger;
import com.adl.base.overlay.OnRenderScene;
import com.adl.base.overlay.SportOverlayRopeSkip;
import com.adl.lib.AdlCameraPreviewInfo;
import com.adl.lib.pose.AdlPoseInfo;
import com.adl.lib.pose.AdlPoseSkeleton;
import com.adl.mount.core.TsModelRes;
import com.adl.mount.core.TsModelResPool;
import com.adl.mount.core.TsTaskDrawer;
import com.adl.mount.core.TsTaskSingleBody;
import com.adl.mount.impl.TsSportLifecycleSingle;
import com.adl.mount.sport.TsSportEngine;
import com.adl.mount.sport.TsSportRegion;
import com.adl.mount.sport.TsYuvFrameRecCache;
import com.adl.sport.general.invertedrow.InvertedRowStatus;
import com.adl.sport.general.ropeskip.EuRopeSkipAF;
import com.adl.sport.general.ropeskip.EuRopeSkipCallback;
import com.adl.sport.general.ropeskip.EuRopeSkipConfig;
import com.adl.sport.general.ropeskip.EuRopeSkipResult;
import com.adl.sport.general.ropeskip.EuRopeSkipTask;
import com.adl.sport.general.situp.SitUpConfig;
import com.adl.ts.general.R;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class RopeSkipAFActivity extends AppCompatActivity {
    SportOverlayRopeSkip overlayRopeSkip;
    TsSportRegion sportRegion;
    SurfaceView surfaceView;
    AdlVideoReader videoReader;
    AdlVideoReader.PlayMode playMode = AdlVideoReader.PlayMode.Auto;
    EuRopeSkipConfig config;
    TsSportEngine sportEngine;
    TsTaskDrawer tsTaskDrawer;
    EuRopeSkipAF euRopeSkipAF;
    String path = "/sdcard/com.adl.sport/RopeSkipAF/双脚交替跳女.mp4";
    Button bntPlay;
    Button bntChange;
    Button bntNext;
    Button bntReset;
    TextView scoreTv;
    TextView infoTv;
    TsSportLifecycleSingle lifecycleSingle;
    int scoreNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rope_skip_afactivity);
        surfaceView = findViewById(R.id.surface_view);
        overlayRopeSkip = findViewById(R.id.overlay);


        scoreTv = findViewById(R.id.score_tv);
        infoTv = findViewById(R.id.info_tv);
        bntPlay = findViewById(R.id.bnt_start);
        bntChange = findViewById(R.id.bnt_change);
        bntNext = findViewById(R.id.bnt_next);
        bntReset = findViewById(R.id.bnt_reset);

        overlayRopeSkip.enableDrawSkeleton(true);
        overlayRopeSkip.enableDebug(true);
        overlayRopeSkip.enableDraw(true);


        sportRegion = TsSportRegion.defCenterRegion(1920, 1080);

        config = new EuRopeSkipConfig();
        config.debug = true;
        config.bodyModelId = 1003;// 人框
        config.poseModelId = 2004;// 骨骼
        config.checkRope = true;
        config.checkRopeRigor = 50;
        config.sportRegion = sportRegion;
        TsModelResPool.loadModel(Arrays.asList( // 加载模型，必须
                new TsModelRes(config.bodyModelId, 4),
                new TsModelRes(config.poseModelId, 4),
                new TsModelRes(2036, 4)
                ));


        euRopeSkipAF = new EuRopeSkipAF(new EuRopeSkipCallback() {
            @Override
            public void onRopeSkipComplete(EuRopeSkipResult result) {
                scoreNum++;
                scoreTv.setText("得分：" + scoreNum);
            }

            @Override
            public void onKeyFrame(AdlPoseSkeleton skeleton, String suffix) {
                super.onKeyFrame(skeleton, suffix);
                AdlPoseInfo poseInfo = new AdlPoseInfo();
                poseInfo.setKey(skeleton.getKey());
                poseInfo.setImageWidth(skeleton.getImageWidth());
                poseInfo.setImageHeight(skeleton.getImageHeight());
                poseInfo.setFrom("Adl");
                poseInfo.setPlayer("");
                poseInfo.getSkeletons().add(skeleton);
                overlayRopeSkip.onProcessor(poseInfo);
            }

            @Override
            public void onCheckRope(boolean ropeExist) {
                super.onCheckRope(ropeExist);
                AdlLogger.d("============== 检测绳:" + (ropeExist ? "存在" : "不存在"));
            }
        });
        euRopeSkipAF.setConfig(config);

        // 基础绘制
        tsTaskDrawer = new TsTaskDrawer();

        // 单引擎
        sportEngine = TsSportEngine.instance();
        sportEngine.init(TsYuvFrameRecCache.pool().prepare(8), 4);
        sportEngine.open(config);// 配置项，必须
        lifecycleSingle = new TsSportLifecycleSingle(euRopeSkipAF);
        sportEngine.setSportLifecycle(lifecycleSingle);
        euRopeSkipAF.setCache(sportEngine.cache());
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
                sportEngine.onFrame(yuv, info, new EuRopeSkipTask().setRegion(sportRegion));
            }
        });


        RoleConfigRopeSkip uiConfig = AdlUIConfig.instance()
                .readConfig(overlayRopeSkip.getConfigKey(), RoleConfigRopeSkip.class);
        overlayRopeSkip.setConfig(uiConfig, OnRenderScene.StatusSportSet);
        overlayRopeSkip.addElement(tsTaskDrawer);

        // 初始化
        overlayRopeSkip.setListener(() -> {
            sportEngine.open(config, overlayRopeSkip, tsTaskDrawer);
            lifecycleSingle.toSporting();
        });

        sportEngine.toSporting();

        bntPlay.setOnClickListener(view -> {
            if (videoReader.isPlaying()) {
                bntPlay.setText("播放");
                videoReader.onPause();
            } else {
                videoReader.onStart();
                bntPlay.setText("暂停");
            }
            overlayRopeSkip.saveConfig();
            overlayRopeSkip.setConfig(overlayRopeSkip.getConfig(), OnRenderScene.StatusSportDoing);
        });

        bntReset.setOnClickListener(view -> {
            if (videoReader.isPlaying()) {
                videoReader.onPause();
            }
            videoReader.onReset(path);
            scoreNum = 0;
            overlayRopeSkip.setConfig(overlayRopeSkip.getConfig(), OnRenderScene.StatusSportSet);
        });

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        sportEngine.toSporting();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sportEngine.toNone();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sportEngine.onRelease();
    }
}