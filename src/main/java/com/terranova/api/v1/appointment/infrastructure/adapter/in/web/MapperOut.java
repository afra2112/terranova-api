package com.terranova.api.v1.appointment.infrastructure.adapter.in.web;

import com.terranova.api.v1.appointment.domain.model.Appointment;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.response.AppointmentResponse;
import com.terranova.api.v1.appointment.infrastructure.adapter.in.web.dto.response.AttendanceResponse;
import org.springframework.stereotype.Component;

@Component
public class MapperOut {

    public AppointmentResponse domainToResponse(Appointment appointment){
        return new AppointmentResponse(
                appointment.appointmentId(),
                appointment.status(),
                appointment.maximumQuorum(),
                appointment.attendances()
                        .stream().map(attendance ->
                                new AttendanceResponse(
                                        attendance.attendanceId(),
                                        attendance.appointmentId(),
                                        attendance.userId(),
                                        attendance.status(),
                                        attendance.inscriptionDate(),
                                        attendance.attended()
                                )
                        ).toList(),
                appointment.takenSlots(),
                appointment.availableSlots(),
                appointment.date(),
                appointment.startTime(),
                appointment.endTime(),
                appointment.description(),
                appointment.reprogrammingAttempts(),
                appointment.productId()
        );
    }
}
