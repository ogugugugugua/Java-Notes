## Tmall项目

### 注册

http://localhost:8080/tmall__war_exploded/registerPage

注意在不同的jsp文件的开头段
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
           pageEncoding="UTF-8" isELIgnored="false"%>
```
中的contentType字符串需要保持一致，否则会出现500的重复定义错误。 