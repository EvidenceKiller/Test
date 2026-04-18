package com.adl.service.db.entity;

import com.adl.service.db.converter.ListFaceEntityConverter;
import com.adl.service.data.StudentData;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = "_student", indices = {@Index(value = {"_account_id"})})
@TypeConverters(value = {ListFaceEntityConverter.class})
public class StudentEntity implements Serializable {

    @ColumnInfo(name = "_user_id")
    private String userId;

    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "_account_id")
    private String accountId;

    @ColumnInfo(name = "_account_name")
    private String accountName;

    @ColumnInfo(name = "_account_num")
    private String accountNum;

    @ColumnInfo(name = "_account_phone_num")
    private String accountPhoneNum;

    @ColumnInfo(name = "_user_phone_num")
    private String userPhoneNum;

    @ColumnInfo(name = "_card_nums")
    private List<String> cardNums;

    @ColumnInfo(name = "_card_num_str")
    private String cardNumStr;

    @ColumnInfo(name = "_class_id")
    private String classId;

    @ColumnInfo(name = "_grade_stage_chinese_name")
    private String gradeStageChineseName;

    @ColumnInfo(name = "_class_name")
    private String className;

    // 学系id
    @ColumnInfo(name = "_faculty_id")
    private String facultyId;

    // 学系名称
    @ColumnInfo(name = "_faculty_name")
    private String facultyName;

    // 院系id
    @ColumnInfo(name = "_college_id")
    private String collegeId;

    // 院系名称
    @ColumnInfo(name = "_college_name")
    private String collegeName;

    // 专业id
    @ColumnInfo(name = "_major_id")
    private String majorId;

    // 专业名称
    @ColumnInfo(name = "_major_code")
    private String majorCode;

    // 专业名称
    @ColumnInfo(name = "_major_name")
    private String majorName;

    @ColumnInfo(name = "_org_name")
    private String orgName;

    // 用户人脸照片
    @ColumnInfo(name = "_user_face_img_url")
    private String userFaceImgUrl;

    @ColumnInfo(name = "_thumbnail_user_face_img_url")
    private String thumbnailUserFaceImgUrl;

    @ColumnInfo(name = "_account_avatar")
    private String accountAvatar;

    @ColumnInfo(name = "_student_code")
    private String studentCode;

    //移动学生学号
    @ColumnInfo(name = "_student_num")
    private String studentNum;

    @ColumnInfo(name = "_acayear_sem_code")
    private String acayearSemCode;

    @ColumnInfo(name = "_acayear_sem_name")
    private String acayearSemName;

    @ColumnInfo(name = "_acayear_sem_en_date")
    private String acayearSemEnDate;

    // 年级id
    @ColumnInfo(name = "_grade_id")
    private String gradeId;

    // 年级名称
    @ColumnInfo(name = "_grade_name")
    private String gradeName;

    // 1男2女9未知
    @ColumnInfo(name = "_user_sex")
    private String userSex;

    @ColumnInfo(name = "_photo_status")
    private String photoStatus;

    //  创建时间
    @ColumnInfo(name = "_create_time")
    private String createTime;

    //  更新时间
    @ColumnInfo(name = "_update_time")
    private String updateTime;

    //  删除标识  0：可用  1：删除
    @ColumnInfo(name = "_del_flag")
    private String delFlag;

    // 学段代码
    @ColumnInfo(name = "_sec_code")
    private String secCode;

    // 学段名称
    @ColumnInfo(name = "_sec_name")
    private String secName;

    // 学段类型 小学1 初中2 高中3 大学4
    @ColumnInfo(name = "_stage_type")
    private String stageType;

    // 年级编号
    @ColumnInfo(name = "_grade_num")
    private String gradeNum;

    // 学段类型 小学1 初中2 高中3 大学4
    @ColumnInfo(name = "_enter_year")
    private Integer enterYear;

    //  班级id
    @ColumnInfo(name = "_class_num")
    private String classNum;

    @ColumnInfo(name = "_class_status")
    private Integer classStatus;

    @ColumnInfo(name = "_nation_code")
    private Integer nationCode;

    @ColumnInfo(name = "_user_name")
    private String userName;

    @ColumnInfo(name = "_cert_type")
    private Integer certType;

    @ColumnInfo(name = "_cert_num")
    private String certNum;

    @ColumnInfo(name = "_avatar_flag")
    private Boolean avatarFlag;

    @ColumnInfo(name = "_record_flag")
    private Boolean recordFlag;

    @ColumnInfo(name = "_face_data_list")
    private List<FaceEntity> faceDataList;

    @ColumnInfo(name = "_account_name_pin_yin")
    private String accountNamePinYin;

    @ColumnInfo(name = "_account_name_first_letter_pin_yin")
    private String accountNameFirstLetterPinYin;

    @ColumnInfo(name = "_birthday")
    private String birthday;

    @ColumnInfo(name = "_address")
    private String address;

    public static StudentEntity convertToEntity(StudentData studentData) {
        if (studentData == null) {
            return null;
        }
        StudentEntity entity = new StudentEntity();
        entity.setUserId(studentData.getUserId());
        entity.setAccountId(studentData.getAccountId());
        entity.setAccountName(studentData.getAccountName());
        entity.setAccountNum(studentData.getAccountNum());
        entity.setAccountPhoneNum(studentData.getAccountPhoneNum());
        entity.setUserPhoneNum(studentData.getUserPhoneNum());
        entity.setCardNums(studentData.getCardNums());
        entity.setCardNumStr(studentData.getCardNumStr());
        entity.setClassId(studentData.getClassId());
        entity.setGradeStageChineseName(studentData.getGradeStageChineseName());
        entity.setClassName(studentData.getClassName());
        entity.setFacultyId(studentData.getFacultyId());
        entity.setFacultyName(studentData.getFacultyName());
        entity.setCollegeId(studentData.getCollegeId());
        entity.setCollegeName(studentData.getCollegeName());
        entity.setMajorId(studentData.getMajorId());
        entity.setMajorCode(studentData.getMajorCode());
        entity.setMajorName(studentData.getMajorName());
        entity.setOrgName(studentData.getOrgName());
        entity.setUserFaceImgUrl(studentData.getUserFaceImgUrl());
        entity.setThumbnailUserFaceImgUrl(studentData.getThumbnailUserFaceImgUrl());
        entity.setAccountAvatar(studentData.getAccountAvatar());
        entity.setStudentCode(studentData.getStudentCode());
        entity.setStudentNum(studentData.getStudentNum());
        entity.setAcayearSemCode(studentData.getAcayearSemCode());
        entity.setAcayearSemName(studentData.getAcayearSemName());
        entity.setAcayearSemEnDate(studentData.getAcayearSemEnDate());
        entity.setGradeId(studentData.getGradeId());
        entity.setGradeName(studentData.getGradeName());
        entity.setUserSex(studentData.getUserSex());
        entity.setPhotoStatus(studentData.getPhotoStatus());
        entity.setCreateTime(studentData.getCreateTime());
        entity.setUpdateTime(studentData.getUpdateTime());
        entity.setDelFlag(studentData.getDelFlag());
        entity.setSecCode(studentData.getSecCode());
        entity.setSecName(studentData.getSecName());
        entity.setStageType(studentData.getStageType());
        entity.setGradeNum(studentData.getGradeNum());
        entity.setEnterYear(studentData.getEnterYear());
        entity.setClassNum(studentData.getClassNum());
        entity.setClassStatus(studentData.getClassStatus());
        entity.setNationCode(studentData.getNationCode());
        entity.setUserName(studentData.getUserName());
        entity.setCertType(studentData.getCertType());
        entity.setCertNum(studentData.getCertNum());
        entity.setAvatarFlag(studentData.getAvatarFlag());
        entity.setRecordFlag(studentData.getRecordFlag());
        if (studentData.getFaceDataList() != null) {
            entity.setFaceDataList(studentData.getFaceDataList().stream()
                    .map(FaceEntity::convertToEntity)
                    .collect(Collectors.toList()));
        }
        entity.setAccountNamePinYin(studentData.getAccountNamePinYin());
        entity.setAccountNameFirstLetterPinYin(studentData.getAccountNameFirstLetterPinYin());
        entity.setBirthday(studentData.getBirthday());
        entity.setAddress(studentData.getAddress());
        return entity;
    }

    public static StudentData convertToData(StudentEntity entity) {
        if (entity == null) {
            return null;
        }
        StudentData data = new StudentData();
        data.setUserId(entity.getUserId());
        data.setAccountId(entity.getAccountId());
        data.setAccountName(entity.getAccountName());
        data.setAccountNum(entity.getAccountNum());
        data.setAccountPhoneNum(entity.getAccountPhoneNum());
        data.setUserPhoneNum(entity.getUserPhoneNum());
        data.setCardNums(entity.getCardNums());
        data.setCardNumStr(entity.getCardNumStr());
        data.setClassId(entity.getClassId());
        data.setGradeStageChineseName(entity.getGradeStageChineseName());
        data.setClassName(entity.getClassName());
        data.setFacultyId(entity.getFacultyId());
        data.setFacultyName(entity.getFacultyName());
        data.setCollegeId(entity.getCollegeId());
        data.setCollegeName(entity.getCollegeName());
        data.setMajorId(entity.getMajorId());
        data.setMajorCode(entity.getMajorCode());
        data.setMajorName(entity.getMajorName());
        data.setOrgName(entity.getOrgName());
        data.setUserFaceImgUrl(entity.getUserFaceImgUrl());
        data.setThumbnailUserFaceImgUrl(entity.getThumbnailUserFaceImgUrl());
        data.setAccountAvatar(entity.getAccountAvatar());
        data.setStudentCode(entity.getStudentCode());
        data.setStudentNum(entity.getStudentNum());
        data.setAcayearSemCode(entity.getAcayearSemCode());
        data.setAcayearSemName(entity.getAcayearSemName());
        data.setAcayearSemEnDate(entity.getAcayearSemEnDate());
        data.setGradeId(entity.getGradeId());
        data.setGradeName(entity.getGradeName());
        data.setUserSex(entity.getUserSex());
        data.setPhotoStatus(entity.getPhotoStatus());
        data.setCreateTime(entity.getCreateTime());
        data.setUpdateTime(entity.getUpdateTime());
        data.setDelFlag(entity.getDelFlag());
        data.setSecCode(entity.getSecCode());
        data.setSecName(entity.getSecName());
        data.setStageType(entity.getStageType());
        data.setGradeNum(entity.getGradeNum());
        data.setEnterYear(entity.getEnterYear());
        data.setClassNum(entity.getClassNum());
        data.setClassStatus(entity.getClassStatus());
        data.setNationCode(entity.getNationCode());
        data.setUserName(entity.getUserName());
        data.setCertType(entity.getCertType());
        data.setCertNum(entity.getCertNum());
        data.setAvatarFlag(entity.getAvatarFlag());
        data.setRecordFlag(entity.getRecordFlag());
        if (entity.getFaceDataList() != null) {
            data.setFaceDataList(entity.getFaceDataList().stream()
                    .map(FaceEntity::convertToData)
                    .collect(java.util.stream.Collectors.toList()));
        }
        data.setAccountNamePinYin(entity.getAccountNamePinYin());
        data.setAccountNameFirstLetterPinYin(entity.getAccountNameFirstLetterPinYin());
        data.setBirthday(entity.getBirthday());
        data.setAddress(entity.getAddress());
        return data;
    }
}
