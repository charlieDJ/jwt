package com.example.boot.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * 统一返回
 *
 * @param <T>
 */
@Setter
@Getter
@SuppressWarnings("unchecked")
public class Response<T> {
    private static final String ok = "200";
    private static final String error= "600";
    private static final String noAuth = "607";

    private String code;
    private String msg;
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
        return new Response("200", "", data);
    }

    public static Response success() {
        return new Response<>(ok, "", new ArrayList<>());
    }

    public static <T> Response<T> error() {
        return new Response<>(error, null);
    }

    public static <T> Response<T> error(String msg) {
        return new Response(error, msg, new ArrayList<>());
    }

    public static <T> Response<T> auth(String msg) {
        return new Response(noAuth, msg, new ArrayList<>());
    }

    public static <T> Response<T> of(String code, String msg, T data) {
        return new Response<>(code, msg, data);
    }

    public static Response of(String code, String msg) {
        return new Response<>(code, msg, new ArrayList<>());
    }

}
