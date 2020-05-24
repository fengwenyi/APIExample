package siample.api_result.business;

import com.fengwenyi.api_result.model.ResultApiPageModel;
import com.fengwenyi.api_result.model.ResultPageModel;

/**
 * @author Erwin Feng
 * @since 2020/5/23
 */
public interface GoodsBusiness {

    /**
     * 分页示例
     * @param currentPage 当前页
     * @return {@link ResultPageModel}
     */
    ResultPageModel<?> page(Long currentPage);

    /**
     * 接口数据分页 示例
     * @param currentPage 当前页
     * @return {@link ResultApiPageModel}
     */
    ResultApiPageModel<Integer, ?> apiPage(Long currentPage);

}
