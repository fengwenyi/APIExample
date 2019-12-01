package com.fengwenyi.api_example.controller;

import com.fengwenyi.api_example.util.ApiResultUtils;
import com.fengwenyi.api_example.util.PageResultUtils;
import com.fengwenyi.api_example.util.ResultUtils;
import com.fengwenyi.api_result.helper.ResultHelper;
import com.fengwenyi.api_result.model.ApiResultModel;
import com.fengwenyi.api_result.model.PageResultModel;
import com.fengwenyi.api_result.model.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回结果工具类测试接口示例
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019/12/1 20:37
 * @see <a href="https://github.com/fengwenyi/api-result">api-result</a>
 */
@Api(tags = "返回结果工具类测试接口示例")
@RestController
@RequestMapping(
        value = "/api/test/result",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiTestResultController {

    /**
     * 成功示例
     * @return {@link ResultModel}
     */
    @ApiOperation(value = "成功示例")
    @GetMapping("/success")
    public ResultModel<?> success() {
        return ResultUtils.success();
    }

    /**
     * 添加方法示例
     * @return {@link ResultModel}
     */
    @ApiOperation(value = "添加方法示例")
    @PostMapping
    public ResultModel<?> add() {
        return ResultHelper.success("添加成功");
    }


    /**
     * 删除方法示例
     * @return {@link ResultModel}
     */
    @ApiOperation(value = "删除方法示例")
    @DeleteMapping
    public ResultModel<?> delete() {
        return ResultUtils.success();
    }

    /**
     * 修改方法示例
     * @return {@link ResultModel}
     */
    @ApiOperation(value = "修改方法示例")
    @PutMapping
    public ResultModel<?> update() {
        return ResultUtils.error("修改失败");
    }

    /**
     * 查询结果返回示例
     * @return {@link ResultModel}
     */
    @ApiOperation(value = "查询结果返回示例")
    @GetMapping
    public ResultModel<?> get() {
        return ResultUtils.success(getData());
    }

    /**
     * API接口返回数据示例
     * @return {@link ApiResultModel}
     */
    @ApiOperation(value = "API接口返回数据示例")
    @GetMapping("/api-data")
    public ApiResultModel<Integer, ?> apiData() {
        return ApiResultUtils.success(getData());
    }

    /**
     * API接口返回错误信息示例
     * @return {@link ApiResultModel}
     */
    @ApiOperation(value = "API接口返回错误信息示例")
    @GetMapping("/api-error")
    public ApiResultModel<Integer, ?> apiError() {
        return ApiResultUtils.error(1101, "API接口错误返回示例");
    }

    /**
     * 分页返回数据示例
     * @return {@link ApiResultModel}
     */
    @ApiOperation(value = "分页返回数据示例")
    @GetMapping("/page")
    public PageResultModel<?> page() {
        return PageResultUtils.success(getData(), 100, 10, 10, 1);
    }

    /**
     *  获取数据示例
     * @return {@link List}
     */
    private List<?> getData() {

        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();

        map1.put("name", "张飞");
        map1.put("desc", "燕人张飞");
        list.add(map1);

        Map<String, String> map2 = new HashMap<>();
        map2.put("name", "赵云");
        map2.put("desc", "常山赵子龙");
        list.add(map2);

        Map<String, String> map3 = new HashMap<>();
        map3.put("name", "关羽");
        map3.put("desc", "温酒斩华雄");
        list.add(map3);

        return list;
    }

}
