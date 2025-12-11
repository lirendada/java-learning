package com.liren.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "product-service", path = "/product")
public interface ProductApi extends ProductInterface {
}