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
        System.out.println("loggedInUser:: "+loggedInUser.getUsername());
        return userCredentialsRepository.findByEmailId(loggedInUser.getUsername());
    }

    public AuthenticationToken getUserAuthenticationToken() {

    	UserCredentialsEntity  user = this.getAuthenticatedUser();
    	Long userId = user.getId();
        Long  roleId = user.getRoleId();
        
        System.out.println("==================Login User Id : ========= "+userId);

        //@TODO getPatientDetails method name is not correct as per context here, as we are just getting user detail.
        
       	String userName = userDetailService.getPatientDetails(userId).getName();
        if(userName.equals("") || userName.equals(null)) {
        	userName = "Please update your name under profile.";
        }
       	
        String role = roleRepository.findOne(roleId).getRole();

        Map<String, Boolean> modules = clinicService.getUserModules(userId, role);


        AuthenticationToken authenticationToken = new AuthenticationToken(userName, modules, role, "");

        return authenticationToken;
    }
}
