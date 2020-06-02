package sample.api_result.result;

import com.fengwenyi.api_result.helper.ResultApiHelper;
import com.fengwenyi.api_result.model.ResultApiModel;

/**
 * 接口响应结果封装工具类
 * @author Erwin Feng
 * @since 2020/5/26
 */
public class ResultUtils {

    /**
     * 成功，无数据
     * @return {@link ResultApiModel}
     */
    public static ResultApiModel<Integer, Void> success() {
        return ResultApiHelper.success(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 成功，携带数据
     * @param data 响应数据
     * @param <T>  响应数据类型
     * @return {@link ResultApiModel}
     */
    public static <T> ResultApiModel<Integer, T> success(T data) {
        return ResultApiHelper.success(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 失败，返回具体的错误码和详细的错误信息
     * @param resultCodeEnum 返回码枚举类，{@link ResultCodeEnum}
     * @return {@link ResultApiModel}
     */
    public static ResultApiModel<Integer, Void> error(ResultCodeEnum resultCodeEnum) {
        return ResultApiHelper.error(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

}
