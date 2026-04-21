package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.search;

import com.terranova.api.v1.product.domain.model.enums.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


public record SearchProductRequest(
        //GENERAL
        UUID userId,
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

        //LAND
        @Positive
        Double minLandSize,
        @Positive
        Double maxLandSize,
        String currentUse,
        LandTopographyEnum topography,
        LandAccessEnum access,

        //FARM
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
        Integer maxBathrooms,

        //CATTLE
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
){
}
