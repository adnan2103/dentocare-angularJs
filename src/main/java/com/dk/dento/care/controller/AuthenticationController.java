package com.dk.dento.care.controller;

import com.dk.dento.care.model.AuthenticationToken;
import com.dk.dento.care.security.IAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class AuthenticationController {

    @Autowired
    IAMService iamService;

    @RequestMapping(value = "/user/authenticate", method = { RequestMethod.GET })
    public AuthenticationToken authenticate() {
        return iamService.getUserAuthenticationToken();
    }
}

