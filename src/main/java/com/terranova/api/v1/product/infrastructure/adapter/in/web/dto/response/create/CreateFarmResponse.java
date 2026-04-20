package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.create;

import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.StatusEnum;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.ImageResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record CreateFarmResponse(
        ProductTypeEnum productType,
        String name,
        BigDecimal price,
        String description,
        StatusEnum status,
        LocalDate publishDate,
        String city,
        Double latitude,
        Double longitude,
        UUID sellerId,
        List<ImageResponse> images,

        Double totalSpaceInM2,
        Double builtSpaceInM2,
        int stratum,
        int roomsQuantity,
        int bathroomsQuantity
) implements CreateProductResponse {}
