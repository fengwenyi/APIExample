package com.fengwenyi.erwin.sample.enums;

import com.fengwenyi.api.result.IResult;
import lombok.AllArgsConstructor;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-25
 */
@AllArgsConstructor
public enum ResultEnum implements IResult {

    PARAM_MISS("PARAM_MISS", "参数缺失"),
    PARAM_VALIDATED("PARAM_VALIDATED", "参数校验失败"),

    SYSTEM_EXCEPTION("SYSTEM_EXCEPTION", "系统发生异常，请稍后重试"),

    USER_DISABLED("USER_DISABLED", "用户被禁用"),
    USER_LOCKED("USER_LOCKED", "用户被锁定"),
    USER_LOGIN_FAILED("USER_LOGIN_FAILED", "登录失败：帐号或凭证不正确"),
    USER_EXISTS("USER_EXISTS", "用户已存在"),
    USER_NOT_ROLE("USER_NOT_ROLE", "用户没有角色"),

    AUTH_FAILED("AUTH_FAILED", "认证失败：请重新登录"),

    ROLE_EXISTS("ROLE_EXISTS", "角色已存在"),

    DATA_NOT_EXIST("DATA_NOT_EXIST", "数据不存在"),
    DATA_EXIST("DATA_EXIST", "数据已存在"),


    PARAM_EXCEPTION("PARAM_EXCEPTION", "参数异常"),

    BIZ_FAILED("BIZ_FAILED", "业务失败"),

    FAILED("FAILED", "失败"),

    ;

    private final String code;
    private final String msg;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
