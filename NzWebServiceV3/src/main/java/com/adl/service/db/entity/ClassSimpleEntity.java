package com.adl.service.db.entity;

import com.adl.service.data.ClassSimpleData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class ClassSimpleEntity {

    /**
     * 班级ID
     */
    private String classId;

    /**
     * 年级ID
     */
    private String gradeId;

    /**
     * 班级编号
     */
    private String classNum;

    /**
     * 班级全名
     */
    private String classFullName;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 年级中文名称
     */
    private String gradeChineseName;

    public static ClassSimpleEntity convertToEntity(ClassSimpleData data) {
        if (data == null) {
            return null;
        }
        ClassSimpleEntity entity = new ClassSimpleEntity();
        entity.setClassId(data.getClassId());
        entity.setGradeId(data.getGradeId());
        entity.setClassNum(data.getClassNum());
        entity.setClassFullName(data.getClassFullName());
        entity.setClassName(data.getClassName());
        entity.setGradeChineseName(data.getGradeChineseName());
        return entity;
    }

    public static ClassSimpleData convertToData(ClassSimpleEntity entity) {
        if (entity == null) {
            return null;
        }
        ClassSimpleData data = new ClassSimpleData();
        data.setClassId(entity.getClassId());
        data.setGradeId(entity.getGradeId());
        data.setClassNum(entity.getClassNum());
        data.setClassFullName(entity.getClassFullName());
        data.setClassName(entity.getClassName());
        data.setGradeChineseName(entity.getGradeChineseName());
        return data;
    }
}
