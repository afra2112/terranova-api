package com.terranova.api.v1.product.application.usecase;

import com.terranova.api.v1.product.domain.model.Product;
import com.terranova.api.v1.product.domain.model.command.search.SearchProductCommand;
import com.terranova.api.v1.product.domain.port.out.AppointmentPort;
import com.terranova.api.v1.product.domain.port.out.ImageRepositoryPort;
import com.terranova.api.v1.product.domain.port.out.ProductRepositoryPort;

import java.util.List;

public class SearchProductsUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ImageRepositoryPort imageRepositoryPort;
    private final AppointmentPort appointmentPort;

    public SearchProductsUseCase(ProductRepositoryPort productRepositoryPort, ImageRepositoryPort imageRepositoryPort, AppointmentPort appointmentPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.imageRepositoryPort = imageRepositoryPort;
        this.appointmentPort = appointmentPort;
    }

    public List<Product> searchProducts(SearchProductCommand command, String expand){

        List<Product> products = productRepositoryPort.searchProducts(command)
                .stream()
                .map(product -> product.withImages(imageRepositoryPort.getByProductId(product.getProductId())))
                .toList();

        if ("appointments".equals(expand)){
            products = products.stream()
                    .map(product -> product.withAppointments(
                            appointmentPort.getByProductId(product.getProductId()))
                    ).toList();
        }

        return products;
    }
}
