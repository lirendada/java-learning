package com.liren.controller;

import com.liren.model.ProductInfo;
import com.liren.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/{productId}")
    public ProductInfo getProductById(@PathVariable("productId")Integer productId) {
        log.info("getProductById, productId={}", productId);
        return productService.selectProductById(productId);
    }
}