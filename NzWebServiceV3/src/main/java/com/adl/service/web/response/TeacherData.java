package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class TeacherData {

    @SerializedName("userId")
    private String userId;

    @SerializedName("accountId")
    private String accountId;

    @SerializedName("accountName")
    private String accountName;

    @SerializedName("accountNum")
    private String accountNum;

    @SerializedName("accountPhoneNum")
    private String accountPhoneNum;

    @SerializedName("userSex")
    private Integer userSex;

    @SerializedName("jobNum")
    private String jobNum;

    @SerializedName("accountAvatar")
    private String accountAvatar;

    @SerializedName("accountNamePinYin")
    private String accountNamePinYin;

    @SerializedName("accountNameFirstLetterPinYin")
    private String accountNameFirstLetterPinYin;

    @SerializedName("userFaceImgUrl")
    private String userFaceImgUrl;

    @SerializedName("thumbnailUserFaceImgUrl")
    private String thumbnailUserFaceImgUrl;

    @SerializedName("createTime")
    private String createTime;

    @SerializedName("updateTime")
    private String updateTime;

    @SerializedName("avatarFlag")
    private Boolean avatarFlag;

    @SerializedName("recordFlag")
    private Boolean recordFlag;

    @SerializedName("delFlag")
    private Boolean delFlag;
}
