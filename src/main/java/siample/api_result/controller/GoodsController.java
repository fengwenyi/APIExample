package siample.api_result.controller;

import com.fengwenyi.api_result.model.ResultPageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siample.api_result.business.GoodsBusiness;

/**
 * 商品接口
 * @author Erwin Feng
 * @since 2020/5/23
 */
@RestController
@RequestMapping(value = "/goods",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class GoodsController {

    @Autowired
    private GoodsBusiness goodsBusiness;

    // 分页
    @GetMapping("/page/{currentPage}")
    public ResultPageModel<?> page(@PathVariable Long currentPage) {
        return goodsBusiness.page(currentPage);
    }

}
