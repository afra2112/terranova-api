package com.terranova.api.v1.appointment.domain.model.product;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.terranova.api.v1.appointment.domain.model.product.enums.ProductTypeEnum;
import com.terranova.api.v1.appointment.domain.model.product.enums.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "productType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CattleResponse.class, name = "CATTLE"),
        @JsonSubTypes.Type(value = FarmResponse.class, name = "FARM"),
        @JsonSubTypes.Type(value = LandResponse.class, name = "LAND")
})
public sealed interface ProductResponse permits CattleResponse, FarmResponse, LandResponse {
    ProductTypeEnum productType();
    String name();
    BigDecimal price();
    String description();
    StatusEnum status();
    LocalDate publishDate();
    String city();
    Double latitude();
    Double longitude();
    UUID sellerId();
    List<ProductImageResponse> images();
}