package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.command.search.SearchProductCommand;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;

import java.util.List;

public class SearchProductsUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ImageRepositoryPort imageRepositoryPort;

    public SearchProductsUseCase(ProductRepositoryPort productRepositoryPort, ImageRepositoryPort imageRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.imageRepositoryPort = imageRepositoryPort;
    }

    public List<Product> searchProducts(SearchProductCommand command){
        return productRepositoryPort.searchProducts(command)
                .stream()
                .map(product -> product.withImages(imageRepositoryPort.getByProductId(product.getProductId())))
                .toList();
    }
}
