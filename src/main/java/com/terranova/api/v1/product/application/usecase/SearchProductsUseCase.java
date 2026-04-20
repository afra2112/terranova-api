package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.command.search.SearchProductCommand;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;

import java.util.List;

public class SearchProductsUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public SearchProductsUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public List<Product> searchProducts(SearchProductCommand command){
        return productRepositoryPort.searchProducts(command);
    }
}
