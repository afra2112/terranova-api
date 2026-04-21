package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.jpa;

import com.terranova.api.v1.product.domain.model.command.search.SearchProductCommand;
import com.terranova.api.v1.product.domain.model.enums.ProductTypeEnum;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.CattleEntity;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.FarmEntity;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.LandEntity;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<ProductEntity> byFilter(SearchProductCommand filter){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            applyGeneralFilters(filter, root, criteriaBuilder, predicates);
            applyCattleFilters(filter, root, criteriaBuilder, predicates);
            applyLandFilters(filter, root, criteriaBuilder, predicates);
            applyFarmFilters(filter, root, criteriaBuilder, predicates);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static void applyGeneralFilters(SearchProductCommand f, Root<ProductEntity> root, CriteriaBuilder cb, List<Predicate> p){

        if (f.userId() != null){
            p.add(cb.notEqual(root.get("sellerId"), f.userId()));
        }

        if (f.name() != null){
            p.add(cb.like(cb.lower(root.get("name")), "%" + f.name().toLowerCase() + "%"));
        }

        if (f.minPrice() != null) {
            p.add(cb.greaterThanOrEqualTo(root.get("price"), f.minPrice()));
        }

        if (f.maxPrice() != null) {
            p.add(cb.lessThanOrEqualTo(root.get("price"), f.maxPrice()));
        }

        if (f.city() != null) {
            p.add(cb.equal(root.get("city"), f.city()));
        }

        if (f.productType() != null) {
            p.add(cb.equal(root.get("productType"), f.productType()));
        }

        if (f.publishDateFrom() != null) {
            p.add(cb.greaterThanOrEqualTo(root.get("publishDate"), f.publishDateFrom()));
        }

        if (f.publishDateTo() != null) {
            p.add(cb.lessThanOrEqualTo(root.get("publishDate"), f.publishDateTo()));
        }

    }

    private static void applyCattleFilters(SearchProductCommand f, Root<ProductEntity> root, CriteriaBuilder cb, List<Predicate> p) {

        if (f.productType() != ProductTypeEnum.CATTLE) return;

        Root<CattleEntity> cattle = cb.treat(root, CattleEntity.class);

        if (f.race() != null) {
            p.add(cb.equal(cattle.get("race"), f.race()));
        }

        if (f.minWeight() != null) {
            p.add(cb.greaterThanOrEqualTo(cattle.get("weightInKg"), f.minWeight()));
        }

        if (f.maxWeight() != null) {
            p.add(cb.lessThanOrEqualTo(cattle.get("weightInKg"), f.maxWeight()));
        }

        if (f.minAge() != null) {
            p.add(cb.greaterThanOrEqualTo(cattle.get("cattleAgeInYears"), f.minAge()));
        }

        if (f.maxAge() != null) {
            p.add(cb.lessThanOrEqualTo(cattle.get("cattleAgeInYears"), f.maxAge()));
        }

        if (f.gender() != null) {
            p.add(cb.equal(cattle.get("gender"), f.gender()));
        }

        if (f.cattleType() != null) {
            p.add(cb.equal(cattle.get("cattleType"), f.cattleType()));
        }

        if (f.minQuantity() != null) {
            p.add(cb.greaterThanOrEqualTo(cattle.get("quantity"), f.minQuantity()));
        }
    }

    private static void applyFarmFilters(SearchProductCommand f, Root<ProductEntity> root, CriteriaBuilder cb, List<Predicate> p) {

        if (f.productType() != ProductTypeEnum.FARM) return;

        Root<FarmEntity> farm = cb.treat(root, FarmEntity.class);

        if (f.minTotalSpace() != null) {
            p.add(cb.greaterThanOrEqualTo(farm.get("totalSpaceInM2"), f.minTotalSpace()));
        }

        if (f.maxTotalSpace() != null) {
            p.add(cb.lessThanOrEqualTo(farm.get("totalSpaceInM2"), f.maxTotalSpace()));
        }

        if (f.minRooms() != null) {
            p.add(cb.greaterThanOrEqualTo(farm.get("roomsQuantity"), f.minRooms()));
        }

        if (f.minBathrooms() != null) {
            p.add(cb.greaterThanOrEqualTo(farm.get("bathroomsQuantity"), f.minBathrooms()));
        }
    }

    private static void applyLandFilters(SearchProductCommand f, Root<ProductEntity> root, CriteriaBuilder cb, List<Predicate> p) {

        if (f.productType() != ProductTypeEnum.LAND) return;

        Root<LandEntity> land = cb.treat(root, LandEntity.class);

        if (f.minLandSize() != null) {
            p.add(cb.greaterThanOrEqualTo(land.get("landSizeInM2"), f.minLandSize()));
        }

        if (f.maxLandSize() != null) {
            p.add(cb.lessThanOrEqualTo(land.get("landSizeInM2"), f.maxLandSize()));
        }

        if (f.currentUse() != null) {
            p.add(cb.equal(land.get("currentUse"), f.currentUse()));
        }

        if (f.topography() != null) {
            p.add(cb.equal(land.get("topography"), f.topography()));
        }

        if (f.access() != null) {
            p.add(cb.equal(land.get("access"), f.access()));
        }
    }
}
