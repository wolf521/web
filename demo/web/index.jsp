<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/15
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
    <from action="/LoginServlet" method="post">
        <input type="text" name="account" />
        <input type="text" name="pass" />
        <input type="submit" />
    </from>
</body>
</html>
