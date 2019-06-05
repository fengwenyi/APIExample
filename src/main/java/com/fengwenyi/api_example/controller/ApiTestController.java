package com.fengwenyi.api_example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.iutil.ApiResult;
import net.iutil.javalib.util.PrintUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    // 增
    @ApiOperation(value = "添加方法示例")
    @PostMapping
    public ApiResult add() {
        PrintUtils.info(" api-test -> add");
        return ApiResult.success();
    }


    // 删
    @ApiOperation(value = "删除方法示例")
    @DeleteMapping
    public ApiResult delete() {
        PrintUtils.info(" api-test -> delete");
        return ApiResult.success();
    }

    // 改
    @ApiOperation(value = "修改方法示例")
    @PutMapping
    public ApiResult update() {
        PrintUtils.info(" api-test -> update");
        return ApiResult.success();
    }

    // 查
    @ApiOperation(value = "查询方法示例")
    @GetMapping
    public ApiResult get() {
        PrintUtils.info(" api-test -> get");
        return ApiResult.success();
    }

}
