package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.domain.model.Image;

import java.util.List;
import java.util.Map;

public interface ImageRepositoryPort {

    List<Image> save(List<Image> images, Long productId);

    Map<Long, List<Image>> getByProductId(List<Long> productsIds);
}
