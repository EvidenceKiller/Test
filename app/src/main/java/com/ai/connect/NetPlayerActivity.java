package com.ai.connect;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.adl.base.common.AdlAvReader;
import com.adl.base.common.AdlExecutor;
import com.adl.base.common.AdlToast;
import com.adl.base.common.AdlVideoNetClient;
import com.adl.base.common.AdlVideoNetServer;
import com.adl.base.common.VideoReaderCallbackSample;
import com.adl.base.file.AdlFileHelper;
import com.adl.ts.general.R;
import com.ai.test.BaseActivity;
import com.ai.test.FileAdapter;

import java.io.File;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/8
 * Describe   : 类描述
 */
public class NetPlayerActivity extends BaseActivity {

    ListView listView;
    FileAdapter mAdapter;
    File curFile;

    AdlVideoNetServer mServer;
    AdlAvReader.PlayMode playMode;
    AdlVideoNetClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_net_player);

        listView = findViewById(R.id.list_view);
        SurfaceView surfaceView = findViewById(R.id.surface_view);
        mAdapter = new FileAdapter(this, file -> {
            curFile = file;
        });
        listView.setAdapter(mAdapter);

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

            }
        });

        mServer = new AdlVideoNetServer();
        mServer.setSurface(surfaceView.getHolder().getSurface());
        mServer.setPlayMode(playMode = AdlAvReader.PlayMode.Auto);
        Log.e("TAG", "onCompleted KKKKKKKKKK curFile:"+curFile+";thread:"+Thread.currentThread().getId());
        mServer.setCallback(new AdlVideoNetServer.OnVideoNetServerCallback() {
            @Override
            public void onInfo(int code, String info) {
                if (code == 100){
                    if (curFile != null) {
                        Log.e("TAG", "onCompleted xxxxxx curFile:"+curFile.getAbsolutePath());
                        mServer.onStop();

                        Log.e("TAG", "onCompleted nnnnnn curFile:"+curFile.getAbsolutePath()+";thread:"+Thread.currentThread().getId());
                        AdlExecutor.postUIDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("TAG", "onCompleted mServer.onStart():"+curFile.getAbsolutePath()+";thread:"+Thread.currentThread().getId());
                                mServer.onReset(curFile.getAbsolutePath());
                                mServer.setPlayMode(playMode = AdlAvReader.PlayMode.Auto);
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                mServer.onStart();
                            }
                        },1000);
                        Log.e("TAG", "onCompleted vvvvvvvvvvvv curFile:"+curFile.getAbsolutePath()+";thread:"+Thread.currentThread().getId());

                    }
                }
            }
        });
        mServer.listenerUdpScan();

        SurfaceView surView2 = findViewById(R.id.surface_view_2);
        surView2.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

            }
        });

        mClient = new AdlVideoNetClient();
        mClient.setSurface(surView2.getHolder().getSurface());
        mClient.enableRender(true);
        mClient.setVideoCallback(new VideoReaderCallbackSample() {

            @Override
            public void onFrame(byte[] yuv, int width, int height, long frameTime) {

            }
        });

        findViewById(R.id.bnt_start_center).setOnClickListener(view -> {
            if (curFile != null) {
                mServer.onStop();
                mServer.onReset(curFile.getAbsolutePath());
            }
        });

        findViewById(R.id.bnt_s_change).setOnClickListener(view -> {
            if (playMode == AdlAvReader.PlayMode.Auto) {
                mServer.setPlayMode(playMode = AdlAvReader.PlayMode.OneFrame);
                AdlToast.show("单帧模式");
            } else {
                mServer.setPlayMode(playMode = AdlAvReader.PlayMode.Auto);
                AdlToast.show("自动模式");
            }
        });

        findViewById(R.id.bnt_s_next).setOnClickListener(view -> {
            mServer.nextFrame();
        });

        findViewById(R.id.bnt_s_send).setOnClickListener(view -> {
            if (mServer.isPlaying()) {
                mServer.onPause();
            } else {
                mServer.onStart();
            }
        });

        findViewById(R.id.bnt_start_client_1).setOnClickListener(view -> {
//            mClient.connect(8880, "_video_server");
            mClient.connect(8880, "_stream_server");

        });

        findViewById(R.id.bnt_start_client_2).setOnClickListener(view -> {

        });

        AdlFileHelper.scanFiles("netPlayer", list -> {
            mAdapter.loadData(list);
        });
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
    }
}
