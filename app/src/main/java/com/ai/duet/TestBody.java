package com.ai.duet;

import com.adl.base.log.AdlLogger;
import com.adl.base.overlay.OnRenderScene;
import com.adl.lib.pose.AdlObject;
import com.adl.lib.pose.AdlPoseSkeleton;
import com.adl.mount.core.TsTaskBody;
import com.adl.mount.core.TsTaskDrawer;
import com.adl.mount.impl.TsSportFrameMulti;
import com.adl.mount.sport.TsConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: AdlMountMaster
 * Author     : 南山
 * Date       : 2024/7/12
 * Describe   : 类描述
 */
public class TestBody extends TsTaskBody {

    private TsSportFrameMulti sportFrame;

    public TestBody() {
        sportFrame = new TsSportFrameMulti();
    }

    public TestBody setConfig(TsConfig config) {
        mConfig = config;
        return this;
    }

    public TestBody setDrawer(TsTaskDrawer drawer) {
        mDrawer = drawer;
        return this;
    }

    public TestBody setOverlay(OnRenderScene overlay) {
        mOverlay = overlay;
        return this;
    }

    // 开始处理
    protected void beginFrame() {
        sportFrame.previewWidth = mYuvFrame.width;
        sportFrame.previewHeight = mYuvFrame.height;
        sportFrame.imageId = mYuvFrame.imageId;
        sportFrame.config = mConfig;
        sportFrame.startTime = System.currentTimeMillis();
        sportFrame.endTime = System.currentTimeMillis();
    }

    // 结束处理
    protected TsSportFrameMulti endFrame() {
        return sportFrame;
    }

    @Override
    public void onBody(List<AdlObject> list) {

        int size = Math.min(5, list.size());
        List<AdlPoseSkeleton> skeletons = new ArrayList<>();
        for (int i = 0; i < size; i++) {

            AdlObject body = list.get(i);

            AdlPoseSkeleton skeleton = callSkeleton(mConfig.poseModelId, mConfig.poseModelFp, body);
            if (skeleton == null) {
                //e("警告： 区域 " + region.toString() + ", 识别骨骼失败");
                //continue;
            }

            //skeletons.addAll(ll);
        }

        if (mDrawer != null) {
            mDrawer.updateAll(list);
            mDrawer.refreshUI();
        }



        AdlLogger.d("单次任务耗时=" + (System.currentTimeMillis() - mStartDetectTime));
    }
}
