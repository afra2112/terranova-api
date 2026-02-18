package com.terranova.api.v1.product.dto.createRequest;

import com.terranova.api.v1.product.dto.FarmGroup;
import com.terranova.api.v1.product.enums.ProductType;
import com.terranova.api.v1.product.enums.StatusEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateFarmRequest(
        ProductType productType,
        String name,
        BigDecimal price,
        String description,
        StatusEnum status,
        LocalDate publishDate,
        String city,
        Double latitude,
        Double longitude,
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
