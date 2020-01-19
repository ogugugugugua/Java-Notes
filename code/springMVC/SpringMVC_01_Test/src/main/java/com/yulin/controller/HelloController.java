package com.yulin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*控制器类，在SpringMVC中用来接收请求*/
@Controller
public class HelloController {
    /*下面的RequestMapping即请求映射，可以将某个后缀的请求直接映射到当前的方法（函数）上*/
    @RequestMapping(path = "/hello")
    public String Hello() {
        System.out.println("Hello SpringMVC");
        return "success";/*其实即返回到success.jsp文件*/
    }
}

/*
为了使得在点击网页按钮后能够调用这个类的Hello方法，显然需要先将这个类进行实例化，
而实例化则可以用Spring中的IOC方法，因此在SpringMVC.xml文件中进行配置，开启注解扫描

 */