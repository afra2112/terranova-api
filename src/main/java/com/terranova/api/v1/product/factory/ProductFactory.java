package com.terranova.api.v1.product.factory;

import com.terranova.api.v1.product.dto.createRequest.CreateProductRequest;
import com.terranova.api.v1.product.entity.Product;
import com.terranova.api.v1.user.entity.User;

public interface ProductFactory {
    Product create(CreateProductRequest request, User seller);
}
