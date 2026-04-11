package com.ai.test;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

/**
 * ProjectName: AiPowerCore
 * Author     : 南山
 * Date       : 2022/12/18
 * Describe   : 类描述
 */
public class BaseActivity extends com.adl.base.activity.BaseActivity {

    protected final String[] Permissions = {
            Manifest.permission.INTERNET,
            // 硬件
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            //  蓝牙相关
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.BLUETOOTH,
            android.Manifest.permission.BLUETOOTH_ADMIN
    };

    public boolean checkPermissions(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public String readString(EditText ed) {
        return ed.getText().toString().trim();
    }
}
