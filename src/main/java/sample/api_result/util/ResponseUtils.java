package sample.api_result.util;

import com.fengwenyi.api_result.helper.ResultHelper;
import com.fengwenyi.api_result.model.ResultModel;

/**
 * 响应工具类
 * @author Erwin Feng
 * @since 2020/6/13
 */
public class ResponseUtils {

    /** 成功信息提示 */
    private static final String SUCCESS_MESSAGE = "Success";

    /**
     * 成功，并返回数据
     * @param data  数据
     * @param <T>   数据类型
     * @return  接口响应成功，并返回数据
     * @see com.fengwenyi.api_result.model.ResultModel
     */
    public static <T>ResultModel<T> success(T data) {
        return ResultHelper.success(SUCCESS_MESSAGE, data);
    }
}