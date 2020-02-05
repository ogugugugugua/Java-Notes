package com.yulin.tmall.service;
import com.yulin.tmall.pojo.Category;
import com.yulin.tmall.util.Page;

import java.util.List;

public interface CategoryService {
    int total();
    List<Category> list(Page page);
    void add(Category category);
    void delete(int id);
    Category getByID(int id);
    void update(Category category);
}