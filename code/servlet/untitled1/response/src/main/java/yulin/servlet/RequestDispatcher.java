package yulin.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求转发：相当于在一个共同的ServletContext下进行Servlet的请求转发，直接指定对应Servlet名字即可
 *   -----           -----------            -----
 *   | A |    --->   | Servlet  |   --->    | B |
 *   |   |    <---   | Context  |   <---    |   |
 *   -----           -----------            -----
 */
public class RequestDispatcher extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        servletContext.getRequestDispatcher("/basicServlet").forward(req, resp);
    }
}


