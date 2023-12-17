package com.fengwenyi.erwin.sample.util;

import cn.dev33.satoken.secure.BCrypt;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-26
 */
public class PasswordUtils {

    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean check(String plaintext, String hashed) {
        return BCrypt.checkpw(plaintext, hashed);
    }

    private static final String lowStr = "abcdefghijklmnopqrstuvwxyz";
    private static final String specialStr = "~!@#$%^&*()_+/-=[]{};:'<>?.";
    private static final String numStr = "0123456789";

    // 随机获取字符串字符
    private static char getRandomChar(String str) {
        SecureRandom random = new SecureRandom();
        return str.charAt(random.nextInt(str.length()));
    }

    // 随机获取小写字符
    private static char getLowChar() {
        return getRandomChar(lowStr);
    }

    // 随机获取大写字符
    private static char getUpperChar() {
        return Character.toUpperCase(getLowChar());
    }

    // 随机获取数字字符
    private static char getNumChar() {
        return getRandomChar(numStr);
    }

    // 随机获取特殊字符
    private static char getSpecialChar() {
        return getRandomChar(specialStr);
    }

    //指定调用字符函数
    private static char getRandomChar(int funNum) {
        return switch (funNum) {
            case 0 -> getLowChar();
            case 1 -> getUpperChar();
            case 2 -> getNumChar();
            default -> getSpecialChar();
        };
    }

    // 指定长度，随机生成复杂密码
    public static String generate(int num) {
        if (num > 20 || num < 8) {
            System.out.println("长度不满足要求");
            return "";
        }
        List<Character> list = new ArrayList<>(num);
        list.add(getLowChar());
        list.add(getUpperChar());
        list.add(getNumChar());
        list.add(getSpecialChar());

        for (int i = 4; i < num; i++) {
            SecureRandom random = new SecureRandom();
            int funNum = random.nextInt(4);
            list.add(getRandomChar(funNum));
        }

        Collections.shuffle(list);   // 打乱排序
        StringBuilder stringBuilder = new StringBuilder(list.size());
        for (Character c : list) {
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }

}
