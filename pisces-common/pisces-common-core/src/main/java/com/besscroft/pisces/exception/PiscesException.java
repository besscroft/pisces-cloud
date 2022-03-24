package com.besscroft.pisces.exception;

import com.besscroft.pisces.constant.HttpStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 自定义全局异常
 * @Author Bess Croft
 * @Date 2022/3/24 10:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PiscesException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    /** 错误码 */
    private Integer code;

    /** 错误提示 */
    private String message;

    /**
     * 空构造方法，避免反序列化问题
     */
    public PiscesException() {
    }

    public PiscesException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public PiscesException(String message) {
        this.code = HttpStatus.ERROR;
        this.message = message;
    }

    public PiscesException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public PiscesException setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public PiscesException setMessage(String message) {
        this.message = message;
        return this;
    }

}
