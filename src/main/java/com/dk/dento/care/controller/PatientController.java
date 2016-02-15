package com.dk.dento.care.controller;

import com.dk.dento.care.entity.TreatmentEntity;
import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.service.AuthenticationService;
import com.dk.dento.care.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/patient")
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
    public UserDetailEntity getPatientDetails(@PathVariable final Long id) {
        return userDetailService.getPatientDetails(id);
    }

    @RequestMapping(value = "/{id}/treatment", method = RequestMethod.GET)
    @ResponseBody
    public Set<TreatmentEntity> getPatientTreatment(@PathVariable final Long id) {
        return userDetailService.getPatientTreatments(id);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity savePatient(@RequestBody final UserDetailEntity userDetailEntity) {
        userDetailService.savePatient(userDetailEntity);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/layout")
    public String getPatientPartialPage() {
        return "patient/layout";
    }
}
