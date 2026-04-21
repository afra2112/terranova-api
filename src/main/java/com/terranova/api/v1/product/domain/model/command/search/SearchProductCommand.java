package com.terranova.api.v1.product.domain.model.command.search;

import com.terranova.api.v1.product.domain.model.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SearchProductCommand (

        //GENERAL
        UUID userId,
        String name,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        String city,
        ProductTypeEnum productType,
        LocalDate publishDateFrom,
        LocalDate publishDateTo,
        Double latitude,
        Double longitude,
        Double radiusKm,

        //CATTLE
        String race,
        Double minWeight,
        Double maxWeight,
        Double minAge,
        Double maxAge,
        CattleGenderEnum gender,
        CattleTypeEnum cattleType,
        Integer minQuantity,
        Integer maxQuantity,

        //FARM
        Double minTotalSpace,
        Double maxTotalSpace,
        Double minBuiltSpace,
        Double maxBuiltSpace,
        Integer minStratum,
        Integer maxStratum,
        Integer minRooms,
        Integer maxRooms,
        Integer minBathrooms,
        Integer maxBathrooms,

        //LAND
        Double minLandSize,
        Double maxLandSize,
        String currentUse,
        LandTopographyEnum topography,
        LandAccessEnum access
){ }
