package com.ai.duet;

import android.graphics.Point;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.adl.base.bean.Quadrilateral;
import com.adl.base.config.AdlUIConfig;
import com.adl.base.config.RoleConfigMulti;
import com.adl.base.config.RoleConfigRopeSkip;
import com.adl.base.element.ElementFpsCounter;
import com.adl.base.log.AdlLogger;
import com.adl.base.overlay.OnRenderScene;
import com.adl.base.overlay.SportOverlayMultiRegion;
import com.adl.base.overlay.SportOverlayRopeSkip;
import com.adl.lib.AdlCamera;
import com.adl.lib.AdlCameraConfig;
import com.adl.lib.AdlCameraFrameProcessor;
import com.adl.lib.AdlCameraPreviewView;
import com.adl.lib.pose.AdlPoseSkeleton;
import com.adl.mount.core.TsModelRes;
import com.adl.mount.core.TsModelResPool;
import com.adl.mount.core.TsTaskDrawer;
import com.adl.mount.core.TsTaskMultiBody;
import com.adl.mount.core.TsTaskSingleBody;
import com.adl.mount.impl.TsSportLifecycleMulti;
import com.adl.mount.impl.TsSportLifecycleSingle;
import com.adl.mount.sport.TsConfig;
import com.adl.mount.sport.TsConfigMulti;
import com.adl.mount.sport.TsSportEngine;
import com.adl.mount.sport.TsSportRegion;
import com.adl.mount.sport.TsYuvFrameRecCache;
import com.adl.sport.general.EuBase;
import com.adl.sport.general.EuCallback;
import com.adl.sport.general.groupskipping.GroupSkippingCallBack;
import com.adl.sport.general.groupskipping.GroupSkippingConfig;
import com.adl.sport.general.groupskipping.GroupSkippingMan;
import com.adl.sport.general.groupskipping.GroupSkippingModelType;
import com.adl.sport.general.groupskipping.GroupSkippingProcessor;
import com.adl.ts.general.R;
import com.ai.test.BaseActivity;
import com.jf.pose.ModePoseType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupSkippingLivingActivity extends BaseActivity {

    SportOverlayMultiRegion overlay;
    Button startBtn;
    Button resetBtn;
    AdlCameraPreviewView previewView;
    TextView scoreTv;

    TsSportRegion sportRegion;
    EuBase euBase;
    GroupSkippingConfig tsConfig;
    TsTaskDrawer drawer;
    AdlCamera adlCamera;
    AdlCameraFrameProcessor frameProcessor;
    ElementFpsCounter fpsCounter;

    TsSportEngine sportEngine;
    Spinner changeModel;
    List<TsSportRegion> regions = new ArrayList<>();

    GroupSkippingProcessor processor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_skipping_living);

        previewView = findViewById(R.id.surface_view);
        startBtn = findViewById(R.id.bnt_start);
        resetBtn = findViewById(R.id.bnt_reset);
        scoreTv = findViewById(R.id.score_tv);
        changeModel = findViewById(R.id.bnt_change_model);
        overlay = findViewById(R.id.overlay);

        overlay.enableDrawSkeleton(true);
        overlay.enableDrawBox(false);
        overlay.enableDraw(true);
        overlay.enableDebug(false);
        RoleConfigMulti uiConfig = AdlUIConfig.instance().readConfig("_five_person", RoleConfigMulti.class);
        uiConfig.setNumber(5);
        overlay.setConfig(uiConfig, OnRenderScene.StatusSportSet);
        TsModelResPool.loadModel(Arrays.asList(new TsModelRes(1000, 6),
                new TsModelRes(2004, 6), new TsModelRes(2018, 6),new TsModelRes(2020, 6)));


        GroupSkippingConfig config = new GroupSkippingConfig();
        config.screenResolution = 1;
        config.debugOnlyModelGT = false;
        config.debug = false;
        config.readVideo = true;
        config.drawBody = true;
        config.drawAllBody = true;
        config.drawSkeleton = true;
        config.highPrecisionHighEnergyConsumption = true;
        config.useSmooth = false;

        processor = new GroupSkippingProcessor(config);
        processor.combineCamera("172.16.4.64", 8888, "admin", "zy123456", true);

        processor.combineOverlay(overlay);
        processor.setCallBack(new GroupSkippingCallBack() {
            @Override
            public void onSkippingMan(List<GroupSkippingMan> groupSkippingManList) {
                String textStr = "";
                for (int i = 0; i < groupSkippingManList.size(); i++) {
                    GroupSkippingMan man = groupSkippingManList.get(i);
                    textStr = textStr + "-------" + "第" + man.positionNum + "道：" + man.score;
                }
                scoreTv.setText(textStr);
            }
        });

        previewView.addFrameProcessor(processor.getFrameProcessor());
        previewView.startPreview(processor.getAdlCamera());
        startBtn.setOnClickListener(view -> {

//            // 设置运动区域
//            if (sportRegion == null) {
//                // 运动区域
//                List<Quadrilateral> list = overlay.getPreviewRegion();
//                if (list != null && list.size() == 5) {
//                    regions.clear();
//                    for (Quadrilateral q : list) {
//                        regions.add(new TsSportRegion(q.p1, q.p2, q.p3, q.p4));
//                    }
//                }
//            }

            int itemId = (int) changeModel.getSelectedItemId();
            if (itemId == 0) {
                config.poseModelId = GroupSkippingModelType.skipRopeSkeletonLarge.value();
            } else if (itemId == 1) {
                config.poseModelId = GroupSkippingModelType.groupSkippingSkeletonLarge.value();
            } else if (itemId == 2) {
                config.poseModelId = GroupSkippingModelType.groupSkippingSkeletonMid.value();
            } else {
                config.poseModelId = GroupSkippingModelType.groupSkippingSkeletonTiny.value();
            }
            processor.setConfig(config);
            processor.combineRegions();

//            processor.toStop();
            processor.toSporting();
            processor.setCheckChannelStatus(true);
            processor.setCheckSkipScore(true);


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
        sportEngine.toNone();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sportEngine.onRelease();
        previewView.release();
    }
}