package com.fengwenyi.apiswaggerstarter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ApiModel(value = "用户数据模型", description = "用户基础信息")
public class UserModel {

    @ApiModelProperty(value = "用户姓名")
    @Size(max = 50)
    private String name;

    @ApiModelProperty("用户年龄")
    @Max(30)
    @Min(20)
    private Integer age;

}
