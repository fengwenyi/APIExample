package com.fengwenyi.erwin.sample.business.sys.index.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-25
 */
@Data
public class LoginDto {

    @NotBlank(message = "帐号不能为空")
    @Length(max = 64, message = "帐号长度不合法")
    private String account;

    @NotBlank(message = "凭证不能为空")
    @Length(max = 64, message = "凭证长度不合法")
    private String certificate;

    @NotBlank(message = "认证类型不能为空")
    @Length(max = 32, message = "认证类型长度不合法")
    private String authType;

    @NotBlank(message = "设备类型不能为空")
    @Length(max = 32, message = "设备类型长度不合法")
    private String deviceType;

}
