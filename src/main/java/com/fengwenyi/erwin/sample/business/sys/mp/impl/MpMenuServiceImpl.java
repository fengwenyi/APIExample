package com.fengwenyi.erwin.sample.business.sys.mp.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengwenyi.erwin.sample.business.sys.entity.MenuEntity;
import com.fengwenyi.erwin.sample.business.sys.mapper.IMenuMapper;
import com.fengwenyi.erwin.sample.business.sys.mp.IMpMenuService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-11
 */
@Service
public class MpMenuServiceImpl
        extends ServiceImpl<IMenuMapper, MenuEntity>
        implements IMpMenuService {
}
