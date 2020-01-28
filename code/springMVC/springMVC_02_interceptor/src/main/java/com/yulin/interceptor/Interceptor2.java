package com.yulin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor2 implements HandlerInterceptor {
    /**
     * 预处理，controller方法执行前
     * return true表示放行，执行下一个拦截器；如果没有下一个拦截器，执行controller中的方法
     * return false表示不放行，则可以：比如通过request/response跳转到某个页面上显示相关信息（比如权限不足）
     * 用途：在这里可以做一些逻辑判断，比如，当用户尚未登录的时候直接跳转到登录页面
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Arrived at Interceptor2 preHandle");

        //直接跳转到error.jsp页面
        //request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request,response);

        return true;
    }

    /**
     * 后处理方法，UserController.testInterceptor方法执行后，success.jsp执行前
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Arrived at Interceptor2 postHandle");
        //当然在这里也可以跳转到其他页面，比如上面preHandle里面的error.jsp
    }

    /**
     * success.jsp页面执行后，该方法执行
     * 用途：比如说可以用来关闭流
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Arrived at Interceptor2 afterCompletion");
        //在这里就不能再跳转到别的页面了
    }
}
