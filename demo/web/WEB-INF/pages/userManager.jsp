<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/17
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>用户管理</title>
        <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
        <script src="scripts/app/userManager.js"></script>
        <link rel="stylesheet" type="text/css" href="styles/app/table.css" />
    </head>
    <body onload="searchAllUser()">
        <div class="content">
            <div class="page_title">
            </div>
            <div class="page_content">
                <table>
                    <tr>
                        <th>id</th>
                        <th>公司id</th>
                        <th>姓名</th>
                        <th>密码</th>
                    </tr>
                    <c:forEach items="${sessionScope.userList}" var="user" begin="0">
                        <tr>
                            <td><c:out value="${user.id}"/></td>
                            <td><c:out value="${user.companyId}"/></td>
                            <td><c:out value="${user.name}"/></td>
                            <td><c:out value="${user.pass}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <form action="UserServlet" method="post">
                id：<input type="text" name="id" />
                用户名：<input type="text" name="name" />
                密码：<input type="text" name="pass" />
                <input type="text" name="type" value="2" style="visibility: hidden"/>
                <button type="submit">修改用户</button>
            </form><br/>
            <form action="UserServlet" method="post">
                id：<input type="text" name="id" />
                <input type="text" name="type" value="3" style="visibility: hidden"/>
                <button type="submit">删除用户</button>
            </form>
        </div>
    </body>
</html>
