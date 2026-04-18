package com.adl.service.data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回结构
 * 对应后端 JSON：
 * <pre>
 * {
 *   "code": 0,
 *   "message": "success",
 *   "data": { ... }
 * }
 * </pre>
 *
 * @param <T> 业务数据类型
 */
@Data
@NoArgsConstructor
public final class BaseResponse<T> {

    /**
     * 业务状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 是否成功标志位
     */
    private Boolean succeed;

    /**
     * 业务数据
     */
    private T data;
}
