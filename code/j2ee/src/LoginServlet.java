import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class LoginServlet extends HttpServlet {
    public LoginServlet(){
        System.out.println("Instanced！！！！！！！！");
    }
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        String name = request.getParameter("name");
//        byte[] bytes = name.getBytes(StandardCharsets.ISO_8859_1);
//        name = new String(bytes, StandardCharsets.UTF_8);
        String password = request.getParameter("password");

        System.out.println("name: "+name);
        System.out.println("password: "+password);

        String html = null;
        if("admin".equals(name) && "123".equals(password)){
            html = "<div style='color:green'>登录成功! Login réussi! </div>";
        }else {
            html = "<div style='color:red'>FAILED!</div>";
        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(html);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{

    }
}
