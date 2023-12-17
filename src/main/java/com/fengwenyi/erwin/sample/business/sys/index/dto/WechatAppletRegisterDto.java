package com.fengwenyi.erwin.sample.business.sys.index.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-27
 */
@Data
public class WechatAppletRegisterDto {

    @NotBlank(message = "机构编码不能为空")
    @Length(max = 64, message = "机构编码长度非法")
    private String organCode;

    @NotBlank(message = "手机号授权码不能为空")
    @Length(max = 64, message = "手机号授权码长度非法")
    private String phoneCode;

    @NotBlank(message = "登录授权码不能为空")
    @Length(max = 64, message = "登录授权码长度非法")
    private String loginCode;

}
