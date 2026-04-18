package com.adl.service.http.request;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public final class BaseGroupRule {
    /**
     * 分组规则
     */
    @SerializedName("empty")
    private Boolean empty;
}
