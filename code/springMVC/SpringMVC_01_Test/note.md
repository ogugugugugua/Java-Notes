## 入门案例整体流程解析

先明确需求：
![02](https://user-images.githubusercontent.com/17522733/72682316-0d5a9100-3acc-11ea-98f7-ab6a93a9597d.png)


#### 1.启动服务器，加载一些配置文件
- 在web.xml中配置一个前端控制器，本质是servlet：
- _DispatcherServlet对象创建_ （由于有`<load-on-startup>1</load-on-startup>`的存在，所以在服务器启动的时候就会被创建。）
- 那么这个DispatcherServlet对象创建后，`<init-param>`决定了它就可以加载SpringMVC.xml的配置文件。
- 在 _SpringMVC.xml_ 文件中，`<context:component-scan base-package="com.yulin.controller"/>`开启了注解扫描，使得 _HelloController_ 这个类也
能够被实例化，把该对象加载进IOC容器中。
- 同时，在SpringMVC.xml文件中InternalResourceViewResolver也被加载进来，这是一个视图解析器。
- 另外还开启了SpringMVC的注解扫描，这个使得HelloController类中的Hello方法上面的`@RequestMapping`注解能够被自动加载，这样使得请求映射也能够生效



![03](https://user-images.githubusercontent.com/17522733/72682314-0d5a9100-3acc-11ea-9b56-9b878eb50dce.png)
#### 2.发送请求，后台处理请求
在index.jsp中有一行`<a href="hello">Click</a>`提供了超链接，发起的请求被`DispatchServlet`拦截，这里的DispatchServlet是一个控制指挥中心的作用。
于是乎，映射器Mapping被调用，对应HelloController上面的`@RequestMapping`，返回一个执行链，即HelloController类实例化对象中的`Hello()`函数。
这个执行链被适配器执行，返回的`success.jsp`在指挥中心调用的视图解析器中被解析，从而返回相应的页面。
![04](https://user-images.githubusercontent.com/17522733/72682315-0d5a9100-3acc-11ea-8f7a-350cdd1affcc.png)
