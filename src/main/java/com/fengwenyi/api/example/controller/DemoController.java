package com.fengwenyi.api.example.controller;

import com.fengwenyi.api.result.ResultTemplate;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-09-11
 */
public class DemoController {

    @Autowired
    private RedissonClient redissonClient;

    public ResultTemplate<?> test(String id) {
        RLock rLock = redissonClient.getLock(id);
        if (Objects.isNull(rLock)) {
            return ResultTemplate.fail();
        }
        try {
            rLock.lock(10, TimeUnit.SECONDS);
            // todo
            return ResultTemplate.success();
        } finally {
            if (rLock.isLocked()) {
                rLock.unlock();
            }
        }
    }

}
