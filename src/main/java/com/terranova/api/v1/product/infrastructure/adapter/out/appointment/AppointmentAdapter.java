package com.terranova.api.v1.product.infrastructure.adapter.out.appointment;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;
import com.terranova.api.v1.product.domain.port.out.AppointmentPort;
import com.terranova.api.v1.product.infrastructure.adapter.mapper.AppointmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AppointmentAdapter implements AppointmentPort {

    private final AppointmentFeign appointmentFeign;
    private final AppointmentMapper appointmentMapper;

    @Override
    public Map<Long, List<Appointment>> getByProductsIds(List<Long> productsIds) {
        return appointmentFeign.getAppointmentsByProductId(productsIds)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue()
                                .stream()
                                .map(appointmentMapper::responseToDomain)
                                .toList()
                ));
    }
}
