package com.xkyxkyxky.springbootsourcecode;

import com.xkyxkyxky.springbootsourcecode.bean.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

@SpringBootTest
@ContextConfiguration(locations = "classpath:ioc/demo.xml")
class SpringbootSourcecodeApplicationTests {

    @Resource
    private HelloService helloService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testHello() {
        System.out.println(helloService.hello());
        System.out.println(helloService.animal());
    }

}
