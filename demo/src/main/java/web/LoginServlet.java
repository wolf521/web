package main.java.web;

import main.java.model.Student;
import main.java.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Administrator on 2017/11/15.
 */
public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String account = request.getParameter("account");
        String pass = request.getParameter("pass");
        PrintWriter writer = response.getWriter();
        request.getSession().setAttribute("user",new User());
        request.getSession().removeAttribute("user");
        request.getSession().setAttribute("student",new Student());
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>测试</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Hello World</h1>");
        writer.println("</body>");
        writer.println("</html>");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
