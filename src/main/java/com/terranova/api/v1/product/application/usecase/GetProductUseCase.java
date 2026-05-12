package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.model.Image;
import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.appointment.Appointment;
import com.terranova.api.v1.product.domain.model.command.search.SearchProductCommand;
import com.terranova.api.v1.product.domain.port.out.AppointmentPort;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import java.util.List;
import java.util.Map;

public class GetProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ImageRepositoryPort imageRepositoryPort;
    private final AppointmentPort appointmentPort;

    public GetProductUseCase(ProductRepositoryPort productRepositoryPort, ImageRepositoryPort imageRepositoryPort, AppointmentPort appointmentPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.imageRepositoryPort = imageRepositoryPort;
        this.appointmentPort = appointmentPort;
    }

    public Product getProduct(Long productId, String expand){
        Product product = productRepositoryPort.getById(productId).orElseThrow(
                () -> new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Product not found by id: " + productId)
        );
        List<Long> ids = List.of(productId);

        Product withImages = product.withImages(imageRepositoryPort.getByProductId(ids).getOrDefault(productId, List.of()));

        return "appointments".equals(expand) ?
                withImages.withAppointments(appointmentPort.getByProductsIds(ids).getOrDefault(productId, List.of())) :
                withImages;
    }

    public List<Product> searchProducts(SearchProductCommand command, String expand){
        List<Product> products = productRepositoryPort.searchProducts(command);
        List<Long> ids = products.stream().map(Product::getProductId).toList();
        Map<Long, List<Image>> images = imageRepositoryPort.getByProductId(ids);

        Map<Long, List<Appointment>> appointments = "appointments".equals(expand) ? appointmentPort.getByProductsIds(ids) : Map.of();

        return products.stream()
                .map(product -> {
                    Product withImages = product.withImages(images.getOrDefault(product.getProductId(), List.of()));
                    return "appointments".equals(expand) ?
                            withImages.withAppointments(appointments.getOrDefault(product.getProductId(), List.of())) :
                            withImages;
                })
                .toList();
    }
}
