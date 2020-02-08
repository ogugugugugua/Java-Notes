## Tmall项目

### 属性管理

1. 
    - 在PropertyController.list() 方法中，把&cid= 参数设置到在page对象的param属性上
`page.setParam("&cid="+c.getId());`
    - 在adminPage.jsp页面中通过${page.param}取出这个参数，比如
```xml
<li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>
  <a  href="?page.start=0${page.param}" aria-label="Previous" >
    <span aria-hidden="true">«</span>
  </a>
</li>
```

