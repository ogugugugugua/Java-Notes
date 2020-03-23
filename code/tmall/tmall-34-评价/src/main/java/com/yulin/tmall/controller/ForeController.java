package com.yulin.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.yulin.tmall.comparator.*;
import com.yulin.tmall.pojo.*;
import com.yulin.tmall.service.*;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ForeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
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


    @RequestMapping("forebuyone")
    public String buyone(int pid, int num, HttpSession session){
        Product product = productService.get(pid);//这个其实可有可无
        int orderItemID = 0;

        User user = (User) session.getAttribute("user");
        boolean found = false;
        List<OrderItem> orderItems = orderItemService.listByUser(user.getId());
        //遍历当前用户的订单商品列表中的所有商品
        for(OrderItem orderItem:orderItems){
            //如果在该列表中存在一个和当前pid对应的商品，即该商品本来已存在于购物车中
            if(orderItem.getProduct().getId().intValue() == product.getId().intValue()){
                //在购物车原有已添加的商品数量上增加新的购买数量
                orderItem.setNumber(orderItem.getNumber() + num);
                //由于修改了当前商品的购买量，需要更新数据库
                orderItemService.update(orderItem);
                found = true;
                //获取当前订单项的ID
                orderItemID = orderItem.getId();
                break;
            }
        }
        //新增的商品并不存在于原来的购物车中
        if(!found){
            OrderItem orderItem = new OrderItem();
            orderItem.setUid(user.getId());
            orderItem.setNumber(num);
            orderItem.setPid(pid);
            orderItemService.add(orderItem);
            orderItemID = orderItem.getId();
        }

        return "redirect:forebuy?oiid="+orderItemID;
    }


    /**
     *
     * @param model
     * @param oiid 字符串数组是为了匹配多个商品项，能够和购物车相融合
     * @param session
     * @return
     */
    @RequestMapping("forebuy")
    public String buy(Model model, String[] oiid, HttpSession session){
        List<OrderItem> orderItemList = new ArrayList<>();
        float total = 0;

        for (String s : oiid) {
            //获取不同的ID对应的各个商品项
            OrderItem orderItem = orderItemService.get(Integer.parseInt(s));
            //将这些商品加入到列表中，便于在网页中得到调用以显示
            orderItemList.add(orderItem);
            //计算总价，记得要用当前商品的价钱乘上其数量
            total += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
        }
        
        session.setAttribute("ois", orderItemList);
        model.addAttribute("total", total);//在要跳转的页面中访问数据
        return "fore/buy";
    }

    /**
     * 加入购物车
     * @param pid 商品ID
     * @param num 商品数量
     * @param session 数据传递用
     * @return
     */
    @RequestMapping("foreaddCart")
    public String addCart(int pid, int num, HttpSession session){
//        Product product = productService.get(pid);
        User user = (User) session.getAttribute("user");
        List<OrderItem> orderItemList = orderItemService.listByUser(user.getId());
        boolean found = false;

        for (OrderItem orderItem : orderItemList) {
            if(orderItem.getProduct().getId() == pid){
                found = true;
                orderItem.setNumber(orderItem.getNumber() + num);
                orderItemService.update(orderItem);
                break;
            }
        }
        if(!found){
            OrderItem orderItem = new OrderItem();
            orderItem.setNumber(num);
            orderItem.setPid(pid);
            orderItem.setUid(user.getId());
            orderItemService.add(orderItem) ;
        }
        return "success";
    }

    @RequestMapping("forecart")
    public String cart(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        List<OrderItem> orderItemList = orderItemService.listByUser(user.getId());
        model.addAttribute("ois",orderItemList);
        return "fore/cart";
    }

    @RequestMapping("forechangeOrderItem")
    @ResponseBody
    public String changeOrderItem(int pid, int number, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        //判断用户是否登录
        if(null==user){
            return "fail";
        }

        //遍历出用户当前所有的未生成订单的OrderItem
        List<OrderItem> orderItemList = orderItemService.listByUser(user.getId());
        for (OrderItem orderItem : orderItemList) {
            //根据pid找到匹配的OrderItem，并修改数量后更新到数据库
            if(orderItem.getProduct().getId().intValue() == pid){
                orderItem.setNumber(number);
                orderItemService.update(orderItem);
                break;
            }
        }
        return "success";
    }

    @RequestMapping("foredeleteOrderItem")
    @ResponseBody
    public String deleteOrderItem(int oiid, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (null == user) {
            return "fail";
        }
        orderItemService.delete(oiid);
        return "success";
    }

    @RequestMapping("forecreateOrder")
    public String createOrder(HttpSession session, Order order){
        //从session中获取user对象
        User user = (User) session.getAttribute("user");
        //通过参数Order接受地址，邮编，收货人，用户留言等信息
        //根据当前时间加上一个4位随机数生成订单号
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);
        //根据上述参数，创建订单对象
        order.setOrderCode(orderCode);
        order.setCreateDate(new Date());
        order.setUid(user.getId());
        //把订单状态设置为等待支付
        order.setStatus(OrderService.waitPay);

        //从session中获取订单项集合
        List<OrderItem> orderItemList = (List<OrderItem>) session.getAttribute("ois");

        //把订单加入到数据库，并且遍历订单项集合，设置每个订单项的order，更新到数据库
        //统计本次订单的总金额
        float total = orderService.add(order, orderItemList);
        //这个forealipay在PageController中
        return "redirect:forealipay?oid=" + order.getId() + "&total=" + total;
    }

    @RequestMapping("forepayed")
    public String payed(int oid, float total, Model model){
        Order order = orderService.get(oid);
        order.setStatus(OrderService.waitDelivery);
        order.setPayDate(new Date());
        orderService.update(order);
        model.addAttribute("o", order);
        return "fore/payed";
    }

    @RequestMapping("forebought")
    public String bought(Model model, HttpSession session) {
        //通过session获取用户user
        User user = (User) session.getAttribute("user");
        //查询user所有的状态不是"delete" 的订单集合os
        List<Order> orderList = orderService.list(user.getId(), OrderService.delete);

        //为这些订单填充订单项
        orderItemService.fill(orderList);
        //把os放在model的属性"os"上
        model.addAttribute("os", orderList);
        return "fore/bought";
    }

    @RequestMapping("foreconfirmPay")
    public String confirmPay(int oid, Model model){
        Order order = orderService.get(oid);
        orderItemService.fill(order);
        model.addAttribute("o",order);
        return "fore/confirmPay";
    }

    @RequestMapping("foreorderConfirmed")
    public String orderConfirmed(int oid, Model model){
        Order order = orderService.get(oid);
        order.setStatus(OrderService.waitReview);
        order.setConfirmDate(new Date());
        orderService.update(order);
        return "fore/orderConfirmed";
    }

    @RequestMapping("foredeleteOrder")
    @ResponseBody
    public String deleteOrder(int oid, Model model){
        Order order = orderService.get(oid);
        order.setStatus(OrderService.delete);
        orderService.update(order);
        return "success";
    }

    @RequestMapping("forereview")
    public String review(int oid, Model model){
        Order order = orderService.get(oid);
        orderItemService.fill(order);
        Product product = order.getOrderItems().get(0).getProduct();
        List<Review> reviews = reviewService.list(product.getId());
        productService.setSaleAndReviewNumber(product);

        model.addAttribute("p",product);
        model.addAttribute("o", order);
        model.addAttribute("reviews", reviews);

        return "fore/review";
    }

    @RequestMapping("foredoreview")
    public String doreview(@RequestParam("oid") int oid, String content, HttpSession session, @RequestParam("pid") int pid){
        //根据oid获取订单对象o
        Order order = orderService.get(oid);
        //修改订单对象状态
        order.setStatus(OrderService.finish);
        //更新订单对象到数据库
        orderService.update(order);

        //根据pid获取产品对象
        Product product = productService.get(pid);
        //对评价信息进行转义
        content = HtmlUtils.htmlEscape(content);

        //从session中获取当前用户
        User user = (User) session.getAttribute("user");

        //创建评价对象review
        Review review = new Review();
        //为评价对象review设置 评价信息，产品，时间，用户
        review.setContent(content);
        review.setUid(user.getId());
        review.setCreateDate(new Date());
        review.setPid(product.getId());

        //增加到数据库
        reviewService.add(review);

        //客户端跳转到/forereview： 评价产品页面，并带上参数showonly=true
        //在reviewPage.jsp中，当参数showonly==true，那么就显示当前产品的所有评价信息
        return "redirect:forereview?oid=" + oid + "&showonly=true";
    }
}
