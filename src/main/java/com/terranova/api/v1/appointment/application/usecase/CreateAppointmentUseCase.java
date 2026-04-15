package com.terranova.api.v1.appointment.application.usecase;

import com.terranova.api.v1.appointment.domain.model.Appointment;
import com.terranova.api.v1.appointment.domain.model.command.CreateAppointmentCommand;
import com.terranova.api.v1.appointment.domain.port.out.AppointmentRepositoryPort;
import com.terranova.api.v1.appointment.domain.port.out.ProductServicePort;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.MapperIn;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;

public class CreateAppointmentUseCase {

    public final AppointmentRepositoryPort appointmentRepositoryPort;
    public final ProductServicePort productServicePort;
    public final MapperIn mapperIn;

    public CreateAppointmentUseCase(AppointmentRepositoryPort appointmentRepositoryPort, MapperIn mapperIn, ProductServicePort productServicePort) {
        this.appointmentRepositoryPort = appointmentRepositoryPort;
        this.mapperIn = mapperIn;
        this.productServicePort = productServicePort;
    }

    public Appointment createAppointment(CreateAppointmentCommand command){
        if (productServicePort.getProductById(command.productId()).isEmpty()){
            throw new BusinessException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Product not found by id: " + command.productId());
        }
        return appointmentRepositoryPort.save(mapperIn.commandToDomain(command));
    }
}
