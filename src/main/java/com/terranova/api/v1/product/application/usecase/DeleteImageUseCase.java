package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.product.domain.port.out.ImageStoragePort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import java.util.List;

public class DeleteImageUseCase {

    private final ImageRepositoryPort imageRepositoryPort;
    private final ImageStoragePort imageStoragePort;

    public DeleteImageUseCase(ImageRepositoryPort imageRepositoryPort, ImageStoragePort imageStoragePort) {
        this.imageRepositoryPort = imageRepositoryPort;
        this.imageStoragePort = imageStoragePort;
    }

    public int deleteImages(Long productId, List<Long> imageIds){
        List<Image> images = imageRepositoryPort.getByProductIdAndIdIn(productId, imageIds);

        if (imageIds.size() != images.size()){
            throw new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Not images found by product id: " + productId + " and Images Ids: " + imageIds);
        }

        int deleted = imageRepositoryPort.deleteByProductIdAndIds(productId, imageIds);

        if (deleted != imageIds.size()){
            throw new BusinessException(ErrorCodeEnum.IMAGE_NOT_BELONGS_TO_PRODUCT, "Some images don't belongs to this product: " + productId);
        }

        images.forEach(image -> {
            imageStoragePort.deleteImages(image.fileName());
        });

        return deleted;
    }
}
