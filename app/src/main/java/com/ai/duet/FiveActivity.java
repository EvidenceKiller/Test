package com.ai.duet;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.adl.base.bean.Point;
import com.adl.base.bean.Quadrilateral;
import com.adl.base.common.AdlVideoReader;
import com.adl.base.common.VideoReaderCallback;
import com.adl.base.element.ElementPerformance;
import com.adl.base.overlay.SportOverlayMultiRegion;
import com.adl.lib.AdlCameraPreviewInfo;
import com.adl.mount.sport.TsSportRegion;
import com.adl.sport.general.groupskipping.GroupSkippingCallBack;
import com.adl.sport.general.groupskipping.GroupSkippingConfig;
import com.adl.sport.general.groupskipping.GroupSkippingMan;
import com.adl.sport.general.groupskipping.GroupSkippingProcessor;
import com.adl.sport.general.multi.MultiSportProcessor;
import com.adl.ts.general.R;
import com.ai.test.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/7
 * Describe   : 类描述
 */
public class FiveActivity extends BaseActivity {

    String path = "/sdcard/zy.sport.cache/skipRope/6月12日 (3).mp4";
    SurfaceView surfaceView;
    AdlVideoReader videoReader;
    AdlVideoReader.PlayMode playMode = AdlVideoReader.PlayMode.Auto;
    Button bntPlay;
    Button bntChange;
    Button bntNext;
    CheckBox cbDisplay;
    CheckBox cbVideo;

    SportOverlayMultiRegion overlay;
    TextView scoreTv;


    GroupSkippingConfig config;
    List<TsSportRegion> regions = new ArrayList<>();
    int threadNumber = 8;
    long lastTime = System.currentTimeMillis();

    ElementPerformance epf;
    boolean running = true;

    /**
     *
     */
    GroupSkippingProcessor processor;

    MultiSportProcessor multiSportProcessor;

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
        cbDisplay = findViewById(R.id.cb_display);
        cbVideo = findViewById(R.id.cb_video);

        overlay.enableDebug(true);
        overlay.enableDrawBox(false);
        overlay.enableDrawSkeleton(true);
        overlay.getSkeletonDrawer().enableMarker(true);
        overlay.getSkeletonDrawer().enableLine(true);


        epf = new ElementPerformance();
//        overlay.addElement(epf);

        findViewById(R.id.bnt_close).setOnClickListener(view -> finish());

        cbDisplay.setOnCheckedChangeListener((compoundButton, b) -> {
            config.drawSkeleton = config.drawAllBody = b;
        });

        cbVideo.setOnCheckedChangeListener((compoundButton, b) -> {
            videoReader.enableRender(b);
        });

        findViewById(R.id.bnt_reset).setOnClickListener(view -> {
            overlay.clearScreen();
            videoReader.onReset(path);
            processor.resetSkippingMan();
        });

        bntPlay.setOnClickListener(view -> {



            // 运动区域
            List<Quadrilateral> list = overlay.getPreviewRegion();
            if (list != null && list.size() == 5) {
                regions.clear();
                for (Quadrilateral q : list) {
                    float properitionW = 1;
                    float properitionH = 1;
                    if (config.screenResolution == 1) {
                        properitionW = (float) 2560 / 1920;
                        properitionH = (float) 1440 / 1080;
                    } else if (config.screenResolution == 2) {
                        properitionW = (float) 3840 / 1920;
                        properitionH = (float) 2160 / 1080;
                    }
                    Point point1 = new Point(q.p1.x * properitionW, q.p1.y * properitionH);
                    Point point2 = new Point(q.p2.x * properitionW, q.p2.y * properitionH);
                    Point point3 = new Point(q.p3.x * properitionW, q.p3.y * properitionH);
                    Point point4 = new Point(q.p4.x * properitionW, q.p4.y * properitionH);
                    regions.add(new TsSportRegion(point1, point2, point3, point4));
                }
            }

            if (videoReader.isPlaying()) {
                bntPlay.setText("播放");

                videoReader.onPause();
            } else {
                videoReader.onStart();
                processor.toSporting();
                processor.setCheckChannelStatus(true);
                processor.setCheckSkipScore(true);
                bntPlay.setText("暂停");
            }

        });

        // 运动配置
        config = new GroupSkippingConfig();
        config.bodyModelId = 1006;// 人框
        config.poseModelId = 2004;// 骨骼
        config.debug = true;
        config.readVideo = true;
        config.drawBody = false;
        config.drawAllBody = false;
        config.drawSkeleton = false;

        processor = new GroupSkippingProcessor(config);
        processor.setCallBack(new GroupSkippingCallBack() {
            @Override
            public void onSkippingMan(List<GroupSkippingMan> groupSkippingManList) {
                String textStr = "";
                for (int i = 0; i < groupSkippingManList.size(); i++) {
                    GroupSkippingMan man = groupSkippingManList.get(i);
                    textStr = textStr + "-------" + "第" + man.positionNum + "道：" + man.score;
                }
                scoreTv.setText(textStr);
//                AdlLogger.d("groupSkippingManList ============= " + groupSkippingManList);
            }
        });

        multiSportProcessor = new MultiSportProcessor();

//        multiSportProcessor.setConfig(config);


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
                processor.getFrameProcessor().onFrame(yuv, info);
            }
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
        processor.toStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        processor.toRelease();
        videoReader.release();
        running = false;
    }
}
