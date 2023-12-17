package com.fengwenyi.erwin.sample.business.sys.admin.service;

import com.fengwenyi.api.result.ListTemplate;
import com.fengwenyi.api.result.PageTemplate;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.role.RoleDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.role.RoleGetPageDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.role.RoleUpdateDto;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.role.RoleVo;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-10
 */
public interface IAdminSysRoleService {
    PageTemplate<RoleVo> getPage(RoleGetPageDto roleGetPageDto);


    void add(RoleDto roleDto);

    ListTemplate<RoleVo> getOptionList();

    void update(Long id, RoleUpdateDto updateDto);

    void delete(Long id);
}
