package com.xkyxkyxky.springbootsourcecode.controller;


import com.xkyxkyxky.springbootsourcecode.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoController {

    @Resource
    private TestService testService;


    @RequestMapping("test")
    public String test() {
        return testService.test();
    }
}
