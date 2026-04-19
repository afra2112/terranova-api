package com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.search;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "productType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SearchFarmRequest.class),
        @JsonSubTypes.Type(value = SearchLandRequest.class),
        @JsonSubTypes.Type(value = SearchCattleRequest.class)
})
public sealed interface SearchProductRequest permits SearchCattleRequest, SearchFarmRequest, SearchLandRequest {
    String name();
    BigDecimal minPrice();
    BigDecimal maxPrice();
    String city();
    ProductTypeEnum productType();
    LocalDate publishDateFrom();
    LocalDate publishDateTo();
    Double latitude();
    Double longitude();
    Double radiusKm();

}
