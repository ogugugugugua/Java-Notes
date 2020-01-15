package com.yulin.service;

import org.springframework.stereotype.Component;

@Component("service")
public class ProductService {
    public void doSomeService(){
        System.out.println("Hey service");
        long sum=0L;
        long l=50000L;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < i; j++) {
                sum +=i*j+Math.pow(i, 3);
            }
        }
    }
}
