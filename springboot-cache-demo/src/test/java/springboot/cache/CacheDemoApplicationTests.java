package springboot.cache;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.cache.mapper.UserMapper;
import springboot.cache.model.User;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CacheDemoApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private DataSource dataSource;

    @Resource
    private Environment environment;

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private User user;

    @Test
    public void userTest() {
        System.out.println(userMapper.list());
    }

    @Test
    public void dataSourceTest(){
        log.info(dataSource.toString());
    }

    @Test
    public void envTet(){
        System.out.println(user);
    }

}
