package com.example.boot.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 统一返回
 *
 * @author dj
 * @param <T>
 */
@Setter
@Getter
public class Response<T> {
    private static final String OK = "200";
    private static final String ERROR = "600";
    private static final String NO_AUTH = "607";
    @ApiModelProperty(value = "响应码", example = "200")
    private String code;
    @ApiModelProperty(value = "响应消息", example = "成功")
    private String msg;
    @ApiModelProperty(value = "响应体")
    private T data;

    public Response() {
    }

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static <T> Response<T> success(T data) {
        return new Response<>("200", "success", data);
    }

    public static <T> Response<T> success() {
        return new Response<>(OK, "success", null);
    }

    public static <T> Response<T> error() {
        return new Response<>(ERROR, null);
    }

    public static Response error(String msg) {
        return new Response<>(ERROR, msg, null);
    }

    public static Response auth(String msg) {
        return new Response<>(NO_AUTH, msg, null);
    }

    public static <T> Response<T> of(String code, String msg, T data) {
        return new Response<>(code, msg, data);
    }

    public static Response of(String code, String msg) {
        return new Response<>(code, msg, null);
    }

}
