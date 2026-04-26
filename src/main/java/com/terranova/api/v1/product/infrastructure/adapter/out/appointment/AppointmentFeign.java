package com.terranova.api.v1.product.infrastructure.adapter.out.appointment;

import com.terranova.api.v1.product.domain.model.appointment.Appointment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "appointment", url = "http://localhost:8080/api/v1/appointments")
public interface AppointmentFeign {

    @GetMapping("/product/{id}")
    List<Appointment> getAppointmentsByProductId(@PathVariable Long id);
}
