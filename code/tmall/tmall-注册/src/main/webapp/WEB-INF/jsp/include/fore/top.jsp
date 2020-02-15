<%--
  Created by IntelliJ IDEA.
  User: XIE Yulin
  Date: 14/02/2020
  Time: 22:35

  置顶导航页面
    这里会根据用户是否登录，决定是否显示退出按钮，或者登录注册按钮，以及购物车中的商品数量。

    至于${contextPath} 和 ${user} 是哪里来的，放在了后面的拦截器里讲解啦：
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<nav class="top ">
    <a href="forehome">
        <span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-home redColor"></span>
        天猫首页
    </a>

    <span>喵，欢迎来天猫</span>

    <c:if test="${!empty user}">                    <!--已经登录状态-->
        <a href="loginPage">${user.name}</a>
        <a href="forelogout">退出</a>
    </c:if>

    <c:if test="${empty user}">                     <!--尚未登录状态-->
        <a href="loginPage">请登录</a>
        <a href="registerPage">免费注册</a>          <!--超链接PageController中的注册页面跳转方法-->
    </c:if>

    <span class="pull-right">                       <!--到订单以及购物车的链接-->
            <a href="forebought">我的订单</a>
            <a href="forecart">
            <span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-shopping-cart redColor"></span>
            购物车<strong>${cartTotalItemNumber}</strong>件</a>
        </span>

</nav>