package com.terranova.api.v1.product.factory;

import com.terranova.api.v1.product.dto.createRequest.CreateProductRequest;
import com.terranova.api.v1.product.entity.Cattle;
import com.terranova.api.v1.product.entity.Farm;
import com.terranova.api.v1.product.entity.Land;
import com.terranova.api.v1.product.entity.Product;
import com.terranova.api.v1.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ProductFactoryImpl implements ProductFactory {

    @Override
    public Product create(CreateProductRequest request, User seller) {
        return switch (request.type()){
            case FARM -> buildFarm(request, seller);
            case LAND -> buildLand(request, seller);
            case CATTLE -> buildCattle(request, seller);
        };
    }

//    private Cattle buildCattle(CreateProductRequest request, User seller){
//        buildBase(Cattle.builder(), request, seller);
//
//        return Cattle.builder()
//                .race(request.race())
//                .weight(request.weightInKg())
//                .cattleAgeInYears(request.cattleAgeInYears())
//                .gender(request.gender())
//                .type(request.cattleType())
//                .quantity(request.quantity())
//                .build();
//    }

//    private Farm buildFarm(CreateProductRequest request, User seller){
//        buildBase(Farm.builder(), request, seller);
//
//        return Farm.builder()
//                .totalSpaceInM2(request.totalSpaceInM2())
//                .builtSpaceInM2(request.builtSpaceInM2())
//                .stratum(request.stratum())
//                .roomsQuantity(request.roomsQuantity())
//                .bathroomsQuantity(request.bathroomsQuantity())
//                .build();
//    }
//
//    private Land buildLand(CreateProductRequest request, User seller){
//        buildBase(Land.builder(), request, seller);
//
//        return Land.builder()
//                .landSizeInM2(request.landSizeInM2())
//                .currentUse(request.currentUse())
//                .topography(request.topography())
//                .access(request.access())
//                .currentServices(request.currentServices())
//                .build();
//    }
//
//    private void buildBase(Product.ProductBuilder<?, ?> builder, CreateProductRequest request, User seller){
//            builder
//                .name(request.name())
//                .price(request.price())
//                .description(request.description())
//                .status(request.status())
//                .publishDate(request.publishDate())
//                .city(request.city())
//                .latitude(request.latitude())
//                .longitude(request.longitude())
//                .seller(seller);
//    }
}
