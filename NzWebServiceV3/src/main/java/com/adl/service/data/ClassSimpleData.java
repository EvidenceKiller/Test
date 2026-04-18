package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class ClassSimpleData {

    /**
     * 班级ID
     */
    @SerializedName("classId")
    private String classId;

    /**
     * 年级ID
     */
    @SerializedName("gradeId")
    private String gradeId;

    /**
     * 班级编号
     */
    @SerializedName("classNum")
    private String classNum;

    /**
     * 班级全名
     */
    @SerializedName("classFullName")
    private String classFullName;

    /**
     * 班级名称
     */
    @SerializedName("className")
    private String className;

    /**
     * 年级中文名称
     */
    @SerializedName("gradeChineseName")
    private String gradeChineseName;
}
