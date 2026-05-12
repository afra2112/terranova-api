package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;

import java.util.List;
import java.util.Map;

public interface AppointmentPort {

    Map<Long, List<Appointment>> getByProductsIds(List<Long> productId);
}
