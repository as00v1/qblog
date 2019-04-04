package com.qiaohx.qblog.service.common.redis;

import com.qiaohx.qblog.api.common.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    /**
     * 存不带失效时间
     *
     * @param key   key
     * @param value value
     */
    @Override
    public void set(String key, Object value) {
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
    public void set(String key, Object value, int ttl) {
        valueOperations.set(key, value, ttl, TimeUnit.SECONDS);
    }

    /**
     * 设置失效时间
     *
     * @param key  key
     * @param ttl 时间
     */
    @Override
    public void expire(String key, long ttl) {
        expire(key, ttl, TimeUnit.SECONDS);
    }

    /**
     * 设置失效时间
     *
     * @param key      key
     * @param time     时间
     * @param timeUnit 时间单位
     */
    @Override
    public void expire(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 设置失效时间点
     *
     * @param key  key
     * @param date 日期
     */
    @Override
    public void expireAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
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
        Object o = valueOperations.get(key);
        if (o != null)
            expire(key, ttl);
        return o;
    }

    /**
     * 获取一个增量
     *
     * @param key key
     * @param ttl 失效时间 毫秒
     * @return
     */
    @Override
    public Long getIncrement(String key, long ttl) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        if ((null == increment || increment.longValue() == 0) && ttl > 0) {//初始设置过期时间
            entityIdCounter.expire(ttl, TimeUnit.MILLISECONDS);//单位毫秒
        }
        return increment;
    }
}
