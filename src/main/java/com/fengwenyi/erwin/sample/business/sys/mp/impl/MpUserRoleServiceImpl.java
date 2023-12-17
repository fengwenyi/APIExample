package com.fengwenyi.erwin.sample.business.sys.mp.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengwenyi.erwin.sample.business.sys.entity.UserRoleEntity;
import com.fengwenyi.erwin.sample.business.sys.mapper.IUserRoleMapper;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
@Service
public class MpUserRoleServiceImpl
        extends ServiceImpl<IUserRoleMapper, UserRoleEntity>
        implements IMpUserRoleService {
}
