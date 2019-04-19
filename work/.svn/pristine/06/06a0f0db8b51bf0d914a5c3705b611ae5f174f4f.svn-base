package com.ck.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.stereotype.Service;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService<T> {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 一天有多少分钟，默认时间是一天
     */
    private static final long MINUTES_OF_ONE_DAY = 24 * 60;


    /**
     * 将 key，value 存放到redis数据库中，默认设置过期时间为一天
     *
     * @param key
     * @param value
     */
    public void set(String key, T value) {
        set(key, value, MINUTES_OF_ONE_DAY);
    }

    /**
     * 将 key，value 存放到redis数据库中，设置过期时间单位是分钟
     *
     * @param key
     * @param value
     * @param expireTime 单位是秒
     */
    public void set(String key, T value, long expireTime) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value,expireTime, TimeUnit.MINUTES);
    }

    /**
     * 判断 key 是否在 redis 数据库中
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }



    /**
     * 获取 key 对应的字符串
     * @param key
     * @return
     */
    public T get(String key) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 获得 key 对应的键值，并更新缓存时间，时间长度为默认值
     * @param key
     * @return
     */
    public T getAndUpdateTime(String key) {
        T result = get(key);
        if (result != null) {
            set(key, result);
        }
        return result;
    }

    /**
     * 删除 key 对应的 value
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * redis List数据结构 :将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     *
     * @param key   the key
     * @param value the value
     * @return the long
     */
    public Long in(String key, String value) {
        Long count = 0L;
        try {
            count = opsForList().rightPush(key, value);
        } catch (JedisConnectionException e) {
            log.error("[{}]", e.getLocalizedMessage());
        }
        return count;
    }

    /**
     * redis List 引擎
     *
     * @return the list operations
     */
    public ListOperations<String, Object> opsForList() {
        ListOperations<String, Object> list = null;
        try {
            list = redisTemplate.opsForList();
        } catch (JedisConnectionException e) {
            log.error("[{}]", e.getLocalizedMessage());
        }
        return list;
    }

    /**
     * redis List数据结构 : 返回列表 key 的长度 ; 如果 key 不存在，则 key 被解释为一个空列表，返回 0 ; 如果 key 不是列表类型，返回一个错误。
     *
     * @param key the key
     * @return the long
     */
    public Long length(String key) {
        Long len = 0L;
        try {
            len = opsForList().size(key);
        } catch (JedisConnectionException e) {
            log.error("[{}]", e.getLocalizedMessage());
        }
        return len;
    }

    /**
     * redis List数据结构 : 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 end 指定。
     *
     * @param key   the key
     * @param start the start
     * @param end   the end
     * @return the list
     */
    public List<Object> getList(String key, int start, int end) {
        List<Object> list = null;
        try {
            list = opsForList().range(key, start, end);
        } catch (JedisConnectionException e) {
            log.error("[{}]", e.getLocalizedMessage());
        }
        return list;
    }

//    public  Map<String,Object> getTest(String map, String key) {
//        Map<String,Object> map1 = null;
//        try {
//            map1 = redisTemplate.opsForHash().entries(map);
//        } catch (JedisConnectionException e) {
//            log.error("[{}]", e.getLocalizedMessage());
//        }
//        return map1;
//    }

}
