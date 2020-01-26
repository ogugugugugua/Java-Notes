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
    <h3>index page</h3><br/><br/>


    <%--  传统方式上传  --%>
    <h4>Traditional upload method:</h4>
    <form action="user/fileupload1" method="post" enctype="multipart/form-data">
        chose file: <input type="file" name="upload"/><br/>
        <input type="submit" name="Submit"/>
    </form><br/><br/><br/><br/>

    <%--  SpringMVC方式上传  --%>
    <h4>SpringMVC upload method:</h4>
    <form action="user/fileupload2" method="post" enctype="multipart/form-data">
        chose file: <input type="file" name="upload"/><br/>
        <input type="submit" name="Submit"/>
    </form>


</body>
</html>
