package com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.jpa;

import com.terranova.api.v1.appointment.infrastructure.adapter.out.persistente.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, Long> {

    @Query("SELECT a FROM AppointmentEntity a WHERE a.productId IN :ids")
    List<AppointmentEntity> getByProductsIds(@Param("ids") List<Long> ids);
}
