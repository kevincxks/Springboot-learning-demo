package springboot.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.cache.mapper.UserMapper;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheDemoApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    public void userTest() {
        System.out.println(userMapper.list());
    }

}
