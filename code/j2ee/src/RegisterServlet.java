import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
//        req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        System.out.println("获取单值参数name: " + req.getParameter("name"));

        String[] hobits = req.getParameterValues("hobits");
        System.out.println("获取具有多值的参数hobits: " + Arrays.asList(hobits));

        Map<String, String[]> map = req.getParameterMap();
        Set<String> parameterName = map.keySet();
        for (String s : parameterName) {
            String[] value = map.get(s);
            System.out.println(s+" : "+ Arrays.asList(value));
        }

        //直接返回响应输出字符串
        String html1 = "Get it!";
        resp.setContentType("text/html1; charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(html1);
    }
}
