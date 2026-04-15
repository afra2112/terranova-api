package com.terranova.api.v1.appointment.domain.model.product;

import com.terranova.api.v1.appointment.domain.model.product.enums.ProductTypeEnum;
import com.terranova.api.v1.appointment.domain.model.product.enums.StatusEnum;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record FarmResponse(
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

        Double totalSpaceInM2,
        Double builtSpaceInM2,
        int stratum,
        int roomsQuantity,
        int bathroomsQuantity
) implements ProductResponse {}
