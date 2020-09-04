<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: XIE Yulin
  Date: 02/04/2020
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>
<html xmlns:th="http://www.thymeleaf.org" xmlns:c="http://java.sun.com/jstl/core">
<head>
    <title>index</title>
    <%--    <script src="vue.js" type="text/javascript"></script>--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/vue/1.0.18/vue.min.js"></script>
</head>
<body>
<h1>index</h1>
<div id="tab">
    <table style="color: red" border="1px">
        <tr>
            <td>{{testVar}}</td>
            <td>hey2</td>
            <td>hey3</td>
        </tr>
    </table>
</div>

<div id="app">
    {{message}}
</div>

<div>
    <h3>Data from backend</h3>
    <h4>${name}</h4>
    <h4>${age}</h4>
    <h4>${user}</h4>
    <h4>${users}</h4>
</div>

<div>
    <table>
        <thead>
        <tr>
            <td>ID</td>
            <td>UserName</td>
            <td>Note</td>
            <td>Item</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.userName}</td>
                <td>${item.getNote()}</td>
                <td>${item}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<br><br><br><br>
<h1>-----------------</h1>

<c:forEach items="${users}" var="item">
    ${item.id}
    ${item.userName}
    ${item.getNote()}
    ${item}
</c:forEach>


<script>
    var data4tab = {
        beans: [],
        testVar: 'hey'
    };
    var tab = new Vue({
        el: '#tab',
        data: data4tab
    });
    var app = new Vue({
        el: '#app',
        data: {message: 'hello'}
    });
</script>
</body>
</html>
