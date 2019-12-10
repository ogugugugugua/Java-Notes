import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class HelloServlet extends HttpServlet {
    public void init(ServletConfig servletConfig){
        System.out.println("initialization of hello servlet");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try{
            response.getWriter().println("<h1>Polytech!</h1>");
            response.getWriter().println(new Date().toLocaleString());
            response.getWriter().println("<h1>Polytech!</h1>");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
