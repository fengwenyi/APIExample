package siample.api_result.result;

import com.fengwenyi.api_result.helper.ResultApiPageHelper;
import com.fengwenyi.api_result.helper.ResultPageHelper;
import com.fengwenyi.api_result.model.ResultApiPageModel;
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
     * @return  {@link ResultPageModel}
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
     * @return  {@link ResultApiPageModel}
     */
    public static <T> ResultApiPageModel<Integer, T> successPageApi(T data, Long totalElements, Long totalPages, Integer pageSize, Long currentPage) {
        return ResultApiPageHelper.success(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data, totalElements, totalPages, pageSize, currentPage);
    }

    /**
     * 接口数据分页返回错误信息
     * @param resultCodeEnum {@link ResultCodeEnum}
     * @return {@link ResultApiPageModel}
     */
    public static ResultApiPageModel<Integer, Void> errorPageApi(ResultCodeEnum resultCodeEnum) {
        return ResultApiPageHelper.error(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

}
