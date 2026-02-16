package com.terranova.api.v1.product.factory;

import com.terranova.api.v1.product.dto.CreateProductRequest;
import com.terranova.api.v1.product.entity.Product;

public interface ProductFactory {
    Product create(CreateProductRequest request);
}
