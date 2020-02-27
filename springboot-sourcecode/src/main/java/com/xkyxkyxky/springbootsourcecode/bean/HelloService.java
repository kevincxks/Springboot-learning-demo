package com.xkyxkyxky.springbootsourcecode.bean;

import lombok.Data;

import javax.annotation.Resource;

@Data
public class HelloService {

    private Student student;

    private Animal animal;

    public String hello() {
        return student.toString();
    }

    public String animal() {
        return animal.getName();
    }
}
