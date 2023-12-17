package com.fengwenyi.erwin.sample.business.sys.strategy;

import com.fengwenyi.erwin.sample.business.sys.enums.AuthTypeEnum;
import com.fengwenyi.erwin.sample.business.sys.index.dto.LoginDto;
import com.fengwenyi.erwin.sample.business.sys.strategy.impl.EmailVerifyCodeLoginStrategyImpl;
import com.fengwenyi.erwin.sample.business.sys.strategy.impl.PasswordLoginStrategyImpl;
import com.fengwenyi.erwin.sample.enums.ResultEnum;
import com.fengwenyi.erwin.sample.exception.BizException;
import com.fengwenyi.erwin.sample.support.SpringContextSupport;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-03
 */
@Slf4j
public class LoginStrategyFactory {

    public static LoginStrategy getStrategy(LoginDto loginDto) {
        LoginStrategy loginStrategy;
        AuthTypeEnum authTypeEnum = AuthTypeEnum.getByCode(loginDto.getAuthType());
        if (Objects.equals(AuthTypeEnum.PASSWORD, authTypeEnum)) {
             loginStrategy = getBean(PasswordLoginStrategyImpl.class);
        } else if (Objects.equals(AuthTypeEnum.EMAIL_VERIFY_CODE, authTypeEnum)) {
            loginStrategy = getBean(EmailVerifyCodeLoginStrategyImpl.class);
        } else {
            log.error("未找到对应的认证类型：{}", loginDto.getAuthType());
            throw new BizException(ResultEnum.USER_LOGIN_FAILED, "登录失败");
        }
        loginStrategy.setLoginDto(loginDto);
        return loginStrategy;
    }

    private static <T> T getBean(Class<T> clazz) {
        return SpringContextSupport.getBean(clazz);
    }

}
