package com.adl.service.web.request;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public final class BaseSportImage {
    /**
     * 图片说明
     */
    @SerializedName("imageDesc")
    private String imageDesc;

    /**
     * 图片相对地址
     */
    @SerializedName("imageUrl")
    private String imageUrl;

    /**
     * 分组编号
     */
    @SerializedName("groupIndex")
    private String groupIndex;
}
