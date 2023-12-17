package com.fengwenyi.erwin.sample.service.impl;

import com.fengwenyi.erwin.sample.service.IRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements IRedisService {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void set(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
