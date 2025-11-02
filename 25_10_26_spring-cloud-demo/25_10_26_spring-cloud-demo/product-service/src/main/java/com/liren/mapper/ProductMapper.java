package com.liren.mapper;

import com.liren.model.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {
    @Select("select * from product_detail where id = #{productId}")
    public ProductInfo selectProductById(Integer productId);
}
