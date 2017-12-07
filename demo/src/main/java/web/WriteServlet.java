package main.java.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Administrator on 2017/11/15.
 */
@WebServlet(name = "WriteServlet",urlPatterns = {"/WriteServlet"})
public class WriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String content = request.getParameter("content");
        PrintWriter writer = response.getWriter();
        //获取绝对路径
        File uploadFolder = new File("d://fileUpload");
        //判断路径是否存在
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        //获取文件
        File file = new File(uploadFolder, "file.txt");
        //判断路径是否存在
        if (!file.exists()) {
            file.createNewFile();
        }
        if(file.exists()){
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
            writer.println("<h1>文件写入成功</h1>");
        }
    }
}
