package com.fengwenyi.erwin.sample.business.sys.admin.dto.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-10
 */
@Data
public class UserUpdateDto {

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
