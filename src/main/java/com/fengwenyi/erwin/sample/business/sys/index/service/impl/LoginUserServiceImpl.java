package com.fengwenyi.erwin.sample.business.sys.index.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.fengwenyi.erwin.sample.business.sys.enums.RoleEnum;
import com.fengwenyi.erwin.sample.business.sys.index.service.ILoginUserService;
import com.fengwenyi.erwin.sample.business.sys.index.service.IUserService;
import com.fengwenyi.erwin.sample.enums.ResultEnum;
import com.fengwenyi.erwin.sample.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-22
 */
@Service
@RequiredArgsConstructor
public class LoginUserServiceImpl implements ILoginUserService {

    private final IUserService userService;

    @Override
    public boolean judgeSuperAdmin() {
        long userId = getUserId();
        List<String> userRoleCodeList = userService.queryUserRoleCodeList(userId);
        if (CollectionUtils.isEmpty(userRoleCodeList)) {
            return false;
        }
        return userRoleCodeList.contains(RoleEnum.SUPER_ADMIN.name());
    }

    @Override
    public long getUserId() {
        Object loginId = StpUtil.getLoginId();
        if (Objects.isNull(loginId)) {
            throw new BizException(ResultEnum.AUTH_FAILED);
        }
        return Long.parseLong(loginId.toString());
    }
}
