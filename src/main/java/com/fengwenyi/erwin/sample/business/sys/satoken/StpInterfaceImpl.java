package com.fengwenyi.erwin.sample.business.sys.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.fengwenyi.erwin.sample.business.sys.index.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final IUserService userService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return userService.queryUserRoleCodeList(Long.parseLong(loginId.toString()));
    }
}
