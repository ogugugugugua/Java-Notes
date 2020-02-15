package com.yulin.tmall.mapper;
 
import com.yulin.tmall.pojo.Category;
import com.yulin.tmall.util.Page;

import java.util.List;
 
public interface CategoryMapper {
    List<Category> list();
    public void add(Category category);
    public void delete(int id);
    public Category getByID(int id);
    public void update(Category category);
}