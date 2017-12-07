<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/7
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>email</title>
</head>
<body>
    <div style="align:center">
        <form action="EmailServlet" method="post">
            收信人：<input type="text" name="to"/><br/>
            发信人：<input type="text" name="from" value="15953185712@163.com"/><br/>
            主  题：<input type="text" name="subject"/><br/>
            内  容：<textarea rows="6" cols="38" name="content"></textarea><br/>
            <input type="submit" value="发送"/>
            <input type="reset" name="取消"/>
        </form>
    </div>
</body>
</html>
