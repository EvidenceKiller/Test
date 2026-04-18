package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class SportMeetSimpleClassData {
    @SerializedName("id")
    private Integer id;

    /**
     * 机构Id
     */
    @SerializedName("orgId")
    private String orgId;

    /**
     * 运动会Id
     */
    @SerializedName("sportMeetId")
    private String sportMeetId;

    /**
     * 报名人数
     */
    @SerializedName("applyUserCount")
    private Integer applyUserCount;

    /**
     * 班级Id
     */
    @SerializedName("classId")
    private String classId;

    @SerializedName("createTime")
    private String createTime;

    @SerializedName("updateTime")
    private String updateTime;
}
