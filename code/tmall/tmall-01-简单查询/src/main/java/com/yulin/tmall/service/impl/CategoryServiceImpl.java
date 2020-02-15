package com.yulin.tmall.service.impl;
import com.yulin.tmall.mapper.CategoryMapper;
import com.yulin.tmall.pojo.Category;
import com.yulin.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service  /*注解@Service声明当前类是一个Service类*/
public class CategoryServiceImpl  implements CategoryService {
    @Autowired   /*通过自动装配@Autowired引入CategoryMapper ，在list方法中调用CategoryMapper 的list方法.*/
    CategoryMapper categoryMapper;
    public List<Category> list(){
        return categoryMapper.list();
    }
 
}