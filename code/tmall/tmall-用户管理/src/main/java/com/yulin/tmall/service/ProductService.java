package com.yulin.tmall.service;

import com.yulin.tmall.pojo.Product;
import com.yulin.tmall.pojo.ProductImage;

import java.util.List;

public interface ProductService {
    void add(Product product);
    void delete(int id);
    void update(Product product);
    Product get(int id);
    void setFirstProductImage(Product p);
    //  因为在业务上需要查询某个分类下的产品，所以list方法会带上对应分类的id。
    List list(int cid);
}
