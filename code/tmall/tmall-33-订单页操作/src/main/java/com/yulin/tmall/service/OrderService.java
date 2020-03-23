package com.yulin.tmall.service;

import com.yulin.tmall.pojo.Order;
import com.yulin.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    void add(Order c);

    void delete(int id);
    void update(Order c);
    Order get(int id);
    List list();

    float add(Order o, List<OrderItem> ois);

    List list(int uid, String excludedStatus);
}
