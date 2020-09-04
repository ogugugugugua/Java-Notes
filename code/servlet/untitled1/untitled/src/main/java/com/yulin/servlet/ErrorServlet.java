package com.yulin.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 这里演示404页面的个性化编写
 * 由于
 * <servlet>
 *     <servlet-name>ErrorServlet</servlet-name>
 *     <servlet-class>com.yulin.servlet.ErrorServlet</servlet-class>
 *   </servlet>
 *   <servlet-mapping>
 *     <servlet-name>ErrorServlet</servlet-name>
 *     <url-pattern>/</url-pattern>
 *   </servlet-mapping>
 * 的原因，所有默认访问地址都会被拦截进入404，除了那些特别指定的servlet会具有更高的优先级
 */
public class ErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.print("<h1>404</h1>");
        String username = req.getParameter("username");
        writer.print(username);
    }
}
