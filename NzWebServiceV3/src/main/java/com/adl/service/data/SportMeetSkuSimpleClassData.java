package com.adl.service.data;

import com.adl.service.internal.json.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonAdapter(StrictJsonDeserializer.class)
public final class SportMeetSkuSimpleClassData {
    @SerializedName("accounts")
    private List<SportMeetSkuAccountData> accounts;

    @SerializedName("classId")
    private String classId;

    @SerializedName("className")
    private String className;

    @SerializedName("classNum")
    private Integer classNum;

    @SerializedName("enterYear")
    private Integer enterYear;

    @SerializedName("gradeId")
    private String gradeId;

    @SerializedName("secCode")
    private Integer secCode;
}
