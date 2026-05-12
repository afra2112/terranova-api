package com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.response;

import com.terranova.api.v1.appointment.domain.model.enums.AppointmentStatusEnum;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AppointmentResponse(
        Long appointmentId,
        AppointmentStatusEnum status,
        Integer maximumQuorum,
        List<AttendanceResponse> attendances,
        Integer takenSlots,
        Integer availableSlots,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        String description,
        Integer reprogrammingAttempts,
        Long productId
) {
}