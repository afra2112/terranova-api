package com.terranova.api.v1.appointment.domain.model;

import com.terranova.api.v1.appointment.domain.model.enums.AppointmentStatusEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public record Appointment(
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
        LocalDateTime latestReprogramming,
        LocalDateTime latestBlockedReprogramming,
        LocalDateTime newAvailableReprogrammingDate,
        Integer reprogrammingAttempts
) {

}
