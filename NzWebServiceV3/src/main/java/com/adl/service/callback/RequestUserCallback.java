package com.adl.service.callback;

import android.content.Context;

import com.adl.service.entity.CitizenEntity;
import com.adl.service.entity.StudentEntity;
import com.adl.service.entity.TeacherEntity;

import java.util.List;

/**
 * ProjectName: AiPowerAuth
 * Author     : 南山
 * Date       : 2024/7/3
 * Describe   : 类描述
 */
public interface RequestUserCallback extends RequestCallback {

    // 同步信息过程中，分步处理
    void nextStatus(int status, String msg);

    //  组织发生变化
    void orgChange(String newOrgId, String oldOrgId);

    //  更新学生数据
    void updateStudent(List<StudentEntity> updateData, List<StudentEntity> deleteData);

    //  更新老师数据
    void updateTeacher(List<TeacherEntity> updateData, List<TeacherEntity> deleteData);

    void updateCitizen(List<CitizenEntity> updateData, List<CitizenEntity> deleteData);
}
