package com.ai.duet;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.constraintlayout.widget.Group;

import com.adl.base.common.AdlAvReader;
import com.adl.base.common.AdlVideoReader;
import com.adl.base.common.VideoReaderCallback;
import com.adl.base.config.AdlUIConfig;
import com.adl.base.config.RoleConfigMulti;
import com.adl.base.element.ElementFpsCounter;
import com.adl.base.element.ElementPerformance;
import com.adl.base.overlay.OnRenderScene;
import com.adl.base.overlay.SportOverlayMultiRegion;
import com.adl.lib.AdlCameraPreviewInfo;
import com.adl.lib.pose.AdlObject;
import com.adl.mount.core.TsModelInfo;
import com.adl.mount.core.TsModelRes;
import com.adl.mount.core.TsModelResPool;
import com.adl.mount.core.TsTaskBodyDefaultFilter;
import com.adl.mount.sport.TsSportRegion;
import com.adl.processor.SportProcessorStatus;
import com.adl.sport.general.groupcrunch.GroupCrunchMan;
import com.adl.sport.general.groupcrunch.GroupCrunchParser;
import com.adl.sport.general.groupjumpingjack.GroupJumpingJackMan;
import com.adl.sport.general.groupjumpingjack.GroupJumpingJackParser;
import com.adl.sport.general.grouppushup.GroupPushUpMan;
import com.adl.sport.general.grouppushup.GroupPushUpParser;
import com.adl.sport.general.groupskipping.GroupSkippingCallBack;
import com.adl.sport.general.groupskipping.GroupSkippingConfig;
import com.adl.sport.general.groupskipping.GroupSkippingMan;
import com.adl.sport.general.groupskipping.GroupSkippingProcessor;
import com.adl.sport.general.groupskippingremix.GroupSkippingLRemixParser;
import com.adl.sport.general.groupskippingremix.GroupSkippingManLRemix;
import com.adl.sport.general.groupskippingremix.GroupSkippingManRemix;
import com.adl.sport.general.groupskippingremix.GroupSkippingRemixParser;
import com.adl.sport.general.groupsquat.GroupSquatMan;
import com.adl.sport.general.groupsquat.GroupSquatParser;
import com.adl.sport.general.multi.MultiSportCallback;
import com.adl.sport.general.multi.MultiSportChannelModel;
import com.adl.sport.general.multi.MultiSportConfig;
import com.adl.sport.general.multi.MultiSportFilter;
import com.adl.sport.general.multi.MultiSportProcessor;
import com.adl.sport.general.multi.MultiSportRegionCheckFilter;
import com.adl.sport.general.multi.MultiSportType;
import com.adl.ts.general.R;
import com.ai.test.BaseActivity;
import com.ai.test.FileAdapter;
import com.jf.pose.ModePoseType;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupSkippingVideoGTActivity extends BaseActivity {
    SportOverlayMultiRegion overlay;
    private MultiSportProcessor multiSportProcessor;
    ElementFpsCounter fpsCounter;
    private GroupSkippingProcessor processor;
    //    String path = "/mnt/sdcard/zy.sport.cache/skipRope/5人静止出圈.mp4";
//    String path = "/sdcard/zy.sport.cache/skipRope/5人静止出圈.mp4";
//    String path = "/sdcard/zy.sport.cache/skipRope/6月12日 (3).mp4";
//    String path = "/sdcard/jumpRope/俯卧撑标准.mp4";
//    String path = "/sdcard/jumpRope/开合跳5人.mp4";
    String path = "/sdcard/com.adl.sport/jumpRope/10人跳绳2.mp4";
//    String path = "/sdcard/jumpRope/跳绳2号位高速.mp4";

    SurfaceView surfaceView;
    AdlVideoReader videoReader;
    AdlVideoReader.PlayMode playMode = AdlVideoReader.PlayMode.Auto;
    Button bntPlay;
    Button bntChange;
    Button bntNext;
    Button bntReset;
    CheckBox cbVideo;
    TextView scoreTv;

    private ListView listView;
    private FileAdapter adapter;
    private List<File> videoFiles = new ArrayList<>();
    private File currentFile;
    private TextView fileNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_skipping_video_gtactivity);

        surfaceView = findViewById(R.id.surface_view);
        overlay = findViewById(R.id.overlay);
        scoreTv = findViewById(R.id.score_tv);
        bntPlay = findViewById(R.id.bnt_start);
        bntChange = findViewById(R.id.bnt_change);
        bntNext = findViewById(R.id.bnt_next);
//        cbDisplay = findViewById(R.id.cb_display);
        cbVideo = findViewById(R.id.cb_video);
        bntReset = findViewById(R.id.bnt_reset);
        cbVideo.setOnCheckedChangeListener((compoundButton, b) -> videoReader.enableRender(b));



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

        // 平滑
//        ((CheckBox) findViewById(R.id.cb_smooth)).setOnCheckedChangeListener((compoundButton, b) -> {
////            multiSportProcessor.changeTo(SportProcessorStatus.Idle);
////            multiSportProcessor.setSmooth(b);
////            multiSportProcessor.onReset();
////            multiSportProcessor.changeTo(SportProcessorStatus.Sporting);
//        });

        bntNext.setOnClickListener(view -> {
            videoReader.nextFrame();
        });





//        MultiSportConfig config = new MultiSportConfig();
//        config.debug = true;
//        config.bodyModelId = 1003;//1054
//        config.poseModelId = 2004;//2002 仰卧起坐   2024 俯卧撑
//        config.drawAllBody = true;
//        config.drawBody = true;
//        config.drawSkeleton = true;
//
//        multiSportProcessor = new MultiSportProcessor();
//        multiSportProcessor.setConfig(config);
//        multiSportProcessor.open(overlay);
//
//        multiSportProcessor.setBodyFilter(new TsTaskBodyDefaultFilter(0.1f) {
//            @Override
//            public AdlObject onFilter(List<AdlObject> list, TsSportRegion region) {
//                return super.onFilter(list, region);
//            }
//        });
//        GroupJumpingJackParser parser = new GroupJumpingJackParser();
//        multiSportProcessor.setParser(parser);
//        multiSportProcessor.setCallback(new MultiSportCallback() {
//            @Override
//            public void onResult(Object o) {
//                GroupJumpingJackParser groupJumpingJackParser = (GroupJumpingJackParser) o;
//                if (groupJumpingJackParser.jumpingJackManList != null) {
//                    List<GroupJumpingJackMan> groupJumpingJackManList = groupJumpingJackParser.jumpingJackManList;
//                    String textStr = "";
//                    for (int i = 0; i < groupJumpingJackManList.size(); i++) {
//                        GroupJumpingJackMan man = groupJumpingJackManList.get(i);
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
//        multiSportProcessor.onInit();


        ///// 5人跳绳 ///////////
        // 必设
//        GroupSkippingConfig config = new GroupSkippingConfig();
//        config.bodyModelId = 1000;
//        config.poseModelId = 2016;
//        config.bodyModelId = 1006;// 人框
//        config.poseModelId = 2004;
        // 选
//        config.screenResolution = 1;
//        config.debugOnlyModelGT = false;
//        config.debug = false;
//        config.readVideo = true;
//        config.drawBody = true;
//        config.drawAllBody = true;
//        config.drawSkeleton = true;
//        config.highPrecisionHighEnergyConsumption = true;
//        config.useSmooth = true;
//         必设
//        processor = new GroupSkippingProcessor(config);
//        processor.combineCamera("172.16.4.64", 8888, "admin", "zy123456", true);
////        processor.combineLifeCycle();
//
//        processor.combineOverlay(overlay);
//
//        processor.setCallBack(new GroupSkippingCallBack() {
//            @Override
//            public void onSkippingMan(List<GroupSkippingMan> groupSkippingManList) {
//                String textStr = "";
//                for (int i = 0; i < groupSkippingManList.size(); i++) {
//                    GroupSkippingMan man = groupSkippingManList.get(i);
//                    textStr = textStr + "-------" + "第" + (man.positionNum + 1) + "道：" + man.score;
//                }
//                scoreTv.setText(textStr);
//            }
//        });


        GroupSkippingLRemixParser parser = new GroupSkippingLRemixParser();
//        GroupSkippingRemixParser parser = new GroupSkippingRemixParser();
        MultiSportConfig configSub = new MultiSportConfig();
        configSub.sportType = MultiSportType.SKIPPING_10_PERSONS;
        configSub.loadModel(new TsModelResPool.OnLoadModelCallback(){
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
        configSub.drawAllBody = true;
        configSub.drawBody = true;
        configSub.drawSkeleton = true;
        configSub.debug = true;


        RoleConfigMulti uiConfig = AdlUIConfig.instance().readConfig(configSub.sportType.sportName(), RoleConfigMulti.class);
        uiConfig.setNumber(configSub.sportType.playerNum());
        overlay.enableDrawSkeleton(true);
        overlay.enableDrawBox(false);
        overlay.enableDraw(true);
        overlay.enableDebug(false);
        overlay.setConfig(uiConfig, OnRenderScene.StatusSportSet);



        // 指定通道模型
//        configSub.channelModels = new ArrayList<>();
//        for (int index = 0; index < 5; index++) {
//            configSub.channelModels.add(new MultiSportChannelModel(index, ModePoseType.ModeRopeSkipMulti160x128));
//        }

        multiSportProcessor = new MultiSportProcessor();
        multiSportProcessor.setConfig(configSub);
        multiSportProcessor.setBodyFilter(new TsTaskBodyDefaultFilter(0.1f) {
            @Override
            public AdlObject onFilter(List<AdlObject> list, TsSportRegion region) {
                return super.onFilter(list, region);
            }
        });
//        multiSportProcessor.setBodyFilter(new MultiSportRegionCheckFilter());
        multiSportProcessor.setParser(parser);
        multiSportProcessor.setCallback(new MultiSportCallback() {

            @Override
            public void onResult(Object o) {
//                GroupSkippingRemixParser groupSkippingRemixParser = (GroupSkippingRemixParser) o;
//                List<GroupSkippingManRemix> groupSkippingManRemixList = groupSkippingRemixParser.groupSkippingManRemixList;
//                String textStr = "";
//                for (int i = 0; i < groupSkippingManRemixList.size(); i++) {
//                    GroupSkippingManRemix man = groupSkippingManRemixList.get(i);
//                    textStr = textStr + "-------" + "第" + (man.positionNum) + "道：" + man.score;
//                }
//                scoreTv.setText(textStr);

                GroupSkippingLRemixParser groupSkippingLRemixParser = (GroupSkippingLRemixParser) o;
                List<GroupSkippingManLRemix> groupSkippingManLRemixList = groupSkippingLRemixParser.groupSkippingManLRemixList;
                String textStr = "";
                for (int i = 0; i < groupSkippingManLRemixList.size(); i++) {
                    GroupSkippingManLRemix man = groupSkippingManLRemixList.get(i);
                    textStr = textStr + "-------" + "第" + (man.positionNum) + "道：" + man.score;
                }
                scoreTv.setText(textStr);
            }

            @Override
            public void onError(int code, String error) {

            }
        });
        multiSportProcessor.open(overlay);
        multiSportProcessor.onInit();

//        TsModelResPool.loadModel(Arrays.asList(new TsModelRes(1054, 6),
//                new TsModelRes(2024, 6)));
//
//        RoleConfigMulti uiConfig = AdlUIConfig.instance().readConfig("_five_person", RoleConfigMulti.class);
//        uiConfig.setNumber(3);
//        overlay.setConfig(uiConfig, OnRenderScene.StatusSportSet);
//
//        multiSportProcessor = new MultiSportProcessor();
//
//        MultiSportConfig config = new MultiSportConfig();
//        config.debug = true;
//        config.bodyModelId = 1054;//1054
//        config.poseModelId = 2024;//2002 仰卧起坐   2024 俯卧撑
//        config.drawAllBody = true;
//        config.drawBody = true;
//        config.drawSkeleton = true;
//
//        multiSportProcessor.setConfig(config);
//        multiSportProcessor.open(overlay);
//        multiSportProcessor.onInit();
//        multiSportProcessor.setBodyFilter(new TsTaskBodyDefaultFilter());

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
//                if (processor != null) {
//                    processor.getFrameProcessor().onFrame(yuv, info);
//                }
                multiSportProcessor.onFrame(yuv, info);
            }

        });


        bntPlay.setOnClickListener(view -> {

//            processor.combineRegions();
            if (videoReader.isPlaying()) {
                bntPlay.setText("播放");
                videoReader.onPause();
            } else {
                videoReader.onStart();

                multiSportProcessor.onReset();
                multiSportProcessor.changeTo(SportProcessorStatus.Sporting);
                parser.checkChannelStatus = true;
                parser.checkSkipScore = true;
//                processor.toSporting();
//                processor.setCheckSkipScore(true);
//                processor.setCheckChannelStatus(true);
                bntPlay.setText("暂停");
            }
        });
//        videoReader.onReset(path);
        bntReset.setOnClickListener(view -> {
            parser.checkSkipScore = true;
            videoReader.onReset(path);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        multiSportProcessor.onRelease();
        videoReader.onStop();
    }
}