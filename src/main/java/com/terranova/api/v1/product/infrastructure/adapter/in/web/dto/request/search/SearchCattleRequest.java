package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.search;

import com.terranova.api.v1.product.domain.model.enums.CattleGenderEnum;
import com.terranova.api.v1.product.domain.model.enums.CattleTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SearchCattleRequest(
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

        String race,
        @PositiveOrZero
        Double minWeight,
        @PositiveOrZero
        Double maxWeight,
        @PositiveOrZero
        Double minAge,
        @PositiveOrZero
        Double maxAge,
        CattleGenderEnum gender,
        CattleTypeEnum cattleType,
        @PositiveOrZero
        Integer minQuantity,
        @PositiveOrZero
        Integer maxQuantity
) implements SearchProductRequest {
}
