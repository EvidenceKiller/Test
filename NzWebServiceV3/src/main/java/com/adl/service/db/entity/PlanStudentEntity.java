package com.adl.service.db.entity;

import com.adl.service.db.converter.ListPlanSportEntityConverter;
import com.adl.service.data.PlanStudentData;

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
@Entity(tableName = "_plan_student", indices = {@Index(value = {"_plan_id"})})
@TypeConverters(value = {ListPlanSportEntityConverter.class})
public final class PlanStudentEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long lid;

    /**
     * 账号标识码
     */
    @ColumnInfo(name = "_account_id")
    private String accountId;

    /**
     * 准考证号
     */
    @ColumnInfo(name = "_admission_num")
    private String admissionNum;

    /**
     * 班级标识码
     */
    @ColumnInfo(name = "_class_id")
    private String classId;

    /**
     * 班级名称
     */
    @ColumnInfo(name = "_class_name")
    private String className;

    /**
     * 年级标识码
     */
    @ColumnInfo(name = "_grade_id")
    private String gradeId;

    /**
     * 年级名称
     */
    @ColumnInfo(name = "_grade_name")
    private String gradeName;

    /**
     * 计划id
     */
    @ColumnInfo(name = "_plan_id")
    private String planId;

    /**
     * 报名项目
     */
    @ColumnInfo(name = "_projects")
    private List<PlanSportEntity> projects;

    /**
     * 学生性别
     */
    @ColumnInfo(name = "_sex")
    private Integer sex;

    /**
     * 学籍号
     */
    @ColumnInfo(name = "_student_code")
    private String studentCode;

    /**
     * 学生姓名
     */
    @ColumnInfo(name = "_student_name")
    private String studentName;

    /**
     * 班级序号
     */
    @ColumnInfo(name = "_class_num")
    private Integer classNum;

    /**
     * 年级序号
     */
    @ColumnInfo(name = "_grade_num")
    private Integer gradeNum;

    /**
     * 阶段ID
     */
    @ColumnInfo(name = "_stage_id")
    private String stageId;

    /**
     * 学号
     */
    @ColumnInfo(name = "_student_num")
    private String studentNum;

    /**
     * 账号名称拼音
     */
    @ColumnInfo(name = "_account_name_pin_yin")
    private String accountNamePinYin;

    /**
     * 学院ID
     */
    @ColumnInfo(name = "_college_id")
    private String collegeId;

    /**
     * 学院名称
     */
    @ColumnInfo(name = "_college_name")
    private String collegeName;

    /**
     * 系部ID
     */
    @ColumnInfo(name = "_faculty_id")
    private String facultyId;

    /**
     * 系部名称
     */
    @ColumnInfo(name = "_faculty_name")
    private String facultyName;

    /**
     * 首字母拼音
     */
    @ColumnInfo(name = "_first_letter_pin_yin")
    private String firstLetterPinYin;

    /**
     * 专业ID
     */
    @ColumnInfo(name = "_major_id")
    private String majorId;

    public static PlanStudentEntity convertToEntity(PlanStudentData data) {
        if (data == null) {
            return null;
        }
        PlanStudentEntity entity = new PlanStudentEntity();
        entity.setAccountId(data.getAccountId());
        entity.setAdmissionNum(data.getAdmissionNum());
        entity.setClassId(data.getClassId());
        entity.setClassName(data.getClassName());
        entity.setGradeId(data.getGradeId());
        entity.setGradeName(data.getGradeName());
        entity.setPlanId(data.getPlanId());
        if (data.getProjects() != null) {
            entity.setProjects(data.getProjects().stream()
                    .map(PlanSportEntity::convertToEntity)
                    .collect(Collectors.toList()));
        }
        entity.setSex(data.getSex());
        entity.setStudentCode(data.getStudentCode());
        entity.setStudentName(data.getStudentName());
        entity.setClassNum(data.getClassNum());
        entity.setGradeNum(data.getGradeNum());
        entity.setStageId(data.getStageId());
        entity.setStudentNum(data.getStudentNum());
        entity.setAccountNamePinYin(data.getAccountNamePinYin());
        entity.setCollegeId(data.getCollegeId());
        entity.setCollegeName(data.getCollegeName());
        entity.setFacultyId(data.getFacultyId());
        entity.setFacultyName(data.getFacultyName());
        entity.setFirstLetterPinYin(data.getFirstLetterPinYin());
        entity.setMajorId(data.getMajorId());
        return entity;
    }

    public static PlanStudentData convertToData(PlanStudentEntity entity) {
        if (entity == null) {
            return null;
        }
        PlanStudentData data = new PlanStudentData();
        data.setAccountId(entity.getAccountId());
        data.setAdmissionNum(entity.getAdmissionNum());
        data.setClassId(entity.getClassId());
        data.setClassName(entity.getClassName());
        data.setGradeId(entity.getGradeId());
        data.setGradeName(entity.getGradeName());
        data.setPlanId(entity.getPlanId());
        if (entity.getProjects() != null) {
            data.setProjects(entity.getProjects().stream()
                    .map(PlanSportEntity::convertToData)
                    .collect(Collectors.toList()));
        }
        data.setSex(entity.getSex());
        data.setStudentCode(entity.getStudentCode());
        data.setStudentName(entity.getStudentName());
        data.setClassNum(entity.getClassNum());
        data.setGradeNum(entity.getGradeNum());
        data.setStageId(entity.getStageId());
        data.setStudentNum(entity.getStudentNum());
        data.setAccountNamePinYin(entity.getAccountNamePinYin());
        data.setCollegeId(entity.getCollegeId());
        data.setCollegeName(entity.getCollegeName());
        data.setFacultyId(entity.getFacultyId());
        data.setFacultyName(entity.getFacultyName());
        data.setFirstLetterPinYin(entity.getFirstLetterPinYin());
        data.setMajorId(entity.getMajorId());
        return data;
    }
}
