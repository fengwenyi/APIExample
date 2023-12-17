package com.fengwenyi.erwin.sample.business.sys.index.controller;

import com.fengwenyi.erwin.sample.business.sys.admin.dto.menu.MenuGetTreeDto;
import com.fengwenyi.erwin.sample.business.sys.admin.service.IAdminSysMenuService;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.menu.MenuTreeItemVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/sign-in-user")
@RequiredArgsConstructor
public class SignInUserController {

    private final IAdminSysMenuService adminSysMenuService;

    @GetMapping("/menu-tree")
    public List<MenuTreeItemVo> getMenuTree(MenuGetTreeDto menuGetTreeDto) {
        return adminSysMenuService.getTree(menuGetTreeDto);
    }

}
