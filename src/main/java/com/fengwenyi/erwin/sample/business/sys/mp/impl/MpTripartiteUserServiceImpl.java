package com.fengwenyi.erwin.sample.business.sys.mp.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengwenyi.erwin.sample.business.sys.entity.TripartiteUserEntity;
import com.fengwenyi.erwin.sample.business.sys.mapper.ITripartiteUserMapper;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpTripartiteUserService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-27
 */
@Service
public class MpTripartiteUserServiceImpl
        extends ServiceImpl<ITripartiteUserMapper, TripartiteUserEntity>
        implements IMpTripartiteUserService {
}
