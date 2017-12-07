package main.java.web.javamail;

import main.java.service.TextMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/12/7.
 */
@WebServlet(name = "EmailServlet", urlPatterns = {"/EmailServlet"})
public class EmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String to = request.getParameter("to");
        String from = request.getParameter("from");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        subject = new String(subject.getBytes("iso-8859-1"), "utf-8");
        content = new String(content.getBytes("iso-8859-1"), "utf-8");
        TextMail mail = new TextMail(to, from, subject, content);
        boolean flag = mail.send();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        if (flag) {
            writer.println("<h1>邮件发送成功</h1>");
        } else {
            writer.println("<h1>邮件发送失败</h1>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
