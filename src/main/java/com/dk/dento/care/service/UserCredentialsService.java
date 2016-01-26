package com.dk.dento.care.service;

import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.model.UserCredentials;
import com.dk.dento.care.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    public UserCredentials getPrincipal(final String emailId) {

        UserCredentials userCredentials = new UserCredentials();

        UserCredentialsEntity userCredentialsEntity = userCredentialsRepository.findByEmailId(emailId);

        userCredentials.setId(userCredentialsEntity.getUserId());
        userCredentials.setEmailId(userCredentialsEntity.getEmailId());
        userCredentials.setLoginEnable(userCredentialsEntity.isLoginEnable());
        userCredentials.setRole(userCredentialsEntity.getRole());
        userCredentials.setPassword(userCredentialsEntity.getPassword());
        return userCredentials;
    }
}
