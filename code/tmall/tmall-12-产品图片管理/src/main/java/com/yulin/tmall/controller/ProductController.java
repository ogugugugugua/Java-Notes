package com.yulin.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yulin.tmall.pojo.Category;
import com.yulin.tmall.pojo.Product;
import com.yulin.tmall.service.CategoryService;
import com.yulin.tmall.service.ProductService;
import com.yulin.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    /**
     * 在listProduct.jsp提交数据的时候，除了提交产品名称，小标题，原价格，优惠价格，库存外还会提交cid
     * @param product 在ProductController中获取Product对象，并插入到数据库
     * @return 客户端跳转到admin_product_list,并带上参数cid
     */
    @RequestMapping("admin_product_add")
    public String add(Product product){
        product.setCreateDate(new Date());//是用来在分类页面按新品排序用的
        productService.add(product);
        return "redirect:admin_product_list?cid="+product.getCid();
    }

    /**
     *
     * @param id 在ProductController的delete方法中获取id
     * @return
     */
    @RequestMapping("admin_product_delete")
    public String delete(int id){
        Product product = productService.get(id);                       //根据id获取Product对象
        productService.delete(id);                                      //借助productService删除这个对象对应的数据
        return "redirect:admin_product_list?cid="+product.getCid();
    }

    /**
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("admin_product_edit")
    public String edit(Model model, int id){
        Product product = productService.get(id);                       //在ProductController的edit方法中，根据id获取product对象

/*      业务层的get已装配了对应分类，控制层不用设置了,这是站长的一个多余的bug：
        Category category = categoryService.getByID(product.getCid());  //根据product对象的cid产品获取Category对象，并把其设置在product对象的category产品上
        product.setCategory(category);*/

        model.addAttribute("p",product);                  //把product对象放在request的 "p" 产品中
        return "admin/editProduct";
    }

    /**
     * 在editProduct.jsp中隐式提供id和cid( cid 通过 p.category.id 获取)
     * @param product 在ProductController的update方法中获取Product对象
     * @return
     */
    @RequestMapping("admin_product_update")
    public String update(Product product){
        productService.update(product); //借助productService更新这个对象到数据库
        return "redirect:admin_product_list?cid="+product.getCid();
    }

    /**
     *
     * @param cid 获取分类 cid
     * @param model
     * @param page 获取分页对象page
     * @return
     */
    @RequestMapping("admin_product_list")
    public String list(int cid, Model model, Page page){

        PageHelper.offsetPage(page.getStart(),page.getCount());     //通过PageHelper设置分页参数
        List<Product> productList = productService.list(cid);

        int total = (int) new PageInfo<>(productList).getTotal();   //通过PageInfo获取产品总数
        page.setTotal(total);                                       //把总数设置给分页page对象

        Category category = categoryService.getByID(cid);           //基于cid，获取当前分类下的产品集合
        page.setParam("&cid="+category.getId());                    //拼接字符串"&cid="+c.getId()，设置给page对象的Param值。 因为产品分页都是基于当前分类下的分页，所以分页的时候需要传递这个cid

        model.addAttribute("ps",productList);         //在listProduct.jsp页面上使用c:forEach 遍历ps集合，并显示
        model.addAttribute("c",category);
        model.addAttribute("page",page);

        return "admin/listProduct";
    }
}
