package main.java.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/11/15.
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "uploadFile";

    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean ismultipart = ServletFileUpload.isMultipartContent(request);
        if (ismultipart) {
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
            fileItemFactory.setSizeThreshold(MEMORY_THRESHOLD);
            // 设置临时存储目录
            fileItemFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            // 设置最大文件上传值
            servletFileUpload.setFileSizeMax(MAX_FILE_SIZE);
            // 设置最大请求值 (包含文件和表单数据)
            servletFileUpload.setSizeMax(MAX_REQUEST_SIZE);
            // 中文处理
            servletFileUpload.setHeaderEncoding("UTF-8");
            // 构造临时路径来存储上传的文件
            // 这个路径相对当前应用的目录
            String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;
            // 如果目录不存在则创建
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            try {
                // 解析请求的内容提取文件数据
                @SuppressWarnings("unchecked")
                List<FileItem> formItems = servletFileUpload.parseRequest(request);
                if (formItems != null && formItems.size() > 0) {
                    // 迭代表单数据
                    for (FileItem item : formItems) {
                        // 处理不在表单中的字段
                        if (!item.isFormField()) {
                            String fileName = new File(item.getName()).getName();
                            //fileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
                            String filePath = uploadPath + File.separator + fileName;
                            File storeFile = new File(filePath);
                            // 保存文件到硬盘
                            item.write(storeFile);
                            response.setContentType("text/html");
                            response.setCharacterEncoding("utf-8");
                            PrintWriter writer = response.getWriter();
                            writer.print("<h1>上传成功，地址为" + filePath + "</h1>");
                        }
                    }
                }

                Iterator iterator = servletFileUpload.parseRequest(request).iterator();
                while (iterator.hasNext()) {
                    FileItem fileItem = (FileItem) iterator.next();
                    if (!fileItem.isFormField()) {
                        String fileName = fileItem.getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File file = new File(filePath);
                        fileItem.write(file);
                        response.setContentType("text/html");
                        response.setCharacterEncoding("utf-8");
                        PrintWriter writer = response.getWriter();
                        writer.print("<h2>上传成功，地址为" + filePath + "</h2>");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
