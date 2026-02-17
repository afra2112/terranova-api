package com.terranova.api.v1.product.service;

import com.terranova.api.v1.product.dto.createRequest.CreateProductRequest;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    CreateProductResponse createProduct(CreateProductRequest request);
}
