package com.terranova.api.v1.product.entity;

import com.terranova.api.v1.product.enums.CattleGenderEnum;
import com.terranova.api.v1.product.enums.CattleTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cattles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Cattle extends Product {

    @Column(nullable = false, length = 30)
    private String race;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private int cattleAgeInYears;

    @Column(nullable = false)
    private CattleGenderEnum gender;

    @Enumerated(EnumType.STRING)
    private CattleTypeEnum type;

    @Column(nullable = false)
    private int quantity;
}
