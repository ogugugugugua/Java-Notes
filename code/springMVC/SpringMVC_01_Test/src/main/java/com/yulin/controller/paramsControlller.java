package com.yulin.controller;

import com.yulin.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/params")/*@RequestMapping这个东西也可以作用在类上*/
public class paramsControlller {

    @RequestMapping("/simpleTest")
    public String simpleTest(String name,String pwd) {
        System.out.println("Enter simpleTest of params");
        System.out.println("name: " + name);
        System.out.println("pwd: " + pwd);
        return "success";
    }

    @RequestMapping("/AccountTest")
    public String AccountTest(Account account){
        System.out.println("Enter account");
        System.out.println(account);
        return "accountSubmited";
    }
}
