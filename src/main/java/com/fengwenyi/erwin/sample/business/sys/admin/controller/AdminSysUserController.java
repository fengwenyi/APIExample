package com.fengwenyi.erwin.sample.business.sys.admin.controller;

import com.fengwenyi.api.result.PageTemplate;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.user.*;
import com.fengwenyi.erwin.sample.business.sys.admin.service.IAdminSysUserService;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.user.UserDetailVo;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.user.UserSimpleVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
@Tag(name = "AdminSysUserController", description = "用户管理")
@RestController
@RequestMapping("/admin/sys/user")
@RequiredArgsConstructor
public class AdminSysUserController {

    private final IAdminSysUserService adminUserService;

    @Operation(summary = "分页查询", description = "用户分页查询接口")
    @GetMapping("/page")
    public PageTemplate<UserSimpleVo> getPage(@Validated UserGetPageDto getUserPageDto) {
        return adminUserService.getPage(getUserPageDto);
    }

    @GetMapping("/{id}")
    public UserDetailVo getDetail(@PathVariable Long id) {
        return adminUserService.getDetail(id);
    }

    @PostMapping
    public void add(@RequestBody @Validated UserDto userDto) {
        adminUserService.add(userDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Validated UserUpdateDto userUpdateDto) {
        adminUserService.update(id, userUpdateDto);
    }

    @PutMapping("/{id}/password")
    public void updatePassword(@PathVariable Long id, @RequestBody @Validated UserPasswordDto userPasswordDto) {
        adminUserService.updatePassword(id, userPasswordDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        adminUserService.delete(id);
    }

    @PostMapping("/checkUsernameExist")
    public void checkUsernameExist(@RequestBody UsernameDto dto) {
        adminUserService.checkUsernameExist(dto);
    }
}
