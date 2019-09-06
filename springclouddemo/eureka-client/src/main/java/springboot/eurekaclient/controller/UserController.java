package springboot.eurekaclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/hello")
    public String hello(){
        return "hello";
    }


    @GetMapping("/user/test")
    public String test(){
        return "test";
    }
}
