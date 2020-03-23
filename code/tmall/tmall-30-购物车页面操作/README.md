## Tmall项目

### 购物车页面操作

------------------------
购物流程图：
![6359](https://user-images.githubusercontent.com/17522733/77069132-27d7ba80-69e8-11ea-9339-c23acdabcad4.png)

用于在购物车页面中进行增减或者删除操作


-------------------------
Bug记录：

由于在cartPage.jsp中代码
```jsp
var page = "forechangeOrderItem";
        $.post(
            page,
            {"pid":pid,"number":num},
            function(result){
                if("success"!=result){
                    location.href="login.jsp";
                }
            }
        );
```
传递的参数是number，所以在ForeController中
```java
public String changeOrderItem(int pid, int number, HttpSession session, Model model)
```
接受的参数名字必须是number才能对应上。
最开始使用了num就会导致传入的参数无法得到识别。
