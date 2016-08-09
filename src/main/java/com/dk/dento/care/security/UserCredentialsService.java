package com.dk.dento.care.security;

import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.model.UserCredentials;
import com.dk.dento.care.repository.UserCredentialsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserCredentials getPrincipal(final String loginId) {
        UserCredentialsEntity userCredentialsEntity = userCredentialsRepository.findByLoginId(loginId);
        return this.userCredentialsEntityToUserCredentials(userCredentialsEntity);
    }

    private UserCredentials userCredentialsEntityToUserCredentials(UserCredentialsEntity userCredentialsEntity) {
        return modelMapper.map(userCredentialsEntity, UserCredentials.class);
    }
}
