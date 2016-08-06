package com.dk.dento.care.repository.clinic;


import com.dk.dento.care.entity.clinic.UserModuleAccessEntity;
import com.dk.dento.care.entity.clinic.UserModuleAccessId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by khana on 30/07/16.
 */
public interface UserModuleAccessRepository extends CrudRepository<UserModuleAccessEntity, UserModuleAccessId> {

    List<UserModuleAccessEntity> findByUserId(Long userId);
}
