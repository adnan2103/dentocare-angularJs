package com.dk.dento.care.repository;


import com.dk.dento.care.entity.StatusEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by khana on 06/02/16.
 */
public interface StatusRepository extends CrudRepository<StatusEntity, Long>{

    StatusEntity findByStatus(String status);
}
