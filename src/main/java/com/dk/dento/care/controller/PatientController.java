package com.dk.dento.care.controller;

import com.dk.dento.care.model.Patient;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserDetailService userDetailService;

    //TODO none of the end point is sending proper http response code and response body
    //TODO need to implement HATEOAS for all DTO.
    /**
     * End point to get all patients of logged in Doctor.
     * End point would be {search?phoneNumber=123456789&name=Adnan} or {search?phoneNumber=123456789}
     * or {search?name=Rohit}
     * @return
     */
    @RequestMapping(
            value = "/search",
            method = RequestMethod.GET
    )
    public @ResponseBody
    ResponseEntity searchByName(
            @RequestParam(value = "name", required = false) final String patientName,
            @RequestParam(value = "phoneNumber", required = false) final String phoneNumber
    ) {
        try{
            List<Patient> patients = userDetailService.getPatientsByNameOrPhoneNumber(patientName, phoneNumber);
            return new ResponseEntity(patients, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity("No patient found", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * End point to get patient detail for given patient id.
     * @param id
     * @return
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity getPatientDetails(@PathVariable final Long id) {
        try {
            Patient patient = userDetailService.getPatientDetails(id);
            return new ResponseEntity(patient, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity("No patient Detail found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * End point to create or update a patient.
     * @param patient
     * @return
     */
    @RequestMapping(
            method = RequestMethod.PUT,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity savePatient(@RequestBody final Patient patient) {
       try{
           Patient patient1 = userDetailService.savePatient(patient);
           return new ResponseEntity(patient1, HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity("Error Occurred while saving or updating patient.", HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }


    /**
     * End point to get hard coded patient detail for now
     * @return
     */
    @RequestMapping(
            value = "/detail",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity getDetail() {
        try {
            Patient patient = userDetailService.getPatientDetails(2L);
            return new ResponseEntity(patient, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity("No patient Detail found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/layout")
    public String getPatientPartialPage() {
        return "patient/layout";
    }
}
