package com.yulin.controller;

import com.yulin.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/testException")
    public String testException() throws Exception {
        System.out.println("testException");
        try{
            //simulate exception
            int a = 10/0;
        }catch (Exception e){
            e.printStackTrace();
            throw new SysException("WE HAVE AN ERROR");
        }

        return "success";
    }
}
