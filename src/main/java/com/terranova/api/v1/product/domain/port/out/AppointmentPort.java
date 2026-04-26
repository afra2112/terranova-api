package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;

import java.util.List;

public interface AppointmentPort {

    List<Appointment> getByProductId(Long productId);
}
