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
public final class BasePageData<T> {

    @SerializedName("records")
    private List<T> records;

    @SerializedName("total")
    private Long total;

    @SerializedName("size")
    private Long size;

    @SerializedName("current")
    private Long current;

    @SerializedName("pages")
    private Long pages;
}
