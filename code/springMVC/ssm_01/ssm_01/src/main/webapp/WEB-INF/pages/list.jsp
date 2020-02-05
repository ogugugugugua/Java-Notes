<%--
  Created by IntelliJ IDEA.
  User: XIE Yulin
  Date: 03/02/2020
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>list</title>
</head>
<body>
    <h3>查询所有账户 success</h3>


    <c:forEach items="${list}" var="account">
        ${account.name}

    </c:forEach>
</body>
</html>
