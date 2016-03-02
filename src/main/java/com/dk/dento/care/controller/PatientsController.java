package com.dk.dento.care.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/patients")
public class PatientsController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserDetailService userDetailService;

    //TODO none of the end point is sending proper http response code and response body
    //TODO need to implement HATEOAS for all DTO.

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

    @RequestMapping("/layout")
    public String getPatientPartialPage() {
        return "patients/layout";
    }
}
