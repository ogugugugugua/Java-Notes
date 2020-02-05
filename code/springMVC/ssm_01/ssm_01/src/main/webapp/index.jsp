<%--
  Created by IntelliJ IDEA.
  User: XIE Yulin
  Date: 03/02/2020
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
    <h3>index</h3>
    <a href="account/findAll">TestFindAll</a>

    <br/><br/>

    <h3>TestSave</h3>

    <form action="account/save" method="post">
        Name:<input type="text" name="name"/><br/>
        Money:<input type="text" name="money"/><br/>
        <input type="submit" name="save"/><br/>
    </form>
</body>
</html>
