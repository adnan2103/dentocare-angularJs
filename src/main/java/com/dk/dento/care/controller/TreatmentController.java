package com.dk.dento.care.controller;

import com.dk.dento.care.model.Treatment;
import com.dk.dento.care.service.AuthenticationService;
import com.dk.dento.care.service.TreatmentService;
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
public class TreatmentController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    TreatmentService treatmentService;

    /**
     * End point to get all treatment for given patient id.
     * @param id
     * @return
     */
    @RequestMapping(
            value = "patient/{id}/treatment",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity getPatientTreatment(@PathVariable final Long id) {
        try {
            Set<Treatment> treatments = treatmentService.getTreatmentsForPatient(id);
            return new ResponseEntity(treatments, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity("No treatments found", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * End point to create/update treatment for given patient id.
     * @param id
     * @param treatments
     * @return
     */
    @RequestMapping(
            value = "patient/{id}/treatment",
            method = RequestMethod.PUT,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity savePatientTreatment(@PathVariable final Long id,
                                                      @RequestBody final List<Treatment> treatments) {
        try {
        Set<Treatment> updatedTreatments = treatmentService.saveTreatmentsForPatient(treatments, id);
            return new ResponseEntity(updatedTreatments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Occurred while saving or updating treatment.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping("treatment/layout")
    public String getPatientPartialPage() {
        return "treatment/layout";
    }
}
