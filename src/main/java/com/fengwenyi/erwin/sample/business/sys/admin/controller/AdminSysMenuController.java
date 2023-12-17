package com.fengwenyi.erwin.sample.business.sys.admin.controller;

import com.fengwenyi.api.result.PageTemplate;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.menu.MenuDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.menu.MenuGetPageDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.menu.MenuGetTreeDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.menu.MenuUpdateDto;
import com.fengwenyi.erwin.sample.business.sys.admin.service.IAdminSysMenuService;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.menu.MenuParentOptionVo;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.menu.MenuTreeItemVo;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.menu.MenuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-14
 */
@RestController
@RequestMapping("/admin/sys/menu")
@RequiredArgsConstructor
public class AdminSysMenuController {

    private final IAdminSysMenuService adminSysMenuService;

    @GetMapping("/tree")
    public List<MenuTreeItemVo> getTree(MenuGetTreeDto menuGetTreeDto) {
        return adminSysMenuService.getTree(menuGetTreeDto);
    }

    @GetMapping("/all-tree")
    public List<MenuTreeItemVo> getAllTree() {
        return adminSysMenuService.getAllTree();
    }

    @GetMapping("/page")
    public PageTemplate<MenuVo> getPage(MenuGetPageDto dto) {
        return adminSysMenuService.getPage(dto);
    }

    @PostMapping
    public void add(@RequestBody @Validated MenuDto dto) {
        adminSysMenuService.add(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Validated MenuUpdateDto dto) {
        adminSysMenuService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        adminSysMenuService.delete(id);
    }

    @GetMapping("/parent-option-list")
    public List<MenuParentOptionVo> getParentOptionList() {
        return adminSysMenuService.getParentOptionList();
    }
}
