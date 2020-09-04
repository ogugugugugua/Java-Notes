package yulin.cookie;

import yulin.pojo.person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * 提供了使用session进行在服务器端进行数据共享交互的功能
 * 使用唯一的session.getId进行会话识别
 * 这里是读 数据
 */
public class SessionTest2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码问题
//        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession();

        person pojo = (person) session.getAttribute("pojo");
        System.out.println(pojo);

        String id = session.getId();
        resp.getWriter().write("session id 是 "+id);

    }
}
