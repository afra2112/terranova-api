package com.terranova.api.v1.product.domain.model.appointment;

import com.terranova.api.v1.appointment.domain.model.Attendance;
import com.terranova.api.v1.appointment.domain.model.enums.AppointmentStatusEnum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record Appointment(
        Long appointmentId,
        AppointmentStatusEnum status,
        Integer maximumQuorum,
        List<Attendance> attendances,
        boolean deleted,
        Integer takenSlots,
        Integer availableSlots,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        String description,
        Long productId
) {
}
