package com.ai.k4;


import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.adl.base.common.AdlExecutor;
import com.adl.base.common.AdlToast;
import com.adl.base.common.AdlVideoReader;
//import com.adl.base.common.AdlVideoRtspReader;

import com.adl.base.common.VideoReaderCallback;
import com.adl.base.config.AdlUIConfig;
import com.adl.base.config.RoleConfigRopeSkip;
import com.adl.base.element.ElementFpsCounter;
import com.adl.base.log.AdlLogger;
import com.adl.base.overlay.OnRenderScene;
import com.adl.base.overlay.SportOverlayVessel;
import com.adl.lib.AdlCameraPreviewInfo;
import com.adl.mount.core.TsTaskDrawer;
import com.adl.mount.sport.TsConfig;
import com.adl.ts.general.R;
import com.ai.test.BaseActivity;
import com.google.android.exoplayer2.ExoPlayer;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.VLCVideoLayout;

import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/8
 * Describe   : 类描述
 */
public class TestRtspActivity extends BaseActivity {

    String url = "rtsp://172.16.0.108/live";
    SportOverlayVessel overlayVessel;
    Button bntPlay;
    Button bntReset;
    Button bntChange;
    Button bntNext;
    SurfaceView surfaceView;
//    AdlVideoRtspReader videoReader;
    AdlVideoReader.PlayMode playMode = AdlVideoReader.PlayMode.Auto;
    long startTime;
    TextView infoTv;
    CheckBox cbAi;
    CheckBox cbRender;
//    VideoView videoView;

    TsConfig tsConfig;
    TsTaskDrawer drawer;
    ElementFpsCounter fpsCounter;

    private LibVLC libVLC;
    private MediaPlayer mediaPlayer;
    private VLCVideoLayout videoLayout;

//    ExoPlayer exoPlayer;
    private PipedInputStream pipedInputStream;
    private PipedOutputStream pipedOutputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_test_rtsp);

        surfaceView = findViewById(R.id.surface_view);
        bntPlay = findViewById(R.id.bnt_start);
        bntReset = findViewById(R.id.bnt_reset);
        infoTv = findViewById(R.id.info_tv);
        overlayVessel = findViewById(R.id.overlay);
        bntChange = findViewById(R.id.bnt_change);
        bntNext = findViewById(R.id.bnt_next);
        cbAi = findViewById(R.id.cb_ai);
        cbRender = findViewById(R.id.cb_render);
//        videoView = findViewById(R.id.video_view);
        videoLayout = findViewById(R.id.videoLayout);

        overlayVessel.enableDrawSkeleton(true);
        overlayVessel.enableDrawBox(false);
        overlayVessel.enableDraw(true);
        overlayVessel.enableDebug(false);

        cbAi.setOnCheckedChangeListener((compoundButton, b) -> cbAi.setText(b ? "算法开启" : "算法关闭"));
        cbRender.setOnCheckedChangeListener((compoundButton, b) -> {
            cbRender.setText(b ? "关闭渲染" : "显示渲染");
//            videoReader.enableRender(b);
        });

        // 创建 LibVLC 实例
        ArrayList<String> options = new ArrayList<>();
        options.add("--aout=opensles");
        options.add("--audio-time-stretch");
        options.add("-vvv");
        libVLC = new LibVLC(this, options);

        // 创建 MediaPlayer 实例
        mediaPlayer = new MediaPlayer(libVLC);

        try {
            // 创建管道输入输出流
            pipedOutputStream = new PipedOutputStream();
            pipedInputStream = new PipedInputStream(pipedOutputStream);

            // 构建自定义流输出命令
            String streamOutput = ":sout=#transcode{vcodec=h264}:std{access=file,mux=raw,dst=-}";

            // 创建 Media 实例并设置输出
            Media media = new Media(libVLC, Uri.parse(url));
            media.addOption(streamOutput);
            mediaPlayer.setMedia(media);
            //media.release();

            // 启动读取线程
            new Thread(new H264Reader()).start();

            // 开始播放
            mediaPlayer.play();
        } catch (IOException e) {
            // Log.e(TAG, "Error setting up pipe: " + e.getMessage());
            e.printStackTrace();
        }


//        // 创建 Media 实例并设置输出
//        Media media = new Media(libVLC, Uri.parse(url));
//        media.addOption(streamOutput);
//        mediaPlayer.setMedia(media);
//        media.release();
//
//        // 开始播放并保存 H.264 数据
//        mediaPlayer.play();

        // exoPlayer = new ExoPlayer.Builder(this).build();
//        exoPlayer = new ExoPlayer.Builder(this)
//                .setRenderersFactory(new DefaultRenderersFactory(this) {
//                    @Override
//                    public Renderer[] createRenderers(Handler eventHandler, VideoRendererEventListener videoRendererEventListener,
//                                                      AudioRendererEventListener audioRendererEventListener,
//                                                      TextOutput textRendererOutput, MetadataOutput metadataRendererOutput) {
//                        Renderer[] renderers = new Renderer[1];
//                        renderers[0] = new YuvRenderer();
//                        return renderers;
//                    }
//                })
//                .setMediaSourceFactory(new DefaultMediaSourceFactory(this))
//                .build();
//
//        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(url));
//        exoPlayer.setMediaItem(mediaItem);
//        exoPlayer.setPlayWhenReady(true);
//        exoPlayer.setVideoSurface(surfaceView.getHolder().getSurface());
//        exoPlayer.prepare();
//        exoPlayer.play();

//        exoPlayer = new SimpleExoPlayer.Builder(this).build();
//        exoPlayer.setVideoSurfaceView(surfaceView);

        bntPlay.setOnClickListener(view -> {

//            if (videoReader.isPlaying()) {
//                bntPlay.setText("播放");
//                videoReader.onPause();
//            } else {
//                videoReader.onStart();
//                bntPlay.setText("暂停");
//            }

//            if (!exoPlayer.isPlaying()) {
//                Uri uri = Uri.parse(url);
//                DataSource.Factory
//                exoPlayer.prepare(MediaSource.);
//            }

//            libVLC = new LibVLC(getApplicationContext());
//            mediaPlayer = new MediaPlayer(libVLC);
////            mediaPlayer.getMedia().setEventListener(new IMedia.EventListener() {
////                @Override
////                public void onEvent(IMedia.Event event) {
////
////                }
////            });
//            mediaPlayer.attachViews(videoLayout, null, true, true);
//            mediaPlayer.play(Uri.parse(url));

//            videoLayout.

//            mediaPlayer.getMedia().setMRL("rtsp://your_rtsp_stream_url"); // 设置RTSP URL
//            mediaPlayer.play(); // 开始播放
//            videoLayout.setVideoLayout(VLCVideoLayout.Orientation.LANDSCAPE); // 设置视频布局方向
//            videoLayout.setZOrderOnTop(false); // 设置视频是否在顶层显示
//            videoLayout.setVideoFrameRate(30); // 设置视频帧率

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

//        videoReader = new AdlVideoRtspReader();
//        videoReader.setPlayMode(playMode);
//        videoReader.setSurface(surfaceView.getHolder().getSurface());
//        videoReader.enableRender(true);
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
//                fpsCounter.updateFps();
//            }
//        });

        // 加载页面配置文件
        RoleConfigRopeSkip uiConfig = AdlUIConfig.instance()
                .readConfig(overlayVessel.getConfigKey(), RoleConfigRopeSkip.class);
        overlayVessel.setConfig(uiConfig, OnRenderScene.StatusSportSet);

        // view初始化完成 -> 下一步
        overlayVessel.setListener(() -> {

        });

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

        AdlExecutor.postUIDelayed(() -> {
//            videoReader.openUrl(url);
            AdlToast.show("打开流");
        }, 500);
    }

    private void reset() {
        startTime = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        videoReader.release();
    }

    private class H264Reader implements Runnable {
        @Override
        public void run() {
            byte[] buffer = new byte[4096];
            try {
                int bytesRead;
                while ((bytesRead = pipedInputStream.read(buffer)) != -1) {
                    byte[] h264Data = new byte[bytesRead];
                    System.arraycopy(buffer, 0, h264Data, 0, bytesRead);
                    processH264Data(h264Data);
                }
            } catch (IOException e) {
                //Log.e(TAG, "Error reading H.264 data: " + e.getMessage());
            }
        }

        private void processH264Data(byte[] h264Data) {
            AdlLogger.d("Received H.264 data length: " + h264Data.length);
            // 在这里可以进行更复杂的 H.264 数据处理，比如解析 NAL 单元等
        }
    }
}
