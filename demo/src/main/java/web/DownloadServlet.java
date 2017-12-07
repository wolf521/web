package main.java.web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Administrator on 2017/11/15.
 */
@WebServlet(name = "DownloadServlet", urlPatterns = {"/DownloadServlet"})
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取文件名
        String filename = request.getParameter("fileName");
        //防止读取name名乱码
        filename = new String(filename.getBytes("iso-8859-1"), "utf-8");
        //获取要下载的文件绝对路径
        String downloadPath = request.getServletContext().getRealPath("/uploadFile/" + filename);
        File file = new File(downloadPath);
        if(!file.exists()){
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            writer.print("<h2>文件不存在</h2>");
            return;
        }
        //设置文件MIME类型
        response.setContentType(getServletContext().getMimeType(filename));
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        //输入流为项目文件，输出流指向浏览器
        InputStream is = new FileInputStream(file);
        ServletOutputStream os = response.getOutputStream();
        /*
         * 设置缓冲区
        * is.read(b)当文件读完时返回-1
        */
        int len = -1;
        byte[] b = new byte[1024];
        while ((len = is.read(b)) != -1) {
            os.write(b, 0, len);
        }
        //关闭流
        is.close();
        os.close();
    }
}
