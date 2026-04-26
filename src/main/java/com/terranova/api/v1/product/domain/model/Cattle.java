package com.terranova.api.v1.product.domain.model;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;
import com.terranova.api.v1.product.domain.model.enums.CattleGenderEnum;
import com.terranova.api.v1.product.domain.model.enums.CattleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Cattle extends Product{
    private String race;
    private Double weightInKg;
    private Double cattleAgeInYears;
    private CattleGenderEnum gender;
    private CattleTypeEnum cattleType;
    private int quantity;

    @Override
    public Product withImages(List<Image> images) {
        return Cattle.builder()
                .productId(this.getProductId())
                .name(this.getName())
                .price(this.getPrice())
                .description(this.getDescription())
                .status(this.getStatus())
                .publishDate(this.getPublishDate())
                .city(this.getCity())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .sellerId(this.getSellerId())
                .productType(this.getProductType())
                .race(this.race)
                .weightInKg(this.weightInKg)
                .gender(this.gender)
                .cattleType(this.cattleType)
                .images(List.copyOf(images))
                .build();
    }

    @Override
    public Product withAppointments(List<Appointment> appointments) {
        return Cattle.builder()
                .productId(this.getProductId())
                .name(this.getName())
                .price(this.getPrice())
                .description(this.getDescription())
                .status(this.getStatus())
                .publishDate(this.getPublishDate())
                .city(this.getCity())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .sellerId(this.getSellerId())
                .productType(this.getProductType())
                .race(this.race)
                .weightInKg(this.weightInKg)
                .gender(this.gender)
                .cattleType(this.cattleType)
                .appointments(List.copyOf(appointments))
                .build();
    }
}
