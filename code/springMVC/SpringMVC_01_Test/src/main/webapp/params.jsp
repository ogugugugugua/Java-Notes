<%--
  Created by IntelliJ IDEA.
  User: XIE Yulin
  Date: 19/01/2020
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>params</title>
</head>
<body>
<%--    <a href="params/simpleTest?name=testname&pwd=testpwd">参数绑定</a>--%>


    <form action="params/AccountTest" method="post">
        Name:<input type="text" name="name"/><br/>
        Password:<input type="text" name="pwd"/><br/>
        Salary:<input type="text" name="salary"/><br/>

        personInfo.age:<input type="text" name="personInfo.age"/><br/> <!--注意这里使用了personInfo.age来定义A类中的B类成员-->
        personInfo.sex:<input type="text" name="personInfo.sex"/><br/> <!--注意这里使用了personInfo.sex来定义A类中的B类成员-->

        list[0].age:<input type="text" name="list[0].age"/><br/>
        list[0].sex:<input type="text" name="list[0].sex"/><br/>
        map['first'].age:<input type="text" name="map['first'].age"/><br/>
        map['first'].sex:<input type="text" name="map['first'].sex"/><br/>

        Date:<input type="text" name="date"/><br/>

        <input type="submit" value="Submit">
    </form>


</body>
</html>
