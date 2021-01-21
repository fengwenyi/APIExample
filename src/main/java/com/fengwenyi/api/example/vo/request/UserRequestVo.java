package com.fengwenyi.api.example.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Erwin Feng
 * @since 2020/4/11 21:53
 */
@Data
public class UserRequestVo {

    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不能为空")
    @Min(value = 1, message = "用户ID不能小于1")
    private Integer uid;

    @ApiModelProperty("姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty("性别")
    @NotBlank(message = "性别不能为空")
    private String gender;

    @ApiModelProperty("年龄")
    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "年龄不能小于0")
    private Integer age;

}
