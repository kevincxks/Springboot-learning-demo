package springboot.eurekaconsumer.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ArticleController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("article/callHello")
    public String callHello(){
        return restTemplate.getForObject("http://eureka-client-user-service/user/hello",String.class);
    }

    @GetMapping("article/test")
    public String callTest(){
        return restTemplate.getForObject("http://eureka-client-user-service/user/test",String.class);
    }
}
