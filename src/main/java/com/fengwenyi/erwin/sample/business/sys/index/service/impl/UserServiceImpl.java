package com.fengwenyi.erwin.sample.business.sys.index.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.fengwenyi.erwin.sample.business.sys.entity.RoleEntity;
import com.fengwenyi.erwin.sample.business.sys.entity.TripartiteUserEntity;
import com.fengwenyi.erwin.sample.business.sys.entity.UserEntity;
import com.fengwenyi.erwin.sample.business.sys.entity.UserRoleEntity;
import com.fengwenyi.erwin.sample.business.sys.enums.RegisterTypeEnum;
import com.fengwenyi.erwin.sample.business.sys.index.pojo.bo.TripartiteRegisterBo;
import com.fengwenyi.erwin.sample.business.sys.index.service.IUserService;
import com.fengwenyi.erwin.sample.business.sys.index.vo.RoleInfoVo;
import com.fengwenyi.erwin.sample.business.sys.index.vo.UserInfoVo;
import com.fengwenyi.erwin.sample.business.sys.index.vo.UserPhoneVo;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpRoleService;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpTripartiteUserService;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpUserRoleService;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpUserService;
import com.fengwenyi.erwin.sample.enums.ResultEnum;
import com.fengwenyi.erwin.sample.exception.BizException;
import com.fengwenyi.erwin.sample.util.PasswordUtils;
import com.fengwenyi.javalib.generate.IdUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-25
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IMpUserService mpUserService;
    private final IMpUserRoleService mpUserRoleService;
    private final IMpRoleService mpRoleService;
    private final IMpTripartiteUserService mpTripartiteUserService;

    @Override
    public UserEntity queryByUsername(String username) {
        return mpUserService.getOne(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getUsername, username)
        );
    }

    @Override
    public UserEntity queryByEmail(String email) {
        return mpUserService.getOne(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getEmail, email)
        );
    }

    @Override
    public UserEntity queryByPhone(String phone) {
        return mpUserService.getOne(
                new LambdaQueryWrapper<UserEntity>()
                        .eq(UserEntity::getPhone, phone)
        );
    }

    @Override
    public UserInfoVo getUserInfo() {
        Object loginId = StpUtil.getLoginId();
        UserEntity userEntity = mpUserService.getById((Serializable) loginId);
        if (Objects.isNull(userEntity)) {
            throw new BizException(ResultEnum.AUTH_FAILED);
        }

        List<UserRoleEntity> userRoleEntityList = mpUserRoleService.list(
                new LambdaQueryWrapper<UserRoleEntity>()
                        .eq(UserRoleEntity::getUserId, userEntity.getId())
        );
        List<RoleInfoVo> roleInfoVoList = new ArrayList<>();
        for (UserRoleEntity userRoleEntity : userRoleEntityList) {
            RoleEntity roleEntity = mpRoleService.getById(userRoleEntity.getRoleId());
            if (Objects.nonNull(roleEntity) && roleEntity.getStatus()) {
                RoleInfoVo roleInfoVo = new RoleInfoVo();
                roleInfoVo.setRoleName(roleEntity.getRoleName());
                roleInfoVo.setValue(roleEntity.getRoleCode());
                roleInfoVoList.add(roleInfoVo);
            }
        }

        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setRoles(roleInfoVoList);
        userInfoVo.setUserId(String.valueOf(userEntity.getId()));
        userInfoVo.setUsername(userEntity.getUsername());
        userInfoVo.setRealName(userEntity.getNickname());
        userInfoVo.setAvatar("");
        userInfoVo.setDesc("");

        return userInfoVo;
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    @Override
    public UserEntity tripartiteRegister(TripartiteRegisterBo registerBo) {

        String account = registerBo.getAccount();
        String unionId = registerBo.getUnionId();
        String openId = registerBo.getOpenId();
        RegisterTypeEnum registerTypeEnum = registerBo.getRegisterTypeEnum();

        long userId = IdWorker.getId();

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setUsername(account);
        userEntity.setPassword(PasswordUtils.hash(IdUtils.genId()));

        TripartiteUserEntity tripartiteUserEntity = new TripartiteUserEntity();
        tripartiteUserEntity.setAccount(account);
        tripartiteUserEntity.setUnionId(unionId);
        tripartiteUserEntity.setOpenId(openId);
        tripartiteUserEntity.setRegisterType(registerTypeEnum.name());
        tripartiteUserEntity.setUserId(userId);

        if (registerTypeEnum == RegisterTypeEnum.WECHAT_APPLET) {
            userEntity.setPhone(account);
            userEntity.setNickname("微信用户");
        }

        mpUserService.save(userEntity);
        mpTripartiteUserService.save(tripartiteUserEntity);

        return mpUserService.getById(userId);
    }

    @Override
    public UserPhoneVo getUserPhone() {

        Object loginId = StpUtil.getLoginId();
        UserEntity userEntity = mpUserService.getById((Serializable) loginId);
        if (Objects.isNull(userEntity)) {
            throw new BizException(ResultEnum.AUTH_FAILED);
        }

        String phone = userEntity.getPhone();

        UserPhoneVo vo = new UserPhoneVo();
        vo.setPhone(phone);

        return vo;
    }

    @Override
    public List<String> queryUserRoleCodeList(long userId) {
        List<String> userRoleCodeList = new ArrayList<>();
        UserEntity userEntity = mpUserService.getById(userId);
        if (Objects.isNull(userEntity)) {
            return userRoleCodeList;
        }
        List<UserRoleEntity> userRoleEntityList = mpUserRoleService.list(
                new LambdaQueryWrapper<UserRoleEntity>()
                        .eq(UserRoleEntity::getUserId, userEntity.getId())
        );
        if (CollectionUtils.isEmpty(userRoleEntityList)) {
            return userRoleCodeList;
        }
        for (UserRoleEntity userRoleEntity : userRoleEntityList) {
            RoleEntity roleEntity = mpRoleService.getById(userRoleEntity.getRoleId());
            if (Objects.nonNull(roleEntity)) {
                userRoleCodeList.add(roleEntity.getRoleCode());
            }
        }
        return userRoleCodeList;
    }

    @Override
    public List<String> getPermissionList() {

        List<String> userRoleCodeList = queryUserRoleCodeList(getUserId());

        if (userRoleCodeList.contains("ADMIN") || userRoleCodeList.contains("SUPER_ADMIN")) {
            return List.of(
                    "ADD",
                    "DELETE",
                    "UPDATE",
                    "QUERY"
            );
        } else {
            return List.of(
                    "QUERY"
            );
        }

    }


    private long getUserId() {
        Object loginId = StpUtil.getLoginId();
        if (Objects.isNull(loginId)) {
            throw new BizException(ResultEnum.AUTH_FAILED);
        }
        return Long.parseLong(loginId.toString());
    }
}
