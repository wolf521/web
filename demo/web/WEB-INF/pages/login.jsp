<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/15
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <body>
        在线人数：<%=session.getAttribute("userNumber") %><br/>
        <%=session.getAttribute("student") %><br/>
        </body>
        <form action="LoginServlet" method="post">
            用户名：<input type="text" name="account"/><br/>
            密码：<input type="text" name="pass"/>
            <button type="submit">登录</button>
        </form>
        <form action="LoginServlet" method="post">
            用户名：<input type="text" name="account"/><br/>
            密码：<input type="password" name="pass"/>
            确认密码：<input type="password" name="pass"/>
            邮箱账号：<input type="text" name="pass"/>
            <button>获取验证码</button>
            验证码：<input type="text" name="pass"/>
            <button type="submit">注册</button>
        </form>
        <a href="/WriteServlet?content='content'">写文件</a><br/>
        <a href="/ReadServlet">读文件</a><br/>
        <form action="UploadServlet" method="post" enctype="multipart/form-data">
            文件：<input type="file" name="file"/>
            <button type="submit">上传</button>
        </form>
        <br/>
        <a href="/DownloadServlet?fileName=zq.doc">下载</a><br/>
        <a href="/CookieServlet">查看cookie</a><br/>
        <a href="/SessionServlet">查看Session</a><br/>
        <a href="/UserServlet?type=0">查询用户</a><br/>
        <form action="UserServlet" method="post">
            用户名：<input type="text" name="name"/>
            <input type="text" name="type" value="1" style="visibility: hidden"/>
            <button type="submit">添加用户</button>
        </form>
        <form action="MailServlet" method="post">
            邮箱账号：<input type="text" name="mailAccount"/><br/>
            内容：<textarea cols="20px" rows="5px" name="mailContent"></textarea><br/>
            <button type="submit">发送邮件</button>
        </form>
        <a href="/DatabaseConnectionPoolServlet">数据库连接池</a><br/>
        <a href="/UserServlet?type=4">导出用户信息</a><br/>
        <form action="UserServlet?type=5" method="post" enctype="multipart/form-data">
            文件：<input type="file" name="file"/>
            <button type="submit">入用户信息</button>
        </form>
        <br/>
        <a href="/xml">xml操作</a><br/>
        <a href="/email">发送邮件</a><br/>
    </body>
</html>
