package com.dk.dento.care.service;

import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by khana on 07/02/16.
 */
@Service
public class AuthenticationService {

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    public UserCredentialsEntity getAuthenticatedUser() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails loggedInUser = (UserDetails) authentication.getPrincipal();

        return userCredentialsRepository.findByLoginId(loggedInUser.getUsername());
    }

}
