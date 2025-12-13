package com.liren.api;

import com.liren.model.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service", path = "/product")
public interface ProductApi {
    @RequestMapping("/{productId}")
    ProductInfo getProductById(@PathVariable("productId") Integer productId);

    // 传递一个参数
    @RequestMapping("/p1")
    String p1(@RequestParam("id")Integer id);

    // 传递多个参数
    @RequestMapping("/p2")
    String p2(@RequestParam("id")Integer id, @RequestParam("name")String name);

    // 传递对象
    @RequestMapping("/p3")
    String p3(@SpringQueryMap ProductInfo productInfo);

    // 传递json对象
    @RequestMapping("/p4")
    String p4(@RequestBody ProductInfo productInfo);
}