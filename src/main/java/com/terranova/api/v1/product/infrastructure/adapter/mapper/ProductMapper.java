package com.terranova.api.v1.product.infrastructure.adapter.mapper;

import com.terranova.api.v1.product.domain.model.Cattle;
import com.terranova.api.v1.product.domain.model.Farm;
import com.terranova.api.v1.product.domain.model.Land;
import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.command.create.CreateCattleCommand;
import com.terranova.api.v1.product.domain.model.command.create.CreateFarmCommand;
import com.terranova.api.v1.product.domain.model.command.create.CreateLandCommand;
import com.terranova.api.v1.product.domain.model.command.create.CreateProductCommand;
import com.terranova.api.v1.product.domain.model.command.search.SearchProductCommand;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.create.CreateCattleRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.create.CreateFarmRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.create.CreateLandRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.create.CreateProductRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.search.SearchProductRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.create.CreateCattleResponse;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.create.CreateFarmResponse;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.create.CreateLandResponse;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.create.CreateProductResponse;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.CattleEntity;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.FarmEntity;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.LandEntity;
import com.terranova.api.v1.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ImageMapper.class)
public interface ProductMapper {

        SearchProductCommand searchRequestToCommand(SearchProductRequest request);

        default CreateProductCommand requestToCommand(CreateProductRequest request) {
                if (request == null) return null;
                return switch (request) {
                        case CreateFarmRequest f -> farmRequestToFarmCommand(f);
                        case CreateLandRequest l -> landRequestToLandCommand(l);
                        case CreateCattleRequest c -> cattleRequestToCattleCommand(c);
                        default -> throw new BusinessException(ErrorCodeEnum.PRODUCT_TYPE_NOT_SUPPORTED, "Product type: " + request);
                };
        }
        CreateFarmCommand farmRequestToFarmCommand(CreateFarmRequest createFarmRequest);
        CreateLandCommand landRequestToLandCommand(CreateLandRequest createLandRequest);
        CreateCattleCommand cattleRequestToCattleCommand(CreateCattleRequest createCattleRequest);

        default Product entityToDomain(ProductEntity entity) {
                if (entity == null) return null;
                return switch (entity) {
                        case FarmEntity f -> farmEntityToDomain(f);
                        case LandEntity l -> landEntityToDomain(l);
                        case CattleEntity c -> cattleEntityToDomain(c);
                        default -> throw new BusinessException(ErrorCodeEnum.PRODUCT_TYPE_NOT_SUPPORTED, "Product type: " + entity);
                };
        }
        Farm farmEntityToDomain(FarmEntity farmEntity);
        Land landEntityToDomain(LandEntity landEntity);
        Cattle cattleEntityToDomain(CattleEntity cattleEntity);

        default ProductEntity domainToEntity(Product product) {
                if (product == null) return null;
                return switch (product) {
                        case Farm f -> farmDomainToEntity(f);
                        case Land l -> landDomainToEntity(l);
                        case Cattle c -> cattleDomainToEntity(c);
                        default -> throw new BusinessException(ErrorCodeEnum.PRODUCT_TYPE_NOT_SUPPORTED, "Product type: " + product);
                };
        }
        FarmEntity farmDomainToEntity(Farm farm);
        LandEntity landDomainToEntity(Land land);
        CattleEntity cattleDomainToEntity(Cattle cattle);

        default CreateProductResponse domainToResponse(Product product) {
                if (product == null) return null;
                return switch (product) {
                        case Farm f -> farmDomainToResponse(f);
                        case Land l -> landDomainToResponse(l);
                        case Cattle c -> cattleDomainToResponse(c);
                        default -> throw new BusinessException(ErrorCodeEnum.PRODUCT_TYPE_NOT_SUPPORTED, "Product type: " + product);
                };
        }
        CreateFarmResponse farmDomainToResponse(Farm farm);
        CreateLandResponse landDomainToResponse(Land land);
        CreateCattleResponse cattleDomainToResponse(Cattle cattle);
}
