package com.ai.jump.lib;

import com.adl.sport.focus.JumpSubStatus;
import com.nz.sport.layout.SportErrorInfo;
import com.nz.sport.layout.SportMainCallback;
import com.nz.sport.layout.SportModelInfo;
import com.nz.sport.layout.SportRuleInfo;

/**
 * ProjectName: AdlMountGeneral
 * Author     : 南山
 * Date       : 2026/1/19
 * Describe   : 类描述
 */
public abstract class SportLongJumpCallback implements SportMainCallback {

    public SportLongJumpCallback() {

    }

    @Override
    public void onSpeaker(String msg) {

    }

    @Override
    public void onError(SportErrorInfo error) {

    }

    @Override
    public void onBreakRule(SportRuleInfo rule) {

    }

    @Override
    public void onModel(SportModelInfo info) {

    }

    public abstract void onChange(JumpSubStatus status);
}
