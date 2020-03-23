package com.yulin.tmall.controller;

import com.yulin.tmall.pojo.Product;
import com.yulin.tmall.pojo.PropertyValue;
import com.yulin.tmall.service.ProductService;
import com.yulin.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;

    @RequestMapping("admin_propertyValue_edit")
    public String edit(Model model,int pid) {
        Product p = productService.get(pid);
        propertyValueService.init(p);
        List<PropertyValue> pvs = propertyValueService.list(p.getId());

        model.addAttribute("p", p);
        model.addAttribute("pvs", pvs);
        return "admin/editPropertyValue";
    }

    /**
     * 修改功能采用的是使用post方式提交ajax的异步调用方式
     * @param pv
     * @return 结合方法update上的注解@ResponseBody和return "success" 就会向浏览器返回字符串 "success"
     */
    @RequestMapping("admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue pv) {
        propertyValueService.update(pv); //通过 propertyValueService.update(propertyValue) 更新到数据库
        return "success";
    }
}