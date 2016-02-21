package com.dk.dento.care.repository;

import com.dk.dento.care.entity.UserDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailRepository extends CrudRepository<UserDetailEntity, Long> {
    /**
     * This will produce the following sql: where name like ?1 (parameter bound wrapped in %)
     * @param name
     * @return
     */
    List<UserDetailEntity> findByNameContaining(String name);
}
