package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public class StudentData implements Serializable {

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

    @SerializedName("userPhoneNum")
    private String userPhoneNum;

    @SerializedName("cardNums")
    private List<String> cardNums;

    @SerializedName("cardNumStr")
    private String cardNumStr;

    @SerializedName("classId")
    private String classId;

    @SerializedName("gradeStageChineseName")
    private String gradeStageChineseName;

    @SerializedName("className")
    private String className;

    // 学系id
    @SerializedName("facultyId")
    private String facultyId;

    // 学系名称
    @SerializedName("facultyName")
    private String facultyName;

    // 院系id
    @SerializedName("collegeId")
    private String collegeId;

    // 院系名称
    @SerializedName("collegeName")
    private String collegeName;

    // 专业id
    @SerializedName("majorId")
    private String majorId;

    // 专业名称
    @SerializedName("majorCode")
    private String majorCode;

    // 专业名称
    @SerializedName("majorName")
    private String majorName;

    @SerializedName("orgName")
    private String orgName;

    // 用户人脸照片
    @SerializedName("userFaceImgUrl")
    private String userFaceImgUrl;

    @SerializedName("thumbnailUserFaceImgUrl")
    private String thumbnailUserFaceImgUrl;

    @SerializedName("accountAvatar")
    private String accountAvatar;

    @SerializedName("studentCode")
    private String studentCode;

    //移动学生学号
    @SerializedName("studentNum")
    private String studentNum;

    @SerializedName("acayearSemCode")
    private String acayearSemCode;

    @SerializedName("acayearSemName")
    private String acayearSemName;

    @SerializedName("acayearSemEnDate")
    private String acayearSemEnDate;

    // 年级id
    @SerializedName("gradeId")
    private String gradeId;

    // 年级名称
    @SerializedName("gradeName")
    private String gradeName;

    // 1男2女9未知
    @SerializedName("userSex")
    private String userSex;

    @SerializedName("photoStatus")
    private String photoStatus;

    //  创建时间
    @SerializedName("createTime")
    private String createTime;

    //  更新时间
    @SerializedName("updateTime")
    private String updateTime;

    //  删除标识  0：可用  1：删除
    @SerializedName("delFlag")
    private String delFlag;

    // 学段代码
    @SerializedName("secCode")
    private String secCode;

    // 学段名称
    @SerializedName("secName")
    private String secName;

    // 学段类型 小学1 初中2 高中3 大学4
    @SerializedName("stageType")
    private String stageType;

    // 年级编号
    @SerializedName("gradeNum")
    private String gradeNum;

    // 学段类型 小学1 初中2 高中3 大学4
    @SerializedName("enterYear")
    private Integer enterYear;

    //  班级id
    @SerializedName("classNum")
    private String classNum;

    @SerializedName("classStatus")
    private Integer classStatus;

    @SerializedName("nationCode")
    private Integer nationCode;

    @SerializedName("userName")
    private String userName;

    @SerializedName("certType")
    private Integer certType;

    @SerializedName("certNum")
    private String certNum;

    @SerializedName("avatarFlag")
    private Boolean avatarFlag;

    @SerializedName("recordFlag")
    private Boolean recordFlag;

    @SerializedName("faceDataList")
    private List<FaceData> faceDataList;

    @SerializedName("accountNamePinYin")
    private String accountNamePinYin;

    @SerializedName("accountNameFirstLetterPinYin")
    private String accountNameFirstLetterPinYin;

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("address")
    private String address;
}
