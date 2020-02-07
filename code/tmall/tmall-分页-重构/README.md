## Tmall项目

### 使用分页插件对分页查询进行重构

```xml
<!--分页插件-->
        <property name="plugins">
            <array>
                <bean class="com.github.pagehelper.PageInterceptor">
                    <property name="properties">
                        <value>
                        </value>
                    </property>
                </bean>
            </array>
        </property>
```
在controller中需要先使用 `PageHelper.offsetPage(page.getStart(),page.getCount());`来进行分页限制，然后在进行对象获取即可
```java 
PageHelper.offsetPage(page.getStart(),page.getCount());
List<Category> cs = categoryService.list(); /*获取所有的Category对象并放在cs中*/

int total = (int) new PageInfo<>(cs).getTotal();
page.setTotal(total);
model.addAttribute("cs", cs);
model.addAttribute("page",page);
return "admin/listCategory"; /*服务器跳转到admin/listCategory视图*/
}
```