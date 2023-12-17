package com.fengwenyi.erwin.sample.business.sys.mp.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengwenyi.erwin.sample.business.sys.entity.UserEntity;
import com.fengwenyi.erwin.sample.business.sys.mapper.IUserMapper;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpUserService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-07-25
 */
@Service
public class MpUserServiceImpl
        extends ServiceImpl<IUserMapper, UserEntity>
        implements IMpUserService {
}
