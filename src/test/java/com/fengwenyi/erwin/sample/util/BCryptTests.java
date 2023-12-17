package com.fengwenyi.erwin.sample.util;

import cn.dev33.satoken.secure.BCrypt;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-26
 */
public class BCryptTests {

    @Test
    public void test() {
        String salt = BCrypt.gensalt();
        System.out.println("salt: " + salt);
        String plain_password = "123456";
        System.out.println("pw: " + plain_password);
        String pw_hash = BCrypt.hashpw(plain_password, salt);
        System.out.println("pw_hash: " + pw_hash);
        boolean check = BCrypt.checkpw(plain_password, pw_hash);
        System.out.println("check: " + check);
    }


}
