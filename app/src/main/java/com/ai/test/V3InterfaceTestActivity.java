package com.ai.test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adl.auth.core.AdlAuthFactory;
import com.adl.service.common.FileUtil;
import com.adl.ts.general.R;

import java.io.File;
import java.io.FileOutputStream;


public class V3InterfaceTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_v3_interface_test);

        findViewById(R.id.btn_1).setOnClickListener(v -> startActivity(new Intent(this, V3CommonInfoCallerTestActivity.class)));
        findViewById(R.id.btn_2).setOnClickListener(v -> startActivity(new Intent(this, V3SportInfoCallerTestActivity.class)));
        findViewById(R.id.btn_3).setOnClickListener(v -> startActivity(new Intent(this, V3OrgTestCallerTestActivity.class)));
        findViewById(R.id.btn_4).setOnClickListener(v -> startActivity(new Intent(this, V3OperationCallerTestActivity.class)));
        findViewById(R.id.btn_5).setOnClickListener(v -> startActivity(new Intent(this, V3BusinessCallerTestActivity.class)));
        findViewById(R.id.btn_6).setOnClickListener(v -> startActivity(new Intent(this, V3LoginAuthCallerTestActivity.class)));
        findViewById(R.id.btn_7).setOnClickListener(v -> startActivity(new Intent(this, V3BigScreenStatsCallerTestActivity.class)));
        findViewById(R.id.btn_8).setOnClickListener(v -> startActivity(new Intent(this, V3BigScreenH5CallerTestActivity.class)));
        findViewById(R.id.btn_9).setOnClickListener(v -> startActivity(new Intent(this, V3OpsMaintCallerTestActivity.class)));

    }

    private void flushResultToFile(String fileName, Object result) {
        File dir = FileUtil.createDirectory("NzWebServiceV3Result");
        File file = new File(dir, fileName);
        
        FileOutputStream out = null;
        try {
            if (file.exists()) {
                file.delete();
            }
            String content = result.toString();
            out = new FileOutputStream(file, true);
            out.write(content.getBytes("UTF-8"));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
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
