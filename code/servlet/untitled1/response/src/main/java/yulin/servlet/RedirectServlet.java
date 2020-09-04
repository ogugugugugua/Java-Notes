package yulin.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * !ATTENTION
 * 重定向：一定要注意在输入路径的时候加上ContextPath()，
 * 否则将会出现重定向后缺少一级路径的问题。
 *
 * ContextPath获取方式：
 * 1.servletContext.getContextPath()
 * 2.req.getContextPath()
 */
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        String contextPath = servletContext.getContextPath();
        System.out.println(contextPath+" : contextPath");
        System.out.println(req.getContextPath()+" : contextPath2");
        resp.sendRedirect(contextPath+"/b2");

    }
}
