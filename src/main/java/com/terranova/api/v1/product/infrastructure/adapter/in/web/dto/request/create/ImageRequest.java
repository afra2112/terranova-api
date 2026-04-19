package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.create;

import org.springframework.web.multipart.MultipartFile;

public record ImageRequest(
        MultipartFile file,
        Integer displayOrder
) {
}
