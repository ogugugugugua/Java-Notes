<%--
  Created by IntelliJ IDEA.
  User: XIE Yulin
  Date: 26/01/2020
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
    <h3>index</h3>
<%--    <a href="user/fileupload1">fileupload</a>--%>
    <form action="user/fileupload1" method="post" enctype="multipart/form-data">
        chose file: <input type="file" name="upload"/><br/>
        <input type="submit" name="Submit"/>
    </form>
</body>
</html>
