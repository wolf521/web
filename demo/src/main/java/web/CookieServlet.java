package main.java.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/11/16.
 * cookieservlet
 */
@WebServlet(name = "CookieServlet",urlPatterns = {"/CookieServlet"})
public class CookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        Cookie cookie = new Cookie("name","小芳");
        response.addCookie(cookie);
        Cookie[] cookies = request.getCookies();
        for(Cookie co : cookies ){
            writer.print("<h4>name:"+co.getName()+",value:"+co.getValue()+"</h4>");
        }
    }
}
