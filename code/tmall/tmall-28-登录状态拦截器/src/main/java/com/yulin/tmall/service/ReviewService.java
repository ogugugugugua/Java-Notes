package com.yulin.tmall.service;

import com.yulin.tmall.pojo.Review;

import java.util.List;

public interface ReviewService {
    void add(Review review);
    void delete(int id);
    void update(Review review);
    Review get(int id);
    List list(int pid);

    int getCount(int pid);
}
