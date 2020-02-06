package com.how2java.mapper;
 
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.how2java.pojo.Product;
 
public interface ProductMapper {
 
    @Select(" select * from product_ where cid = #{cid}")
    public List<Product> listByCategory(int cid);
    
    
}