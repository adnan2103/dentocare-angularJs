package com.dk.dento.care.service;

import com.dk.dento.care.entity.EmailAddress;
import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.model.UserCredentials;
import com.dk.dento.care.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    public UserCredentials getPrincipal(final String email) {

        UserCredentials userCredentials = new UserCredentials();

        UserCredentialsEntity userCredentialsEntity = userCredentialsRepository.findByEmailAddress(new EmailAddress(email));

        userCredentials.setId(userCredentialsEntity.getUserId());
        userCredentials.setEmailId(userCredentialsEntity.getEmailAddress().toString());
        userCredentials.setLoginEnable(userCredentialsEntity.isLoginEnable());
       // userCredentials.setRole(userCredentialsEntity.getRoleType().name());
        userCredentials.setPassword(userCredentialsEntity.getPassword());
        return userCredentials;
    }
}
