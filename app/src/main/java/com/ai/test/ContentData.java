package com.ai.test;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: AdlMountGeneral
 * Author     : 南山
 * Date       : 2025/7/23
 * Describe   : 类描述
 */
public class ContentData {

    // 左肩
    public List<ContentDataItem> leftShoulder;
    // 右肩
    public List<ContentDataItem> rightShoulder;

    // 左髋
    public List<ContentDataItem> leftHip;
    // 右髋
    public List<ContentDataItem> rightHip;

    // 左膝
    public List<ContentDataItem> leftKnee;
    // 右膝
    public List<ContentDataItem> rightKnee;

    public ContentData() {
        leftShoulder = new ArrayList<>();
        rightShoulder = new ArrayList<>();
        leftHip = new ArrayList<>();
        rightHip = new ArrayList<>();
        leftKnee = new ArrayList<>();
        rightKnee = new ArrayList<>();
    }

    public void clear() {
        leftShoulder.clear();
        rightShoulder.clear();
        leftHip.clear();
        rightHip.clear();
        leftKnee.clear();
        rightKnee.clear();
    }
}
