package com.yulin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 控制器类，在SpringMVC中用来接收请求
 */
@Controller
public class HelloController {
    /**
     * 下面的RequestMapping即请求映射，可以将某个后缀的请求直接映射到当前的方法（函数）上
     */
    @RequestMapping(path = "/hello")
    public String Hello() {
        System.out.println("Hello SpringMVC");
        return "success";/*其实即返回到success.jsp文件*/
    }

    /**
     * 测试是否能够不指定path或者value就进行赋值
     * @return String / xxx.jsp file name
     */
    @RequestMapping("/test/ReqMap")
    public String testRequestMapping(){
        System.out.println("Enter testRequestMapping");
        return "testReqMap";
    }

    /**
     * 这里是wrong是因为普通的超链接过来使用的是GET方法，这里是为了测试@RequestMapping中各个method的实用性而已
     * @return String / xxx.jsp file name
     */
    @RequestMapping(value = "ReqMapMethodWRONG", method = {RequestMethod.POST})
    public String RequestMappingMethodWRONG(){
        System.out.println("Enter RequestMappingMethodWRONG");
        return "ReqMapMethodWRONG";
    }

    @RequestMapping(value = "ReqMapMethodRIGHT", method = {RequestMethod.GET})
    public String RequestMappingMethodRIGHT(){
        System.out.println("Enter RequestMappingMethodRIGHT");
        return "ReqMapMethodRIGHT";
    }
}

/*
为了使得在点击网页按钮后能够调用这个类的Hello方法，显然需要先将这个类进行实例化，
而实例化则可以用Spring中的IOC方法，因此在SpringMVC.xml文件中进行配置，开启注解扫描

 */