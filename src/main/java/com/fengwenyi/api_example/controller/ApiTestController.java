package com.fengwenyi.api_example.controller;

import com.fengwenyi.api_example.util.ResultUtils;
import com.fengwenyi.api_result.helper.ResultHelper;
import com.fengwenyi.api_result.model.ResultModel;
import com.fengwenyi.javalib.util.PrintUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Api Test
 * @author Erwin Feng
 * @since 2019-06-04 20:41
 */
@Api(tags = "测试接口示例")
@RestController
@RequestMapping(
        value = "/api/test",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiTestController {

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
     * 查询方法示例
     * @return {@link ResultModel}
     */
    @ApiOperation(value = "查询方法示例")
    @GetMapping
    public ResultModel<?> get() {
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

        return ResultUtils.success(list);
    }

}
