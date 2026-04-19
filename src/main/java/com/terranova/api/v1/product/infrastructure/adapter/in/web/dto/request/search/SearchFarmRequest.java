package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.search;

import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SearchFarmRequest(
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
        Double minTotalSpace,
        @Positive
        Double maxTotalSpace,
        @PositiveOrZero
        Double minBuiltSpace,
        @PositiveOrZero
        Double maxBuiltSpace,
        @Positive
        Integer minStratum,
        @Positive
        Integer maxStratum,
        @PositiveOrZero
        Integer minRooms,
        @PositiveOrZero
        Integer maxRooms,
        @PositiveOrZero
        Integer minBathrooms,
        @PositiveOrZero
        Integer maxBathrooms

) implements SearchProductRequest {
}
