package com.fengwenyi.erwin.sample.service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-08-04
 */
public interface IRedisService {

    void set(String key, String value, long expireTime);

    String get(String key);

}
