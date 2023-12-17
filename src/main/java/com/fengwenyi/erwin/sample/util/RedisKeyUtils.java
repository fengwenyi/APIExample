package com.fengwenyi.erwin.sample.util;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
public class RedisKeyUtils {

    public static String emailVerifyCode(String email) {
        return String.format("windRunner:verifyCode:email:%s:string", email);
    }

}
