package com.terranova.api.v1.product.dto.createRequest;

import com.terranova.api.v1.product.dto.LandGroup;
import com.terranova.api.v1.product.enums.LandAccessEnum;
import com.terranova.api.v1.product.enums.LandTopographyEnum;
import com.terranova.api.v1.product.enums.ProductType;
import com.terranova.api.v1.product.enums.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateLandRequest(
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

        @NotNull(groups = LandGroup.class)
        @Positive(groups = LandGroup.class)
        Double landSizeInM2,

        @NotBlank(groups = LandGroup.class)
        String currentUse,

        @NotNull(groups = LandGroup.class)
        LandTopographyEnum topography,

        @NotNull(groups = LandGroup.class)
        LandAccessEnum access,

        @NotBlank(groups = LandGroup.class)
        String currentServices
) implements CreateProductRequest { }
