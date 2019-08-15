package com.example.boot.common.enumeration;

import lombok.Getter;

/**
 * @author dengjia
 */
@Getter
public enum ResponseCodes {

    /**
     * 返回状态码和信息
     */
    SUCCESS("200", "请求成功"),
    PERMISSION_ERROR("401", "权限错误"),
    SYSTEM_ERROR("600", "系统错误"),
    PARAM_FORMAT_ERROR("607", "参数格式错误"),
    DATA_FORMAT_ERROR("608", "数据格式错误"),
    AUTH_CODE_ERROR("616", "验证码错误"),
    USER_ERROR("645", "用户错误"),
    TOKEN_ERROR("671", "登录信息错误"),
    UNKNOWN_ERROR("999999", "未知错误"),
    NO_INFO("724", "无信息"),
    ORDER_NOT_FOUND("677", "订单未找到"),
    ADULT_STATUS_ERROR("618", "号码状态有误");

    private String code;
    private String msg;

    ResponseCodes(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
