package com.terranova.api.v1.product.dto.createRequest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.terranova.api.v1.product.enums.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
public sealed interface CreateProductRequest permits CreateCattleRequest, CreateFarmRequest, CreateLandRequest {
        ProductType type();
        String name();
        BigDecimal price();
        String description();
        StatusEnum status();
        LocalDate publishDate();
        String city();
        Double latitude();
        Double longitude();
        UUID idSeller();
}