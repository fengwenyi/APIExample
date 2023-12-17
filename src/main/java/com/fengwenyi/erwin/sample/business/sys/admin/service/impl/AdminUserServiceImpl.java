package com.fengwenyi.erwin.sample.business.sys.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengwenyi.api.result.PageTemplate;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.user.*;
import com.fengwenyi.erwin.sample.business.sys.admin.service.IAdminSysUserService;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.role.RoleVo;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.user.UserDetailVo;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.user.UserSimpleVo;
import com.fengwenyi.erwin.sample.business.sys.entity.RoleEntity;
import com.fengwenyi.erwin.sample.business.sys.entity.UserEntity;
import com.fengwenyi.erwin.sample.business.sys.entity.UserRoleEntity;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpRoleService;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpUserRoleService;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpUserService;
import com.fengwenyi.erwin.sample.enums.ResultEnum;
import com.fengwenyi.erwin.sample.exception.BizException;
import com.fengwenyi.erwin.sample.util.BizUtils;
import com.fengwenyi.erwin.sample.util.PageUtils;
import com.fengwenyi.erwin.sample.util.PasswordUtils;
import com.fengwenyi.javalib.convert.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements IAdminSysUserService {

    private final IMpUserService mpUserService;
    private final IMpRoleService mpRoleService;
    private final IMpUserRoleService mpUserRoleService;

    @Override
    public PageTemplate<UserSimpleVo> getPage(UserGetPageDto getUserPageDto) {

        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        String username = getUserPageDto.getUsername();
        if (StringUtils.hasText(username)) {
            queryWrapper.eq(UserEntity::getUsername, username);
        }
        String nickname = getUserPageDto.getNickname();
        if (StringUtils.hasText(nickname)) {
            queryWrapper.like(UserEntity::getNickname, nickname);
        }
        queryWrapper.orderByDesc(UserEntity::getId);

        Page<UserEntity> pageResult = mpUserService.page(
                new Page<>(getUserPageDto.getCurrent(), getUserPageDto.getPageSize()),
                queryWrapper
        );

        PageTemplate<UserSimpleVo> pageTemplate = PageUtils.convert(pageResult);

        if (!CollectionUtils.isEmpty(pageResult.getRecords())) {
            pageTemplate.setContent(
                    pageResult.getRecords()
                            .stream()
                            .map(t -> {
                                UserSimpleVo userVo = new UserSimpleVo();
                                userVo.setId(String.valueOf(t.getId()));
                                userVo.setUsername(t.getUsername());
                                userVo.setEmail(t.getEmail());
                                userVo.setPhone(t.getPhone());
                                userVo.setNickname(t.getNickname());
                                userVo.setRegisterTime(DateTimeUtils.format(t.getCreateDateTime(), DateTimeUtils.DATE_TIME));
                                userVo.setStatus(t.getStatus());

                                List<UserRoleEntity> userRoleEntityList = mpUserRoleService.list(
                                        new LambdaQueryWrapper<UserRoleEntity>()
                                                .eq(UserRoleEntity::getUserId, t.getId())
                                );
                                List<RoleVo> roleVoList = new ArrayList<>();
                                for (UserRoleEntity userRoleEntity : userRoleEntityList) {
                                    RoleEntity roleEntity = mpRoleService.getById(userRoleEntity.getRoleId());
                                    if (Objects.nonNull(roleEntity) && roleEntity.getStatus()) {
                                        RoleVo roleVo = new RoleVo();
                                        roleVo.setId(String.valueOf(roleEntity.getId()));
                                        roleVo.setRoleCode(roleEntity.getRoleCode());
                                        roleVo.setRoleName(roleEntity.getRoleName());
                                        roleVo.setStatus(roleEntity.getStatus());
                                        roleVo.setSortNum(roleEntity.getSortNum());
                                        roleVoList.add(roleVo);
                                    }
                                }
                                userVo.setRoleVoList(roleVoList);

                                if (!CollectionUtils.isEmpty(roleVoList)) {
                                    StringJoiner roleNameJoiner = new StringJoiner("，");
                                    List<String> roleIds = new ArrayList<>();
                                    for (RoleVo roleVo : roleVoList) {
                                        roleNameJoiner.add(roleVo.getRoleName());
                                        roleIds.add(roleVo.getId());
                                    }
                                    userVo.setRoleNames(roleNameJoiner.toString());

                                    userVo.setRoleIds(roleIds);
                                }

                                return userVo;
                            })
                            .collect(Collectors.toList())
            );
        }

        return pageTemplate;
    }

    @Override
    public UserDetailVo getDetail(Long id) {
        UserEntity userEntity = mpUserService.getById(id);
        UserDetailVo userVo = new UserDetailVo();
        userVo.setId(String.valueOf(userEntity.getId()));
        userVo.setUsername(userEntity.getUsername());
        userVo.setEmail(userEntity.getEmail());
        userVo.setPhone(userEntity.getPhone());
        userVo.setNickname(userEntity.getNickname());
        userVo.setRegisterTime(DateTimeUtils.format(userEntity.getCreateDateTime(), DateTimeUtils.DATE_TIME));
        return userVo;
    }

    @Override
    public void add(UserDto userDto) {

        String username = userDto.getUsername();

        boolean judgeUsernameExists = mpUserService.exists(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getUsername, username)
        );
        if (judgeUsernameExists) {
            throw new BizException(ResultEnum.USER_EXISTS, "操作失败：用户名已存在");
        }

        checkEmail(userDto.getEmail());

        checkPhone(userDto.getPhone());

        String nickname = StringUtils.hasText(userDto.getNickname())
                ? userDto.getNickname() : BizUtils.generateNickname();

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(PasswordUtils.hash(userDto.getPassword()));
        userEntity.setNickname(nickname);
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPhone(userDto.getPhone());
        userEntity.setStatus(userDto.getStatus());

        mpUserService.save(userEntity);

        // 角色
        saveUserRole(userEntity.getId(), userDto.getRoleIds());
    }

    @Override
    public void update(Long id, UserUpdateDto userUpdateDto) {
        UserEntity userEntity = mpUserService.getById(id);

        String email = userUpdateDto.getEmail();
        if (StringUtils.hasText(email)) {
            if (!email.equals(userEntity.getEmail())) {
                checkEmail(email);
                userEntity.setEmail(email);
            }
        }

        String phone = userUpdateDto.getPhone();
        if (StringUtils.hasText(phone)) {
            if (!phone.equals(userEntity.getPhone())) {
                checkPhone(phone);
                userEntity.setPhone(phone);
            }
        }

        userEntity.setNickname(userUpdateDto.getNickname());
        userEntity.setStatus(userUpdateDto.getStatus());

        mpUserService.updateById(userEntity);

        // 用户角色
        mpUserRoleService.remove(new LambdaQueryWrapper<UserRoleEntity>().eq(UserRoleEntity::getUserId, userEntity.getId()));

        saveUserRole(userEntity.getId(), userUpdateDto.getRoleIds());

    }

    @Override
    public void updatePassword(Long id, UserPasswordDto userPasswordDto) {
        mpUserService.update(
                new LambdaUpdateWrapper<UserEntity>()
                        .set(UserEntity::getPassword, PasswordUtils.hash(userPasswordDto.getPassword()))
                        .set(UserEntity::getUpdateDateTime, LocalDateTime.now())
                        .eq(UserEntity::getId, id)
        );
    }

    @Override
    public void delete(Long id) {
        mpUserRoleService.remove(new LambdaQueryWrapper<UserRoleEntity>().eq(UserRoleEntity::getUserId, id));
        mpUserService.removeById(id);
    }

    @Override
    public void checkUsernameExist(UsernameDto dto) {

        boolean judgeUsernameExists = mpUserService.exists(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getUsername, dto.getUsername())
        );

        if (judgeUsernameExists) {
            throw new BizException(ResultEnum.USER_EXISTS, "检查失败：用户名已存在");
        }

    }

    private void checkEmail(String email) {
        if (StringUtils.hasText(email)) {
            boolean judgeEmailExists = mpUserService.exists(
                    new LambdaQueryWrapper<UserEntity>()
                            .eq(UserEntity::getEmail, email)
            );
            if (judgeEmailExists) {
                throw new BizException(ResultEnum.USER_EXISTS, "操作失败：邮箱已存在");
            }
        }
    }

    private void checkPhone(String phone) {
        if (StringUtils.hasText(phone)) {
            boolean judgePhoneExists = mpUserService.exists(
                    new LambdaQueryWrapper<UserEntity>()
                            .eq(UserEntity::getPhone, phone)
            );
            if (judgePhoneExists) {
                throw new BizException(ResultEnum.USER_EXISTS, "操作失败：手机号已存在");
            }
        }
    }

    private void saveUserRole(Long userId, List<Long> roleIdList) {

        if (CollectionUtils.isEmpty(roleIdList)) {
            return;
        }

        for (Long roleId : roleIdList) {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(userId);
            userRoleEntity.setRoleId(roleId);
            mpUserRoleService.save(userRoleEntity);
        }
    }

}
