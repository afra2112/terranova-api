package com.terranova.api.v1.product.service.impl;

import com.terranova.api.v1.product.dto.*;
import com.terranova.api.v1.product.enums.ProductType;
import com.terranova.api.v1.product.repository.ProductRepository;
import com.terranova.api.v1.product.service.ProductService;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Validator validator;

    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {

        Class<?> productGroup = getGroupFromRequestProductType(request.type());
        validator.validate(request, productGroup);

        return null;
    }

    private Class<?> getGroupFromRequestProductType(ProductType productType){
        return switch (productType){
            case FARM -> FarmGroup.class;
            case LAND -> LandGroup.class;
            case CATTLE -> CattleGroup.class;
        };
    }
}
