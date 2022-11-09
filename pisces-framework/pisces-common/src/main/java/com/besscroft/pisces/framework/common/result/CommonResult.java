package com.besscroft.pisces.framework.common.result;

import com.besscroft.pisces.framework.common.constant.HttpStatus;
import lombok.Data;

/**
 * @Description 服务调用通用返回对象
 * @Author Bess Croft
 * @Date 2022/2/2 21:49
 */
@Data
public class CommonResult<T> {

    /** 状态码 */
    private int code;

    /** 消息 */
    private String message;

    /** 追踪id */
    private String traceId;

    /** 数据对象 */
    private T data;

    /**
     * 初始化一个新创建的 CommonResult 对象，使其表示一个空消息。
     */
    public CommonResult() {
    }

    /**
     * 初始化一个新创建的 CommonResult 对象
     * @param code 状态码
     * @param message 消息提示
     * @param data 数据对象
     */
    public CommonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 初始化一个新创建的 CommonResult 对象
     * @param code 状态码
     * @param message 消息提示
     * @param data 数据对象
     * @param traceId 追踪id
     */
    public CommonResult(int code, String message, T data, String traceId) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.traceId = traceId;
    }

    /**
     * 成功封装方法
     * @return 成功消息
     */
    public static CommonResult success() {
        return success("调用成功！");
    }

    /**
     * 成功封装方法
     * @param message 消息提示
     * @param <T>
     * @return 成功消息
     */
    public static <T> CommonResult<T> success(String message) {
        return new CommonResult<T>(HttpStatus.SUCCESS, message, null);
    }

    /**
     * 成功封装方法
     * @param data 数据对象
     * @param <T>
     * @return 成功消息
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(HttpStatus.SUCCESS, "调用成功！", data);
    }

    /**
     * 成功封装方法
     * @param message 消息提示
     * @param data 数据对象
     * @param <T>
     * @return 成功消息
     */
    public static <T> CommonResult<T> success(String message, T data) {
        return new CommonResult<T>(HttpStatus.SUCCESS, message, data);
    }

    /**
     * 失败封装方法
     * @return 错误消息
     */
    public static CommonResult failed() {
        return failed("调用失败");
    }

    /**
     * 失败封装方法
     * @param message 消息提示
     * @param <T>
     * @return 错误消息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(HttpStatus.ERROR, message, null);
    }

    /**
     * 失败封装方法
     * @param message 消息提示
     * @param data 数据对象
     * @param <T>
     * @return 错误消息
     */
    public static <T> CommonResult<T> failed(String message, T data) {
        return new CommonResult<T>(HttpStatus.ERROR, message, data);
    }

    /**
     * 失败封装方法
     * @param data 数据对象
     * @param <T>
     * @return 错误消息
     */
    public static <T> CommonResult<T> failed(T data) {
        return new CommonResult<T>(HttpStatus.ERROR, "调用失败！", data);
    }

    /**
     * 失败封装方法
     * @param code 数据对象
     * @param message 消息提示
     * @param <T>
     * @return 错误消息
     */
    public static <T> CommonResult<T> failed(int code, String message) {
        return new CommonResult<T>(HttpStatus.ERROR, message, null);
    }

    /**
     * 失败封装方法
     * @param code 数据对象
     * @param message 消息提示
     * @param data 数据对象
     * @param <T>
     * @return 错误消息
     */
    public static <T> CommonResult<T> failed(int code, String message, T data, String traceId) {
        return new CommonResult<T>(HttpStatus.ERROR, message, data, traceId);
    }

}
