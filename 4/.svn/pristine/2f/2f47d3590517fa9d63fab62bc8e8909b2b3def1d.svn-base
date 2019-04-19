package com.ck.PAEngine.redis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ck.PAEngine.utils.SerializeUtil;

import redis.clients.jedis.Jedis;

/**
 * <h3>redis工具类</h3>
 * 
 * @author LIUJUNWU
 * @version 1.0.0
 */
public class RedisHelper extends RedisProvider{
	/**
	 * Set the string value as value of the key. Default settings at save
	 * time(2000s)
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String set(String key, String value) {
		Jedis jedis = null;
		String rtn = null;
		try {
			jedis = getJedis();
			rtn = jedis.setex(key, EXPIRE, value);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
		return rtn;
	}
 
	public static String set2(String key, String value) {
		Jedis jedis = null;
		String rtn = null;
		try {
			jedis = getJedis();
			rtn = jedis.setex(key, 360000, value);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
		return rtn;
	}
 
	/**
	 * Get the value of the specified key.
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		Jedis jedis = null;
		String rtn = null;
		try {
			jedis = getJedis();
			rtn = jedis.get(key);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
		return rtn;
	}
 
	/**
	 * Get the values of all the specified keys
	 * 
	 * @param keys
	 * @return
	 */
	public static List<String> mget(String... keys) {
		Jedis jedis = null;
		List<String> rtn = new ArrayList<String>();
		try {
			jedis = getJedis();
			rtn = jedis.mget(keys);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
		return rtn;
	}
 
	/**
	 * Set the the respective keys to the respective values.
	 * 
	 * @param keysvalues
	 * @return
	 */
	public static String mset(String... keysvalues) {
		Jedis jedis = null;
		String rtn = null;
		try {
			jedis = getJedis();
			rtn = jedis.mset(keysvalues);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
		return rtn;
	}
 
	/**
	 * Return all the fields and associated values in a hash.
	 * 
	 * @param key
	 * @return
	 */
//	public static Map<String, String> hgetall(String key) {
//		Jedis jedis = null;
//		Map<String, String> rtn = Map.newHashMap();
//		try {
//			jedis = getJedis();
//			rtn = jedis.hgetAll(key);
//		} catch (Exception e) {
//			jedispool.returnBrokenResource(jedis);
//		} finally {
//			returnResource(jedispool, jedis);
//		}
//		return rtn;
//	}
 
	/**
	 * Set the specified hash field to the specified value.
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public static Long hset(String key, String field, String value) {
 
		Jedis jedis = null;
		Long rtn = null;
		try {
			jedis = getJedis();
			rtn = jedis.hset(key, field, value);
			jedis.expire(key, EXPIRE);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
		return rtn;
	}
 
	/**
	 * 以map形式存放对象.
	 * 
	 * @param key
	 * @param field
	 * @param obj
	 * @return
	 */
	public static long setObject(String key, String field, Object obj) {
		Jedis jedis = null;
		Long rtn = null;
		try {
			jedis = getJedis();
			rtn = jedis.hset(("drugRelation:"+key).getBytes(), field.getBytes(), SerializeUtil.serialize(obj));
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		}finally {
			returnResource(jedispool, jedis);
		}
		return rtn;
	}
 
	/**
	 * 获取对象.
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public static Object getObject(String key, String field) {
		Jedis jedis = null;
		byte[] rtn = null;
		try {
			jedis = getJedis();
			rtn = jedis.hget(key.getBytes(), field.getBytes());
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
		return SerializeUtil.unserialize(rtn);
	}
 
	public static void addObject(String key, Object obj) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.sadd(key.getBytes());
			SerializeUtil.serialize(obj);
			jedis.expire(key.getBytes(), EXPIRE);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
	}	
 
	public static List<Object> getAllObject(String key) {
		List<Object> list = new ArrayList<Object>();
		Jedis jedis = null;
		try {
			jedis = getJedis();
			Set<byte[]> set = jedis.smembers(key.getBytes());
			if (set != null && !set.isEmpty()) {
				Iterator<byte[]> it = set.iterator();
				for (; it.hasNext();) {
					byte[] b = it.next();
					Object obj = SerializeUtil.unserialize(b);
					list.add(obj);
				}
			}
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
		return list;
	}
 
	public static void delAllObject(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.del(key.getBytes());
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
	}
 
	public static Long hset2(String key, String field, String value) {
 
		Jedis jedis = null;
		Long rtn = null;
		try {
			jedis = getJedis();
			rtn = jedis.hset(key, field, value);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
		return rtn;
	}
 
	public static void hdel2(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.del(key);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
	}
 
	public static void flush() {
		Jedis jedis = null;
		jedis = getJedis();
		jedis.flushAll();
	}
 
	public static String hget(String key, String field) {
 
		Jedis jedis = null;
		String rtn = null;
		try {
			jedis = getJedis();
			rtn = jedis.hget(key, field);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
		return rtn;
	}
 
	public static long hdel(String key, String[] field) {
		Jedis jedis = null;
		Long rtn = null;
		try {
			jedis = getJedis();
			rtn = jedis.hdel(key, field);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
		return rtn;
	}
 
	public static long mdel(String[] key) {
		Jedis jedis = null;
		Long rtn = null;
		try {
			jedis = getJedis();
			rtn = jedis.del(key);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
		return rtn;
	}
 
	/**
	 * 设置分布式锁
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static long setLock(String key, String value) {
		Jedis jedis = null;
		Long rtn = null;
		try {
			jedis = getJedis();
			rtn = jedis.setnx(key, value);
			jedis.expire(key, EXPIRE);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
		return rtn;
	}
 
	/**
	 * 释放锁
	 * 
	 * @param key
	 * @return
	 */
	public static long delLock(String key) {
		Jedis jedis = null;
		Long rtn = null;
		try {
			jedis = getJedis();
			rtn = jedis.del(key);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		} finally {
			returnResource(jedispool, jedis);
		}
		return rtn;
	}
 
	/**
	 * 存储子调用链的list
	 * @param dateKey
	 * @param cidList
	 */
	public static void memoryCid(String dateKey,String cid){
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.sadd(dateKey, cid);
			jedis.expire(dateKey, EXPIRE);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		}finally {
			returnResource(jedispool, jedis);
		}
	}
	
	/**
	 * 获取调用链list
	 * @param dateKey
	 * @return
	 */
	public static Set<String> getAllCids(String dateKey){
		Jedis jedis = null;
		Set<String> set = null;
		try {
			jedis = getJedis();
			set = jedis.smembers(dateKey);
		} catch (Exception e) {
			jedispool.returnBrokenResource(jedis);
		}finally {
			returnResource(jedispool, jedis);
		}
		return set;
	}
}
