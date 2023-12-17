package com.fengwenyi.erwin.sample.business.sys.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengwenyi.api.result.PageTemplate;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.menu.MenuDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.menu.MenuGetPageDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.menu.MenuGetTreeDto;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.menu.MenuUpdateDto;
import com.fengwenyi.erwin.sample.business.sys.admin.service.IAdminSysMenuService;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.menu.MenuParentOptionVo;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.menu.MenuTreeItemVo;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.menu.MenuVo;
import com.fengwenyi.erwin.sample.business.sys.entity.MenuEntity;
import com.fengwenyi.erwin.sample.business.sys.entity.RoleMenuEntity;
import com.fengwenyi.erwin.sample.business.sys.entity.UserRoleEntity;
import com.fengwenyi.erwin.sample.business.sys.index.service.ILoginUserService;
import com.fengwenyi.erwin.sample.business.sys.index.service.IUserService;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpMenuService;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpRoleMenuService;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpUserRoleService;
import com.fengwenyi.erwin.sample.enums.ResultEnum;
import com.fengwenyi.erwin.sample.exception.BizException;
import com.fengwenyi.erwin.sample.util.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-14
 */
@Service
@RequiredArgsConstructor
public class AdminSysMenuServiceImpl implements IAdminSysMenuService {

    private final IMpMenuService mpMenuService;
    private final IMpRoleMenuService mpRoleMenuService;
    private final IUserService userService;
    private final ILoginUserService loginUserService;
    private final IMpUserRoleService mpUserRoleService;

    @Override
    public List<MenuTreeItemVo> getTree(MenuGetTreeDto menuGetTreeDto) {

        long userId = loginUserService.getUserId();

        List<UserRoleEntity> userRoleEntityList = mpUserRoleService.list(
                new LambdaQueryWrapper<UserRoleEntity>()
                        .eq(UserRoleEntity::getUserId, userId)
        );

        if (CollectionUtils.isEmpty(userRoleEntityList)) {
            throw new BizException(ResultEnum.USER_NOT_ROLE, "你还没有角色权限，请联系管理员");
        }

        List<Long> roleIds = userRoleEntityList.stream().map(UserRoleEntity::getRoleId).toList();

        List<RoleMenuEntity> roleMenuEntityList = mpRoleMenuService.list(
                new LambdaQueryWrapper<RoleMenuEntity>()
                         .in(RoleMenuEntity::getRoleId, roleIds)
        );

        if (CollectionUtils.isEmpty(roleMenuEntityList)) {
            throw new BizException(ResultEnum.USER_NOT_ROLE, "你还没有权限，请联系管理员");
        }

        List<Long> menuIdList = roleMenuEntityList.stream().map(RoleMenuEntity::getMenuId).toList();

        List<MenuEntity> list = mpMenuService.list(
                new LambdaQueryWrapper<MenuEntity>()
                        .in(MenuEntity::getId, menuIdList)
                        .eq(MenuEntity::getStatus, true)
                        .orderByDesc(MenuEntity::getSortNum)
        );

//        ListTemplate<MenuVo> listTemplate = new ListTemplate<>();

        List<MenuTreeItemVo> treeList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(list)) {
            List<MenuTreeItemVo> menuVoList = new ArrayList<>();
            for (MenuEntity menuEntity : list) {
                menuVoList.add(entityConvertTreeItemVo(menuEntity));
            }
            treeList = listToTree(menuVoList);
            //listTemplate.setContent(treeList);
        }

        return treeList;
    }

    @Override
    public PageTemplate<MenuVo> getPage(MenuGetPageDto dto) {

        LambdaQueryWrapper<MenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(dto.getChineseName())) {
            queryWrapper.like(MenuEntity::getChineseName, dto.getChineseName());
        }
        queryWrapper.orderByDesc(MenuEntity::getSortNum);
        queryWrapper.orderByAsc(MenuEntity::getId);

        Page<MenuEntity> pageResult = mpMenuService.page(
                new Page<>(dto.getCurrent(), dto.getPageSize()),
                queryWrapper
        );

        PageTemplate<MenuVo> pageTemplate = PageUtils.convert(pageResult);
        if (!CollectionUtils.isEmpty(pageResult.getRecords())) {
            pageTemplate.setContent(
                    pageResult.getRecords().stream().map(this::entityConvertVo).toList()
            );
        }

        return pageTemplate;
    }

    @Override
    public void add(MenuDto dto) {

        MenuEntity entity = new MenuEntity();
        entity.setMenuName(dto.getName());
        entity.setMenuPath(dto.getPath());
        entity.setComponent(dto.getComponent());
        entity.setTitle(dto.getTitle());
        entity.setAffix(dto.getAffix());
        entity.setIcon(dto.getIcon());
        entity.setHideMenu(dto.getHideMenu());
        entity.setIgnoreKeepAlive(dto.getIgnoreKeepAlive());
        entity.setCurrentActiveMenu(dto.getCurrentActiveMenu());
        entity.setRedirect(dto.getRedirect());
        entity.setSortNum(dto.getSortNum());

        if (Objects.nonNull(dto.getParentId())) {
            entity.setParentId(dto.getParentId());
        }

        entity.setChineseName(dto.getChineseName());
        entity.setStatus(dto.getStatus());

        mpMenuService.save(entity);
    }

    @Override
    public void update(Long id, MenuUpdateDto dto) {

        MenuEntity entity = mpMenuService.getById(id);

        entity.setMenuName(dto.getName());
        entity.setMenuPath(dto.getPath());
        entity.setComponent(dto.getComponent());
        entity.setTitle(dto.getTitle());
        entity.setAffix(dto.getAffix());
        entity.setIcon(dto.getIcon());
        entity.setRedirect(dto.getRedirect());
        entity.setSortNum(dto.getSortNum());
        entity.setHideMenu(dto.getHideMenu());
        entity.setIgnoreKeepAlive(dto.getIgnoreKeepAlive());
        entity.setCurrentActiveMenu(dto.getCurrentActiveMenu());

        if (Objects.nonNull(dto.getParentId())) {
            entity.setParentId(dto.getParentId());
        }

        entity.setChineseName(dto.getChineseName());
        entity.setStatus(dto.getStatus());

        mpMenuService.updateById(entity);
    }

    @Override
    public void delete(Long id) {
        mpMenuService.removeById(id);
    }

    @Override
    public List<MenuTreeItemVo> getAllTree() {
        List<MenuEntity> list = mpMenuService.list(
                new LambdaQueryWrapper<MenuEntity>()
                        .eq(MenuEntity::getStatus, true)
                        .orderByDesc(MenuEntity::getSortNum)
        );

        List<MenuTreeItemVo> treeList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(list)) {
            List<MenuTreeItemVo> menuVoList = new ArrayList<>();
            for (MenuEntity menuEntity : list) {
                menuVoList.add(entityConvertTreeItemVo(menuEntity));
            }
            treeList = listToTree(menuVoList);
            //listTemplate.setContent(treeList);
        }

        return treeList;
    }

    @Override
    public List<MenuParentOptionVo> getParentOptionList() {
        List<MenuEntity> list = mpMenuService.list(
                new LambdaQueryWrapper<MenuEntity>()
                        .isNull(MenuEntity::getParentId)
                        .orderByDesc(MenuEntity::getSortNum)
                        .orderByAsc(MenuEntity::getId)
        );
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.stream()
                .map(t -> {
                    MenuParentOptionVo vo = new MenuParentOptionVo();
                    vo.setId(t.getId() + "");
                    vo.setChineseName(t.getChineseName());
                    return vo;
                })
                .toList();
    }

    private List<MenuTreeItemVo> listToTree(List<MenuTreeItemVo> list) {
        // 最终树
        List<MenuTreeItemVo> treeList = new ArrayList<>();
        // 筛选跟节点
        List<MenuTreeItemVo> rootList = list.stream().filter(vo -> !StringUtils.hasText(vo.getParentId())).toList();
        // 寻找子节点
         rootList.forEach(tree -> treeList.add(findChildren(tree, list)));
        return treeList;
    }

    private MenuTreeItemVo findChildren(MenuTreeItemVo tree, List<MenuTreeItemVo> list) {
        list.stream().filter(node -> tree.getId().equals(node.getParentId())).toList().forEach(
                node -> {
                    if (tree.getChildren() == null) {
                        tree.setChildren(new ArrayList<>());
                    }
                    tree.getChildren().add(findChildren(node, list));
                }
        );
        return tree;
    }

    private MenuTreeItemVo entityConvertTreeItemVo(MenuEntity menuEntity) {
        MenuTreeItemVo treeItemVo = new MenuTreeItemVo();
        treeItemVo.setId(String.valueOf(menuEntity.getId()));
        if (Objects.nonNull(menuEntity.getParentId())) {
            treeItemVo.setParentId(String.valueOf(menuEntity.getParentId()));
        }
        treeItemVo.setPath(menuEntity.getMenuPath());
        treeItemVo.setName(menuEntity.getMenuName());
        treeItemVo.setComponent(menuEntity.getComponent());
        treeItemVo.setRedirect(menuEntity.getRedirect());

        MenuTreeItemVo.Meta meta = new MenuTreeItemVo.Meta();
        meta.setIcon(menuEntity.getIcon());
        meta.setAffix(menuEntity.getAffix());
        meta.setTitle(menuEntity.getTitle());
        meta.setHideMenu(menuEntity.getHideMenu());
        meta.setIgnoreKeepAlive(menuEntity.getIgnoreKeepAlive());
        meta.setCurrentActiveMenu(menuEntity.getCurrentActiveMenu());

        treeItemVo.setMeta(meta);
        treeItemVo.setSortNum(menuEntity.getSortNum());
        treeItemVo.setChineseName(menuEntity.getChineseName());
        treeItemVo.setStatus(menuEntity.getStatus());
        return treeItemVo;
    }

    private MenuVo entityConvertVo(MenuEntity menuEntity) {
        MenuVo vo = new MenuVo();
        vo.setId(String.valueOf(menuEntity.getId()));
        if (Objects.nonNull(menuEntity.getParentId())) {
            vo.setParentId(String.valueOf(menuEntity.getParentId()));
        }
        vo.setPath(menuEntity.getMenuPath());
        vo.setName(menuEntity.getMenuName());
        vo.setComponent(menuEntity.getComponent());
        vo.setRedirect(menuEntity.getRedirect());
        vo.setIcon(menuEntity.getIcon());
        vo.setAffix(menuEntity.getAffix());
        vo.setTitle(menuEntity.getTitle());
        vo.setHideMenu(menuEntity.getHideMenu());
        vo.setIgnoreKeepAlive(menuEntity.getIgnoreKeepAlive());
        vo.setCurrentActiveMenu(menuEntity.getCurrentActiveMenu());
        vo.setSortNum(menuEntity.getSortNum());
        vo.setChineseName(menuEntity.getChineseName());
        vo.setStatus(menuEntity.getStatus());
        return vo;
    }

}
