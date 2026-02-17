package com.terranova.api.v1.product.dto.createResponse;

import com.terranova.api.v1.product.enums.CattleGenderEnum;
import com.terranova.api.v1.product.enums.CattleTypeEnum;
import com.terranova.api.v1.product.enums.ProductType;
import com.terranova.api.v1.product.enums.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateCattleResponse(
        ProductType type,
        String name,
        BigDecimal price,
        String description,
        StatusEnum status,
        LocalDate publishDate,
        String city,
        Double latitude,
        Double longitude,
        UUID idSeller,

        String race,
        Double weightInKg,
        Double cattleAgeInYears,
        CattleGenderEnum gender,
        CattleTypeEnum cattleType,
        int quantity
) implements CreateProductResponse {}
