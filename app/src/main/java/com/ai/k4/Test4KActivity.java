package com.ai.k4;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.adl.base.common.AdlToast;
import com.adl.base.common.AdlVideoReader;
import com.adl.base.common.VideoReaderCallback;
import com.adl.base.config.AdlUIConfig;
import com.adl.base.config.RoleConfigPullUp;
import com.adl.base.config.RoleConfigRopeSkip;
import com.adl.base.element.ElementFpsCounter;
import com.adl.base.file.AdlFileHelper;
import com.adl.base.overlay.OnRenderScene;
import com.adl.base.overlay.SportOverlayPullUp;
import com.adl.base.overlay.SportOverlayVessel;
import com.adl.lib.AdlCameraPreviewInfo;
import com.adl.mount.core.TsModelRes;
import com.adl.mount.core.TsModelResPool;
import com.adl.mount.core.TsTaskDrawer;
import com.adl.mount.core.TsTaskSampleBody;
import com.adl.mount.impl.TsSampleProcessor;
import com.adl.mount.impl.TsSportFrameSample;
import com.adl.mount.impl.TsSportLifecycleSample;
import com.adl.mount.sport.TsConfig;
import com.adl.mount.sport.TsSportEngine;
import com.adl.mount.sport.TsYuvFrameRecCache;
import com.adl.ts.general.R;
import com.ai.test.BaseActivity;
import com.ai.test.FileAdapter;

import java.io.File;
import java.util.Arrays;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/8
 * Describe   : 类描述
 */
public class Test4KActivity extends BaseActivity {

    SportOverlayPullUp overlayVessel;
    Button bntPlay;
    Button bntReset;
    Button bntChange;
    Button bntNext;
    SurfaceView surfaceView;
    AdlVideoReader videoReader;
    AdlVideoReader.PlayMode playMode = AdlVideoReader.PlayMode.Auto;
    long startTime;
    TextView infoTv;
    CheckBox cbAi;
    CheckBox cbRender;

    TsConfig tsConfig;
    TsTaskDrawer drawer;
    ElementFpsCounter fpsCounter;

    TsSportEngine sportEngine;

    ListView listView;
    FileAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_test_4k);

        surfaceView = findViewById(R.id.surface_view);
        listView = findViewById(R.id.list_view);
        bntPlay = findViewById(R.id.bnt_start);
        bntReset = findViewById(R.id.bnt_reset);
        infoTv = findViewById(R.id.info_tv);
        overlayVessel = findViewById(R.id.overlay);
        bntChange = findViewById(R.id.bnt_change);
        bntNext = findViewById(R.id.bnt_next);
        cbAi = findViewById(R.id.cb_ai);
        cbRender = findViewById(R.id.cb_render);

        overlayVessel.enableDrawSkeleton(true);
        overlayVessel.enableDrawBox(false);
        overlayVessel.enableDraw(true);
        overlayVessel.enableDebug(false);

        cbAi.setOnCheckedChangeListener((compoundButton, b) -> cbAi.setText(b ? "算法开启" : "算法关闭"));
        cbRender.setOnCheckedChangeListener((compoundButton, b) -> {
            cbRender.setText(b ? "关闭渲染" : "显示渲染");
            videoReader.enableRender(b);
        });

        mAdapter = new FileAdapter(this, file -> {
            videoReset(file);
        });
        listView.setAdapter(mAdapter);

        bntPlay.setOnClickListener(view -> {

            if (videoReader.isPlaying()) {
                bntPlay.setText("播放");
                videoReader.onPause();
            } else {
                videoReader.onStart();
                bntPlay.setText("暂停");
            }
        });

        findViewById(R.id.bnt_close).setOnClickListener(view -> finish());

        // 运动配置
        tsConfig = new TsConfig();
        tsConfig.debug = true;
        tsConfig.bodyModelId = 1006;// 人框
        tsConfig.poseModelId = 2004;// 骨骼

        fpsCounter = new ElementFpsCounter("视频解码");
        fpsCounter.setPosition(20, 200);
        overlayVessel.addElement(fpsCounter);

        // 绘制
        drawer = new TsTaskDrawer();

        // 运动实现
        TsSampleProcessor sampleProcessor = new TsSampleProcessor() {

            @Override
            public void onAnalyzer(TsSportFrameSample frame) {

            }

            @Override
            public void onInit() {

            }

            @Override
            public void onReset() {

            }

            @Override
            public void onRelease() {

            }

            @Override
            public String version() {
                return null;
            }
        };

        // 单引擎
        sportEngine = TsSportEngine.instance();
        sportEngine.init(TsYuvFrameRecCache.pool().prepare(24), 4);
        sportEngine.open(tsConfig);// 配置项，必须
        sportEngine.setSportLifecycle(new TsSportLifecycleSample(sampleProcessor));
        TsModelResPool.loadModel(Arrays.asList( // 加载模型，必须
                new TsModelRes(tsConfig.bodyModelId, 5),
                new TsModelRes(tsConfig.poseModelId, 5)));

        videoReader = new AdlVideoReader();
        videoReader.setPlayMode(playMode);
        videoReader.setSurface(surfaceView.getHolder().getSurface());
        videoReader.enableRender(false);
        videoReader.setCallback(new VideoReaderCallback() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onFrame(byte[] yuv, int width, int height, long frameTime) {
                AdlCameraPreviewInfo info = new AdlCameraPreviewInfo(width, height);
                info.setImageId(String.valueOf(frameTime));
                info.setFrameTimestamp(frameTime);
                fpsCounter.updateFps();

                if (cbAi.isChecked()) {
                    sportEngine.onFrame(yuv, info, new TsTaskSampleBody());
                }
            }
        });

        // 加载页面配置文件
        RoleConfigPullUp uiConfig = AdlUIConfig.instance()
                .readConfig(overlayVessel.getConfigKey(), RoleConfigPullUp.class);
        overlayVessel.setConfig(uiConfig, OnRenderScene.StatusSportSet);

        // view初始化完成 -> 下一步
        overlayVessel.setListener(() -> {
            // 配置，绘制 绑定
            sportEngine.open(tsConfig, overlayVessel, drawer);
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

        AdlFileHelper.scanFileInRoot("4k", list -> {
            mAdapter.loadData(list);
        });
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
        startTime = 0;
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
        videoReader.release();
    }
}
