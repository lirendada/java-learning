package com.liren.service;

import com.liren.api.ProductApi;
import com.liren.mapper.OrderMapper;
import com.liren.model.OrderInfo;
import com.liren.model.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductApi productApi;

    public OrderInfo selectOrderById(Integer orderId) {
        OrderInfo orderInfo = orderMapper.selectOrderById(orderId);

        ProductInfo productInfo = productApi.getProductById(orderInfo.getProductId());

        orderInfo.setProductInfo(productInfo);
        return orderInfo;
    }
}
