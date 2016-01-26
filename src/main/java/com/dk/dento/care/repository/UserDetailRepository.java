package com.dk.dento.care.repository;

import com.dk.dento.care.entity.UserDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends CrudRepository<UserDetailEntity, Long> {

}
