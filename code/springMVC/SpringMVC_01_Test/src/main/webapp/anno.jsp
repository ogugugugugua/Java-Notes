<%--
  Created by IntelliJ IDEA.
  User: XIE Yulin
  Date: 25/01/2020
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>anno</title>
</head>
<body>
<%--测试@RequestParam注解--%>
    <h3>RequestParam</h3>
    <a href="Anno/testRequestParam?username=test">RequestParam</a>

    <br/>

<%--测试@RequestBody注解--%>
    <form action="Anno/testRequestBody" method="post">
        Name: <input type="text" name="username"/><br/>
        Age: <input type="text" name="age"/><br/>
        <input type="submit" value="SUBMIT"/>
    </form>

<%--测试@PathVariable注解--%>
    <br/>
    <a href="Anno/testPathVariable/1000">testPathVariable</a>

<%--测试@RequestHeader注解--%>
    <br/>
    <a href="Anno/testRequestHeader">testRequestHeader</a>

</body>
</html>
