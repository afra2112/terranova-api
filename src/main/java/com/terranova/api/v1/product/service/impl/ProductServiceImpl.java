package com.terranova.api.v1.product.service.impl;

import com.terranova.api.v1.common.enums.ErrorCodeEnum;
import com.terranova.api.v1.common.exception.BusinessException;
import com.terranova.api.v1.product.dto.*;
import com.terranova.api.v1.product.dto.createRequest.CreateProductRequest;
import com.terranova.api.v1.product.dto.createResponse.CreateProductResponse;
import com.terranova.api.v1.product.entity.Product;
import com.terranova.api.v1.product.enums.ProductType;
import com.terranova.api.v1.product.factory.ProductFactory;
import com.terranova.api.v1.product.mapper.ProductMapper;
import com.terranova.api.v1.product.repository.ProductRepository;
import com.terranova.api.v1.product.service.ProductService;
import com.terranova.api.v1.user.entity.User;
import com.terranova.api.v1.user.repository.UserRepository;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final Validator validator;
    private final ProductFactory productFactory;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public CreateProductResponse createProduct(CreateProductRequest request) {

        Class<?> productGroup = getGroupFromRequestProductType(request.productType());
        validator.validate(request, productGroup);

        Product product = productFactory.create(
                request,
                userRepository.findByUserId(request.idSeller()).orElseThrow(
                        () -> new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "User SELLER not found with id: " + request.idSeller())
                )
        );

        return productMapper.toDTO(productRepository.save(product));
    }

    private Class<?> getGroupFromRequestProductType(ProductType productType){
        return switch (productType){
            case FARM -> FarmGroup.class;
            case LAND -> LandGroup.class;
            case CATTLE -> CattleGroup.class;
        };
    }
}
