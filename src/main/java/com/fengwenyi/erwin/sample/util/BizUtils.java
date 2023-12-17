package com.fengwenyi.erwin.sample.util;

import java.math.BigDecimal;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-25
 */
public class BizUtils {

    public static String generateNickname() {
        int number = (int) (Math.random() * 100000);
        return "用户" + number;
    }

    public static int yuanConvertFen(BigDecimal amount) {
        return amount.multiply(new BigDecimal(100)).intValue();
    }

}
