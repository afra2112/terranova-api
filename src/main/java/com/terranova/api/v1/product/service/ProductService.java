package com.terranova.api.v1.product.service;

import com.terranova.api.v1.product.dto.CreateProductRequest;
import com.terranova.api.v1.product.dto.CreateProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    CreateProductResponse createProduct(CreateProductRequest request);
}
