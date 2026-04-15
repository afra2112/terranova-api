package com.terranova.api.v1.appointment.domain.port.out;

import com.terranova.api.v1.appointment.domain.model.product.ProductResponse;

import java.util.Optional;

public interface ProductServicePort {

    Optional<ProductResponse> getProductById(Long productId);
}
