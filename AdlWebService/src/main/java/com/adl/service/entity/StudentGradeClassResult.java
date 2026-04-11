package com.adl.service.entity;

import androidx.room.ColumnInfo;

public class StudentGradeClassResult extends StudentClassResult {

    // 年级id
    @ColumnInfo(name = "_grade_id")
    private String gradeId;

    //  班级id
    @ColumnInfo(name = "_class_id")
    private String classId;

    // 年级班级中文名称(eg:八年级一班)
    @ColumnInfo(name = "_grade_stage_chinese_name",defaultValue = "")
    private String gradeStageChineseName;

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getGradeStageChineseName() {
        return gradeStageChineseName;
    }

    public void setGradeStageChineseName(String gradeStageChineseName) {
        this.gradeStageChineseName = gradeStageChineseName;
    }
}
