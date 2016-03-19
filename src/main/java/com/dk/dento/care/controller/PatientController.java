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
import org.springframework.web.bind.annotation.ResponseBody;


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
     * End point to create / update a patient.
     * @param patient
     * @return
     */
    @RequestMapping(
            value = "/save",
            method = RequestMethod.PUT,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity savePatient(@RequestBody final Patient patient) {
        try{
            Patient patient1 = userDetailService.savePatient(patient);
            return new ResponseEntity(patient1, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Error Occurred while saving or updating patient.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/layout")
    public String getPatientPartialPage() {
        return "patient/layout";
    }
}
