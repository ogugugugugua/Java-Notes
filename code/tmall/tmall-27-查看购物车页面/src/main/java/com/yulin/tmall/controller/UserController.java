package com.yulin.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yulin.tmall.pojo.User;
import com.yulin.tmall.service.UserService;
import com.yulin.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("admin_user_list")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<User> UserList = userService.list();

        int total = (int) new PageInfo<>(UserList).getTotal();
        page.setTotal(total);

        model.addAttribute("us",UserList);
        model.addAttribute("page",page);

        return "admin/listUser";
    }
}
