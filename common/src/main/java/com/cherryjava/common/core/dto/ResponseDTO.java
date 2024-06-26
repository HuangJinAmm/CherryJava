package com.cherryjava.common.core.dto;

import com.cherryjava.common.exception.ApiException;
import com.cherryjava.common.exception.error.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 响应信息主体
 *
 * @author hjamm
 */
public class ResponseDTO<T> {

    private Integer code;

    private String msg;

    @JsonInclude
    private T data;

    public ResponseDTO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseDTO<T> ok() {
        return build(null, ErrorCode.SUCCESS.code(), ErrorCode.SUCCESS.message());
    }

    public static <T> ResponseDTO<T> ok(T data) {
        return build(data, ErrorCode.SUCCESS.code(), ErrorCode.SUCCESS.message());
    }

    public static <T> ResponseDTO<T> fail() {
        return build(null, ErrorCode.FAILED.code(), ErrorCode.FAILED.message());
    }

    public static <T> ResponseDTO<T> fail(T data) {
        return build(data, ErrorCode.FAILED.code(), ErrorCode.FAILED.message());
    }

    public static <T> ResponseDTO<T> fail(ApiException exception) {
        return build(null, exception.getErrorCode().code(), exception.getMessage());
    }

    public static <T> ResponseDTO<T> fail(ApiException exception, T data) {
        return build(data, exception.getErrorCode().code(), exception.getMessage());
    }

    public static <T> ResponseDTO<T> build(T data, Integer code, String msg) {
        return new ResponseDTO<>(code, msg, data);
    }

    // 去掉直接填充错误码的方式， 这种方式不能拿到i18n的错误消息  统一通过ApiException来构造错误消息
//    public static <T> ResponseDTO<T> fail(ErrorCodeInterface code, Object... args) {
//        return build(null, code, args);
//    }

}

