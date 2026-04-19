package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.create;

import com.terranova.api.v1.product.domain.model.group.LandGroup;
import com.terranova.api.v1.product.domain.model.enums.LandAccessEnum;
import com.terranova.api.v1.product.domain.model.enums.LandTopographyEnum;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record CreateLandRequest(
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