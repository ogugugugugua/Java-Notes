package com.yulin.tmall.service;

import com.yulin.tmall.pojo.Property;

import java.util.List;

public interface PropertyService {
    void add(Property property);
    void delete(int id);
    void update(Property property);
    Property get(int id);

//  因为在业务上需要查询某个分类下的属性，所以list方法会带上对应分类的id。
    List<Property> list(int cid);
}
