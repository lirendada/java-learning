package com.liren.service;

import com.liren.mapper.ProductMapper;
import com.liren.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public ProductInfo selectProductById(Integer orderId) {
        return productMapper.selectProductById(orderId);
    }
}
