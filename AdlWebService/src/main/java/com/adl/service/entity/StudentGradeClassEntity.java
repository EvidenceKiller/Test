package com.adl.service.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Describe   :
 * Author     : zhoumiqi
 * Date       : 2025/2/14
 */
public class StudentGradeClassEntity implements Serializable {

    private List<StudentClassEntity> classList;
    private String id;
    private String gradeChineseName;
    private String gradeStageChineseName;

    public List<StudentClassEntity> getClassList() {
        return classList;
    }

    public void setClassList(List<StudentClassEntity> classList) {
        this.classList = classList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGradeChineseName() {
        return gradeChineseName;
    }

    public void setGradeChineseName(String gradeChineseName) {
        this.gradeChineseName = gradeChineseName;
    }

    public String getGradeStageChineseName() {
        return gradeStageChineseName;
    }

    public void setGradeStageChineseName(String gradeStageChineseName) {
        this.gradeStageChineseName = gradeStageChineseName;
    }
}
