package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.jpa;

import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.product.infrastructure.adapter.mapper.ImageMapper;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.ImageEntity;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ImageRepositoryAdapter implements ImageRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;
    private final JpaImageRepository jpaImageRepository;
    private final ImageMapper imageMapper;

    @Override
    public List<Image> save(List<Image> images, Long productId) {
        List<ImageEntity> entities = images.stream().map(imageMapper::domainToEntity).toList();

        entities.forEach(entity -> {
            ProductEntity product = entityManager.getReference(ProductEntity.class, productId);
            entity.setProduct(product);
            entity.setExternallySaved(true);
            entity.getProduct().setStatus(StatusEnum.IMAGES_UPLOADED);
        });

        return jpaImageRepository.saveAll(entities)
                .stream()
                .map(imageMapper::entityToDomain)
                .toList();
    }

    @Override
    public Map<Long, List<Image>> getByProductId(List<Long> productsIds) {
        return jpaImageRepository.findByProductsIds(productsIds)
                .stream()
                .map(imageMapper::entityToDomain)
                .collect(Collectors.groupingBy(Image::productId));
    }
}
