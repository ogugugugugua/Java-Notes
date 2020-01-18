package com.yulin.service;

import org.springframework.stereotype.Component;

@Component("s")
public class SomeService {
    public void doSomeService(){
        System.out.println("Hey do some service.");
    }
    public int anotherService(){
        System.out.println("return here");
        return 1;
    }
}
