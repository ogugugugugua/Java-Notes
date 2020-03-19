package com.yulin.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.yulin.tmall.comparator.*;
import com.yulin.tmall.pojo.*;
import com.yulin.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class ForeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    UserService userService;
    @Autowired
    ReviewService reviewService;

    @RequestMapping("forehome")
    public String home(Model model){
        List<Category> categoryList = categoryService.list();
        productService.fill(categoryList);
        productService.fillByRow(categoryList);
        model.addAttribute("cs",categoryList);
        return "fore/home";
    }

    @RequestMapping("foreregister")
    public String register(Model model, User user){
        String name = user.getName();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userService.isExist(name);

        if(exist){
            String msg = "用户名已被使用";
            model.addAttribute("msg",msg);
            model.addAttribute("user", null);
            return "fore/register";
        }
        userService.add(user);
        return "redirect:registerSuccessPage";
    }

    @RequestMapping("forelogin")
    public String login(Model model, @RequestParam("name") String name, @RequestParam("password") String password, HttpSession session){
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name, password);

        if(null ==user){
            model.addAttribute("msg","用户或密码错误");
            return "fore/login";
        }
        session.setAttribute("user", user);
        return "redirect:forehome";
    }

    @RequestMapping("forelogout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:forehome";
    }

    @RequestMapping("foreproduct")
    public String product(int pid, Model model){
        Product product = productService.get(pid);
        List<ProductImage> productSingleImages = productImageService.list(product.getId(), ProductImageService.type_single);
        List<ProductImage> productDetailImages = productImageService.list(product.getId(), ProductImageService.type_detail);
        product.setProductDetailImages(productDetailImages);
        product.setProductSingleImages(productSingleImages);

        List<PropertyValue> propertyValues = propertyValueService.list(product.getId());
        List<Review> reviews = reviewService.list(product.getId());
        productService.setSaleAndReviewNumber(product);

        model.addAttribute("reviews",reviews);
        model.addAttribute("p",product);
        model.addAttribute("pvs",propertyValues );

        return "fore/product";
    }

    @RequestMapping("forecheckLogin")
    @ResponseBody
    public String checkLogin(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(null!=user){
            return "success";
        }else {
            return "fail";
        }
    }

    @RequestMapping("foreloginAjax")
    @ResponseBody
    public String loginAjax(@RequestParam("name") String name, @RequestParam("password") String password, HttpSession session){
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name, password);
        if(null==user){
            return "fail";
        }
        session.setAttribute("user", user);
        return "success";
    }

    @RequestMapping("forecategory")
    public String category(int cid, String sort,Model model){
        Category category = categoryService.getByID(cid);
        productService.fill(category);
        productService.setSaleAndReviewNumber(category.getProducts());

        if(null!=sort){
            switch (sort){
                case "review":
                    Collections.sort(category.getProducts(),new ProductReviewComparator());
                    break;
                case "date":
                    Collections.sort(category.getProducts(), new ProductDateComparator());
                    break;
                case "saleCount":
                    Collections.sort(category.getProducts(), new ProductSaleCountComparator());
                    break;
                case "price":
                    Collections.sort(category.getProducts(), new ProductPriceComparator());
                    break;
                case "all":
                    Collections.sort(category.getProducts(), new ProductAllComparator());
                    break;
            }
        }
        model.addAttribute("c",category);
        return "fore/category";
    }

    /**
     * 通过search.jsp或者simpleSearch.jsp提交数据到路径 /foresearch
     * 导致ForeController.search()方法被调用
     * @param keyword 获取参数，根据keyword进行模糊查询，获取满足条件的前20个产品
     * @param model
     * @return 服务端跳转
     */
    @RequestMapping("foresearch")
    public String search(String keyword, Model model){
        PageHelper.offsetPage(0,20);
        List<Product> products = productService.search(keyword);
        productService.setSaleAndReviewNumber(products);//为这些产品设置销量和评价数量
        model.addAttribute("ps",products);//把产品结合设置在model的"ps"属性上
        return "fore/searchResult";                     //服务端跳转到 searchResult.jsp 页面
    }
}
