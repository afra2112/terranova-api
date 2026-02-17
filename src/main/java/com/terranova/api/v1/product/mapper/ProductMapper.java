package com.terranova.api.v1.product.mapper;

import com.terranova.api.v1.product.entity.Cattle;
import com.terranova.api.v1.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    CreateProductResponse toDto(Product product){
        return switch (product){
            case Cattle cattle ->
        }
    }

    CreateProductResponse
}
