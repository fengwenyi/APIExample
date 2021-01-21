package com.fengwenyi.api.example.controller;

import com.fengwenyi.api.example.vo.request.UserRequestVo;
import com.fengwenyi.api.example.vo.response.UserResponseVo;
import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.api.example.data.UserData;
import com.fengwenyi.api.example.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户接口，示例
 * @author Erwin Feng
 * @since 1.0.0
 * @see <a href="https://github.com/fengwenyi/api-result">api-result</a>
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping(value = "/api/user",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    /**
     * 查询所有用户
     * @return 用户列表
     */
    @ApiOperation(value = "查询所有用户")
    @GetMapping
    public ResultTemplate<List<UserResponseVo>> users() {
        List<UserEntity> userEntityList = UserData.queryAll();
        List<UserResponseVo> userResponseVoList = userEntityList.stream()
                        .map(userEntity -> {
                            UserResponseVo userResponseVo = new UserResponseVo();
                            BeanUtils.copyProperties(userEntity, userResponseVo);
                            return userResponseVo;
                        })
                        .collect(Collectors.toList());
        return ResultTemplate.success(userResponseVoList);
    }

    /**
     * 查询指定用户
     * @param uid 用户ID
     * @return 用户
     */
    @ApiOperation(value = "查询指定用户")
    @GetMapping("/{uid}")
    public ResultTemplate<UserResponseVo> user(@PathVariable Integer uid) {
        UserEntity userEntity = UserData.query(uid);
        UserResponseVo userResponseVo = new UserResponseVo();
        BeanUtils.copyProperties(userEntity, userResponseVo);
        return ResultTemplate.success(userResponseVo);
    }

    /**
     * 添加用户
     * @param userRequestVo 用户信息
     * @return 添加成功，返回用户信息
     */
    @ApiOperation(value = "添加用户")
    @PostMapping
    public ResultTemplate<UserResponseVo> user(@Validated @RequestBody UserRequestVo userRequestVo) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userRequestVo, userEntity);
        UserData.save(userEntity);
        UserResponseVo userResponseVo = new UserResponseVo();
        BeanUtils.copyProperties(userEntity, userResponseVo);
        return ResultTemplate.success(userResponseVo);
    }

    /**
     * 修改用户部分信息
     * @param uid 用户ID
     * @param name 用户名称
     * @return 修改成功，返回用户信息
     */
    @ApiOperation(value = "修改用户部分信息")
    @PutMapping("/{uid}")
    public ResultTemplate<UserResponseVo> user(@PathVariable Integer uid, String name) {
        UserEntity userEntity = UserData.query(uid);
        userEntity.setName(name);
        UserData.save(userEntity);
        UserResponseVo userResponseVo = new UserResponseVo();
        BeanUtils.copyProperties(userEntity, userResponseVo);
        return ResultTemplate.success(userResponseVo);
    }

    /**
     * 修改用户全部信息
     * @param uid 用户ID
     * @param userRequestVo 用户信息
     * @return 修改成功，返回用户信息
     */
    @ApiOperation(value = "修改用户全部信息")
    @PatchMapping("/{uid}")
    public ResultTemplate<UserResponseVo> user(@PathVariable Integer uid, @Validated @RequestBody UserRequestVo userRequestVo) {
        UserEntity userEntity = UserData.query(uid);
        userEntity
                .setName(userRequestVo.getName())
                .setGender(userRequestVo.getGender())
                .setAge(userRequestVo.getAge());
        UserData.save(userEntity);
        UserResponseVo userResponseVo = new UserResponseVo();
        BeanUtils.copyProperties(userEntity, userResponseVo);
        return ResultTemplate.success(userResponseVo);
    }

    /**
     * 根据用户ID删除指定用户
     * @param uid 用户ID
     * @return 删除成功，或者失败
     */
    @ApiOperation(value = "根据用户ID删除指定用户")
    @DeleteMapping("/{uid}")
    public ResultTemplate<Void> delete(@PathVariable Integer uid) {
        if (Objects.isNull(uid)) {
            return ResultTemplate.fail("抱歉，用户ID不能为空");
        }
        UserData.delete(uid);
        return ResultTemplate.success();
    }

}
