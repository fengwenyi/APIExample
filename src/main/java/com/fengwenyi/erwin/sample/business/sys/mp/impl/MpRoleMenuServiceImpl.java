package com.fengwenyi.erwin.sample.business.sys.mp.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengwenyi.erwin.sample.business.sys.entity.RoleMenuEntity;
import com.fengwenyi.erwin.sample.business.sys.mapper.IRoleMenuMapper;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-11
 */
@Service
public class MpRoleMenuServiceImpl
        extends ServiceImpl<IRoleMenuMapper, RoleMenuEntity>
        implements IMpRoleMenuService {
}
