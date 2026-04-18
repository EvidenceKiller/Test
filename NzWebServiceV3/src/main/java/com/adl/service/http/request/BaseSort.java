package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public final class BaseSort {
    @SerializedName("direction")
    private BaseDirection direction;
    @SerializedName("column")
    private String column;
}
