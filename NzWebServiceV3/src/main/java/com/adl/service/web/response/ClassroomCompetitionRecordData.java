package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.ToString;


/**
 * Name : SportDetailListData
 * Author : zhouxiangnan
 * Date : 2026/4/7
 * Describe : 定义接口响应数据结构
 */
@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class ClassroomCompetitionRecordData {
    private String orgId;
    private String orgName;
    private String gradeId;
    private String gradeName;
    private String classId;
    private String className;
    private List<String> accountIds;
    private List<String> avatars;
    private String userName;
    private String sex;
    private String studentCode;
    private String appCode;
    private String appName;
    private String sportSkuId;
    private String sportSkuName;
    private String sportResultTotal;
    private String originalSportResultTotal;
    private String sportResultAvg;
    private String originalSportResultAvg;
    private Long sportTime;
    private String multiPersonId;
    private Integer multiPersonRank;
    private List<String> multiPersonRanks;
    private Integer multiPersonIndex;
    private String ptSportStandardScore;
    private Long sportStartTime;
    private Long sportEndTime;
    private String sportVideoUrls;
    private Integer accuracyType;
    private Integer accuracyMethod;
    private String sportUnitCode;
    private String sportUnitName;
    private Integer sportPriority;
}