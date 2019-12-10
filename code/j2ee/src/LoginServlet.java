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
        String html = null;
        if("admin".equals(name) && "123".equals(password)){
            html = "<div style='color:green'>登录成功! Login réussi! </div>";
        }else {
            html = "<div style='color:red'>FAILED!</div>";
        }
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(html);

        //分别使用服务器或客户端响应调取不同的html文件进行显示
//        if("admin".equals(name) && "123".equals(password)){
//            request.getRequestDispatcher("success.html").forward(request,response);
//            System.out.println("server side jump");
//        }else {
//            response.sendRedirect("fail.html");
//            System.out.println("client side jump");
//        }
        System.out.println("浏览器发出请求时的完整URL，包括协议 主机名 端口(如果有): " + request.getRequestURL());
        System.out.println("浏览器发出请求的资源名部分，去掉了协议和主机名: " + request.getRequestURI());
        System.out.println("请求行中的参数部分: " + request.getQueryString());
        System.out.println("浏览器所处于的客户机的IP地址: " + request.getRemoteAddr());
        System.out.println("浏览器所处于的客户机的主机名: " + request.getRemoteHost());
        System.out.println("浏览器所处于的客户机使用的网络端口: " + request.getRemotePort());
        System.out.println("服务器的IP地址: " + request.getLocalAddr());
        System.out.println("服务器的主机名: " + request.getLocalName());
        System.out.println("得到客户机请求方式: " + request.getMethod());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{

    }
}
