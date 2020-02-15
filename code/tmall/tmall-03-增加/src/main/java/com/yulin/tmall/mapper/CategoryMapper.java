package com.yulin.tmall.mapper;
 
import com.yulin.tmall.pojo.Category;
import com.yulin.tmall.util.Page;

import java.util.List;
 
public interface CategoryMapper {
    List<Category> list(Page page);
    public int total();
    public void add(Category category);
}