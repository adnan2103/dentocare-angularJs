package com.dk.dento.care.controller;

import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.Patient;
import com.dk.dento.care.service.AuthenticationService;
import com.dk.dento.care.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserDetailService userDetailService;

    @RequestMapping("/all")
    public @ResponseBody
    List<UserDetailEntity> getAllPatientsForLogedInDoctor() {

        UserCredentialsEntity doctor = authenticationService.getAuthenticatedUser();
        return userDetailService.getAllPatientForDoctor(doctor);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Patient getPatientDetails(@PathVariable final Long id) {
        return userDetailService.getPatientDetails(id);
    }

    @RequestMapping("/layout")
    public String getPatientPartialPage() {
        return "patients/layout";
    }
}
