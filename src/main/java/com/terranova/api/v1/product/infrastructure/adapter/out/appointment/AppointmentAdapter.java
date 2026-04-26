package com.terranova.api.v1.product.infrastructure.adapter.out.appointment;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;
import com.terranova.api.v1.product.domain.port.out.AppointmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppointmentAdapter implements AppointmentPort {

    private final AppointmentFeign appointmentFeign;

    @Override
    public List<Appointment> getByProductId(Long productId) {
        return appointmentFeign.getAppointmentsByProductId(productId);
    }
}
