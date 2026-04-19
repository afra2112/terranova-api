package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.create;

import com.terranova.api.v1.product.domain.model.group.CattleGroup;
import com.terranova.api.v1.product.domain.model.enums.CattleGenderEnum;
import com.terranova.api.v1.product.domain.model.enums.CattleTypeEnum;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record CreateCattleRequest(
        @NotNull
        ProductTypeEnum productType,
        @NotBlank
        String name,
        @NotNull
        @Positive
        BigDecimal price,
        @NotBlank
        String description,
        @NotBlank
        String city,
        @NotNull
        Double latitude,
        @NotNull
        Double longitude,
        @NotNull
        UUID idSeller,


        @NotBlank(groups = CattleGroup.class)
        String race,

        @NotNull(groups = CattleGroup.class)
        Double weightInKg,

        @NotNull(groups = CattleGroup.class)
        Double cattleAgeInYears,

        @NotNull(groups = CattleGroup.class)
        CattleGenderEnum gender,

        @NotNull(groups = CattleGroup.class)
        CattleTypeEnum cattleType,

        @NotNull(groups = CattleGroup.class)
        int quantity
) implements CreateProductRequest {}