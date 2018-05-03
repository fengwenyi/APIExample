package com.fengwenyi.demo.api.controller;

import com.fengwenyi.demo.api.tool.ReturnCode;
import com.fengwenyi.javalib.result.Result;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wenyi Feng
 */
@Api(tags = "API接口Test")
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation(value = "method name", notes = "this is method more info")
    /*@ApiImplicitParams({@ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String"),
                        @ApiImplicitParam(name = "age", value = "年龄", required = true, dataType = "Integer")})*/
    @RequestMapping("/test")
    public String test (@ApiParam @RequestParam String name,
                        @ApiParam(name = "age", required = true, value = "AGE") @RequestParam("age") Integer age) {
        Result result = new Result();
        System.out.println(name + "/" + age + "" + "===");
        result.setResult(ReturnCode.SUCCESS);
        return new Gson().toJson(result);
    }

}
