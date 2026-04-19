package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.create;

import com.terranova.api.v1.product.domain.model.group.FarmGroup;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record CreateFarmRequest(
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

        @NotNull(groups = FarmGroup.class)
        @Positive(groups = FarmGroup.class)
        Double totalSpaceInM2,

        @NotNull(groups = FarmGroup.class)
        @Positive(groups = FarmGroup.class)
        Double builtSpaceInM2,

        @NotNull(groups = FarmGroup.class)
        @Positive(groups = FarmGroup.class)
        int stratum,

        @NotNull(groups = FarmGroup.class)
        @Positive(groups = FarmGroup.class)
        int roomsQuantity,

        @NotNull(groups = FarmGroup.class)
        @Positive(groups = FarmGroup.class)
        int bathroomsQuantity
) implements CreateProductRequest {}