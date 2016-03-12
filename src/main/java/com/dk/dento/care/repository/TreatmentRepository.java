package com.dk.dento.care.repository;

import com.dk.dento.care.entity.TreatmentEntity;
import com.dk.dento.care.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by khana on 06/02/16.
 */
public interface TreatmentRepository extends CrudRepository<TreatmentEntity, Long> {

     Iterable<TreatmentEntity> findByUserDetailEntity(UserDetailEntity userDetailEntity);

}
