package siample.api_result.result;

import com.fengwenyi.api_result.helper.ResultPageHelper;
import com.fengwenyi.api_result.model.ResultPageModel;

/**
 * @author Erwin Feng
 * @since 2020/5/23
 */
public class ResultPageUtils {

    /**
     * 正确返回
     * @param data
     * @param totalElements
     * @param totalPages
     * @param pageSize
     * @param currentPage
     * @param <T>
     * @return
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

}
