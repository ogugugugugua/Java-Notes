package com.yulin.tmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * register.jsp 是放在WEB-INF目录下的，是无法通过浏览器直接访问的。
 * 为了访问这些放在WEB-INF下的jsp，准备一个专门的PageController类，专门做服务端跳转。
 * 比如访问registerPage，就会服务端跳转到WEB-INF/jsp/fore/register.jsp 去，这样就解决了无法被访问的问题。
 * 这个类很简单，就是单纯用来做服务端跳转用的
 */
@Controller
@RequestMapping("")
public class PageController {
    @RequestMapping("registerPage")
    public String registerPage() {
        return "fore/register";
    }
    @RequestMapping("registerSuccessPage")
    public String registerSuccessPage() {
        return "fore/registerSuccess";
    }
    @RequestMapping("loginPage")
    public String loginPage() {
        return "fore/login";
    }
    @RequestMapping("forealipay")
    public String alipay(){
        return "fore/alipay";
    }
}
