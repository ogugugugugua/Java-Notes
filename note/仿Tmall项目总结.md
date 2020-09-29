# 项目总结

## 前台页面

- 首页
- 分类页 （需要用到mysql的搜索，固定项目）
- 查询结果页（需要用到mysql的搜索，按照输入项目）
- 产品页（图片展示）
- 结算页（表单填写）
- 支付结账页面+支付成功页面（后面可以接入真正的支付模块）
- 购物车页面
- 我的订单页面
- 确认收货页面+确认收货成功页面
- 评价页面
- 登录页面（登录验证功能）
- 注册页面

## 后台页面

- 分类管理
- 分类属性管理
- 产品管理
- 产品属性管理
- 产品图片管理
- 用户管理
- 订单管理

---

## 数据库设计

![6041](https://user-images.githubusercontent.com/17522733/94553782-4fda8d00-0259-11eb-8fa2-50f9be41e5bb.png)

|     表名      |  中文定义  | 含义                                                         |
| :-----------: | :--------: | ------------------------------------------------------------ |
|   Category    |   分类表   | 存放分类信息，包括分类名称，如男装，平板电视，沙发等         |
|   Property    |   属性表   | 存放属性信息，包括属性名称，如重量，颜色，品牌，厂商，型号等 |
| PropertyValue |  属性值表  | 存放属性值信息，如重量是900g，颜色是黄色等                   |
| ProductImage  | 产品图片表 | 存放产品图片信息，包括type即单个或详情图类型                 |
|    Review     |   评价表   | 存放评价信息，包括创建时间和评价本身                         |
|    Product    |   产品表   | 存放产品信息，包括产品名称，原始和优惠价格，库存等           |
|   OrderItem   |  订单项表  | 存放订单项信息，包括购买产品种类，数量等                     |
|     Order     |   订单表   | 存放订单信息，包括订单号，邮寄地址，商品数目，电话号码，订单状态(支付，发货，收货)日期等 |
|     User      |   用户表   | 存放用户信息，包括用户名，密码                               |



在建立表的时候，由于有依赖关系的存在，所以需要先建立被外键指向的表，如Category和User。

- 一个订单对应（包含）多个订单项   ！
- 一个用户对应（包含）多个订单
- 一个用户对应（包含）多个评价
- 一个用户对应（包含）多个订单项   ！
- 一个产品对应（包含）多个评价
- 一个产品对应（包含）多个图片
- 一个产品对应（包含）多个属性值   ！
- 一个属性对应（包含）多个属性值
- 一个分类对应（包含）多个属性       ！
- 一个分类对应（包含）多个产品



`属性，属性值，产品`关系：属性值表有pid指向产品的id，有ptid指向属性的id。属性值表中的每一行都会有关联的那个产品id以及其所属的那个属性id，这样就可以辨别开了。







## 改进

（1）

- 目前：一个产品对应一个 {多种属性值} ，比如黄色，一斤重。
- 若要对应多个{多种属性值}，就意味着：对于该产品的每个属性，在PropertyValue表中具有多条相同pid(产品id) ，相同ptid(属性id)，但是值不同的行。
- 可以在Product中加入一个List，里面存放该商品不同属性的不同属性值id，即PropertyValue的id。
- 然后在购买订单项里面，我们就可以根据这个list获得所购买的产品的具体属性值了。

（2）

- 没有考虑到高并发的情况。使用的jsp技术，动态资源和静态资源全部耦合在一起，服务器压力大。
- 可以使用前后端分离技术，前端服务器负责控制页面引用&跳转&路由，前端页面异步调用后端的接口，后端/应用服务器使用tomcat（把tomcat想象成一个数据提供者），加快整体响应速度。
- 这样debug会更加容易：页面逻辑，跳转错误，浏览器兼容性问题，脚本错误，页面样式等问题，全部由前端工程师来负责。接口数据出错，数据没有提交成功，应答超时等问题，全部由后端工程师来解决。
- 多页面应用同一套接口

（3）

- 目前的mybatis部分使用了MybatisGenerator这个工具，但是据我了解好像有一个mybatisplus框架更加优秀，可以去了解一下

（4）

- 外键约束导致无法删除数据
- 在创建表结构的时候，有外键约束，导致当存在从表数据的时候，主表数据无法被删除。 为什么会这样呢？ 假设即使有从表数据，主表也允许被删除，那么那些从表数据就变成脏数据了。 对于这个问题通常有两种解决办法： 1. 使用级联删除。即删除主表的时候，从表自动删除。 这样做在技术上最简单，但是在业务上最危险，不推荐。 2. 删除有从表数据的主表时，提醒用户依然有从表数据，建议用户一条一条删除从表数据，再删除主表数据。 这样技术上无改动，业务上最安全。 建议采纳此种方案。

（5）增加实际的支付功能

（6）后台管理员登录模块。目前是通过访问指定的url去到后台

（7）Redis集成进去，在 Service 这一层上面做，首次查询数据库，以后用缓存

（8）集成es

---

## 前端技术准备

[HTML], [CSS], [JAVASCRIPT], [JQUERY],[AJAX], [Bootstrap]

其中：BootStrap 是Twitter的工程师开发的前端框架，可以非常方便的设计出好看的页面效果。

---

## 实现细节

- 在产品页，未登录状态时候，点击立即购买，会弹出登录模态窗口

  在产品页，如果已经登录，点击购买，会提交数据到服务端，生成订单项，并且跳转到结算页面

- 在未登录状态，点击加入购物车，会弹出模态窗口

  在已登录状态，点击加入购物车，使用**ajax**异步提交数据到服务端，生成订单项，并且使当前 "加入购物车" 按钮变得不可点击

- 

---

## Jsp包含关系

比如在`listCategory.jsp`中，包含有几个公共的jsp文件：

> \1. <%@include file="../include/admin/adminHeader.jsp"%>
> \2. <%@include file="../include/admin/adminNavigator.jsp"%>
> \3. <%@include file="../include/admin/adminPage.jsp"%>
> \4. <%@include file="../include/admin/adminFooter.jsp"%>

而在`adminHeader.jsp`中：

1. 表示本页面会使用html5的技术 <!DOCTYPE html>
2. jsp指令 `<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>`  charset告诉浏览器使用UTF-8进行中文编码识别，pageEncoding说明当前jsp文件使用UTF-8进行编码，最后isELIgnored代表会使用EL表达式。
3. 引入JSTL：`<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %> ` 使用c和fmt两种标准标签库
4. 预先定义一些判断输入框的函数，方便后面使用

---

## 查询

### 构建步骤

1. Maven格式：选中 org.apache.maven.archetypes:maven-archetype-webapp

2. 新建pojo、新建mapper、新建service、新建serviceImpl

3. 注解@Service声明当前类是一个Service类
   通过自动装配@Autowired引入CategoryMapper ，在list方法中调用CategoryMapper 的list方法.

4. 新建controller：注解@Controller声明当前类是一个控制器、注解@Autowired把CategoryServiceImpl自动装配进了CategoryService 接口

5. 新建mapper.xml，其中的namespace比如是上面mapper的包名，要保持一致。

6. 在resources目录下新建log4j.properties。开启日志，记录运行情况和sql语句等。

7. 在resources目录下新建jdbc.properties，此配置文件给出了访问数据库需要的必须信息（临时）

8. 在resources目录下新建applicationContext.xml【非常重要】：

   1. 启动了对注解的识别`<context:component-scan base-package="com.how2java.tmall.service" />`

   2. 指定对jdbc.properties的引用：`<context:property-placeholder location="classpath:jdbc.properties"/>`

   3. 利用jdbc.properties配置数据源`<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">`

   4. 配置Mybatis的SessionFactory，其中声明了别名，并且使用前面配置的数据源

      ```xml
      <bean id="sqlSession" class="org.mybatis.spring.SqlSessionFactoryBean">
              <property name="typeAliasesPackage" value="com.yulin.tmall.pojo" />
              <property name="dataSource" ref="dataSource"/>
              <property name="mapperLocations" value="classpath:mapper/*.xml"/>
      </bean>
      ```

9. 在resources目录下新建SpringMVC.xml：

   1. 开启注解的识别

      ```xml
      <!--启动注解识别-->
      <context:annotation-config/>
      <context:component-scan base-package="com.yulin.tmall.controller">
          <context:include-filter type="annotation"
                                  expression="org.springframework.stereotype.Controller"/>
      </context:component-scan>
      ```

   2. 开通静态资源的访问，否则访问图片，css,js等文件可能出错：`<mvc:default-servlet-handler />`

   3. 视图定位：定位到/WEB-INF/JSP/*.jsp

   4. 对上传文件的解析

10. 修改在`src/main/webapp/WEB-INF/web.xml`位置的web.xml

    1. 指定spring的配置文件为classpath下的applicationContext.xml

    2. 设置中文过滤器

    3. 指定springMVC配置文件为classpath下的SpringMVC.xml

       ```xml
       <!-- spring mvc核心：分发servlet -->
         <servlet>
           <servlet-name>mvc-dispatcher</servlet-name>
           <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
           <!-- spring mvc的配置文件 -->
           <init-param>
             <param-name>contextConfigLocation</param-name>
             <param-value>classpath:springMVC.xml</param-value>
           </init-param>
           <load-on-startup>1</load-on-startup>
         </servlet>
         <servlet-mapping>
           <servlet-name>mvc-dispatcher</servlet-name>
           <!--配置了servlet对所有的请求都进行拦截-->
           <!--即所有的访问都会被springMVC的DispatcherServlet所拦截-->
           <!--拦截到后会交给控制器controller来处理-->
           <url-pattern>/</url-pattern>
         </servlet-mapping>
       ```

11. 新建listCategory.jsp：主要作用是通过43行的forEach标签遍历"cs"里的内容，然后挨个显示出来



### 【思路】

1. 首先在浏览器上访问路径/admin_category_list

2. Tomcat根据web.xml上的SpringMVC配置，拦截了这个访问请求，将其交由DispatcherServlet处理

3. DispatcherServlet根据SpringMVC的配置，将这次请求交给CategoryController来处理，所以需要对这个类进行实例化

4. 在实例化CategoryController的过程中，Autowired注入CategoryServiceImpl

5. 在实例化CategoryServiceImplr的过程中，Autowired注入CategoryMapper

6. 根据ApplicationContext.xml中Mybatis的配置信息，将CategoryMapper与CategoryMapper.xml关联起来了

7. 这样拿到实例化后的CategoryController后，调用list方法

8. 在list方法中，访问CategoryService，再调用CategoryMapper对应的方法，获取到数据，把数据放在"cs"上，接着服务端跳转到listCategory.jsp文件中

9. 在listCategory.jsp中进行数据的显示

  ![未命名文件 (8)](https://user-images.githubusercontent.com/17522733/94555902-87970400-025c-11eb-9b81-b576a603de14.png)



## 增加/删除

1. 映射路径admin_category_delete
2. 提供参数接受id注入
3. **提供session参数，用于后续定位文件位置**
4. 通过categoryService删除数据
5. 通过session获取ControllerContext然后获取分类图片位置，接着删除分类图片
6. 客户端跳转到 admin_category_list

## 重构

### 分页方式重构

- 为了提升开发效率，把当前的分页方式换成pageHelper分页插件来实现
- 通过分页插件指定分页参数： `PageHelper.offsetPage(page.getStart(),page.getCount());`
- 调用list() 获取对应分页的数据: `categoryService.list();`
- 通过PageInfo获取总数: ` int total = (int) new PageInfo<>(cs).getTotal();`

### MybatisGenerator

- 在已经存在的数据库表结构基础上，通过工具，自动生成Category.java, CategoryMapper.java和CategoryMapper.xml等文件
- 在resouces下创建generatorConfig.xml文件，其目的就是为了正确使用本插件而提供必要的配置信息
- 指定链接数据库的账号和密码
- 指定生成的pojo,mapper, xml文件的存放位置
- 生成对应表及类名
- MybatisGenerator会生成一个类叫做XXXXExample的。 它的作用是进行排序，条件查询的时候使用。

## 各种后台管理

在Property基础上增加了一个Category 字段。这个属性的用途将会在编辑功能讲解 步骤里进行使用。



## 前台不需要登录

- 创建了一个新的Controller: ForeController,专门用来对应前台页面的路径

- 轮播部分，都是静态的页面，没有用到服务端数据

- 无需登录：比如登录，注册本身，分类页面，查询，产品页面等

- 需要登录：比如购买，加入购物车，查看购物车，结算页面，订单页面等

- 登录：如果对象存在，则**把对象保存在session中**，并客户端跳转到首页"forehome"

- 退出登录： 在session中去掉"user"，客户端跳转到首页:

- 模态登录：需要先检查当前是否已经登录，由于对于已经登录的情况来说，user对象已经保存在了session中，所以只需要判断session中的User对象是否为空即可。如果为空的话，`$("#loginModal").modal('show'); `   点击登录按钮时，使用[imgAndInfo.jsp](https://how2j.cn/k/tmall_ssm/tmall_ssm-1532/1532.html#step6353) 页面中的ajax代码进行登录验证

- 分类这个页面有排序功能，使用到了5个Comparator比较器`ProductAllComparator 综合比较器`等，他们都实现了`java.util.Comparator`的接口，在拿到列表之后进行排列：`Collections.sort(c.getProducts(),``new` `ProductReviewComparator());`

- productsByCategory.jsp显示当前分类下的所有产品

- 产品搜索：每个页面都包含了搜索的jsp，首页和搜索结果页包含的是search.jsp，其他页面包含的是simpleSearch.jsp。这两个页面都提供了一个form，提交数据keyword到foresearch这个路径。 导致ForeController.search()方法被调用。里面具体调用了`List<Product> ps= productService.search(keyword);`，再内层调用了mapper：

  ```java
  ProductExample example = new ProductExample();
  example.createCriteria().andNameLike("%" + keyword + "%");
  example.setOrderByClause("id desc");
  List result = productMapper.selectByExample(example);
  ```

## 前台需要登录

- 立即购买：会在OrderItem表里插入一条数据，这条数据会表示:
  - \1. pid =844 购买的商品id
    \2. oid = null, 这个订单项还没有生成对应的订单，即还在购物车中
    \3. uid= 3，用户的id是3
    \4. number=3, 购买了3件产品
- 访问的地址 /forebuyone 导致ForeController.buyone()方法被调用
  \1. 获取参数pid
  \2. 获取参数num
  \3. 根据pid获取产品对象p
  \4. 从session中获取用户对象user
- 立即购买：新增订单项OrderItem需要先从session中获取用户id，查询其没有生成订单的订单项集合，遍历这个集合。如果存在则修改数量；否则根据jsp获取参数pid和数量num，新建订单项
- 在结算页面：把订单项集合放在session的属性 "ois" 上。把总价格放在 model的属性 "total" 上。
- 加入购物车：逻辑和立即购买雷同，但是返回一个字符串即可，不需要跳转页面。
- 查看购物车：需要用session判断是否登录。获取为这个用户关联的订单项集合 ois，把ois放在model中，服务端跳转到cart.jsp
- 登录状态拦截器LoginInterceptor：继承了HandlerInterceptorAdapter，准备字符串数组 noNeedAuthPage，存放哪些不需要登录也能访问的路径；对于某个访问路径，如果不在，那么就需要进行是否登录验证，然后从session中取出User对象进行判断，如果对象不存在就客户端跳转到login.jsp
- 提交订单后，在数据库中生成一条Order记录。
  不仅如此，订单项的oid字段也会被设置为这条Order记录的id。所以需要进行事务管理。在OrderServiceImpl中的add函数上增加这个`@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")`
- 在我的订单页中，通过session获得User的Id，查询该用户所有状态不是delete的订单集合os，放在model上，服务器跳转

## 应用到的设计模式

- MVC贯穿整个后台和前台开发
- 模块化的jsp设计

## 常见提问：

1. 服务端跳转和客户端跳转的使用场景：

   如果有数据要传递到下一个页面则使用服务端跳转，否则使用客户端跳转

   比如： return "admin/listCategory" 用的是前端控制器的转发，服务器跳转，从查询页面跳转到编辑页面，请求域中的数据不丢失

   return "redirect:/admin_category_list"用的是重定向关键字，重新发起请求，客户端跳转

   **总结**来说：一般来说增删改用重定向，查询用转发

2. 
