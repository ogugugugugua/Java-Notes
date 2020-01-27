<%--
  Created by IntelliJ IDEA.
  User: XIE Yulin
  Date: 25/01/2020
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>testResponse</title>

    <script src="js/jquery.min.js"></script>

<%--    <script>--%>
<%--&lt;%&ndash;        如果单纯这样写，按钮是没有反应的，原因是web.xml里面的DispatcherServlet把静态资源都拦截了&ndash;%&gt;--%>
<%--        $(function () {--%>
<%--            $("#btn").click(function () {--%>
<%--                alert("hello button!");--%>
<%--            });--%>
<%--        });--%>
<%--    </script>--%>

    <script>
        // 页面加载，绑定单击事件
        $(function(){
            $("#btn").click(function(){
                alert("hello btn");
                // 发送ajax请求
                // $.ajax({
                //     // 编写json格式，设置属性和值
                //     url:"user/testAjax",
                //     contentType:"application/json;charset=UTF-8",
                //     data:'{"username":"hehe","password":"123","age":30}',
                //     dataType:"json",
                //     type:"post",
                //     success:function(data){
                //         // data服务器端响应的json的数据，进行解析
                //         alert(data);
                //         alert(data.username);
                //         alert(data.password);
                //         alert(data.age);
                //     }
                // });

            });
        });

    </script>

</head>
<body>
<%--    基本的，测试返回一些字符串--%>
    <a href="user/testString">testString</a>

    <br/>

    <a href="user/testVoid">testVoid</a>

    <br/>

    <a href="user/testForwardOrRedirect">testForwardOrRedirect</a>

    <br/>
    <button id="btn">发送ajax请求</button>
</body>
</html>
