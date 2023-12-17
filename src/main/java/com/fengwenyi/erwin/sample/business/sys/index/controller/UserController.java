package com.fengwenyi.erwin.sample.business.sys.index.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.fengwenyi.erwin.sample.business.sys.index.service.IUserService;
import com.fengwenyi.erwin.sample.business.sys.index.vo.UserInfoVo;
import com.fengwenyi.erwin.sample.business.sys.index.vo.UserPhoneVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-25
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/permission-list")
    public List<String> getPermissionList() {
        return userService.getPermissionList();
    }

    @GetMapping("/info")
    public UserInfoVo getUserInfo() {
        return userService.getUserInfo();
    }

    @GetMapping("/phone")
    public UserPhoneVo getUserPhone() {
        return userService.getUserPhone();
    }

    @PostMapping("/logout")
    @SaIgnore
    public void logout() {
        userService.logout();
    }
}
