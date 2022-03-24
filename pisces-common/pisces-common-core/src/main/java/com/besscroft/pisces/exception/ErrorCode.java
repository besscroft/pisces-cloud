package com.besscroft.pisces.exception;

import lombok.Data;

/**
 * @Description 异常错误码对象
 * @Author Bess Croft
 * @Date 2022/3/24 10:47
 */
@Data
public class ErrorCode {

    /** 错误码 */
    private final Integer code;

    /** 错误提示 */
    private final String message;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
