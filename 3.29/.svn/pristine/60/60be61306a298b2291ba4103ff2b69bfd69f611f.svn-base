package ck.preprocessorData;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * <h3>连接redis</h3>
 * 
 * @author LIUJUNWU
 * @version 1.0.0
 */
public class RedisProvider {

	protected static JedisPool jedispool;
	protected static int EXPIRE = 130;
	static{
        JedisPoolConfig jedisconfig = new JedisPoolConfig();
//        jedisconfig.setMaxActive(512);
        jedisconfig.setMaxIdle(100);
//        jedisconfig.setMaxWait(100000);
        jedisconfig.setTestOnBorrow(true);
        jedisconfig.setTestOnReturn(true);
        jedispool = new JedisPool("192.168.0.7",6379);
//        jedispool = new JedisPool("192.168.0.146",6379);
//        jedispool = new JedisPool("127.0.0.1",6379);
        
	}
	
	public static Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = jedispool.getResource();
        } catch (JedisConnectionException jce) {
            jedis = jedispool.getResource();
        }
        return jedis;
    }
 
    public static void returnResource(JedisPool pool, Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }

}
