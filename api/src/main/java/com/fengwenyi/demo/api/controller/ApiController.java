package com.fengwenyi.demo.api.controller;

import com.fengwenyi.demo.api.tool.ReturnCode;
import com.fengwenyi.javalib.result.Result;
import com.google.gson.Gson;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wenyi Feng
 */
@Api(tags = "API接口Demo")
@RestController
@RequestMapping("/api")
public class ApiController {

    // get
    @ApiOperation(value = "获取用户", notes = "获取用户")
    @GetMapping("/get-user")
    public UserModel getUser () {
        UserModel userModel = new UserModel();
        userModel.name = "zhansan";
        userModel.age = 25;
        return userModel;
    }

    // post
    @ApiOperation(value = "添加用户", notes = "通过用户姓名和年龄添加用户")
    @PostMapping("/add-user")
    public String addUser (@ApiParam(name = "name", value = "姓名", required = true) @RequestParam("name") String name,
                           @ApiParam(name = "age", value = "AGE", required = true) @RequestParam("age") Integer age) {
        Result result = new Result();
        result.setData(ReturnCode.SUCCESS, name + " / " + age);
        return new Gson().toJson(result);
    }

    // put
    @ApiOperation(value = "修改用户", notes = "通过用户ID修改用户数据")
    @PutMapping("/update-user/{id}")
    public String updateUserById (@ApiParam(name = "id", value = "ID", required = true)
                                        @PathVariable("id") Integer id,
                                  @ApiParam(name = "name", value = "姓名", required = false)
                                        @RequestParam(value = "name", required = false) String name,
                                  @ApiParam(name = "age", value = "年龄", required = false)
                                        @RequestParam(value = "age", required = false) Integer age) {
        Result result = new Result();
        result.setData(ReturnCode.SUCCESS, id + " / " + name + " / " + age);
        return new Gson().toJson(result);
    }

    // delete
    @ApiOperation(value = "删除用户", notes = "通过用户ID删除用户数据")
    @DeleteMapping("/delete-user/{id}")
    public String deleteUserById (@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") Integer id) {
        Result result = new Result();
        result.setData(ReturnCode.SUCCESS, id);
        return new Gson().toJson(result);
    }

}
