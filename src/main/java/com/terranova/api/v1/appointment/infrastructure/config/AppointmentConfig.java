package com.terranova.api.v1.appointment.infrastructure.config;

import com.terranova.api.v1.appointment.application.usecase.CreateAppointmentUseCase;
import com.terranova.api.v1.appointment.domain.port.out.AppointmentRepositoryPort;
import com.terranova.api.v1.appointment.domain.port.out.ProductServicePort;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.MapperIn;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConfig {

    @Bean
    public CreateAppointmentUseCase createAppointmentUseCase(AppointmentRepositoryPort appointmentRepositoryPort, MapperIn mapperIn, ProductServicePort productServicePort){
       return new CreateAppointmentUseCase(appointmentRepositoryPort, mapperIn, productServicePort);
    }
}
