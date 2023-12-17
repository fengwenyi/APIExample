package com.fengwenyi.erwin.sample.business.sys.admin.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-10
 */
@Data
public class UserPasswordDto {

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 32, message = "密码长度非法")
    private String password;

}
