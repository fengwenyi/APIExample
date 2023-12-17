package com.fengwenyi.erwin.sample.business.sys.index.service;


import com.fengwenyi.erwin.sample.business.sys.entity.UserEntity;
import com.fengwenyi.erwin.sample.business.sys.index.pojo.bo.TripartiteRegisterBo;
import com.fengwenyi.erwin.sample.business.sys.index.vo.UserInfoVo;
import com.fengwenyi.erwin.sample.business.sys.index.vo.UserPhoneVo;

import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-25
 */
public interface IUserService {

    UserEntity queryByUsername(String username);

    UserEntity queryByEmail(String email);

    UserEntity queryByPhone(String phone);

    UserInfoVo getUserInfo();

    void logout();

    UserEntity tripartiteRegister(TripartiteRegisterBo registerBo);

    UserPhoneVo getUserPhone();

    List<String> queryUserRoleCodeList(long userId);

    List<String> getPermissionList();

}
