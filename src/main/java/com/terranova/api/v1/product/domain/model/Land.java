package com.terranova.api.v1.product.domain.model;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;
import com.terranova.api.v1.product.domain.model.enums.LandAccessEnum;
import com.terranova.api.v1.product.domain.model.enums.LandTopographyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Land extends Product{
    private Double landSizeInM2;
    private String currentUse;
    private LandTopographyEnum topography;
    private LandAccessEnum access;
    private String currentServices;

    @Override
    public Product withImages(List<Image> images) {
        return Land.builder()
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
                .landSizeInM2(this.landSizeInM2)
                .currentServices(this.currentUse)
                .topography(this.topography)
                .access(this.access)
                .currentServices(this.currentServices)
                .images(List.copyOf(images))
                .build();
    }

    @Override
    public Product withAppointments(List<Appointment> appointments) {
        return Land.builder()
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
                .landSizeInM2(this.landSizeInM2)
                .currentServices(this.currentUse)
                .topography(this.topography)
                .access(this.access)
                .currentServices(this.currentServices)
                .appointments(List.copyOf(appointments))
                .build();
    }
}
