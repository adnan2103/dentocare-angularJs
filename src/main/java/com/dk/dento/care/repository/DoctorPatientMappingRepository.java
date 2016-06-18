package com.dk.dento.care.repository;

import com.dk.dento.care.entity.DoctorPatientMappingEntity;
import com.dk.dento.care.entity.DoctorPatientMappingId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by khana on 02/02/16.
 */
public interface DoctorPatientMappingRepository extends CrudRepository<DoctorPatientMappingEntity, DoctorPatientMappingId> {

    @Query("select dpm from DoctorPatientMappingEntity dpm where doctor_id = ?1 order by patient_id desc")
    List<DoctorPatientMappingEntity> findAllPatientsForDoctor(Long doctorId);

}
