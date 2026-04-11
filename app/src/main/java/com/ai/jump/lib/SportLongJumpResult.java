package com.ai.jump.lib;

import com.adl.lib.pose.AdlPoseSkeleton;
import com.adl.sport.focus.JumpResultBean2;
import com.nz.sport.layout.SportResultInfo;

/**
 * ProjectName: AdlMountGeneral
 * Author     : 南山
 * Date       : 2026/1/19
 * Describe   : 类描述
 */
public class SportLongJumpResult extends SportResultInfo {

    public AdlPoseSkeleton startKey;
    public AdlPoseSkeleton fallKey;
    public float distance;
    public JumpResultBean2 resultBean;

}
