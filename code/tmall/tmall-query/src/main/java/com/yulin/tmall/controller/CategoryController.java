package com.yulin.tmall.controller;
 
import com.yulin.tmall.pojo.Category;
import com.yulin.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
import java.util.List;
 
@Controller /*声明当前类是一个控制器*/
@RequestMapping("") /*表示访问的时候无需额外的地址*/
public class CategoryController{
    @Autowired /*把CategoryServiceImpl自动装配进了CategoryService接口*/
    CategoryService categoryService;
    @RequestMapping("admin_category_list") /*映射admin_category_list路径的访问*/
    public String list(Model model){
        List<Category> cs = categoryService.list(); /*获取所有的Category对象并放在cs中*/
        model.addAttribute("cs",cs);
        return "admin/listCategory"; /*服务器跳转到admin/listCategory视图*/
        /*admin/Category会根据后续的springMVC.xml配置文件，跳转到WEB-INF/jsp/admin/listCategory.jsp文件*/
    }
}


/*
 * 在上面一步，收到浏览器请求后，所有的访问都会被springMVC的DispatcherServlet所拦截，拦截到后会交给控制器controller来处理
 * 也就是对应这里的“@RequestMapping("admin_category_list") -- 映射admin_category_list路径的访问”
 * 于是会实例化CategoryController这个对象，并且调用list方法。而在实例化过程中就需要注入一个CategoryService的实现类
 * 这里的CategoryService是一个接口，在这里只有一个实现类，即CategoryServiceImpl。
 * 于是在注入CategoryServiceImpl的时候，就发现需要注入CategoryMapper。
 * 而在这里我们的CategoryMapper是一个接口，并没有手写的实现类，因此MyBatis会自动生成一个动态代理的类，来实现这个接口的工作。
 *
 * 我们看resources/applicationContext.xml/46行里面，会扫描到所有的“<property name="mapperLocations" value="classpath:mapper/*.xml"/>”
 * 文件里第66行的“<property name="basePackage" value="com.yulin.tmall.mapper"/>”也会扫描所有的mapper
 * 因此所有的mapper类和mapper.xml都被Spring管理起来了。
 * 在resources/mapper/CategoryMapper.xml中，我们通过“<mapper namespace="com.yulin.tmall.mapper.CategoryMapper">”
 * 进而把CategoryMapper和Category.xml关联起来。
 *
 * 总结就是：
 * 调用list方法会注入CategoryServiceImpl-->注入CategoryMapper的动态代理类-->Spring关联起来-->
 * 调用mapper/CategoryMapper.xml里面的select * from category order by id desc查询语句
 * 返回的结果就放到一个集合里面，这个集合通过model.addAttribute("cs",cs)放到model上面，
 * 接着服务器跳转到xxx/admin/listCategory.jsp
 * 该文件里第43行的<c:forEach items="${cs}" var="c">就对应起来了，把所有的结果都输出一下。
 */