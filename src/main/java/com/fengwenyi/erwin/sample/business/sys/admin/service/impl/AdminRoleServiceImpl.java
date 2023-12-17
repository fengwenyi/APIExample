package com.fengwenyi.erwin.sample.business.sys.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengwenyi.api.result.ListTemplate;
import com.fengwenyi.api.result.PageTemplate;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.role.RoleDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.role.RoleGetPageDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.role.RoleUpdateDto;
import com.fengwenyi.erwin.sample.business.sys.admin.service.IAdminSysRoleService;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.role.RoleVo;
import com.fengwenyi.erwin.sample.business.sys.entity.MenuEntity;
import com.fengwenyi.erwin.sample.business.sys.entity.RoleEntity;
import com.fengwenyi.erwin.sample.business.sys.entity.RoleMenuEntity;
import com.fengwenyi.erwin.sample.business.sys.entity.UserRoleEntity;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpMenuService;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpRoleMenuService;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpRoleService;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpUserRoleService;
import com.fengwenyi.erwin.sample.enums.ResultEnum;
import com.fengwenyi.erwin.sample.exception.BizException;
import com.fengwenyi.erwin.sample.util.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-10
 */
@Service
@RequiredArgsConstructor
public class AdminRoleServiceImpl implements IAdminSysRoleService {

    private final IMpRoleService mpRoleService;
    private final IMpUserRoleService mpUserRoleService;
    private final IMpRoleMenuService mpRoleMenuService;
    private final IMpMenuService mpMenuService;

    @Override
    public PageTemplate<RoleVo> getPage(RoleGetPageDto pageDto) {

        LambdaQueryWrapper<RoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(pageDto.getRoleName())) {
            queryWrapper.like(RoleEntity::getRoleName, pageDto.getRoleName());
        }

        queryWrapper.orderByDesc(RoleEntity::getSortNum);
        queryWrapper.orderByAsc(RoleEntity::getId);

        Page<RoleEntity> pageResult = mpRoleService.page(
                new Page<>(pageDto.getCurrent(), pageDto.getPageSize()),
                queryWrapper
        );
        PageTemplate<RoleVo> pageTemplate = PageUtils.convert(pageResult);

        if (!CollectionUtils.isEmpty(pageResult.getRecords())) {
            pageTemplate.setContent(
                    pageResult.getRecords()
                            .stream()
                            .map(t -> {
                                RoleVo vo = entityConvertVo(t);

                                List<Long> ruleMenuIds = mpRoleMenuService.list(
                                        new LambdaQueryWrapper<RoleMenuEntity>()
                                                .eq(RoleMenuEntity::getRoleId, t.getId())
                                ).stream().map(RoleMenuEntity::getMenuId).toList();

                                List<Long> menuIds = new ArrayList<>(ruleMenuIds);

                                if (!CollectionUtils.isEmpty(ruleMenuIds)) {
                                    for (Long menuId : ruleMenuIds) {
                                        List<Long> childrenMenuIds = mpMenuService.list(
                                                new LambdaQueryWrapper<MenuEntity>()
                                                        .eq(MenuEntity::getParentId, menuId)
                                                        .eq(MenuEntity::getStatus, true)
                                        ).stream().map(MenuEntity::getId).toList();;
                                        if (!CollectionUtils.isEmpty(childrenMenuIds)) {
                                            if (!new HashSet<>(ruleMenuIds).containsAll(childrenMenuIds)) {
                                                menuIds.remove(menuId);
                                            }
                                        }
                                    }
                                }

                                if (!CollectionUtils.isEmpty(menuIds)) {
                                    vo.setMenuIds(menuIds.stream().map(String::valueOf).toList());
                                }

                                return vo;
                            })
                            .collect(Collectors.toList())
            );
        }

        return pageTemplate;
    }

    @Override
    public void add(RoleDto dto) {

        boolean exists = mpRoleService.exists(
                new LambdaQueryWrapper<RoleEntity>()
                        .eq(RoleEntity::getRoleCode, dto.getRoleCode())
        );

        if (exists) {
            throw new BizException(ResultEnum.ROLE_EXISTS);
        }

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleCode(dto.getRoleCode());
        roleEntity.setRoleName(dto.getRoleName());
        roleEntity.setSortNum(dto.getSortNum());
        roleEntity.setStatus(dto.getStatus());

        mpRoleService.save(roleEntity);

        // 菜单
        saveRoleMenu(roleEntity.getId(), dto.getMenuIds());
    }

    @Override
    public ListTemplate<RoleVo> getOptionList() {
        List<RoleEntity> list = mpRoleService.list(
                new LambdaQueryWrapper<RoleEntity>()
                        .eq(RoleEntity::getStatus, true)
                        .orderByDesc(RoleEntity::getSortNum)
        );
        ListTemplate<RoleVo> listTemplate = new ListTemplate<>();
        if (!CollectionUtils.isEmpty(list)) {
            listTemplate.setContent(
                    list
                            .stream()
                            .map(this::entityConvertVo)
                            .collect(Collectors.toList())
            );
        }
        return listTemplate;
    }

    @Override
    public void update(Long id, RoleUpdateDto updateDto) {
        RoleEntity roleEntity = mpRoleService.getById(id);
        if (Objects.isNull(roleEntity)) {
            throw new BizException(ResultEnum.DATA_NOT_EXIST);
        }
        roleEntity.setStatus(updateDto.getStatus());
        roleEntity.setRoleName(updateDto.getRoleName());
        roleEntity.setSortNum(updateDto.getSortNum());
        roleEntity.setStatus(updateDto.getStatus());
        mpRoleService.updateById(roleEntity);

        mpRoleMenuService.remove(
                new LambdaQueryWrapper<RoleMenuEntity>()
                        .eq(RoleMenuEntity::getRoleId, id)
        );

        saveRoleMenu(id, updateDto.getMenuIds());
    }

    @Override
    public void delete(Long id) {
        mpUserRoleService.remove(
                new LambdaQueryWrapper<UserRoleEntity>()
                        .eq(UserRoleEntity::getRoleId, id)
        );
        mpRoleService.removeById(id);
    }

    private RoleVo entityConvertVo(RoleEntity entity) {
        RoleVo roleVo = new RoleVo();
        roleVo.setId(String.valueOf(entity.getId()));
        roleVo.setRoleCode(entity.getRoleCode());
        roleVo.setRoleName(entity.getRoleName());
        roleVo.setStatus(entity.getStatus());
        roleVo.setSortNum(entity.getSortNum());
        return roleVo;
    }

    /**
     * 保存角色菜单关联关系
     */
    private void saveRoleMenu(Long roleId, List<Long> menuIds) {

        if (Objects.isNull(roleId) || CollectionUtils.isEmpty(menuIds)) {
            return;
        }

        for (Long menuId : menuIds) {
            RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
            roleMenuEntity.setRoleId(roleId);
            roleMenuEntity.setMenuId(menuId);
            mpRoleMenuService.save(roleMenuEntity);

            // 处理不含父节点的问题
            MenuEntity menuEntity = mpMenuService.getById(menuId);
            if (Objects.nonNull(menuEntity)) {
                Long parentId = menuEntity.getParentId();
                if (Objects.nonNull(parentId) && !menuIds.contains(parentId)) {
                    RoleMenuEntity roleParentMenuEntity = new RoleMenuEntity();
                    roleParentMenuEntity.setRoleId(roleId);
                    roleParentMenuEntity.setMenuId(parentId);
                    mpRoleMenuService.save(roleParentMenuEntity);
                }
            }
        }
    }
}
