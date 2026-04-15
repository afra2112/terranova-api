package com.terranova.api.v1.appointment.domain.model.product;

import com.terranova.api.v1.appointment.domain.model.product.enums.LandAccessEnum;
import com.terranova.api.v1.appointment.domain.model.product.enums.LandTopographyEnum;
import com.terranova.api.v1.appointment.domain.model.product.enums.ProductTypeEnum;
import com.terranova.api.v1.appointment.domain.model.product.enums.StatusEnum;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record LandResponse(
        ProductTypeEnum productType,
        String name,
        BigDecimal price,
        String description,
        StatusEnum status,
        LocalDate publishDate,
        String city,
        Double latitude,
        Double longitude,
        UUID sellerId,
        List<ProductImageResponse> images,

        Double landSizeInM2,
        String currentUse,
        LandTopographyEnum topography,
        LandAccessEnum access,
        String currentServices
) implements ProductResponse { }