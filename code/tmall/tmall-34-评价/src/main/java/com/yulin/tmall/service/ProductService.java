package com.yulin.tmall.service;

import com.yulin.tmall.pojo.Category;
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

    //放在哪个Service里，是看主要查询的那个表。 这里查询的是 Product表，所以就放在ProductService里了，而Category表本身没有被查询。
    public void fill(List<Category> categories);        //为多个分类填充产品集合
    public void fill(Category category);                //为分类填充产品集合
    public void fillByRow(List<Category> categories);   //为多个分类填充推荐产品集合，即把分类下的产品集合，按照8个为一行，拆成多行，以利于后续页面上进行显示

    //增加为产品设置销量和评价数量的方法
    void setSaleAndReviewNumber(Product p);
    void setSaleAndReviewNumber(List<Product> ps);

    //搜索商品
    List<Product> search(String keyword);

}
