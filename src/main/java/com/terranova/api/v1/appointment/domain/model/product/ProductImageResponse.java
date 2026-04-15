package com.terranova.api.v1.appointment.domain.model.product;

import java.time.LocalDateTime;

public record ProductImageResponse(
        Long productId,
        Long idImage,
        String fileName,
        String url,
        String contentType,
        long size,
        Integer displayOrder,
        LocalDateTime createdAt
) {
}
