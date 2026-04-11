package com.ai.test;

import android.os.Bundle;
import android.widget.ImageView;

import com.adl.base.activity.NextWriter;
import com.adl.ts.general.R;

/**
 * ProjectName: AdlMountGeneral
 * Author     : 南山
 * Date       : 2025/7/16
 * Describe   : 类描述
 */
public class ReportActivity extends BaseActivity {

    DataItemView leftShoulder;
    DataItemView rightShoulder;
    DataItemView leftHip;
    DataItemView rightHip;
    DataItemView leftKnee;
    DataItemView rightKnee;

    ImageView leftShoulderIv;
    ImageView rightShoulderIv;
    ImageView leftHipIv;
    ImageView rightHipIv;
    ImageView leftKneeIv;
    ImageView rightKneeIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_content_report);

        leftShoulder = findViewById(R.id.left_shoulder_view);
        rightShoulder = findViewById(R.id.right_shoulder_view);
        leftHip = findViewById(R.id.left_hip_view);
        rightHip = findViewById(R.id.right_hip_view);
        leftKnee = findViewById(R.id.left_knee_view);
        rightKnee = findViewById(R.id.right_knee_view);

        leftShoulderIv = findViewById(R.id.left_shoulder_view_src);
        rightShoulderIv = findViewById(R.id.right_shoulder_view_src);
        leftHipIv = findViewById(R.id.left_hip_view_src);
        rightHipIv = findViewById(R.id.right_hip_view_src);
        leftKneeIv = findViewById(R.id.left_knee_view_src);
        rightKneeIv = findViewById(R.id.right_knee_view_src);

        ContentData data = ContentActivity.contentData;

        leftShoulder.setData(ContentActivity.currentTime, data.leftShoulder, 75, 110, 120);
        rightShoulder.setData(ContentActivity.currentTime, data.rightShoulder, 75, 110, 120);
        leftHip.setData(ContentActivity.currentTime, data.leftHip, 160, 175, 180);
        rightHip.setData(ContentActivity.currentTime, data.rightHip, 160, 175, 180);
        leftKnee.setData(ContentActivity.currentTime, data.leftKnee, 145, 180, 180);
        rightKnee.setData(ContentActivity.currentTime, data.rightKnee, 145, 180, 180);

        leftShoulderIv.setImageBitmap(ContentActivity.shoulderLBitmap);
        rightShoulderIv.setImageBitmap(ContentActivity.shoulderRBitmap);
        leftHipIv.setImageBitmap(ContentActivity.hipLBitmap);
        rightHipIv.setImageBitmap(ContentActivity.hipRBitmap);
        leftKneeIv.setImageBitmap(ContentActivity.kneeLBitmap);
        rightKneeIv.setImageBitmap(ContentActivity.kneeRBitmap);

        leftShoulderIv.setOnClickListener(view -> {
            ReportPreviewActivity.imagePath = ContentActivity.shoulderLBitmap;
            NextWriter.with(ReportActivity.this, ReportPreviewActivity.class).go();
        });

        rightShoulderIv.setOnClickListener(view -> {
            ReportPreviewActivity.imagePath = ContentActivity.shoulderRBitmap;
            NextWriter.with(ReportActivity.this, ReportPreviewActivity.class).go();
        });

        leftHipIv.setOnClickListener(view -> {
            ReportPreviewActivity.imagePath = ContentActivity.hipLBitmap;
            NextWriter.with(ReportActivity.this, ReportPreviewActivity.class).go();
        });

        rightHipIv.setOnClickListener(view -> {
            ReportPreviewActivity.imagePath = ContentActivity.hipRBitmap;
            NextWriter.with(ReportActivity.this, ReportPreviewActivity.class).go();
        });

        leftKneeIv.setOnClickListener(view -> {
            ReportPreviewActivity.imagePath = ContentActivity.kneeLBitmap;
            NextWriter.with(ReportActivity.this, ReportPreviewActivity.class).go();
        });

        rightKneeIv.setOnClickListener(view -> {
            ReportPreviewActivity.imagePath = ContentActivity.kneeRBitmap;
            NextWriter.with(ReportActivity.this, ReportPreviewActivity.class).go();
        });

        findViewById(R.id.bnt_close).setOnClickListener(view -> finish());
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
