package com.fengwenyi.erwin.sample.business.sys.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-03
 */
@Getter
@RequiredArgsConstructor
public enum AuthTypeEnum {

    PASSWORD("password", "密码认证"),
    IMAGE_VERIFY_CODE("imageVerifyCode", "图片验证码"),
    EMAIL_VERIFY_CODE("emailVerifyCode", "邮箱验证码"),
    PHONE_VERIFY_CODE("phoneVerifyCode", "手机验证码"),

    ;

    private final String code;
    private final String msg;

    public static AuthTypeEnum getByCode(String code) {

        if (!StringUtils.hasText(code)) {
            return null;
        }

        for (AuthTypeEnum authType : values()) {
            if (authType.code.equals(code)) {
                return authType;
            }
        }

        return null;

    }

}
