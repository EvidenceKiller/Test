package com.ai.duet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.adl.base.log.AdlLogger;
import com.adl.ts.general.R;
import com.ai.test.BaseActivity;
import com.yanzhenjie.permission.AndPermission;

public class SwicthActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        if (checkPermissions(Permissions)) {
        } else {
            AndPermission.with(this)
                    .runtime()
                    .permission(Permissions)
                    .onGranted(data -> {
                    })
                    .onDenied(data -> {
                        AdlLogger.d("--->");
                    }).start();
        }
        findViewById(R.id.btn_uvc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SwicthActivity.this, UVCCameraActivity.class));
            }
        });
        findViewById(R.id.btn_v4l2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SwicthActivity.this, V4l2CameraActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
