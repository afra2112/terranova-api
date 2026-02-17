package com.terranova.api.v1.product.dto.createResponse;

import com.terranova.api.v1.product.enums.LandAccessEnum;
import com.terranova.api.v1.product.enums.LandTopographyEnum;
import com.terranova.api.v1.product.enums.ProductType;
import com.terranova.api.v1.product.enums.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateLandResponse(
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

        Double landSizeInM2,
        String currentUse,
        LandTopographyEnum topography,
        LandAccessEnum access,
        String currentServices
) implements CreateProductResponse { }
