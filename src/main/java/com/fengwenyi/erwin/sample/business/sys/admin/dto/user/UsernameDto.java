package com.fengwenyi.erwin.sample.business.sys.admin.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-28
 */
@Data
public class UsernameDto {

    @NotBlank(message = "用户名不能为空")
    @Length(max = 64, message = "用户名长度非法")
    private String username;

}
