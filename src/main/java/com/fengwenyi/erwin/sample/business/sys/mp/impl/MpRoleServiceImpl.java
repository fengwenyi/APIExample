package com.fengwenyi.erwin.sample.business.sys.mp.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengwenyi.erwin.sample.business.sys.entity.RoleEntity;
import com.fengwenyi.erwin.sample.business.sys.mapper.IRoleMapper;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpRoleService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
@Service
public class MpRoleServiceImpl
        extends ServiceImpl<IRoleMapper, RoleEntity>
        implements IMpRoleService {
}
