package com.yulin.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yulin.tmall.pojo.Order;
import com.yulin.tmall.service.OrderItemService;
import com.yulin.tmall.service.OrderService;
import com.yulin.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @RequestMapping("admin_order_list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());     //获取分页对象
        List<Order> list = orderService.list();                     //查询订单集合

        int total = (int) new PageInfo<>(list).getTotal();          //获取订单总数并设置在分页对象上
        page.setTotal(total);

        orderItemService.fill(list);                                //借助orderItemService.fill()方法为这些订单填充上orderItems信息

        model.addAttribute("os", list);               //把订单集合和分页对象设置在model上,在listOrder.jsp借助c:forEach把订单集合遍历出来
        model.addAttribute("page", page);

        return "admin/listOrder";                                   //服务端跳转到admin/listOrder.jsp页面
    }

    @RequestMapping("admin_order_delivery")
    public String delivery(Order o) throws IOException {
        o.setDeliveryDate(new Date());
        o.setStatus(OrderService.waitConfirm);
        orderService.update(o);
        return "redirect:admin_order_list";
    }

}
