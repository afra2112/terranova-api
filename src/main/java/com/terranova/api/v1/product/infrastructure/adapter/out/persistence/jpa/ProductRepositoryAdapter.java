package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.jpa;

import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.command.search.SearchProductCommand;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;
import com.terranova.api.v1.product.infrastructure.adapter.mapper.ImageMapper;
import com.terranova.api.v1.product.infrastructure.adapter.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final JpaProductRepository jpaProductRepository;
    private final ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        return productMapper.entityToDomain(jpaProductRepository.save(productMapper.domainToEntity(product)));
    }

    @Override
    public Optional<Product> getById(Long productId) {
        return jpaProductRepository.findById(productId).map(productMapper::entityToDomain);
    }

    @Override
    public List<Product> searchProducts(SearchProductCommand filter) {
        return jpaProductRepository.findAll(ProductSpecification.byFilter(filter))
                .stream()
                .map(productMapper::entityToDomain)
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return jpaProductRepository.existsById(id);
    }
}
