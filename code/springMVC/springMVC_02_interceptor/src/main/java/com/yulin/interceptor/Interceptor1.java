package com.yulin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor1 implements HandlerInterceptor {
    /**
     * 预处理，controller方法执行前
     * return true表示放行，执行下一个拦截器；如果没有下一个拦截器，执行controller中的方法
     * return false表示不放行，则可以：比如通过request/response跳转到某个页面上显示相关信息（比如权限不足）
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Interceptor1 let you go");
        return true;
    }
}
