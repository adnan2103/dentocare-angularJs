package com.dk.dento.care.service;


import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.model.UserCredentials;
import com.dk.dento.care.repository.UserCredentialsRepository;
import com.dk.dento.care.service.mapper.UserDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private UserDetailMapper userDetailMapper;


    public UserCredentials getPrincipal(final String email) {

        UserCredentialsEntity userCredentialsEntity = userCredentialsRepository.findByEmail(email);
        return userDetailMapper.userCredentialsEntityToUserCredentials(userCredentialsEntity);

    }
}
