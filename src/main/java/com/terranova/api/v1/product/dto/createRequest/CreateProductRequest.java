package com.terranova.api.v1.product.dto.createRequest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.terranova.api.v1.product.enums.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "productType"
)
public sealed interface CreateProductRequest permits CreateCattleRequest, CreateFarmRequest, CreateLandRequest {
        @NotNull
        ProductType productType();
        @NotBlank
        String name();
        @NotNull
        @Positive
        BigDecimal price();
        @NotBlank
        String description();
        @NotBlank
        String city();
        @NotNull
        Double latitude();
        @NotNull
        Double longitude();
        @NotNull
        UUID idSeller();
}