package com.adl.service.web.response;

import com.adl.service.internal.StrictJsonDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonAdapter(StrictJsonDeserializer.class)
public final class CitizenPageData {

    @SerializedName("records")
    private List<Object> records;

    @SerializedName("total")
    private Long total;

    @SerializedName("size")
    private Long size;

    @SerializedName("current")
    private Long current;

    @SerializedName("orders")
    private List<Object> orders;

    @SerializedName("optimizeCountSql")
    private Object optimizeCountSql;

    @SerializedName("searchCount")
    private Object searchCount;

    @SerializedName("optimizeJoinOfCountSql")
    private Boolean optimizeJoinOfCountSql;

    @SerializedName("maxLimit")
    private Long maxLimit;

    @SerializedName("countId")
    private String countId;

    @SerializedName("pages")
    private Long pages;
}
