package springboot.shirodemo;

import org.apache.shiro.crypto.hash.Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.shirodemo.util.JwtUtils;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest("springboot.shirodemo.ShiroDemoApplication")
public class ShiroDemoApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        Map<String,String> map =new HashMap<>();
        map.put("password","kevin");
        map.put("username","bob");
        System.out.println(map);
        System.out.println(JwtUtils.createToken(map));
    }

    @Test
    public void test122() throws Exception {
        Map<String,String> map =new HashMap<>();
        map.put("password","kevin");
        map.put("username","bob");
        System.out.println(map);
        System.out.println(JwtUtils.createToken(map));
    }

}
