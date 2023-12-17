package com.fengwenyi.erwin.sample.business.sys.strategy;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.fengwenyi.erwin.sample.business.sys.entity.UserEntity;
import com.fengwenyi.erwin.sample.business.sys.index.dto.LoginDto;
import com.fengwenyi.erwin.sample.business.sys.index.vo.LoginVo;
import com.fengwenyi.erwin.sample.enums.ResultEnum;
import com.fengwenyi.erwin.sample.exception.BizException;
import com.fengwenyi.erwin.sample.service.IRedisService;
import com.fengwenyi.erwin.sample.util.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-03
 */
public abstract class LoginStrategy {

    protected IRedisService redisService;

    protected abstract UserEntity queryUser(String account);

    protected abstract void checkCertificate(String certificate);

    private LoginDto loginDto;

    public void setLoginDto(LoginDto loginDto) {
        this.loginDto = loginDto;
    }

    public LoginVo login() {

        UserEntity userEntity = queryUser(loginDto.getAccount());

        checkUserAllowLogin(userEntity);

        checkCertificate(loginDto.getCertificate());

        StpUtil.login(userEntity.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(tokenInfo.tokenValue);

        return loginVo;

    }

    private void checkUserAllowLogin(UserEntity userEntity) {

        if (Objects.isNull(userEntity)) {
            throw new BizException(ResultEnum.USER_LOGIN_FAILED);
        }

        if (!BooleanUtils.isTrue(userEntity.getStatus())) {
            throw new BizException(ResultEnum.USER_DISABLED);
        }

        if (BooleanUtils.isTrue(userEntity.getLocked())) {
            throw new BizException(ResultEnum.USER_LOCKED);
        }

    }

    protected void checkVerifyCode(String cacheKey, String certificate) {
        String verifyCode = redisService.get(cacheKey);
        if (!StringUtils.hasText(verifyCode)) {
            throw new BizException(ResultEnum.USER_LOGIN_FAILED, "登录失败：验证码已失效");
        }
        if (!verifyCode.equals(certificate)) {
            throw new BizException(ResultEnum.USER_LOGIN_FAILED, "登录失败：验证码不正确");
        }
    }

    @Autowired
    public void setRedisService(IRedisService redisService) {
        this.redisService = redisService;
    }
}
