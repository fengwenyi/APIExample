package siample.api_result.business;

import com.fengwenyi.api_result.model.ResultPageApiModel;
import com.fengwenyi.api_result.model.ResultPageModel;

/**
 * @author Erwin Feng
 * @since 2020/5/23
 */
public interface GoodsBusiness {

    ResultPageModel<?> page(Long currentPage);

    ResultPageApiModel<Integer, ?> apiPage(Long currentPage);

}
