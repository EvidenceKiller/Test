package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class DateConfigData implements Serializable {
    private String id;

    // 计划id
    private String planId;

    // 预约时间yyyy-mm-dd
    private String reserveDate;

    // 开始时间 hh:ss
    private String startTime;

    // 结束时间 hh:ss
    private String endTime;

    // 是否区分预约日期（0否 1是）
    private Integer reserveType;

    // 预约人数上限
    private Integer reserveMaxNum;

    // 运动类型 （0跑步类、1非跑步类）
    private Integer sportType;

    // 每周重复的预约日期（1,2,3对应周一,周二,周三）
    private String reserveDay;

    // 发布账户ID
    private String creatorId;

    // 发布账户姓名
    private String creatorName;

    // 更新时间
    private Long updateTime;

    // 创建时间
    private Long createTime;
}
