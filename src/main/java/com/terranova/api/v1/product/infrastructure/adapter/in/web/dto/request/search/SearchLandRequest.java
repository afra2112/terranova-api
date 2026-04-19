package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.search;

import com.terranova.api.v1.product.domain.model.enums.LandAccessEnum;
import com.terranova.api.v1.product.domain.model.enums.LandTopographyEnum;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SearchLandRequest(
        String name,
        @Positive
        BigDecimal minPrice,
        @Positive
        BigDecimal maxPrice,
        String city,
        ProductTypeEnum productType,
        LocalDate publishDateFrom,
        LocalDate publishDateTo,
        Double latitude,
        Double longitude,
        @Positive
        Double radiusKm,

        @Positive
        Double minLandSize,
        @Positive
        Double maxLandSize,
        String currentUse,
        LandTopographyEnum topography,
        LandAccessEnum access
) implements SearchProductRequest {
}
