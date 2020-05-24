package siample.api_result.result;

import com.fengwenyi.api_result.helper.ResultPageApiHelper;
import com.fengwenyi.api_result.helper.ResultPageHelper;
import com.fengwenyi.api_result.model.ResultPageApiModel;
import com.fengwenyi.api_result.model.ResultPageModel;

/**
 * @author Erwin Feng
 * @since 2020/5/23
 */
public class ResultPageUtils {

    /**
     * 数据分页返回数据及分页信息
     * @param data          数据
     * @param totalElements 总条数
     * @param totalPages    总页数
     * @param pageSize      分页大小
     * @param currentPage   当前页
     * @param <T>           数据类型
     * @return  {@link ResultPageApiModel}
     */
    public static <T> ResultPageModel<T> success(T data, Long totalElements, Long totalPages, Integer pageSize, Long currentPage) {
        return ResultPageHelper.success("Success", data, totalElements, totalPages, pageSize, currentPage);
    }

    /**
     * 错误
     * @param message
     * @return
     */
    public static ResultPageModel<?> error(String message) {
        return ResultPageHelper.error(message);
    }

    /**
     * 接口数据分页返回数据及分页信息
     * @param data          数据
     * @param totalElements 总条数
     * @param totalPages    总页数
     * @param pageSize      分页大小
     * @param currentPage   当前页
     * @param <T>           数据类型
     * @return  {@link ResultPageApiModel}
     */
    public static <T> ResultPageApiModel<Integer, T> successPageApi(T data, Long totalElements, Long totalPages, Integer pageSize, Long currentPage) {
        return ResultPageApiHelper.success(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data, totalElements, totalPages, pageSize, currentPage);
    }

    /**
     * 接口数据分页返回错误信息
     * @param resultCodeEnum {@link ResultCodeEnum}
     * @return {@link ResultPageApiModel}
     */
    public static ResultPageApiModel<Integer, Void> errorPageApi(ResultCodeEnum resultCodeEnum) {
        return ResultPageApiHelper.error(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

}
