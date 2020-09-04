package com.example.demo.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功后应该有用户的session
        Object loginUser = request.getSession().getAttribute("loginUser");

        if (null == loginUser) {
            request.setAttribute("ErrorMsg", "Please login");
            request.getRequestDispatcher("index.html").forward(request, response);
            return false;
        } else
            return true;
    }
}
