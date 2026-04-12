package com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto;

import com.terranova.api.v1.appointment.domain.model.Attendance;
import com.terranova.api.v1.appointment.domain.model.enums.AppointmentStatusEnum;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public record CreateAppointmentResponse(
        Long appointmentId,
        AppointmentStatusEnum status,
        Integer maximumQuorum,
        ArrayList<Attendance> attendances,
        boolean deleted,
        Integer takenSlots,
        Integer availableSlots,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        String description,
        Long productId,
        Integer reprogrammingAttempts
) {
}