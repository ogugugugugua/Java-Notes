package com.yulin.tmall.interceptor;

import com.yulin.tmall.pojo.Category;
import com.yulin.tmall.pojo.OrderItem;
import com.yulin.tmall.pojo.User;
import com.yulin.tmall.service.CategoryService;
import com.yulin.tmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OtherInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /*这里是获取分类集合信息，用于放在搜索栏下面*/
        List<Category> categoryList = categoryService.list();
        request.getSession().setAttribute("cs", categoryList);

        /*这里是获取当前的contextPath:tmall_29__war_exploded,用与放在左上角那个变形金刚，点击之后才能够跳转到首页，否则点击之后也仅仅停留在当前页面*/
        HttpSession session = request.getSession();
        String ntextPath = session.getServletContext().getContextPath();
        request.getSession().setAttribute("contextPath", ntextPath);

        User user = (User) session.getAttribute("user");
        int cartTotalItemNumber = 0;
        if(null!=user){
            List<OrderItem> orderItemList = orderItemService.listByUser(user.getId());
            for (OrderItem orderItem : orderItemList) {
                cartTotalItemNumber += orderItem.getNumber();
            }
        }
        request.getSession().setAttribute("cartTotalItemNumber", cartTotalItemNumber);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
