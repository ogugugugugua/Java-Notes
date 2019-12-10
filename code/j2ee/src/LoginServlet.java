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
        String password = request.getParameter("password");

        System.out.println("name: "+name);
        System.out.println("password: "+password);

        //直接返回响应输出字符串
//        String html = null;
//        if("admin".equals(name) && "123".equals(password)){
//            html = "<div style='color:green'>登录成功! Login réussi! </div>";
//        }else {
//            html = "<div style='color:red'>FAILED!</div>";
//        }
//        response.setContentType("text/html; charset=UTF-8");
//        PrintWriter printWriter = response.getWriter();
//        printWriter.println(html);

        //分别使用服务器或客户端响应调取不同的html文件进行显示
        if("admin".equals(name) && "123".equals(password)){
            request.getRequestDispatcher("success.html").forward(request,response);
            System.out.println("server side jump");
        }else {
            response.sendRedirect("fail.html");
            System.out.println("client side jump");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{

    }
}
