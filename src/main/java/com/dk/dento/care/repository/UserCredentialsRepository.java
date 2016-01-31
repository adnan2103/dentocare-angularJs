package com.dk.dento.care.repository;

import com.dk.dento.care.entity.EmailAddress;
import com.dk.dento.care.entity.UserCredentialsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends CrudRepository<UserCredentialsEntity, Long> {

    UserCredentialsEntity findByEmailAddress(EmailAddress emailAddress);

}
