package com.fengwenyi.erwin.sample.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-10
 */
public class BizUtilsTests {

    @Test
    public void testGenerateNickname() {
        String nickname = BizUtils.generateNickname();
        System.out.println(nickname);
    }

    @Test
    public void testWechatAppletV3ExpireTimeFormat() {
        LocalDateTime localDateTime = LocalDateTime.now();
//        String pattern = "yyyy-MM-dd'T'HH:mm:ssXXX";
//        String pattern = "uuuu-MM-dd'T'HH:mm:ssXXX";
//        String result = DateTimeUtils.format(localDateTime, pattern);
//        System.out.println(result);

//        String result = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(localDateTime);
//        System.out.println(result);

//        String result = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date());
//        System.out.println(result);

//        String str = "2019-09-30T10:05:16+10:00";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ssXXX");
//        OffsetDateTime datetime = OffsetDateTime.parse(str, formatter);
//        System.out.println(datetime);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ssXXX");
//        formatter.format()

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ssXXX");
//        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneId.systemDefault().getRules().getOffset(localDateTime));
        OffsetDateTime now = OffsetDateTime.now();
        String result = now.format(formatter);
        System.out.println(result);

    }

}
