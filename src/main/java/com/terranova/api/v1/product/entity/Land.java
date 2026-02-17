package com.terranova.api.v1.product.entity;

import com.terranova.api.v1.product.enums.LandAccessEnum;
import com.terranova.api.v1.product.enums.LandTopographyEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "lands")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Land extends Product{

    @Column(nullable = false)
    private Double landSizeInM2;

    @Column(nullable = false)
    private String currentUse;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LandTopographyEnum topography;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LandAccessEnum access;

    @Column(nullable = false)
    private String currentServices;

}
