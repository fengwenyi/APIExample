package com.fengwenyi.api_example.util;

import com.fengwenyi.api_result.helper.ResultApiHelper;
import com.fengwenyi.api_result.model.ResultApiModel;

/**
 * API接口返回结果工具类
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/1 20:10
 */
public class ApiResultUtils {

    /**
     * 成功，携带返回码和描述信息
     * @return {@link ResultApiModel}
     */
    public static ResultApiModel<Integer, ?> success() {
        return ResultApiHelper.success(0, "Success");
    }

    /**
     * 成功，携带返回码、描述信息和数据
     * @param data 数据
     * @param <T>  数据的类型
     * @return {@link ResultApiModel}
     */
    public static <T> ResultApiModel<Integer, T> success(T data) {
        return ResultApiHelper.success(0, "Success", data);
    }

    /**
     * 出错，携带错误吗和详细描述信息
     * @param code 返回码
     * @param message 相信描述信息
     * @return {@link ResultApiModel}
     */
    public static ResultApiModel<Integer, Void> error(int code, String message) {
        return ResultApiHelper.error(code, message);
    }

}
