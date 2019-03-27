package com.qiaohx.qblog.service.common.redis;

import com.qiaohx.qblog.api.common.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisServiceImpl implements RedisService {

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    /**
     * 存不带失效时间
     *
     * @param key   key
     * @param value value
     */
    @Override
    public void set(String key, String value) {
        valueOperations.set(key, value);
    }

    /**
     * 放，带失效时间
     *
     * @param key   key
     * @param value value
     * @param ttl   ttl
     */
    @Override
    public void set(String key, String value, int ttl) {
        valueOperations.set(key, value, ttl);
    }

    /**
     * 取值
     *
     * @param key key
     * @return value
     */
    @Override
    public Object get(String key) {
        return valueOperations.get(key);
    }

    /**
     * 取值，续期
     *
     * @param key key
     * @param ttl
     * @return value
     */
    @Override
    public Object get(String key, int ttl) {
        return null;
    }
}
