package com.fengwenyi.erwin.sample.business.sys.strategy.impl;

import com.fengwenyi.erwin.sample.business.sys.entity.UserEntity;
import com.fengwenyi.erwin.sample.business.sys.index.service.IUserService;
import com.fengwenyi.erwin.sample.business.sys.strategy.LoginStrategy;
import com.fengwenyi.erwin.sample.enums.ResultEnum;
import com.fengwenyi.erwin.sample.exception.BizException;
import com.fengwenyi.erwin.sample.util.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-03
 */
@Service
@RequiredArgsConstructor
public class PasswordLoginStrategyImpl extends LoginStrategy {

    private final IUserService userService;

    private UserEntity userEntity;

    @Override
    protected UserEntity queryUser(String account) {
        this.userEntity = userService.queryByUsername(account);
        return userEntity;
    }

    @Override
    protected void checkCertificate(String certificate) {
        if (!PasswordUtils.check(certificate, userEntity.getPassword())) {
            throw new BizException(ResultEnum.USER_LOGIN_FAILED, "登录失败：用户名或密码不正确");
        }
    }

}
