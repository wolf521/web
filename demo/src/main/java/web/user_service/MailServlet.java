package main.java.web.user_service;

import main.java.util.EmailHelper;
import main.java.util.ErrorException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/17.
 */
@WebServlet(name = "MailServlet",urlPatterns = {"/MailServlet"})
public class MailServlet extends HttpServlet {
    private EmailHelper emailHelper = new EmailHelper();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mailAccount = request.getParameter("mailAccount");
        String mailContent = request.getParameter("mailContent");
        Map<String,String> mailMap = new HashMap<String,String>();
        mailMap.put(mailAccount,mailContent);
        try{
            emailHelper.sendHtmlMail(mailMap);
        } catch (Exception e){
            request.getSession().setAttribute("errorMessage", "服务器异常，请稍后重试");
            throw new ErrorException();
        }
    }
}
