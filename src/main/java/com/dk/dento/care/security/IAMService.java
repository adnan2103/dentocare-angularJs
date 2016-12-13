package com.dk.dento.care.security;

import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.model.AuthenticationToken;
import com.dk.dento.care.repository.RoleRepository;
import com.dk.dento.care.repository.UserCredentialsRepository;
import com.dk.dento.care.service.ClinicService;
import com.dk.dento.care.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by khana on 07/02/16.
 */
@Service
public class IAMService {

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private ClinicService clinicService;

    public UserCredentialsEntity getAuthenticatedUser() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails loggedInUser = (UserDetails) authentication.getPrincipal();

        return userCredentialsRepository.findByLoginId(loggedInUser.getUsername());
    }

    public AuthenticationToken getUserAuthenticationToken() {

        Long  userId = this.getAuthenticatedUser().getId();

        String userName = userDetailService.getPatientDetails(userId).getName();
        String role = roleRepository.findOne(userId).getRole();

        Map<String, Boolean> modules = clinicService.getUserModules(userId, role);


        AuthenticationToken authenticationToken = new AuthenticationToken(userName, modules, role, "");

        return authenticationToken;
    }
}
