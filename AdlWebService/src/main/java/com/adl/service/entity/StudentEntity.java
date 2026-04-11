package com.adl.service.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.adl.service.db.converter.ListFaceDataConverter;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/2
 * Describe   : 类描述
 * Path       : /busi/terminal/account/list
 */
@Entity(tableName = "_student_entity"
        , indices = {@Index(value = {"_account_id"})})
@TypeConverters(value = {ListFaceDataConverter.class})
public class StudentEntity implements Serializable {

    @ColumnInfo(name = "_user_id", defaultValue = "")
    private String userId;

    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "_account_id")
    private String accountId;

    @ColumnInfo(name = "_account_name")
    private String accountName;

    @ColumnInfo(name = "_account_num")
    private String accountNum;

    @ColumnInfo(name = "_account_phone_Num")
    private String accountPhoneNum;

    @ColumnInfo(name = "_card_nums")
    private List<String> cardNums;

    @ColumnInfo(name = "_card_nums_str")
    private String cardNumStr;

    // 院系id
    @ColumnInfo(name = "_college_id")
    private String collegeId;

    // 院系名称
    @ColumnInfo(name = "_college_name")
    private String collegeName;

    // 学系id
    @ColumnInfo(name = "_faculty_id")
    private String facultyId;

    // 学系名称
    @ColumnInfo(name = "_faculty_name")
    private String facultyName;

    // 专业id
    @ColumnInfo(name = "_major_id")
    private String majorId;

    // 专业名称
    @ColumnInfo(name = "_major_name")
    private String majorName;

    // 学段代码
    @ColumnInfo(name = "_sec_code")
    private String secCode;

    // 学段名称
    @ColumnInfo(name = "_sec_name")
    private String secName;

    // 学段类型 小学1 初中2 高中3 大学4
    @ColumnInfo(name = "_stage_type")
    private String stageType;

    // 年级名称
    @ColumnInfo(name = "_grade_name")
    private String gradeName;

    // 年级id
    @ColumnInfo(name = "_grade_id")
    private String gradeId;

    // 年级编号
    @ColumnInfo(name = "_grade_num")
    private String gradeNum;

    //  班级id
    @ColumnInfo(name = "_class_id")
    private String classId;

    //  班级名称
    @ColumnInfo(name = "_class_name")
    private String className;

    //  班级编号
    @ColumnInfo(name = "_class_num")
    private String classNum;

    // 用户人脸照片
    @ColumnInfo(name = "_user_face_img_url")
    private String userFaceImgUrl;

    // 学号
    @ColumnInfo(name = "_student_code")
    private String studentCode;

    //
    @ColumnInfo(name = "_acayear_sem_code")
    private String acayearSemCode;

    // 1男2女9未知
    @ColumnInfo(name = "_user_sex")
    private String userSex;

    @ColumnInfo(name = "_photo_status")
    private String photoStatus;

    @ColumnInfo(name = "_face_data")
    private String faceData;

    @ColumnInfo(name = "_face_data_list")
    private List<FaceDataEntity> faceDataList;

    //  创建时间
    @ColumnInfo(name = "_create_time")
    private String createTime;

    //  更新时间
    @ColumnInfo(name = "_update_time")
    private String updateTime;

    //  删除标识  0：可用  1：删除
    @ColumnInfo(name = "_del_flag", defaultValue = "0")
    private String delFlag;

    //移动学生学号
    @ColumnInfo(name = "_student_num")
    private String studentNum;

    // 年级班级中文名称(eg:初中二年级4班)
    @ColumnInfo(name = "_grade_stage_chinese_name", defaultValue = "")
    private String gradeStageChineseName;
    @ColumnInfo(name = "_thumbnail_user_face_imgUrl", defaultValue = "")
    private String thumbnailUserFaceImgUrl;

    @ColumnInfo(name = "_account_name_pinyin", defaultValue = "")
    private String accountNamePinYin = "";

    @ColumnInfo(name = "_account_name_first_letter_pinyin", defaultValue = "")
    private String accountNameFirstLetterPinYin = "";

    @ColumnInfo(name = "local_avatar_path", defaultValue = "")
    private String localAvatarPath = "";

    public String getThumbnailUserFaceImgUrl() {
        return thumbnailUserFaceImgUrl;
    }

    public void setThumbnailUserFaceImgUrl(String thumbnailUserFaceImgUrl) {
        this.thumbnailUserFaceImgUrl = thumbnailUserFaceImgUrl;
    }

    public String getAccountNamePinYin() {
        return accountNamePinYin;
    }

    public void setAccountNamePinYin(String accountNamePinYin) {
        this.accountNamePinYin = accountNamePinYin;
    }

    public String getAccountNameFirstLetterPinYin() {
        return accountNameFirstLetterPinYin;
    }

    public void setAccountNameFirstLetterPinYin(String accountNameFirstLetterPinYin) {
        this.accountNameFirstLetterPinYin = accountNameFirstLetterPinYin;
    }

    public String getLocalAvatarPath() {
        return localAvatarPath;
    }

    public void setLocalAvatarPath(String localAvatarPath) {
        this.localAvatarPath = localAvatarPath;
    }

    public StudentEntity() {
        this.accountId = "";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @NotNull
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(@NotNull String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountPhoneNum() {
        return accountPhoneNum;
    }

    public void setAccountPhoneNum(String accountPhoneNum) {
        this.accountPhoneNum = accountPhoneNum;
    }

    public List<String> getCardNums() {
        return cardNums;
    }

    public void setCardNums(List<String> cardNums) {
        this.cardNums = cardNums;
    }

    public String getCardNumStr() {
        return cardNumStr;
    }

    public void setCardNumStr(String cardNumStr) {
        this.cardNumStr = cardNumStr;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getUserFaceImgUrl() {
        return userFaceImgUrl;
    }

    public void setUserFaceImgUrl(String userFaceImgUrl) {
        this.userFaceImgUrl = userFaceImgUrl;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getAcayearSemCode() {
        return acayearSemCode;
    }

    public void setAcayearSemCode(String acayearSemCode) {
        this.acayearSemCode = acayearSemCode;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(String gradeNum) {
        this.gradeNum = gradeNum;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getPhotoStatus() {
        return photoStatus;
    }

    public void setPhotoStatus(String photoStatus) {
        this.photoStatus = photoStatus;
    }

    public String getFaceData() {
        return faceData;
    }

    public void setFaceData(String faceData) {
        this.faceData = faceData;
    }

    public List<FaceDataEntity> getFaceDataList() {
        return faceDataList;
    }

    public void setFaceDataList(List<FaceDataEntity> faceDataList) {
        this.faceDataList = faceDataList;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getStageType() {
        return stageType;
    }

    public void setStageType(String stageType) {
        this.stageType = stageType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getGradeStageChineseName() {
        return gradeStageChineseName;
    }

    public void setGradeStageChineseName(String gradeStageChineseName) {
        this.gradeStageChineseName = gradeStageChineseName;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "userId='" + userId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountNum='" + accountNum + '\'' +
                ", accountPhoneNum='" + accountPhoneNum + '\'' +
                ", cardNums=" + cardNums +
                ", cardNumStr='" + cardNumStr + '\'' +
                ", collegeId='" + collegeId + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", facultyId='" + facultyId + '\'' +
                ", facultyName='" + facultyName + '\'' +
                ", majorId='" + majorId + '\'' +
                ", majorName='" + majorName + '\'' +
                ", secCode='" + secCode + '\'' +
                ", secName='" + secName + '\'' +
                ", stageType='" + stageType + '\'' +
                ", gradeName='" + gradeName + '\'' +
                ", gradeId='" + gradeId + '\'' +
                ", gradeNum='" + gradeNum + '\'' +
                ", classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", classNum='" + classNum + '\'' +
                ", userFaceImgUrl='" + userFaceImgUrl + '\'' +
                ", studentCode='" + studentCode + '\'' +
                ", acayearSemCode='" + acayearSemCode + '\'' +
                ", userSex='" + userSex + '\'' +
                ", photoStatus='" + photoStatus + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", gradeStageChineseName='" + gradeStageChineseName + '\'' +
                '}';
    }
}
