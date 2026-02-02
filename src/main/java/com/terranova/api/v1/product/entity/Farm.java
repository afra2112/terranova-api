package com.terranova.api.v1.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "farms")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Farm extends Product{

    @Column(nullable = false)
    private Double totalSpaceInM2;

    @Column(nullable = false)
    private Double buildedSpaceInM2;

    @Column(nullable = false)
    private int stratum;

    @Column(nullable = false)
    private int roomsQuantity;

    @Column(nullable = false)
    private int bathroomsQuantity;
}
