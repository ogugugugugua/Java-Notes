package com.yulin.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yulin.tmall.pojo.Category;
import com.yulin.tmall.pojo.Property;
import com.yulin.tmall.service.CategoryService;
import com.yulin.tmall.service.PropertyService;
import com.yulin.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PropertyService propertyService;

    @RequestMapping("admin_property_add")
    public String add(Property property){
        propertyService.add(property);
        return "redirect:admin_property_list?cid="+property.getCid();
    }

    @RequestMapping("admin_property_delete")
    public String delete(int id){
        Property property = propertyService.get(id);            //根据id获取Property对象
        propertyService.delete(id);                             //借助propertyService删除这个对象对应的数据
        return "redirect:admin_property_list?cid="+property.getCid();
    }

    @RequestMapping("admin_property_edit")
    public String edit(Model model, int id){
        Property property = propertyService.get(id);                    //根据id获取Property对象
        Category category = categoryService.getByID(property.getCid()); //根据properoty对象的cid属性获取Category对象，
        property.setCategory(category);                                 //并把其设置在Property对象的category属性上
        model.addAttribute("p",property);                 //把Property对象放在request的 "p" 属性中
        return "admin/editProperty";
    }

    @RequestMapping("admin_property_update")
    public String update(Property property){
        propertyService.update(property);
        return "redirect:admin_property_list?cid="+property.getCid();
    }

    /**
     *
     * @param cid 获取分类 cid
     * @param model
     * @param page 获取分页对象page
     * @return
     */
    @RequestMapping("admin_property_list")
    public String list(int cid, Model model, Page page){
        Category c = categoryService.getByID(cid);

        PageHelper.offsetPage(page.getStart(),page.getCount()); //通过PageHelper设置分页参数
        List<Property> ps = propertyService.list(cid);          //基于cid，获取当前分类下的属性集合

        int total = (int) new PageInfo<>(ps).getTotal();        //通过PageInfo获取属性总数
        page.setTotal(total);                                   //把总数设置给分页page对象
        page.setParam("&cid="+c.getId());                       //拼接字符串"&cid="+c.getId()，设置给page对象的Param值。因为属性分页都是基于当前分类下的分页，所以分页的时候需要传递这个cid

        model.addAttribute("ps", ps);             //把属性集合设置到 request的 "ps" 属性上 && 在listProperty.jsp页面上使用c:forEach 遍历ps集合，并显示
        model.addAttribute("c", c);               //把分类对象设置到 request的 "c" 属性上。
        model.addAttribute("page", page);         //把分页对象设置到 request的 "page" 对象上

        return "admin/listProperty";                            //服务端跳转到admin/listProperty.jsp页面
    }
}
