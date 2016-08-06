package com.dk.dento.care.repository.clinic;

import com.dk.dento.care.entity.clinic.ModulesMappingEntity;
import com.dk.dento.care.entity.clinic.ModulesMappingId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by khana on 30/07/16.
 */
public interface ModulesMappingRepository extends CrudRepository<ModulesMappingEntity, ModulesMappingId> {

    List<ModulesMappingEntity> findByClinicId(Long clinicId);
}
