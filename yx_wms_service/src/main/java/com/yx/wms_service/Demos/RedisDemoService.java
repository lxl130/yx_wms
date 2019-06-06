package com.yx.wms_service.Demos;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisDemoService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 一周有多少秒
     */
    private static final long WEEK_SECONDS = 7 * 24 * 60 * 60;


    /**
     * Desc: 读取所有key
     * @param key
     * @param value
     */
    public Set<String> getAllKey(){
       return redisTemplate.keys("*");
    }


    /**
     * Desc: 将 key，value 存放到redis数据库中，默认设置过期时间为一周
     *
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        set( key,  JSON.toJSONString(value), WEEK_SECONDS);
    }

    /**
     * Desc: 将 key，value 存放到redis数据库中，设置过期时间单位是秒
     * @param key
     * @param value
     * @param expireTime
     */
    public void set(String key, Object value, long expireTime) {
        set( key,  JSON.toJSONString(value), expireTime);
    }
    /**
     * Desc: 将 key，value 存放到redis数据库中，设置过期时间单位是秒
     * @param key
     * @param value
     * @param expireTime
     */
    public void set(String key, String value, long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }


    /**
     * Desc: 判断 key 是否在 redis 数据库中
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * Desc: 获取与 key 对应的对象
     *
     * @param key
     * @param clazz 目标对象类型
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        String s = get(key);
        if (s == null) {
            return null;
        }
        return  JSON.parseObject(s,clazz);
    }

    /**
     * 获取 key 对应的字符串
     * @param key
     * @return
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除 key 对应的 value
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
