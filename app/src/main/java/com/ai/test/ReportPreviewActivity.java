package com.ai.test;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.adl.ts.general.R;
import com.github.chrisbanes.photoview.PhotoView;

/**
 * ProjectName: AdlMountGeneral
 * Author     : 南山
 * Date       : 2025/7/16
 * Describe   : 类描述
 */
public class ReportPreviewActivity extends BaseActivity {

    public static Bitmap imagePath;

    PhotoView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_content_report_preview);
        iv = findViewById(R.id.iv);

        if (imagePath != null) {
            iv.setImageBitmap(imagePath);
        }

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
