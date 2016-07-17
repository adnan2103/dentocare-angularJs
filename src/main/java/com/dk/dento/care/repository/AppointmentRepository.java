package com.dk.dento.care.repository;

import com.dk.dento.care.entity.AppointmentEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by khana on 17/07/16.
 */
public interface AppointmentRepository extends CrudRepository<AppointmentEntity, Long> {

    Iterable<AppointmentEntity> findByDoctorId(Long doctorId);
}
