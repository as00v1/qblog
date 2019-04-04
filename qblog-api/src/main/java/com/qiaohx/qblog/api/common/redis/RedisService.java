package com.qiaohx.qblog.api.common.redis;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * redis操作接口
 */
public interface RedisService {

    /**
     * 存不带失效时间
     * @param key key
     * @param value value
     */
    void set(String key, Object value);
    /**
     * 放，带失效时间
     * @param key key
     * @param value value
     * @param ttl ttl
     */
    void set(String key, Object value, int ttl);


    /**
     * 设置失效时间
     * @param key key
     * @param ttl 时间
     */
    void expire(String key, long ttl);

    /**
     * 设置失效时间
     * @param key key
     * @param time 时间
     * @param timeUnit 时间单位
     */
    void expire(String key, long time, TimeUnit timeUnit);

    /**
     * 设置失效时间点
     * @param key key
     * @param date 日期
     */
    void expireAt(String key, Date date);

    /**
     * 取值
     * @param key key
     * @return value
     */
    Object get(String key);

    /**
     * 取值，续期
     * @param key key
     * @return value
     */
    Object get(String key, int ttl);

    /**
     * 获取一个增量
     * @param key
     * @param ttl
     * @return
     */
    Long getIncrement(String key, long ttl);
}
