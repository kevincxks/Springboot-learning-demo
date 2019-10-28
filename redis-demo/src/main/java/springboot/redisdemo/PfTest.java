package springboot.redisdemo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Pipeline;
import springboot.redisdemo.model.Person;

import java.util.concurrent.ThreadLocalRandom;

public class PfTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        for (int i = 0; i <1000 ; i++) {
            jedis.set("key"+i,i+"");
        }


    }
}
