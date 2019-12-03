package com.fengwenyi.api_example.exception;

/**
 * 数据解析异常
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/4 00:19
 */
public class DataParseException extends RuntimeException {

    public DataParseException(String message) {
        super(message);
    }
}
