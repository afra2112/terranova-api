package com.terranova.api.v1.appointment.infrastructure.adapter.out.product;

import com.terranova.api.v1.appointment.domain.model.product.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8080/api/v1/products")
public interface ProductFeignClient {

    @GetMapping("/{id}")
    ProductResponse getProductById(@PathVariable Long id);
}
