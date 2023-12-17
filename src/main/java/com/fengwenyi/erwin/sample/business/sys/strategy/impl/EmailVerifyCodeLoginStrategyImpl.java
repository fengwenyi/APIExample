package com.fengwenyi.erwin.sample.business.sys.strategy.impl;

import com.fengwenyi.erwin.sample.business.sys.entity.UserEntity;
import com.fengwenyi.erwin.sample.business.sys.index.service.IUserService;
import com.fengwenyi.erwin.sample.business.sys.strategy.LoginStrategy;
import com.fengwenyi.erwin.sample.util.RedisKeyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-03
 */
@Service
@RequiredArgsConstructor
public class EmailVerifyCodeLoginStrategyImpl extends LoginStrategy {

    private final IUserService userService;
    private UserEntity userEntity;

    @Override
    protected UserEntity queryUser(String account) {
        this.userEntity = userService.queryByEmail(account);
        return userEntity;
    }

    @Override
    protected void checkCertificate(String certificate) {
        String cacheKey = RedisKeyUtils.emailVerifyCode(userEntity.getEmail());
        checkVerifyCode(cacheKey, certificate);
    }
}
