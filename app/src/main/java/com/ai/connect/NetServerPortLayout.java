package com.ai.connect;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.adl.base.common.AdlAvReader;
import com.adl.base.common.AdlExecutor;
import com.adl.base.common.AdlToast;
import com.adl.base.common.AdlVideoNetServer;
import com.adl.base.file.AdlFileHelper;
import com.adl.base.log.AdlLogger;
import com.adl.ts.general.R;
import com.ai.test.FileSelectDialog;

import java.io.File;

/**
 * ProjectName: AdlMountGeneral
 * Author     : 南山
 * Date       : 2025/5/16
 * Describe   : 类描述
 */
public class NetServerPortLayout extends FrameLayout {

    private SurfaceView surfaceView;
    private int windowIndex;
    private int windowPort;
    private int masterPort = 9081;
    private TextView indexTv;
    private TextView bntStart;
    private TextView bntPlay;
    private TextView bntChange;
    private EditText portEt;
    private String dirName;
    private File curFile;

    private AdlVideoNetServer mServer;
    private AdlAvReader.PlayMode playMode;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public NetServerPortLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.item_net_server_port, null);
        addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        indexTv = view.findViewById(R.id.index_tv);
        portEt = view.findViewById(R.id.ed_port);
        surfaceView = view.findViewById(R.id.surface_view);
        bntPlay = view.findViewById(R.id.bnt_play);
        bntChange = view.findViewById(R.id.bnt_change);
        bntStart = view.findViewById(R.id.bnt_start);

        view.findViewById(R.id.bnt_select).setOnClickListener(view1 -> {
            new FileSelectDialog(context).setCallback(file -> {
                curFile = file;
                showInfo();
            }).show(dirName);
        });

        bntStart.setOnClickListener(view1 -> {
            start();
        });

        bntPlay.setOnClickListener(view1 -> {
            /*if (mServer == null) return;
            if (mServer.isPlaying()) {
                mServer.onPause();
                bntPlay.setText("播放");
            } else {
                mServer.onStart();
                bntPlay.setText("暂停");
            }*/
        });

        view.findViewById(R.id.bnt_next).setOnClickListener(view1 -> {
            if (mServer == null) return;
            mServer.nextFrame();
        });

        bntChange.setOnClickListener(view1 -> {
            if (mServer == null) return;
            if (playMode == AdlAvReader.PlayMode.Auto) {
                mServer.setPlayMode(playMode = AdlAvReader.PlayMode.OneFrame);
                bntChange.setText("单帧模式");
            } else {
                mServer.setPlayMode(playMode = AdlAvReader.PlayMode.Auto);
                bntChange.setText("自动模式");
            }
        });

        ((CheckBox) view.findViewById(R.id.cb_render)).setOnCheckedChangeListener((compoundButton, b) -> {
            if (mServer != null) mServer.enableRender(b);
        });
    }

    private void start() {
        if (mServer == null) {
            String str = portEt.getText().toString().trim();
            int port = str.isEmpty() ? 8880 : Integer.parseInt(str);

            mServer = new AdlVideoNetServer();
            mServer.setSurface(surfaceView.getHolder().getSurface());
            mServer.setPlayMode(playMode = AdlAvReader.PlayMode.Auto);
            mServer.enableRender(true);
            mServer.setCallback((code, info) -> {
                AdlLogger.d(info);
                AdlToast.show(info);
                // 客户端连接上来
                if (code == 2) {
                    // 新连接
                }
                // 播放完成
                else if (code == 100) {

                }
                // 打开文件
                else if (code == 20) {
                    if (curFile != null && mServer != null) {
                        AdlLogger.e("NetServerPort 打开文件: " + curFile.getAbsolutePath());
                        mServer.onReset(curFile.getAbsolutePath());
                        AdlExecutor.postIODelayed(() -> mServer.onStart(), 10000);
                    }
                }
            });
            //mServer.setMasterPort(masterPort);
            mServer.listenerUdpScan(port);
            bntStart.setText("服务已启动");
            AdlToast.show("启动成功 masterPort: " + masterPort);
        }
    }

    public void setPortIndex(int index, int masterPort, int port) {
        this.windowIndex = index;
        this.windowPort = port;
        this.masterPort = masterPort;
        indexTv.setText("窗口" + windowIndex + ":  " + "None" + " : " + masterPort);
        portEt.setText(String.valueOf(windowPort));
    }

    public void setRootDir(String dirName) {
        this.dirName = dirName;
    }

    private void showInfo() {
        if (curFile != null) {
            indexTv.setText("窗口" + windowIndex + ":  " + curFile.getName() + " : ");
        } else {
            indexTv.setText("窗口" + windowIndex + ":  " + "None" + " : ");
        }
    }

    public void setVideoFile(String filePath) {
        File sportRootDir = AdlFileHelper.createSportRootDir();
        curFile = new File(sportRootDir.getAbsolutePath() + "/" + filePath);
        AdlLogger.e("LocalServer setVideoFile: " + curFile.getAbsolutePath());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                showInfo();
            }
        });
    }
    public void onStop() {
        if (mServer != null) {
            mServer.onStop();
            mServer = null;
        }
    }
}
