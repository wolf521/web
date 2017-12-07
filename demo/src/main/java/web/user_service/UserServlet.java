package main.java.web.user_service;

import main.java.model.ResponseResult;
import main.java.model.User;
import main.java.util.ErrorException;
import main.java.web_service.web_user_service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/11/17.
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "uploadFile";

    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        switch (type){
            case "0":searchAllUser(request, response);break;
            case "1":insertUser(request, response);break;
            case "2":updateUser(request, response);break;
            case "3":deleteUser(request, response);break;
            case "4":exportExcelUser(request, response);break;
            case "5":importUserInfo(request, response);break;
        }
    }

    /**
     * 查询所有用户
     *
     * @param request
     * @param response
     */
    public void searchAllUser(HttpServletRequest request, HttpServletResponse response) {
        ResponseResult responseResult = userService.searchAllUser();
        HttpSession session = request.getSession();
        session.setAttribute("userList", responseResult.getData());
        try {
            request.getRequestDispatcher("/userManager").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新建用户
     *
     * @param request
     * @param response
     */
    public void insertUser(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        String name = request.getParameter("name");
//        try{
//            name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
//        } catch (UnsupportedEncodingException e){
//            e.printStackTrace();
//        }
        user.setName(name);
        userService.insertUser(user);
        ResponseResult responseResult = userService.searchAllUser();
        HttpSession session = request.getSession();
        session.setAttribute("userList", responseResult.getData());
        try {
            request.getRequestDispatcher("/userManager").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改用户
     *
     * @param request
     * @param response
     */
    public void updateUser(HttpServletRequest request, HttpServletResponse response)throws NullPointerException{
        User user = new User();
        user.setId(request.getParameter("id"));
        user.setName(request.getParameter("name"));
        user.setPass(request.getParameter("pass"));
        userService.updateUser(user);
        ResponseResult responseResult = userService.searchAllUser();
        HttpSession session = request.getSession();
        session.setAttribute("userList", responseResult.getData());
        try {
            request.getRequestDispatcher("/userManager").forward(request, response);
        } catch (Exception e) {
            session.setAttribute("errorMessage", "服务器异常，请稍后重试");
            throw new ErrorException();
        }
    }

    /**
     * 删除用户
     *
     * @param request
     * @param response
     */
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setId(request.getParameter("id"));
        userService.deleteUser(user);
        ResponseResult responseResult = userService.searchAllUser();
        HttpSession session = request.getSession();
        session.setAttribute("userList", responseResult.getData());
        try {
            request.getRequestDispatcher("/userManager").forward(request, response);
        } catch (Exception e) {
            session.setAttribute("errorMessage", "服务器异常，请稍后重试");
            throw new ErrorException();
        }
    }

    /**
     * 导出用户信息
     */
    public void exportExcelUser(HttpServletRequest request, HttpServletResponse response){
        try{
            String fileName = "用户信息";
            // 获取输出流
            OutputStream outputStream = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setCharacterEncoding("utf-8");
            fileName = URLEncoder.encode(fileName,"utf-8");
            response.setHeader("Content-Disposition","attachment;filename="+fileName+".xls");
            response.setContentType("application/msexcel");
            userService.exportExcelUser(outputStream,request.getServletContext().getRealPath("./") + File.separator+"images/1.png");
        } catch (Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "服务器异常，请稍后重试");
            throw new ErrorException();
        }
    }

    /**
     * 导入用户信息
     */
    public void importUserInfo(HttpServletRequest request, HttpServletResponse response){
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
                Iterator iterator = servletFileUpload.parseRequest(request).iterator();
                while (iterator.hasNext()) {
                    FileItem fileItem = (FileItem) iterator.next();
                    if (!fileItem.isFormField()) {
                        String fileName = fileItem.getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File file = new File(filePath);
                        fileItem.write(file);
                        ResponseResult responseResult = userService.importUserInfo(file);
                        response.setContentType("text/html");
                        response.setCharacterEncoding("utf-8");
                        PrintWriter writer = response.getWriter();
                        writer.print(responseResult.getMessage());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
