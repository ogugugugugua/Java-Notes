package com.yulin.tmall.service.impl;

import com.yulin.tmall.mapper.ProductImageMapper;
import com.yulin.tmall.pojo.ProductImage;
import com.yulin.tmall.pojo.ProductImageExample;
import com.yulin.tmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageMapper imageMapper;

    @Override
    public void add(ProductImage productImage) {
        imageMapper.insert(productImage);
    }

    @Override
    public void delete(int id) {
        imageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ProductImage productImage) {
        imageMapper.updateByPrimaryKeySelective(productImage);
    }

    @Override
    public ProductImage get(int id) {
        return imageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int pid, String type) {
        ProductImageExample example = new ProductImageExample();
        example.createCriteria().andPidEqualTo(pid).andTypeEqualTo(type);
        example.setOrderByClause("id desc");
        return imageMapper.selectByExample(example);
    }
}
