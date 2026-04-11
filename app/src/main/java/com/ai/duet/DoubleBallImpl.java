package com.ai.duet;

import com.adl.lib.pose.AdlObject;
import com.adl.lib.pose.AdlPoseInfo;
import com.adl.mount.impl.TsDoubleProcessor;
import com.adl.mount.impl.TsSportFrameDouble;

import java.util.List;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/6/14
 * Describe   : 类描述
 */
public class DoubleBallImpl implements TsDoubleProcessor {

    @Override
    public void onAnalyzer(TsSportFrameDouble frame) {
        AdlPoseInfo poseInfo = frame.poseInfo;
        List<AdlObject> list = frame.allBody;

        // TODO 逻辑处理

    }

    @Override
    public void onInit() {

    }

    @Override
    public void onReset() {

    }

    @Override
    public void onRelease() {

    }

    @Override
    public String version() {
        return null;
    }
}
