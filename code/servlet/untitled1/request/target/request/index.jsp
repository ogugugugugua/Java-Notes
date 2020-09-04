<%--
  Created by IntelliJ IDEA.
  User: XIE Yulin
  Date: 13/04/2020
  Time: 11:22

  BUG:
  <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
  上面这里的isELIgnored="false"非常非常非常重要，如果没有这句话的话，${pageContext.request.contextPath}就不会被识别成表达式，
  而是变成直接的字符串，就会导致错误

--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--上面这里的isELIgnored="false"非常非常非常重要，如果没有这句话的话，${pageContext.request.contextPath}就不会被识别成表达式，而是变成直接的字符串--%>

<html>
<head>
    <title>login</title>
</head>
<body>
<%--<h1>Login page</h1>--%>
<div>
<%--    <form action="/request_war_exploded/login" method="post">     &lt;%&ndash; ok &ndash;%&gt;--%>
    <form action="${pageContext.request.contextPath}/login" method="post">  <%-- ok --%>
        name:
        <input type="text" name="name"> <br>
        password:
        <input type="password" name="password"> <br>
        hobbies:
        <input type="checkbox" name="hob" value="1">1
        <input type="checkbox" name="hob" value="2">2
        <input type="checkbox" name="hob" value="3">3
        <input type="checkbox" name="hob" value="4">4
        <input type="checkbox" name="hob" value="5">5

        <input type="submit">

</form>
</div>

</body>
</html>
