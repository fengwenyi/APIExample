package com.fengwenyi.api.example.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Erwin Feng
 * @since 2020/4/11 21:53
 */
@Data
@Accessors(chain = true)
@ApiModel
public class UserResponseVo {

    @ApiModelProperty("用户ID")
    private Integer uid;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("年龄")
    private Integer age;

}
