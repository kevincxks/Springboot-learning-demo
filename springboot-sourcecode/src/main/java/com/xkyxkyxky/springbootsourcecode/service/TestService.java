package com.xkyxkyxky.springbootsourcecode.service;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Resource
    List<ApplicationContextInitializer> list;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String test() {
        System.out.println("fuck");
        return applicationContext.getEnvironment().getProperty("key1");
    }


}
