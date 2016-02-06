package com.dk.dento.care.service;

import com.dk.dento.care.entity.EmailAddress;
import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by khana on 07/02/16.
 */
@Service
public class AuthenticationService {

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    public UserCredentialsEntity getAuthenticatedUser() {
        return userCredentialsRepository.findByEmailAddress(new EmailAddress("adnan@khan.com"));
    }

}
