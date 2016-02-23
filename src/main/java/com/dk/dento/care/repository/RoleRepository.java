package com.dk.dento.care.repository;

import com.dk.dento.care.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by khana on 06/02/16.
 */
public interface RoleRepository extends CrudRepository<RoleEntity, Long>{

    RoleEntity findByRole(String role);
}
