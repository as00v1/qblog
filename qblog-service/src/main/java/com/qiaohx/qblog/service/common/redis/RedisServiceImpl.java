package com.qiaohx.qblog.service.common.redis;

import com.qiaohx.qblog.api.common.AbstractBaseService;
import com.qiaohx.qblog.api.common.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class RedisServiceImpl extends AbstractBaseService implements RedisService {
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

    /**
     * 删除
     *
     * @param key key
     */
    @Override
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 加锁
     *
     * @param key   key
     * @param value value
     * @return true/false
     */
    @Override
    public boolean lock(String key, String value) {
        logger.info(String.format("加锁 %s , %s", key, value));
        if(valueOperations.setIfAbsent(key,value)){//对应setnx命令
            //可以成功设置,也就是key不存在
            logger.info("加锁完成！");
            return true;
        }

        //判断锁超时 - 防止原来的操作异常，没有运行解锁操作  防止死锁
        String currentValue = (String)valueOperations.get(key);
        //如果锁过期
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){//currentValue不为空且小于当前时间
            //获取上一个锁的时间value
            String oldValue = (String)valueOperations.getAndSet(key, value);//对应getset，如果key存在
            //假设两个线程同时进来这里，因为key被占用了，而且锁过期了。获取的值currentValue=A(get取的旧的值肯定是一样的),两个线程的value都是B,key都是K.锁时间已经过期了。
            //而这里面的getAndSet一次只会一个执行，也就是一个执行之后，上一个的value已经变成了B。只有一个线程获取的上一个值会是A，另一个线程拿到的值是B。
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue) ){
                //oldValue不为空且oldValue等于currentValue，也就是校验是不是上个对应的商品时间戳，也是防止并发
                logger.info("锁超时，新加锁");
                return true;
            }
        }
        logger.info("加锁失败！");
        return false;
    }

    /**
     * 解锁
     *
     * @param key   key
     * @param value value
     */
    @Override
    public void unlock(String key, String value) {
        logger.info(String.format("解锁 %s , %s", key, value));
        String currentValue = (String) valueOperations.get(key);
        if(!StringUtils.isEmpty(currentValue) && currentValue.equals(value) ){
            delete(key);//删除key
        }
    }

}
