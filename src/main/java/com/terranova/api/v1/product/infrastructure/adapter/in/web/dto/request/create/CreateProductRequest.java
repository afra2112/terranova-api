package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.create;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;

import java.math.BigDecimal;
import java.util.UUID;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "productType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateCattleRequest.class, name = "CATTLE"),
        @JsonSubTypes.Type(value = CreateFarmRequest.class, name = "FARM"),
        @JsonSubTypes.Type(value = CreateLandRequest.class, name = "LAND")
})
public sealed interface CreateProductRequest permits CreateCattleRequest, CreateFarmRequest, CreateLandRequest {
    ProductTypeEnum productType();
    String name();
    BigDecimal price();
    String description();
    String city();
    Double latitude();
    Double longitude();
    UUID idSeller();
}