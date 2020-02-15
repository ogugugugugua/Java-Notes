## Tmall项目

### 产品管理

- 
    - 在ProductController.list() 方法中，把&cid= 参数设置到在page对象的param属性上
`page.setParam("&cid="+c.getId());`
    - 在adminPage.jsp页面中通过${page.param}取出这个参数，比如
```xml
<li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>
  <a  href="?page.start=0${page.param}" aria-label="Previous" >
    <span aria-hidden="true">«</span>
  </a>
</li>
```

-
ProductController中有
```java
ProductService productService;
CategoryService categoryService;
```
两个类成员。基本上CategoryService这个成员是用于根据product对象的cid产品获取Category对象，从而将其赋值给Product类中的Category。
ProductService则是用于连接Service层中的各种操作。

- 
ProductServiceImpl类中有一个`CategoryService categoryService;`数据成员，作用是：
```java
public void setCategory(Product p){
    int cid = p.getCid();
    Category c = categoryService.getByID(cid);
    p.setCategory(c);
}
```
通过首先获取Product表单中的Cid，调用CategoryService获取对应的Category，再给当前的Product类设置其所属的Category(后期增加的数据成员)