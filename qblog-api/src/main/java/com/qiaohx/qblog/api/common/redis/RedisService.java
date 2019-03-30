package com.qiaohx.qblog.api.common.redis;

/**
 * redis操作接口
 */
public interface RedisService {

    /**
     * 存不带失效时间
     * @param key key
     * @param value value
     */
    void set(String key, String value);
    /**
     * 放，带失效时间
     * @param key key
     * @param value value
     * @param ttl ttl
     */
    void set(String key, String value, int ttl);

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
