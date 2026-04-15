package com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalTime;

public record CreateAppointmentRequest(
        @NotNull
        LocalDate date,

        @NotNull
        LocalTime startTime,

        @NotNull
        LocalTime endTime,

        @Nullable
        String description,

        @NotNull
        @Positive
        Long productId,

        @NotNull
        @Positive
        Integer maxQuorum
) {
}
