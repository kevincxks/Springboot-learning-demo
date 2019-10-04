package springboot.redisdemo.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.experimental.Accessors;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class Person {

    private String name;

    private Integer identify;

    private Integer age;

    private String nickname;


    public static void writeToRedis(Person person){
        String json = JSON.toJSONString(person);
        System.out.println(json);
        Jedis jedis = new Jedis("localhost",6379);
        jedis.set(person.getIdentify().toString(),json);
    }

    public static void writeHashToRedis(Person person) throws IllegalAccessException {
        Class<? extends Person> aClass = person.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        Jedis jedis = new Jedis("localhost",6379);
        Map<String,String> map = new HashMap<>();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Object o = declaredField.get(person);
            System.out.println(o.toString());
            map.put(declaredField.getName(),o.toString());
        }
        jedis.hmset(person.getIdentify().toString(),map);
    }


    public static Person readFromRedis(Integer identify){
        Jedis jedis = new Jedis("localhost",6379);
        String obj = jedis.get(identify.toString());
        Person person = JSON.parseObject(obj,Person.class);
        return person;
    }

    public static Person readHashFromRedis(Integer identify) throws IllegalAccessException {
        Jedis jedis = new Jedis("localhost",6379);
        Map<String, String> stringStringMap = jedis.hgetAll(identify.toString());
        Class<Person> personClass = Person.class;
        Field[] declaredFields = personClass.getDeclaredFields();
        Person person = new Person();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            declaredField.setAccessible(true);
            if (declaredField.getType()==Integer.class){
                declaredField.set(person,Integer.parseInt(stringStringMap.get(name)));
            }
            else
                declaredField.set(person,stringStringMap.get(name));
        }
        return person;

    }

    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person()
                .setIdentify(95290)
                .setAge(20)
                .setName("kevin")
                .setNickname("bazi");
        writeHashToRedis(person);
        Person person1 = readHashFromRedis(95290);
        System.out.println(person1);

    }
}
