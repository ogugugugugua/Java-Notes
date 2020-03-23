## Tmall项目

### 登录状态拦截器

------------------------
购物流程图：
![6359](https://user-images.githubusercontent.com/17522733/77069132-27d7ba80-69e8-11ea-9339-c23acdabcad4.png)


查看购物车页面有个问题，必须建立在已经登录的状态之上。 如果没有登录，而访问地址：
http://127.0.0.1:8080/tmall_ssm/forecart
会导致异常产生。
![](https://stepimagewm.how2j.cn/6385.png)
这个拦截器用于过滤那些需要登录的操作，如果当前状态是尚未登录，则会跳转到登录页面
```java
if(null==user){
    response.sendRedirect("loginPage");
    return false;
}
```
具体解释在代码的注释中