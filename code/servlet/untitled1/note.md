```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
	version="4.0">

</web-app>
```


untitled1：
父项目

untitled：
1、演示基本的利用HttpServletResponse进行数据返回，在网页中输出字符串
2、演示404页面的个性化编写
3、优先级学习

untitled2：
1、演示利用ServletContext进行数据的共享

untitled3：
1、演示资源读取，重点难点在路径的获取，classes文件夹所在层为/路径

response：
1、演示重定向：一定要注意在输入路径的时候加上ContextPath()，否则将会出现重定向后缺少一级路径的问题。
2、演示请求转发：相当于在一个共同的ServletContext下进行Servlet的请求转发，直接指定对应Servlet名字即可

request：
1、利用HttpServletRequest的getParameter或者getParameterValues函数接受从index.jsp中传过来的数据，并在命令行输出
2、进行重定向以模拟登录成功效果