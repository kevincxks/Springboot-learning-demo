package springboot.redisdemo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

public class RedlockDemo {

    public static String lock(String key){
        Jedis jedis = new Jedis("localhost",6379);
        return jedis.set(key,"true", SetParams.setParams().ex(5).nx());
        }

public static void unlock(String key){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.del(key);
        }

public static void main(String[] args) {
        System.out.println(lock("kevin")==null);
        }
        }
