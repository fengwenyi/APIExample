package com.fengwenyi.erwin.sample.business.sys.admin.controller;

import com.fengwenyi.api.result.ListTemplate;
import com.fengwenyi.api.result.PageTemplate;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.role.RoleDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.role.RoleGetPageDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.role.RoleUpdateDto;
import com.fengwenyi.erwin.sample.business.sys.admin.service.IAdminSysRoleService;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.role.RoleVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-10
 */
@RestController
@RequestMapping("/admin/sys/role")
@RequiredArgsConstructor
public class AdminSysRoleController {

    private final IAdminSysRoleService adminRoleService;

    @GetMapping("/page")
    public PageTemplate<RoleVo> getPage(RoleGetPageDto roleGetPageDto) {
        return adminRoleService.getPage(roleGetPageDto);
    }

    @PostMapping
    public void add(@RequestBody @Validated RoleDto roleDto) {
        adminRoleService.add(roleDto);
    }

    @GetMapping("/optionList")
    public ListTemplate<RoleVo> getOptionList() {
        return adminRoleService.getOptionList();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Validated RoleUpdateDto updateDto) {
        adminRoleService.update(id, updateDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        adminRoleService.delete(id);
    }

}
