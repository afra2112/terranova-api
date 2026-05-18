package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.delete;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record DeleteImageRequest(
        @Positive
        Long productId,

        @NotEmpty
        List<Long> imagesIds
) {
}
