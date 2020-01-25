package com.yulin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 这个类是为了测试各种注解，比如 @RequestParam
 */
@Controller
@RequestMapping("Anno")
public class AnnoController {
    /**
     * 测试@RequestParam注解
     * @param name
     * @return
     */
    @RequestMapping("testRequestParam")
    public String RequestParam(@RequestParam(value = "username") String name){
        System.out.println("RequestParam");
        System.out.println(name);
        return "success";
    }



    /**
     * 获取请求体内容RequestBody
     * @param body
     * @return
     */
    @RequestMapping("testRequestBody")
    public String RequestBody(@RequestBody String body){
        System.out.println("testRequestBody");
        System.out.println(body);
        return "success";
    }


    /**
     * 测试@PathVariable
     * 据说主要用于后期的异步json,使得多个方法可以使用同样的前缀名称
     * @return
     */
    @RequestMapping("testPathVariable/{id}")
    public String PathVariable(@PathVariable("id") String ID){
        System.out.println("testPathVariable");
        System.out.println("id:"+ID);
        return "success";
    }

    /**
     * 测试@RequestHeader,获取请求头信息
     * @return
     */
    @RequestMapping("testRequestHeader")
    public String RequestHeader(@RequestHeader("Accept") String header){
        System.out.println("testPathVariable");
        System.out.println(header);
        return "success";
    }

    /**
     * 拥有@ModelAttribute注解的方法会首先被执行，可以用于在用户提交不完整数据的表单，且对应的方法执行前，
     * 先查询数据库补充相关缺失信息，返回完整的信息给对应的方法
     * @return
     */
    @ModelAttribute
    public String runFirst(){
        System.out.println("I run First");
        return "success";
    }
}
