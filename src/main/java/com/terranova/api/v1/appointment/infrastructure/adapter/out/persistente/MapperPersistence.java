package com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente;

import com.terranova.api.v1.appointment.domain.model.Appointment;
import com.terranova.api.v1.appointment.domain.model.Attendance;
import com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.entity.AppointmentEntity;
import com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.entity.AttendanceEntity;
import org.springframework.stereotype.Component;

@Component
public class MapperPersistence {

    public Appointment entityToDomain(AppointmentEntity entity){
        return new Appointment(
                entity.getAppointmentId(),
                entity.getStatus(),
                entity.getMaxQuorum(),
                entity.getAttendances()
                        .stream()
                        .map(attendanceEntity ->
                                new Attendance(
                                        attendanceEntity.getAttendanceId(),
                                        entity.getAppointmentId(),
                                        attendanceEntity.getUserId(),
                                        attendanceEntity.getStatus(),
                                        attendanceEntity.getInscriptionDate(),
                                        attendanceEntity.isAttended()
                                )
                        ).toList(),
                entity.isDeleted(),
                entity.getTakenSlots(),
                entity.getAvailableSlots(),
                entity.getDate(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getDescription(),
                entity.getProductId(),
                entity.getLatestReprogramming(),
                entity.getLatestBlockedReprogramming(),
                entity.getNewAvailableReprogrammingDate(),
                entity.getReprogrammingAttempts()
        );
    }

    public AppointmentEntity domainToEntity(Appointment appointment){
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .appointmentId(appointment.appointmentId())
                .status(appointment.status())
                .maxQuorum(appointment.maximumQuorum())
                .deleted(appointment.deleted())
                .takenSlots(appointment.takenSlots())
                .availableSlots(appointment.availableSlots())
                .date(appointment.date())
                .startTime(appointment.startTime())
                .endTime(appointment.endTime())
                .description(appointment.description())
                .productId(appointment.productId())
                .latestReprogramming(appointment.latestReprogramming())
                .latestBlockedReprogramming(appointment.latestBlockedReprogramming())
                .newAvailableReprogrammingDate(appointment.newAvailableReprogrammingDate())
                .reprogrammingAttempts(appointment.reprogrammingAttempts())
                .build();

        appointmentEntity.setAttendances(
                appointment.attendances()
                        .stream()
                        .map(domain -> new AttendanceEntity(
                                domain.attendanceId(),
                                domain.userId(),
                                domain.status(),
                                domain.inscriptionDate(),
                                domain.attended(),
                                appointmentEntity
                        )).toList()
        );

        return appointmentEntity;
    }
}
