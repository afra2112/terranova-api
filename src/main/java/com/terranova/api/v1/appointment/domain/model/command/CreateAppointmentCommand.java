package com.terranova.api.v1.appointment.domain.model.command;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateAppointmentCommand(
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        String description,
        Long productId,
        Integer maxQuorum
) {
}
