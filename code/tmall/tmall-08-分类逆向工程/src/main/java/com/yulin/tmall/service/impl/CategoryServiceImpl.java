package com.yulin.tmall.service.impl;
import com.yulin.tmall.mapper.CategoryMapper;
import com.yulin.tmall.pojo.Category;
import com.yulin.tmall.pojo.CategoryExample;
import com.yulin.tmall.service.CategoryService;
import com.yulin.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service  /*注解@Service声明当前类是一个Service类*/
public class CategoryServiceImpl  implements CategoryService {
    @Autowired   /*通过自动装配@Autowired引入CategoryMapper ，在list方法中调用CategoryMapper 的list方法.*/
    CategoryMapper categoryMapper;

    @Override
    public List<Category> list(){
        CategoryExample example =new CategoryExample();
        example.setOrderByClause("id desc");
        return categoryMapper.selectByExample(example);
    }

    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Category getByID(int id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

}