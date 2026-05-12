package com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.jpa;

import com.terranova.api.v1.appointment.domain.model.Appointment;
import com.terranova.api.v1.appointment.domain.port.out.AppointmentRepositoryPort;
import com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.MapperPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AppointmentRepositoryAdapter implements AppointmentRepositoryPort {

    private final AppointmentJpaRepository appointmentJpaRepository;
    private final MapperPersistence mapperPersistence;

    @Override
    public Appointment save(Appointment appointment) {
        return mapperPersistence.entityToDomain(appointmentJpaRepository.save(mapperPersistence.domainToEntity(appointment)));
    }

    @Override
    public Map<Long, List<Appointment>> getByProductsIds(List<Long> productsIds) {
        return appointmentJpaRepository.getByProductsIds(productsIds)
                .stream()
                .map(mapperPersistence::entityToDomain)
                .collect(Collectors.groupingBy(Appointment::productId));
    }
}
