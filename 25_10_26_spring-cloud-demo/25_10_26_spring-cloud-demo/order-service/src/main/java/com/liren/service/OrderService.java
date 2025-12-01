package com.liren.service;

import com.liren.mapper.OrderMapper;
import com.liren.model.OrderInfo;
import com.liren.model.ProductInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    public OrderInfo selectOrderById(Integer orderId) {
        OrderInfo orderInfo = orderMapper.selectOrderById(orderId);

        String url = "http://product-service/product/"+ orderInfo.getProductId();
        log.info("product-service url: " + url);
        ProductInfo productInfo = restTemplate.getForObject(url, ProductInfo.class);
        orderInfo.setProductInfo(productInfo);
        return orderInfo;
    }
}
