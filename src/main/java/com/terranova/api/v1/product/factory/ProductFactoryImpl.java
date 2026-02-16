package com.terranova.api.v1.product.factory;

import com.terranova.api.v1.product.dto.CreateProductRequest;
import com.terranova.api.v1.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFactoryImpl implements ProductFactory {

    @Override
    public Product create(CreateProductRequest request) {
        return null;
    }
}
