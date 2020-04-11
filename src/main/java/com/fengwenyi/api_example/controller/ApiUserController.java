package com.fengwenyi.api_example.controller;

import com.fengwenyi.api_example.data.UserData;
import com.fengwenyi.api_example.entity.UserEntity;
import com.fengwenyi.api_example.util.ResultUtils;
import com.fengwenyi.api_example.vo.param.UserParamVO;
import com.fengwenyi.api_result.model.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口
 * @author Erwin Feng
 * @since 2020/4/11 21:43
 */
@Api(tags = "用户接口")
@Slf4j
@RestController
@RequestMapping(value = "/api/user",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class ApiUserController {

    /**
     * 查询所有用户
     * @param paramVO {@link UserParamVO}，过滤条件
     * @return {@link ResultModel}，用户数组
     */
    @ApiOperation(value = "查询所有用户")
    @GetMapping
    public ResultModel<?> users(@Validated UserParamVO paramVO) {
        log.info(paramVO.toString());
        return ResultUtils.success(UserData.getUsers());
    }

    /**
     * 查询指定用户
     * @param uid 用户ID
     * @return {@link ResultModel}，用户对象
     */
    @ApiOperation(value = "查询指定用户")
    @GetMapping("/{uid}")
    public ResultModel<?> user(@PathVariable Integer uid) {
        return ResultUtils.success(UserData.get(uid));
    }

    /**
     * 添加用户
     * @param paramVO {@link UserParamVO}
     * @return {@link ResultModel}，添加成功，返回用户信息
     */
    @ApiOperation(value = "添加用户")
    @PostMapping
    public ResultModel<?> user(@Validated @RequestBody UserParamVO paramVO) {
        UserEntity userEntity = new UserEntity()
                .setUid(paramVO.getId())
                .setName(paramVO.getName())
                .setSex(paramVO.getSex())
                .setAge(paramVO.getAge());
        UserData.set(userEntity);
        return ResultUtils.success(userEntity);
    }

    /**
     * 修改用户部分信息
     * @param uid 用户ID
     * @param name 用户名称
     * @return {@link ResultModel}，修改成功，返回用户信息
     */
    @ApiOperation(value = "修改用户部分信息")
    @PutMapping("/{uid}")
    public ResultModel<?> user(@PathVariable Integer uid, String name) {
        UserEntity userEntity = UserData.get(uid);
        userEntity.setName(name);
        UserData.set(userEntity);
        return ResultUtils.success(userEntity);
    }

    /**
     * 修改用户全部信息
     * @param uid 用户ID
     * @param paramVO {@link UserParamVO}
     * @return {@link ResultModel}，修改成功，返回用户信息
     */
    @ApiOperation(value = "修改用户全部信息")
    @PatchMapping("/{uid}")
    public ResultModel<?> user(@PathVariable Integer uid, @Validated @RequestBody UserParamVO paramVO) {
        UserEntity userEntity = UserData.get(uid);
        userEntity
                .setName(paramVO.getName())
                .setSex(paramVO.getSex())
                .setAge(paramVO.getAge());
        UserData.set(userEntity);
        return ResultUtils.success(uid);
    }

    /**
     * 删除指定用户
     * @param uid 用户ID
     * @return {@link ResultModel}
     */
    @ApiOperation(value = "删除指定用户")
    @DeleteMapping("/{uid}")
    public ResultModel<?> delete(@PathVariable Integer uid) {
        UserData.delete(uid);
        return ResultUtils.success();
    }

}
