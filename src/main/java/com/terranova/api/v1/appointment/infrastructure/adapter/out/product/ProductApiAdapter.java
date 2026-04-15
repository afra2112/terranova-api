package com.terranova.api.v1.appointment.infrastructure.adapter.out.product;

import com.terranova.api.v1.appointment.domain.model.product.ProductResponse;
import com.terranova.api.v1.appointment.domain.port.out.ProductServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductApiAdapter implements ProductServicePort {

    private final ProductFeignClient productFeignClient;

    @Override
    public Optional<ProductResponse> getProductById(Long productId) {
        return Optional.of(productFeignClient.getProductById(productId));
    }
}
