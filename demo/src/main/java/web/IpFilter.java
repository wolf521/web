package main.java.web;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/16.
 */
public class IpFilter implements javax.servlet.Filter {
    protected FilterConfig config;
    protected String ip;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String remoteIp = req.getRemoteAddr();
        if (ip.equals(remoteIp)) {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            resp.getWriter().print("<h1>ip已被禁用</h1>");
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        this.ip = this.config.getInitParameter("ip");
    }
}
