package com.springboot.chapter5.controller;

import com.springboot.chapter5.pojo.User;
import com.springboot.chapter5.service.UserBatchService;
import com.springboot.chapter5.service.UserService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserBatchService userBatchService;

    @RequestMapping("/query")
    public User testQuery() {
        System.out.println("in!");
        User daisy = userService.selectUserByName("Daisy");
        System.out.println(daisy);
        return daisy;
    }

    @RequestMapping("/queries")
    public List<User> testQueries() {
        System.out.println("in!");
        List<User> daisy = userService.selectUsersByName("Daisy");
        System.out.println(daisy);
        return daisy;
    }

    @RequestMapping("/insert")
    public List<User> testInsert(String name, int age, Double money) {
        User user = new User();
        user.setName(name);
        user.setMoney(money);
        user.setAge(age);
        int update = userService.insertService(user);
        System.out.println("update result: " + update);
        return userService.selectAllUser();
    }


    @RequestMapping("/changemoney")
    public List<User> testchangemoney() {
        userService.changemoney();
        return userService.selectAllUser();
    }

    @RequestMapping("/delete")
    public String testDelete() {
        userService.deleteService(4);
        userService.deleteService(5);
        userService.deleteService(3);
        return "OK";
    }

    @RequestMapping("/testAllQuery")
    public List<User> testAllQuery() {
        return userService.selectAllUser();
    }

    @RequestMapping("/testInsetUsers")
    public List<User> testInsetUsers(){
        User user1 = new User();
        user1.setAge(17);
        user1.setMoney(17.1);
        user1.setName("user1");

        User user2 = new User();
        user2.setAge(18);
        user2.setMoney(18.2);
        user2.setName("user2");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        userBatchService.insertUsers(userList);

        return userService.selectAllUser();
    }
}
