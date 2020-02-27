package com.xkyxkyxky.springbootsourcecode.bean;

public class AnimalFactory {

    public Animal getAnimal(String type) {
        if("Dog".equals(type)) {
            return new Dog();
        } else {
            return new Cat();
        }
    }
}
