package com.liren.controller;

import com.liren.api.ProductApi;
import com.liren.model.ProductInfo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@Slf4j
@RestController
@RequestMapping("/feign")
public class FeginController {
    @Autowired
    private ProductApi productApi;

    @RequestMapping("/t1")
    public String test1(String userName, HttpServletResponse response) {
        log.info("接收到filter请求添加的参数：userName={}", userName);
        response.setStatus(502);
        return productApi.p1(1);
    }

    @RequestMapping("/t2")
    public String test2() {
        return productApi.p2(2, "name");
    }

    @RequestMapping("/t3")
    public String test3() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(3);
        productInfo.setProductName("name");
        return productApi.p3(productInfo);
    }

    @RequestMapping("/t4")
    public String test4() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(4);
        productInfo.setProductName("name");
        return productApi.p4(productInfo);
    }
}
