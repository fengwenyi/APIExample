package com.fengwenyi.demo.api.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * <类说明>
 * <p>
 * <功能详细描述>
 *
 * @author Wenyi Feng
 * @since 2018-08-28
 */
@Component
@ApiModel(value = "用户数据模型", description = "用户基础信息")
public class UserModel {

    @ApiModelProperty(value = "用户姓名")
    @Size(max = 50)
     String name;

    @ApiModelProperty("用户年龄")
    @Max(30)
    @Min(20)
     Integer age;

}
