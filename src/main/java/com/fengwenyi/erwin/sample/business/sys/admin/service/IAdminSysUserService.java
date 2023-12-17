package com.fengwenyi.erwin.sample.business.sys.admin.service;

import com.fengwenyi.api.result.PageTemplate;
import com.fengwenyi.erwin.sample.business.sys.admin.dto.user.*;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.user.UserDetailVo;
import com.fengwenyi.erwin.sample.business.sys.admin.vo.user.UserSimpleVo;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
public interface IAdminSysUserService {
    PageTemplate<UserSimpleVo> getPage(UserGetPageDto getUserPageDto);

    UserDetailVo getDetail(Long id);

    void add(UserDto userDto);

    void update(Long id, UserUpdateDto userUpdateDto);

    void updatePassword(Long id, UserPasswordDto userPasswordDto);

    void delete(Long id);

    void checkUsernameExist(UsernameDto dto);
}
