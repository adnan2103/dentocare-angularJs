package com.dk.dento.care.controller;

import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.model.Patient;
import com.dk.dento.care.model.Treatment;
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
import java.util.Set;

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
     * End point would be {search?phoneNumber=123456789&name=Rohit} or {search?phoneNumber=123456789}
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
     * End point to get all treatment for given patient id.
     * @param id
     * @return
     */
    @RequestMapping(
            value = "/{id}/treatment",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity getPatientTreatment(@PathVariable final Long id) {
        try {
            Set<Treatment> treatments = userDetailService.getPatientTreatments(id);
            return new ResponseEntity(treatments, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity("No treatments found", HttpStatus.NOT_FOUND);
        }

    }

    //TODO need to complete this with DTO mapping.
    /**
     * End point to create/update treatment for given patient id.
     * @param patinetId
     * @param treatments
     * @return
     */
    @RequestMapping(
            value = "/{patinetId}/treatment",
            method = RequestMethod.PUT,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity savePatientTreatment(@PathVariable final Long patinetId,
                                                      @RequestBody final List<Treatment> treatments) {
        try {
            userDetailService.savePatientTreatments(treatments, patinetId);
            return new ResponseEntity("Updated.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Occurred while saving or updating treatment.", HttpStatus.INTERNAL_SERVER_ERROR);
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

    @RequestMapping("/layout")
    public String getPatientPartialPage() {
        return "patient/layout";
    }
}
