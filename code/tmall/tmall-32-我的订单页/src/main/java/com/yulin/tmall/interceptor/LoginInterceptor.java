package com.yulin.tmall.interceptor;

import com.yulin.tmall.pojo.User;
import com.yulin.tmall.service.CategoryService;
import com.yulin.tmall.service.OrderItemService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;

    /**
     * 这个过滤器就判断如果不是注册，登录，产品这些，就进行登录校验
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        //准备字符串数组 noNeedAuthPage，存放那些不需要登录也能访问的路径
        String[] noNeedAuthPage = new String[]{
                "home","checkLogin","register","loginAjax","login","product","category","search"
        };
        //获取uri
        String uri = request.getRequestURI();
        //去掉前缀/tmall_28__war_exploded
        uri = StringUtils.remove(uri,contextPath);

        //如果访问的地址是/fore开头
        if(uri.startsWith("/fore")){

            //取出fore后面的字符串，比如是forecart,那么就取出cart
            String method = StringUtils.substringAfterLast(uri,"/fore");

            //判断cart是否是在noNeedAuthPage
            //如果不在，那么就需要进行是否登录验证
            if(!Arrays.asList(noNeedAuthPage).contains(method)){
                //从session中取出"user"对象
                User user = (User) session.getAttribute("user");
                //如果对象不存在，就客户端跳转到login.jsp
                if(null==user){
                    response.sendRedirect("loginPage");
                    return false;
                }
            }
        }
        //否则就正常执行
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object Handler, ModelAndView modelAndView) throws Exception{}

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{}
}
