package com.terranova.api.v1.product.dto;

import com.terranova.api.v1.product.enums.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateProductRequest(
        @NotNull
        ProductType type,

        @NotBlank
        String name,

        @NotNull
        @DecimalMin(value = "1")
        @Digits(integer = 13, fraction = 0)
        BigDecimal price,

        @NotBlank
        String description,

        @NotNull
        StatusEnum status,

        @NotNull
        LocalDate publishDate,

        @NotNull
        String city,

        @NotNull
        Double latitude,

        @NotNull
        Double longitude,

        @NotNull
        @Positive
        Long idSeller,

        // ---- Cattle fields ----
        @NotBlank(groups = CattleGroup.class)
        String race,

        @NotNull(groups = CattleGroup.class)
        @Positive
        Double weightInKg,

        @NotNull(groups = CattleGroup.class)
        @Positive
        Double cattleAgeInYears,

        @NotNull(groups = CattleGroup.class)
        CattleGenderEnum gender,

        @NotNull(groups = CattleGroup.class)
        CattleTypeEnum cattleType,

        @NotNull(groups = CattleGroup.class)
        @Positive
        int quantity,

        // ---- Farm fields ----
        @NotNull(groups = FarmGroup.class)
        @Positive
        Double totalSpaceInM2,

        @NotNull(groups = FarmGroup.class)
        @Positive
        Double builtSpaceInM2,

        @NotNull(groups = FarmGroup.class)
        @Positive
        int stratum,

        @NotNull(groups = FarmGroup.class)
        @Positive
        int roomsQuantity,

        @NotNull(groups = FarmGroup.class)
        @Positive
        int bathroomsQuantity,

        // ---- Land fields ----
        @NotNull(groups = LandGroup.class)
        @Positive
        Double landSizeInM2,

        @NotBlank(groups = LandGroup.class)
        String currentUse,

        @NotNull(groups = LandGroup.class)
        LandTopographyEnum topography,

        @NotNull(groups = LandGroup.class)
        LandAccessEnum access,

        @NotBlank(groups = LandGroup.class)
        String currentServices
) {
}