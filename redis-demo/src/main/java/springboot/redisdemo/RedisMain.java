package springboot.redisdemo;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.EvictionConfig;
import org.apache.commons.pool2.impl.EvictionPolicy;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisMain {

    public static void main(String[] args) {
        //连接池配置
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10); //最大连接数
        //连接池
        JedisPool jedisPool = new JedisPool(poolConfig,"localhost",6379);

        Jedis jedis = jedisPool.getResource();

        System.out.println(jedis.ping());

    }
}
