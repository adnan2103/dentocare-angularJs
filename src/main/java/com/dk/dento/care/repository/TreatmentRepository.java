package com.dk.dento.care.repository;

import com.dk.dento.care.entity.TreatmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by khana on 06/02/16.
 */
public interface TreatmentRepository extends CrudRepository<TreatmentEntity, Long> {

    @Query("select treatment from TreatmentEntity treatment where user_id = ?1")
    List<TreatmentEntity> findAllTreatmentForPatient(Long userId);
}
