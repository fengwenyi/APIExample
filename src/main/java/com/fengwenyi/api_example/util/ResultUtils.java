package com.fengwenyi.api_example.util;

import com.fengwenyi.api_result.helper.ResultHelper;
import com.fengwenyi.api_result.model.ResultModel;

/**
 * 返回结果封装工具类
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/11/30 13:54
 */
public class ResultUtils {

    /**
     *  成功
     * @return {@link ResultModel}
     */
    public static ResultModel <?> success() {
        return ResultHelper.success("Success");
    }

    /**
     *  成功，携带数据
     * @param data 数据
     * @param <T>  数据的类型
     * @return {@link ResultModel}
     */
    public static <T> ResultModel <T> success(T data) {
        return ResultHelper.success("Success", data);
    }

    /**
     *  错误，携带详细的错误描述信息
     * @param message 详细的错误描述信息
     * @return {@link ResultModel}
     */
    public static ResultModel <?> error(String message) {
        return ResultHelper.error(message);
    }

}
