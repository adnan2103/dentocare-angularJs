package com.dk.dento.care.repository.clinic;

import com.dk.dento.care.entity.clinic.UserMappingEntity;

import com.dk.dento.care.entity.clinic.UserModuleAccessId;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by khana on 30/07/16.
 */
public interface UserMappingRepository extends CrudRepository<UserMappingEntity, UserModuleAccessId> {

    //@TODO I need to correct mappings so that user id will be mapped to only one clinic.
    UserMappingEntity findByUserId(Long userId);
}
