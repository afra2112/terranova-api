package com.terranova.api.v1.appointment.application.usecase;

import com.terranova.api.v1.appointment.domain.model.Appointment;
import com.terranova.api.v1.appointment.domain.port.out.AppointmentRepositoryPort;

import java.util.List;
import java.util.Map;

public class GetAppointmentsByProductUseCase {

    private final AppointmentRepositoryPort appointmentRepositoryPort;

    public GetAppointmentsByProductUseCase(AppointmentRepositoryPort appointmentRepositoryPort) {
        this.appointmentRepositoryPort = appointmentRepositoryPort;
    }

    public Map<Long, List<Appointment>> getAppointmentsByProducts(List<Long> productsIds){
        return appointmentRepositoryPort.getByProductsIds(productsIds);
    }
}
