package com.terranova.api.v1.product.infrastructure.adapter.in.web.controller;

import com.terranova.api.v1.product.application.usecase.CreateImageUseCase;
import com.terranova.api.v1.product.application.usecase.CreateProductUseCase;
import com.terranova.api.v1.product.application.usecase.DeleteImageUseCase;
import com.terranova.api.v1.product.application.usecase.GetProductUseCase;
import com.terranova.api.v1.product.domain.model.command.create.CreateImageCommand;
import com.terranova.api.v1.product.domain.model.group.CattleGroup;
import com.terranova.api.v1.product.domain.model.group.FarmGroup;
import com.terranova.api.v1.product.domain.model.group.LandGroup;
import com.terranova.api.v1.product.domain.port.out.ValidatorPort;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.create.CreateProductRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.delete.DeleteImageRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.request.search.SearchProductRequest;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.create.CreateProductResponse;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.ImageResponse;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.delete.DeleteImageResponse;
import com.terranova.api.v1.product.infrastructure.adapter.mapper.ImageMapper;
import com.terranova.api.v1.product.infrastructure.adapter.mapper.ProductMapper;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final DeleteImageUseCase deleteImageUseCase;
    private final CreateImageUseCase createImageUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final ProductMapper productMapper;
    private final ImageMapper imageMapper;
    private final ValidatorPort validatorPort;

    @GetMapping("/search")
    public ResponseEntity<List<CreateProductResponse>> searchProducts(@RequestBody SearchProductRequest request, @RequestParam(required = false) String expand){
        return ResponseEntity.ok().body(
                getProductUseCase.searchProducts(productMapper.searchRequestToCommand(request), expand)
                        .stream()
                        .map(productMapper::domainToResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateProductResponse> getProductById(@Valid @PathVariable Long id, @RequestParam(required = false) String expand){
        return ResponseEntity.ok(productMapper.domainToResponse(getProductUseCase.getProduct(id, expand)));
    }

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request){
        validatorPort.validate(request, getGroupFromRequestProductType(request.productType().name()));
        return ResponseEntity.ok().body(productMapper.domainToResponse(createProductUseCase.createProduct(productMapper.requestToCommand(request))));
    }

    @PostMapping(value = "/{id}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<List<ImageResponse>> saveImagesForProduct(@Valid @PathVariable Long id, @RequestPart("files") List<MultipartFile> files){
        List<CreateImageCommand> commands = IntStream.range(0, files.size())
                .mapToObj(i -> {
                    MultipartFile file = files.get(i);
                    try {
                        return new CreateImageCommand(
                                file.getOriginalFilename(),
                                file.getContentType(),
                                file.getSize(),
                                i+1,
                                file.getBytes()
                        );
                    } catch (IOException e) {
                        throw new BusinessException(ErrorCodeEnum.IMAGE_READ_ERROR, e.getMessage());
                    }
                })
                .toList();

        return ResponseEntity.ok(createImageUseCase.createImages(commands, id).stream()
                .map(imageMapper::domainToResponse).toList());
    }

    @DeleteMapping(value = "/{id}/images")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<DeleteImageResponse> deleteImage(@RequestBody @Valid DeleteImageRequest request){
        return ResponseEntity.ok(new DeleteImageResponse(deleteImageUseCase.deleteImages(request.productId(), request.imagesIds())));
    }

    private Class<?> getGroupFromRequestProductType(String productType){
        return switch (productType){
            case "FARM" -> FarmGroup.class;
            case "LAND" -> LandGroup.class;
            case "CATTLE" -> CattleGroup.class;
            default -> throw new BusinessException(ErrorCodeEnum.PRODUCT_TYPE_NOT_SUPPORTED);
        };
    }
}