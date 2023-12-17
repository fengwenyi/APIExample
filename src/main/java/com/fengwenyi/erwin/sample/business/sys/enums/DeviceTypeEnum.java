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
public enum DeviceTypeEnum {

    ANDROID("Android", "安卓"),
    IOS("iOS", "平台"),
    WECHAT_APPLET("wechatApplet", "微信小程序"),
    MANAGEMENT_PLATFORM("managementPlatform", "管理平台"),

    ;

    private final String code;
    private final String msg;

    public static DeviceTypeEnum getByCode(String code) {

        if (!StringUtils.hasText(code)) {
            return null;
        }

        for (DeviceTypeEnum authType : values()) {
            if (authType.code.equals(code)) {
                return authType;
            }
        }

        return null;

    }

}
