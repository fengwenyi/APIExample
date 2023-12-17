package com.fengwenyi.erwin.sample.business.sys.admin.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-09
 */
@Data
public class UserDto {

    @NotBlank(message = "用户名不能为空")
    @Length(max = 32, message = "用户名长度非法")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(max = 32, message = "密码长度非法")
    private String password;

    @Length(max = 32, message = "邮箱长度非法")
    private String email;

    @Length(max = 20, message = "手机号长度非法")
    private String phone;

    @Length(max = 64, message = "昵称长度非法")
    private String nickname;

    private Boolean status;

    private List<Long> roleIds;

    private List<String> organCodes;

}
