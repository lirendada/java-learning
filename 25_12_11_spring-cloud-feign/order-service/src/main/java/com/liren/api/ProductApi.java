package com.liren.api;

import com.liren.model.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "product-service", path = "/product")
public interface ProductApi {
    @RequestMapping("/{productId}")
    ProductInfo getProductById(@PathVariable("productId") Integer productId);
}