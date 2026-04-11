package com.ai.jump;

import android.os.Bundle;

import com.adl.base.common.AdlToast;
import com.adl.base.speech.AdlTextSpeaker;
import com.adl.ts.general.R;
import com.ai.jump.lib.SportLongJumpSetCallback;
import com.ai.jump.lib.SportLongJumpSetLayout;
import com.ai.test.BaseActivity;
import com.nz.sport.layout.SportModelInfo;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/5/8
 * Describe   : 类描述
 */
public class LongJumpSetActivity extends BaseActivity {

    SportLongJumpSetLayout longJumpLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_long_jump_set);
        longJumpLayout = findViewById(R.id.long_jump_set_layout);

        longJumpLayout.setCallback(new SportLongJumpSetCallback() {
            @Override
            public void onSpeaker(String msg) {
                AdlTextSpeaker.speak(msg);
            }

            @Override
            public void onModel(SportModelInfo info) {
                AdlTextSpeaker.speak(info.msg);
            }
        });

        findViewById(R.id.bnt_change).setOnClickListener(view -> {
            longJumpLayout.toChangeDirection();
        });

        findViewById(R.id.bnt_save).setOnClickListener(view -> {
            if (longJumpLayout.toSave().isOk()) {
                AdlToast.show("保存成功");
            }
        });

        findViewById(R.id.bnt_auto).setOnClickListener(view -> {

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        longJumpLayout.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        longJumpLayout.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
