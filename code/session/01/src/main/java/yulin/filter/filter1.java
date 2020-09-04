package yulin.filter;

import javax.servlet.*;
import java.io.IOException;

public class filter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init....");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("destroy....");
    }
}
