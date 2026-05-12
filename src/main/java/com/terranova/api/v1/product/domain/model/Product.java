package com.terranova.api.v1.product.domain.model;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public abstract class Product {
    private Long productId;
    private String name;
    private BigDecimal price;
    private String description;
    private String status;
    private LocalDate publishDate;
    private String city;
    private Double latitude;
    private Double longitude;
    private UUID sellerId;
    private ProductTypeEnum productType;
    private List<Image> images;
    private List<Appointment> appointments;

    public abstract Product withImages(List<Image> images);
    public abstract Product withAppointments(List<Appointment> appointments);
}
