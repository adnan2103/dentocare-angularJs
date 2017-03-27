package com.dk.dento.care.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dk.dento.care.model.UserCredentials;
import com.dk.dento.care.security.UserCredentialsService;


@Controller
public class AccountManagementController {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountManagementController.class);
    
    @Autowired
    UserCredentialsService userCredentialsService;

    
    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity registerNewUser(@RequestBody final UserCredentials userCredentials) {
        try {
            
        	/*
        	 1. create user.
        	insert into user_detail(name,gender,age) values('','',0);
			
			2. create credentials.
			insert into user_credentials(user_id,email_id,mobile_number,account_creation_mode,social_identifier,last_login_date,is_mobile_verified,is_email_verified,login_enabled,password,role_id) values (5,'new@gmail.com','','Registration',null,now(),false,false,true,'$2a$10$FBAKClV1zBIOOC9XMXf3AO8RoGXYVYsfvUdoLxGkd/BnXEn4tqT3u',2);
			
			3. create clinic.
			insert into clinic(name) values('newaccount@gmail.com');
			
			4. give module access to modules.
			insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(1,1,now(),'2016-12-31');
			insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(1,2,now(),'2016-12-31');
			insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(1,3,now(),'2016-12-31');
			insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(1,4,now(),'2016-12-31');
			insert into clinic_modules_mapping(clinic_id,module_id,start_date,expiry_date) values(1,5,now(),'2016-12-31');
			
			5. map new user to clinic.
			insert into clinic_user_mapping(clinic_id, user_id) values(1,5);

        	*/
        	
            return new ResponseEntity("You are registered Succesfully.", HttpStatus.OK);
        } catch(Exception e) {
            LOGGER.error(" Error occurred while registration {} ", e.getMessage());
            return new ResponseEntity("Internal Server Error.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
