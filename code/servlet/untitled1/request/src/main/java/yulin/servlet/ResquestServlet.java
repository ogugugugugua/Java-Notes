package yulin.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * 接受从index.jsp中传过来的数据，并在命令行输出
 */
public class ResquestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String[] hobs = req.getParameterValues("hob");
        System.out.println("---------------------");
        System.out.println(name);
        System.out.println(password);
        System.out.println(Arrays.toString(hobs));
        System.out.println("---------------------");
        resp.sendRedirect(req.getContextPath()+"/success.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
