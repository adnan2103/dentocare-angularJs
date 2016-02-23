package com.dk.dento.care.controller;

import com.dk.dento.care.entity.TreatmentEntity;
import com.dk.dento.care.entity.UserCredentialsEntity;
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
import java.util.Set;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserDetailService userDetailService;

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
    List<Patient> searchByName(
            @RequestParam(value = "name", required = false) final String patientName,
            @RequestParam(value = "phoneNumber", required = false) final String phoneNumber
    ) {
        return userDetailService.getPatientsByNameOrPhoneNumber(patientName, phoneNumber);
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
    List<Patient> getAllPatientsForLoggedInDoctor() {

        UserCredentialsEntity doctor = authenticationService.getAuthenticatedUser();
        return userDetailService.getAllPatientForDoctor(doctor);
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
    public Patient getPatientDetails(@PathVariable final Long id) {
        return userDetailService.getPatientDetails(id);
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
    public Set<TreatmentEntity> getPatientTreatment(@PathVariable final Long id) {
        return userDetailService.getPatientTreatments(id);
    }

    //TODO need to complete this with DTO mapping.
    /**
     * End point to create/update treatment for given patient id.
     * @param id
     * @param treatmentEntities
     * @return
     */
    @RequestMapping(
            value = "/{id}/treatment",
            method = RequestMethod.PUT,
            produces = "application/json"
    )
    @ResponseBody
    public Iterable<TreatmentEntity> savePatientTreatment(@PathVariable final Long id,
                                                      @RequestBody final List<TreatmentEntity> treatmentEntities) {
        return userDetailService.savePatientTreatments(treatmentEntities);
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
        Patient patient1 = userDetailService.savePatient(patient);
        return new ResponseEntity(patient1, HttpStatus.OK);
    }

    @RequestMapping("/layout")
    public String getPatientPartialPage() {
        return "patient/layout";
    }
}
