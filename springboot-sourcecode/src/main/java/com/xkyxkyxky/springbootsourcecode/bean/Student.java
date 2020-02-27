package com.xkyxkyxky.springbootsourcecode.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Data
public class Student {
    private String name;

    private Integer age;

    private List<String> classList;
}
