package com.yulin.tmall.service.impl;

import com.yulin.tmall.mapper.OrderMapper;
import com.yulin.tmall.mapper.UserMapper;
import com.yulin.tmall.pojo.Order;
import com.yulin.tmall.pojo.OrderExample;
import com.yulin.tmall.pojo.User;
import com.yulin.tmall.service.OrderService;
import com.yulin.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;

    @Override
    public void add(Order c) {
        orderMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order c) {
        orderMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> orders = orderMapper.selectByExample(example);
        SetUser(orders);
        return orders;
    }

    public void SetUser(List<Order> orderList){
        for (Order order : orderList) {
            SetUser(order);
        }
    }

    public void SetUser(Order order){
        Integer uid = order.getUid();
        User user = userService.get(uid);
        order.setUser(user);
    }
}
