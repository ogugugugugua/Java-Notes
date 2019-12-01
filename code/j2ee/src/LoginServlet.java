import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        System.out.println("name: "+name);
        System.out.println("password: "+password);

        String htmlResponse = null;
        if("admin".equals(name) && "123".equals(password)){
            htmlResponse = "<div style='color:green'>SUCCESS!</div>";
        }else {
            htmlResponse = "<div style='color:red'>FAILED!</div>";
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.println(htmlResponse);
    }
}
