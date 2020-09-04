package yulin.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class CookieTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if(cookies[i].getName().equals("time")){
                cookies[i].setMaxAge(5*60); //seconds
                Date date = new Date(Long.parseLong(cookies[i].getValue()));
                resp.getWriter().write("time: " + date);
            }
            resp.addCookie(new Cookie("time", System.currentTimeMillis() + ""));
        }
    }
}
