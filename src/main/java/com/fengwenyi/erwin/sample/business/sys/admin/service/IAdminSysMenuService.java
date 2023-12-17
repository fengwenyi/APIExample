package com.fengwenyi.erwin.sample.business.sys.admin.service;

import com.fengwenyi.api.result.PageTemplate;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.menu.MenuDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.menu.MenuGetPageDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.menu.MenuGetTreeDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.menu.MenuUpdateDto;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.menu.MenuParentOptionVo;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.menu.MenuTreeItemVo;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.menu.MenuVo;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-14
 */
public interface IAdminSysMenuService {

    List<MenuTreeItemVo> getTree(MenuGetTreeDto menuGetTreeDto);

    PageTemplate<MenuVo> getPage(MenuGetPageDto dto);

    void add(MenuDto dto);

    void update(Long id, MenuUpdateDto dto);

    void delete(Long id);

    List<MenuTreeItemVo> getAllTree();

    List<MenuParentOptionVo> getParentOptionList();

}
