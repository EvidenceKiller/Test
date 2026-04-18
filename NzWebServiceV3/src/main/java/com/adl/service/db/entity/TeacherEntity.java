package com.adl.service.db.entity;

import com.adl.service.db.converter.ListFaceEntityConverter;
import com.adl.service.data.TeacherData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = "_teacher")
@TypeConverters(value = {ListFaceEntityConverter.class})
public final class TeacherEntity {

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

    @ColumnInfo(name = "_user_sex")
    private Integer userSex;

    @ColumnInfo(name = "_job_num")
    private String jobNum;

    @ColumnInfo(name = "_account_avatar")
    private String accountAvatar;

    @ColumnInfo(name = "_account_name_pin_yin")
    private String accountNamePinYin;

    @ColumnInfo(name = "_account_name_first_letter_pin_yin")
    private String accountNameFirstLetterPinYin;

    @ColumnInfo(name = "_user_face_img_url")
    private String userFaceImgUrl;

    @ColumnInfo(name = "_thumbnail_user_face_img_url")
    private String thumbnailUserFaceImgUrl;

    @ColumnInfo(name = "_create_time")
    private String createTime;

    @ColumnInfo(name = "_update_time")
    private String updateTime;

    @ColumnInfo(name = "_avatar_flag")
    private Boolean avatarFlag;

    @ColumnInfo(name = "_record_flag")
    private Boolean recordFlag;

    @ColumnInfo(name = "_del_flag")
    private Boolean delFlag;

    @ColumnInfo(name = "_face_data_list")
    private List<FaceEntity> faceDataList;

    public static TeacherEntity convertToEntity(TeacherData teacherData) {
        if (teacherData == null) {
            return null;
        }
        TeacherEntity entity = new TeacherEntity();
        entity.setUserId(teacherData.getUserId());
        entity.setAccountId(teacherData.getAccountId());
        entity.setAccountName(teacherData.getAccountName());
        entity.setAccountNum(teacherData.getAccountNum());
        entity.setAccountPhoneNum(teacherData.getAccountPhoneNum());
        entity.setUserSex(teacherData.getUserSex());
        entity.setJobNum(teacherData.getJobNum());
        entity.setAccountAvatar(teacherData.getAccountAvatar());
        entity.setAccountNamePinYin(teacherData.getAccountNamePinYin());
        entity.setAccountNameFirstLetterPinYin(teacherData.getAccountNameFirstLetterPinYin());
        entity.setUserFaceImgUrl(teacherData.getUserFaceImgUrl());
        entity.setThumbnailUserFaceImgUrl(teacherData.getThumbnailUserFaceImgUrl());
        entity.setCreateTime(teacherData.getCreateTime());
        entity.setUpdateTime(teacherData.getUpdateTime());
        entity.setAvatarFlag(teacherData.getAvatarFlag());
        entity.setRecordFlag(teacherData.getRecordFlag());
        entity.setDelFlag(teacherData.getDelFlag());
        if (teacherData.getFaceDataList() != null) {
            entity.setFaceDataList(teacherData.getFaceDataList().stream()
                    .map(FaceEntity::convertToEntity)
                    .collect(java.util.stream.Collectors.toList()));
        }
        return entity;
    }

    public static TeacherData convertToData(TeacherEntity entity) {
        if (entity == null) {
            return null;
        }
        TeacherData data = new TeacherData();
        data.setUserId(entity.getUserId());
        data.setAccountId(entity.getAccountId());
        data.setAccountName(entity.getAccountName());
        data.setAccountNum(entity.getAccountNum());
        data.setAccountPhoneNum(entity.getAccountPhoneNum());
        data.setUserSex(entity.getUserSex());
        data.setJobNum(entity.getJobNum());
        data.setAccountAvatar(entity.getAccountAvatar());
        data.setAccountNamePinYin(entity.getAccountNamePinYin());
        data.setAccountNameFirstLetterPinYin(entity.getAccountNameFirstLetterPinYin());
        data.setUserFaceImgUrl(entity.getUserFaceImgUrl());
        data.setThumbnailUserFaceImgUrl(entity.getThumbnailUserFaceImgUrl());
        data.setCreateTime(entity.getCreateTime());
        data.setUpdateTime(entity.getUpdateTime());
        data.setAvatarFlag(entity.getAvatarFlag());
        data.setRecordFlag(entity.getRecordFlag());
        data.setDelFlag(entity.getDelFlag());
        if (entity.getFaceDataList() != null) {
            data.setFaceDataList(entity.getFaceDataList().stream()
                    .map(FaceEntity::convertToData)
                    .collect(java.util.stream.Collectors.toList()));
        }
        return data;
    }
}