package com.fengwenyi.erwin.sample.business.sys.strategy.impl;

import com.fengwenyi.erwin.sample.business.sys.entity.UserEntity;
import com.fengwenyi.erwin.sample.business.sys.strategy.LoginStrategy;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-27
 */
@Service
public class WechatAppletLoginStrategyImpl extends LoginStrategy {
    @Override
    protected UserEntity queryUser(String account) {
        return null;
    }

    @Override
    protected void checkCertificate(String certificate) {

    }



}
