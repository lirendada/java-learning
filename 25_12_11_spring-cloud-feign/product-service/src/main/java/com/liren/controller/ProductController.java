package com.liren.controller;

import com.liren.model.ProductInfo;
import com.liren.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/{productId}")
    public ProductInfo getProductById(@RequestParam("productId") Integer productId) {
        return productService.selectProductById(productId);
    }

    // 传递一个参数
    @RequestMapping("/p1")
    public String p1(@RequestParam("id")Integer id) {
        return "p1接收到参数:" + id;
    }

    // 传递多个参数
    @RequestMapping("/p2")
    public String p2(@RequestParam("id")Integer id, @RequestParam("name")String name) {
        return "p2接收到参数:id=" + id + "，name=" + name;
    }

    // 传递对象
    @RequestMapping("/p3")
    public String p3(ProductInfo productInfo) {
        return "p3接收到参数:productInfo=" + productInfo;
    }

    // 传递json对象
    @RequestMapping("/p4")
    public String p4(@RequestBody ProductInfo productInfo) {
        return "p4接收到参数:productInfo=" + productInfo;
    }
}