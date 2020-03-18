package com.yulin.tmall.service.impl;

import com.yulin.tmall.mapper.ReviewMapper;
import com.yulin.tmall.pojo.Review;
import com.yulin.tmall.pojo.ReviewExample;
import com.yulin.tmall.pojo.User;
import com.yulin.tmall.service.ReviewService;
import com.yulin.tmall.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;

    @Override
    public void add(Review review) {
        reviewMapper.insert(review);
    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Review review) {
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public Review get(int id) {
        return reviewMapper.selectByPrimaryKey(id);
    }


    public List<Review> list(int pid) {
        ReviewExample example = new ReviewExample();
        example.createCriteria().andPidEqualTo(pid);
        example.setOrderByClause("id desc");
        List<Review> reviewList = reviewMapper.selectByExample(example);
        setUser(reviewList);
        return reviewList;
    }

    public void setUser(Review review){
        Integer uid = review.getUid();
        User user = userService.get(uid);
        review.setUser(user);       //POJO中的方法
    }

    public void setUser(List<Review> reviewList){
        for (Review review : reviewList) {
            setUser(review);
        }
    }

    @Override
    public int getCount(int pid) {
        return list(pid).size();
    }
}
