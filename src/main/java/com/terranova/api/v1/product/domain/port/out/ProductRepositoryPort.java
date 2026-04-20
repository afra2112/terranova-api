package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.command.search.SearchProductCommand;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Product save(Product product);

    Optional<Product> getById(Long productId);

    List<Product> searchProducts(SearchProductCommand filter);

    boolean existsById(Long id);
}
