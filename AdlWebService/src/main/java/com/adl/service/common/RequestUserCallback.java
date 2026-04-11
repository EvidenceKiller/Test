package com.adl.service.common;

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
public abstract class RequestUserCallback extends RequestCallback {

    public RequestUserCallback(Context context) {
        super(context);
    }

    public RequestUserCallback(Context context, boolean showDialog) {
        super(context);
        showDialog(showDialog);
    }

    // 同步信息过程中，分步处理
    public abstract void nextStatus(int status, String msg);

    //  组织发生变化
    public void orgChange(String newOrgId, String oldOrgId) {
    }

    //  更新学生数据
    public void updateStudent(List<StudentEntity> updateData, List<StudentEntity> deleteData) {
    }

    //  更新老师数据
    public void updateTeacher(List<TeacherEntity> updateData, List<TeacherEntity> deleteData) {
    }

    public void updateCitizen(List<CitizenEntity> updateData, List<CitizenEntity> deleteData) {
    }
}
