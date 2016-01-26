package com.dk.dento.care.controller;

import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.Patient;
import com.dk.dento.care.model.UserCredentials;
import com.dk.dento.care.service.UserCredentialsService;
import com.dk.dento.care.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    UserCredentialsService userCredentialsService;

    @Autowired
    UserDetailService userDetailService;

    @RequestMapping("/all")
    public @ResponseBody
    List<Patient> getName() {
        return userDetailService.getAllPatientForDoctor();
    }

    @RequestMapping("/layout")
    public String getPatientPartialPage() {
        return "patients/layout";
    }
}
