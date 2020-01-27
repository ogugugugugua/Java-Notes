package com.yulin.controller;

import com.yulin.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     *
     * @param model
     * @return String
     */
    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("testString..");
        User user = new User();
        user.setAge(23);
        user.setName("hey");
        user.setPassword("123");

        /*Model对象*/
        model.addAttribute("user",user);
        return "success";
    }

    /**
     *
     * @return void
     */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("testVoid..");

        /*有以下三种响应方式*/

        /*(1)请求转发*/
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);


        /*(2)重定向*/
        //response.sendRedirect(request.getContextPath()+"/redirect.jsp");


        /*(3)直接进行相应*/
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("直接响应");


        return;
    }


    /**
     * 使用关键字的方式进行转发或者重定向
     * @param
     * @return String
     */
    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect(){
        System.out.println("testForwardOrRedirect..");

        //请求的转发
        //return "forward:/WEB-INF/pages/success.jsp";

        //重定向
        return "redirect:/index.jsp";
    }

}
