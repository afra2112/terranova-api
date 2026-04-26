package com.terranova.api.v1.product.domain.model;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Farm extends Product {
    private Double totalSpaceInM2;
    private Double builtSpaceInM2;
    private int stratum;
    private int roomsQuantity;
    private int bathroomsQuantity;

    @Override
    public Product withImages(List<Image> images) {
        return Farm.builder()
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
                .totalSpaceInM2(this.totalSpaceInM2)
                .builtSpaceInM2(this.builtSpaceInM2)
                .stratum(this.stratum)
                .roomsQuantity(this.roomsQuantity)
                .bathroomsQuantity(this.bathroomsQuantity)
                .images(List.copyOf(images))
                .build();
    }

    @Override
    public Product withAppointments(List<Appointment> appointments) {
        return Farm.builder()
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
                .totalSpaceInM2(this.totalSpaceInM2)
                .builtSpaceInM2(this.builtSpaceInM2)
                .stratum(this.stratum)
                .roomsQuantity(this.roomsQuantity)
                .bathroomsQuantity(this.bathroomsQuantity)
                .appointments(List.copyOf(appointments))
                .build();
    }
}
