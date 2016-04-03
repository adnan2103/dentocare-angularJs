package com.dk.dento.care.controller;

import com.dk.dento.care.model.Patient;
import com.dk.dento.care.service.AuthenticationService;
import com.dk.dento.care.service.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientsController {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientsController.class);

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserDetailService userDetailService;

    /**
     * End point to get all patients of logged in Doctor.
     * @return
     */
    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET
    )
    public @ResponseBody
    ResponseEntity getAllPatientsForLoggedInDoctor() {

        try {
            List<Patient> patients = userDetailService.getAllPatient();
            return new ResponseEntity(patients, HttpStatus.OK);

        } catch(Exception e) {
            LOGGER.error("Error occurred while fetching all patients {} ",e.getMessage());
            return new ResponseEntity("No patient found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/layout")
    public String getPatientPartialPage() {
        return "patients/layout";
    }
}
