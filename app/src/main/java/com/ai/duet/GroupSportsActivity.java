package com.ai.duet;

import com.adl.base.config.AdlUIConfig;
import com.adl.base.config.RoleConfigMulti;
import com.adl.base.overlay.OnRenderScene;
import com.adl.base.overlay.SportOverlayMultiRegion;
import com.adl.lib.AdlCamera;
import com.adl.lib.AdlCameraConfig;
import com.adl.lib.AdlCameraPreviewView;
import com.adl.lib.pose.AdlObject;
import com.adl.lib.pose.AdlPoseInfo;
import com.adl.mount.core.TsModelInfo;
import com.adl.mount.core.TsModelRes;
import com.adl.mount.core.TsModelResPool;
import com.adl.mount.core.TsTaskBodyDefaultFilter;
import com.adl.mount.core.TsTaskBodyFilter;
import com.adl.mount.sport.TsSportRegion;
import com.adl.processor.SportProcessorStatus;
import com.adl.sport.general.EuError;
import com.adl.sport.general.groupcrunch.GroupCrunchMan;
import com.adl.sport.general.groupcrunch.GroupCrunchParser;
import com.adl.sport.general.groupjumpingjack.GroupJumpingJackMan;
import com.adl.sport.general.groupjumpingjack.GroupJumpingJackParser;
import com.adl.sport.general.grouppushup.GroupPushUpMan;
import com.adl.sport.general.grouppushup.GroupPushUpParser;
import com.adl.sport.general.groupskipping.GroupSkippingMan;
import com.adl.sport.general.groupskipping.GroupSkippingModelType;
import com.adl.sport.general.groupskippingremix.GroupSkippingLRemixParser;
import com.adl.sport.general.groupskippingremix.GroupSkippingManLRemix;
import com.adl.sport.general.groupskippingremix.GroupSkippingManRemix;
import com.adl.sport.general.groupskippingremix.GroupSkippingRemixParser;
import com.adl.sport.general.groupsquat.GroupSquatMan;
import com.adl.sport.general.groupsquat.GroupSquatParser;
import com.adl.sport.general.invertedrow.InvertedRowCallBack;
import com.adl.sport.general.invertedrow.InvertedRowConfig;
import com.adl.sport.general.invertedrow.InvertedRowProcessor;
import com.adl.sport.general.multi.MultiSportCallback;
import com.adl.sport.general.multi.MultiSportConfig;
import com.adl.sport.general.multi.MultiSportFilter;
import com.adl.sport.general.multi.MultiSportProcessor;
import com.adl.sport.general.multi.MultiSportType;
import com.adl.ts.general.R;
import com.ai.test.BaseActivity;

import android.graphics.ImageFormat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupSportsActivity extends BaseActivity {

    private MultiSportProcessor multiSportProcessor;
    SportOverlayMultiRegion overlay;
    Button startBtn;
    Button resetBtn;
    Spinner changeModel;
    AdlCameraPreviewView previewView;
    TextView scoreTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_sports);
        previewView = findViewById(R.id.surface_view);
        startBtn = findViewById(R.id.bnt_start);
        changeModel = findViewById(R.id.bnt_change_model);
        scoreTv = findViewById(R.id.score_tv);
        overlay = findViewById(R.id.overlay);
        overlay.enableDrawSkeleton(true);
        overlay.enableDrawBox(false);
        overlay.enableDraw(true);
        overlay.enableDebug(false);


        multiSportProcessor = new MultiSportProcessor();

        MultiSportConfig config = new MultiSportConfig();
        config.debug = true;
//        config.bodyModelId = 1054;//1054
//        config.poseModelId = 2024;//2002 仰卧起坐   2024 俯卧撑
        config.drawAllBody = true;
        config.drawBody = true;
        config.drawSkeleton = true;
        config.sportType = MultiSportType.SKIPPING_10_PERSONS;
        config.loadModel(new TsModelResPool.OnLoadModelCallback() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSucceed(List<TsModelInfo> list) {
                // 模型加载成功
            }

            @Override
            public void onFinish(List<TsModelInfo> list, List<TsModelInfo> errrorList) {

            }

            @Override
            public void onEnd(String str) {

            }
        });

//        InvertedRowProcessor processor = new InvertedRowProcessor(new InvertedRowConfig());
//        processor.setCallback(new InvertedRowCallBack() {
//            @Override
//            public void invalidMotion(String errMsg) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//
//            @Override
//            public void callCountDown() {
//
//            }
//
//            @Override
//            public void cancel() {
//
//            }
//
//            @Override
//            public void onInit(EuError error) {
//
//            }
//
//            @Override
//            public void onPrepare() {
//
//            }
//
//            @Override
//            public void onFrame(AdlPoseInfo poseInfo) {
//
//            }
//        });

//        TsModelResPool.loadModel(Arrays.asList(new TsModelRes(2028, 6), new TsModelRes(2029, 6), new TsModelRes(2030, 6), new TsModelRes(1000, 6)));
        RoleConfigMulti uiConfig = AdlUIConfig.instance().readConfig(config.sportType.sportName(), RoleConfigMulti.class);
        uiConfig.setNumber(config.sportType.playerNum());
        overlay.setConfig(uiConfig, OnRenderScene.StatusSportSet);

        multiSportProcessor.setConfig(config);
        multiSportProcessor.open(overlay);
        multiSportProcessor.setBodyFilter(new MultiSportFilter(true));
//        multiSportProcessor.setBodyFilter(new TsTaskBodyDefaultFilter(0.1f) {
//            @Override
//            public AdlObject onFilter(List<AdlObject> list, TsSportRegion region) {
//                return super.onFilter(list, region);
//            }
//        });

        previewView.addFrameProcessor(multiSportProcessor);
        AdlCamera adlCamera = AdlCameraConfig.builder()
                .setCameraType(AdlCameraConfig.CameraType.HK)
                .setConnect("192.168.124.64", 8888, "admin", "zy123456")
                .setPreviewSize(1920, 1080)
                .setPreviewFps(20)
//                .setPreviewFormat(ImageFormat.YV12)
                .setPreviewEnable(true)
                .build();
        previewView.startPreview(adlCamera);

        // 跳绳
        GroupSkippingLRemixParser parser = new GroupSkippingLRemixParser();
        multiSportProcessor.setSmooth(false);
        multiSportProcessor.setParser(parser);
        multiSportProcessor.setCallback(new MultiSportCallback() {
            @Override
            public void onResult(Object o) {
                if (o != null) {
                    GroupSkippingLRemixParser groupSkippingLRemixParser = (GroupSkippingLRemixParser) o;
                    if (groupSkippingLRemixParser.groupSkippingManLRemixList != null) {
                        List<GroupSkippingManLRemix> groupSkippingManLRemixList = groupSkippingLRemixParser.groupSkippingManLRemixList;
                        String textStr = "";
                        for (int i = 0; i < groupSkippingManLRemixList.size(); i++) {
                            GroupSkippingManLRemix man = groupSkippingManLRemixList.get(i);
                            textStr = textStr + "—" + "第" + (man.positionNum + 1) + "道：" + man.score;
                        }
                        scoreTv.setText(textStr);
                    }
                }

            }

            @Override
            public void onError(int code, String error) {

            }
        });

        findViewById(R.id.bnt_close).setOnClickListener(view -> finish());
// 下蹲
//        GroupSquatParser parser = new GroupSquatParser();
//        multiSportProcessor.setParser(parser);
//        multiSportProcessor.setCallback(new MultiSportCallback() {
//            @Override
//            public void onResult(Object o) {
//                GroupSquatParser groupSquatParser = (GroupSquatParser) o;
//                if (groupSquatParser.groupSquatManList != null) {
//                    List<GroupSquatMan> groupSquatManList = groupSquatParser.groupSquatManList;
//                    String textStr = "";
//                    for (int i = 0; i < groupSquatManList.size(); i++) {
//                        GroupSquatMan man = groupSquatManList.get(i);
//                        textStr = textStr + "-------" + "第" + man.positionNum + "道：" + man.score;
//                    }
//                    scoreTv.setText(textStr);
//                }
//            }
//
//            @Override
//            public void onError(int code, String error) {
//
//            }
//        });

        // 3人仰卧起坐
//        GroupCrunchParser parser = new GroupCrunchParser();
//        multiSportProcessor.setParser(parser);
//        multiSportProcessor.setCallback(new MultiSportCallback() {
//            @Override
//            public void onResult(Object o) {
//                GroupCrunchParser groupCrunchParser = (GroupCrunchParser) o;
//                if (groupCrunchParser.groupCrunchManList != null) {
//                    List<GroupCrunchMan> groupCrunchManList = groupCrunchParser.groupCrunchManList;
//                    String textStr = "";
//                    for (int i = 0; i < groupCrunchManList.size(); i++) {
//                        GroupCrunchMan man = groupCrunchManList.get(i);
//                        textStr = textStr + "-------" + "第" + man.positionNum + "道：" + man.score;
//                    }
//                    scoreTv.setText(textStr);
//                }
//            }
//
//            @Override
//            public void onError(int code, String error) {
//
//            }
//        });

//        GroupPushUpParser parser = new GroupPushUpParser();
//        multiSportProcessor.setParser(parser);
//        multiSportProcessor.setCallback(new MultiSportCallback() {
//            @Override
//            public void onResult(Object o) {
//                GroupPushUpParser groupPushUpParser = (GroupPushUpParser) o;
//                if (groupPushUpParser.groupPushUpManList != null) {
//                    List<GroupPushUpMan> groupPushUpManList = groupPushUpParser.groupPushUpManList;
//                    String textStr = "";
//                    for (int i = 0; i < groupPushUpManList.size(); i++) {
//                        GroupPushUpMan man = groupPushUpManList.get(i);
//                        textStr = textStr + "-------" + "第" + man.positionNum + "道：" + man.score;
//                    }
//                    scoreTv.setText(textStr);
//                }
//            }
//
//            @Override
//            public void onError(int code, String error) {
//
//            }
//        });


        startBtn.setOnClickListener(view -> {
            int itemId = (int) changeModel.getSelectedItemId();
            if (itemId == 0) {
                config.sportType = MultiSportType.SKIPPING_10_PERSONS;
            } else if (itemId == 1) {
                config.sportType = MultiSportType.SKIPPING_10_PERSONS_MID;
            } else if (itemId == 2) {
                config.sportType = MultiSportType.SKIPPING_10_PERSONS_MIN;
            }
            multiSportProcessor.setConfig(config);
            multiSportProcessor.onReset();
            multiSportProcessor.changeTo(SportProcessorStatus.Sporting);
            parser.checkChannelStatus = true;
            parser.checkSkipScore = true;
        });

        multiSportProcessor.onInit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TsModelResPool.instance().unLoad(Arrays.asList(2028, 2029, 2030, 1000));
        multiSportProcessor.onRelease();
    }
}