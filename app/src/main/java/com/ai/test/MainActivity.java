package com.ai.test;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.adl.base.activity.NextWriter;
import com.adl.base.speech.AdlTextSpeaker;
import com.adl.ts.general.R;
import com.ai.connect.NetPlayerActivity;
import com.ai.connect.NetPlayerServerActivity;
import com.ai.connect.TcpTestActivity;
import com.ai.duet.DoubleActivity;
import com.ai.duet.DoubleCamaraActivity;
import com.ai.duet.DoubleHighLegLiftActivity;
import com.ai.duet.FiveActivity;
import com.ai.duet.GroupSkippingLivingActivity;
import com.ai.duet.GroupSkippingVideoGTActivity;
import com.ai.duet.GroupSportsActivity;
import com.ai.duet.SinglePersonLiveActivity;
import com.ai.duet.SinglePersonVideoActivity;
import com.ai.duet.UVCCameraActivity;
import com.ai.jump.LongJumpActivity;
import com.ai.jump.LongJumpSetActivity;
import com.ai.k4.Test4KActivity;
import com.ai.ropeskip.RopeSkipAFActivity;
import com.ai.situp.SitUpActivity;

public class MainActivity extends BaseActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);

        AdlTextSpeaker.init(this);

        findViewById(R.id.bnt_0).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, CameraActivity.class).go();
        });

        findViewById(R.id.bnt_3_00).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, NetPlayerActivity.class).go();
        });

        findViewById(R.id.bnt_3_01).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, NetPlayerServerActivity.class).go();
        });

        findViewById(R.id.bnt_1).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, SitUpActivity.class).go();
        });

        findViewById(R.id.bnt_3).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, Performance1Activity.class).go();
        });

        // 单人测试
        findViewById(R.id.bnt_5).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, SingleActivity.class).go();
        });

        // 双人测试
        findViewById(R.id.bnt_6).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, DoubleActivity.class).go();
        });

        // 多人测试
        findViewById(R.id.bnt_7).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, FiveActivity.class).go();
        });

        // 常规测试
        findViewById(R.id.bnt_8).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, Test4KActivity.class).go();
        });

        // 双人高抬腿测试
        findViewById(R.id.bnt_9).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, DoubleHighLegLiftActivity.class).go();
        });

        // 双人摄像头测试
        findViewById(R.id.bnt_10).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, DoubleCamaraActivity.class).go();
        });

        // 五人跳绳IPC_天山
        findViewById(R.id.bnt_11).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, GroupSkippingLivingActivity.class).go();
        });

        // 五人跳绳视频_高通
        findViewById(R.id.bnt_12).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, GroupSkippingVideoGTActivity.class).go();
        });

        // 双人摄像头测试
        findViewById(R.id.bnt_13).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, UVCCameraActivity.class).go();
        });

        findViewById(R.id.bnt_14).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, GroupSportsActivity.class).go();
        });

        findViewById(R.id.bnt_15).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, SinglePersonVideoActivity.class).go();
        });

        findViewById(R.id.bnt_16).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, RopeSkipAFActivity.class).go();
        });

        findViewById(R.id.bnt_17).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, SinglePersonLiveActivity.class).go();
        });

        findViewById(R.id.bnt_18).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, CameraChangeActivity.class).go();
        });

        findViewById(R.id.bnt_18_1).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, IPCRecorderActivity.class).go();
        });

        findViewById(R.id.bnt_20).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, LongJumpActivity.class).go();
        });

        findViewById(R.id.bnt_20_1).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, LongJumpSetActivity.class).go();
        });

        findViewById(R.id.bnt_20_2).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, TcpTestActivity.class).go();
        });

        findViewById(R.id.bnt_20_3).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, HKCameraPreviewActivity.class).go();
        });

        findViewById(R.id.bnt_30).setOnClickListener(v -> {
            NextWriter.with(MainActivity.this, V3InterfaceTestActivity.class).go();
        });
    }
}
