package siample.api_result.business.impl;

import com.fengwenyi.api_result.model.ResultPageApiModel;
import com.fengwenyi.api_result.model.ResultPageModel;
import org.springframework.stereotype.Service;
import siample.api_result.business.GoodsBusiness;
import siample.api_result.result.ResultPageUtils;

/**
 * @author Erwin Feng
 * @since 2020/5/23
 */
@Service
public class GoodsBusinessImpl implements GoodsBusiness {
    @Override
    public ResultPageModel<?> page(Long currentPage) {
        return ResultPageUtils.success("test string", 1L, 1L, 1, 1L);
    }

    @Override
    public ResultPageApiModel<Integer, ?> apiPage(Long currentPage) {
        return ResultPageUtils.successPageApi("test string", 1L, 1L, 1, 1L);
    }
}
