package main.java.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by Administrator on 2017/11/16.
 */
@WebServlet(name = "SessionServlet",urlPatterns = {"/SessionServlet"})
public class SessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        HttpSession httpSession = request.getSession();
        writer.println("创建时间："+new Date(httpSession.getCreationTime()));
        writer.println("上次访问时间："+new Date(httpSession.getLastAccessedTime()));
        writer.println("id："+httpSession.getId());
        httpSession.setAttribute("name","小芳");
        Enumeration enumeration = httpSession.getAttributeNames();
        while(enumeration.hasMoreElements()){
            String name = (String)enumeration.nextElement();
            String value = httpSession.getAttribute(name).toString();
            writer.print("name:"+name+",value:"+value);
        }
    }
}
