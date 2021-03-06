package com.dk.dento.care.controller;

import com.dk.dento.care.model.Treatment;
import com.dk.dento.care.service.TreatmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


@Controller
public class TreatmentController {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TreatmentController.class);

    @Autowired
    TreatmentService treatmentService;

    /**
     * End point to get all treatment for given patient id.
     * @param id
     * @return
     */
    @RequestMapping(
            value = "patient/{id}/treatments",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity getPatientTreatments(@PathVariable final Long id) {
        try {
            List<Treatment> treatments = treatmentService.getTreatmentsForPatient(id);
            return new ResponseEntity(treatments, HttpStatus.OK);
        } catch(Exception e) {
            LOGGER.error("Error occurred while geting treatments for patient {} ",e.getMessage());
            return new ResponseEntity("No treatments found", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * End point to get all treatment for logged in doctor.
     * @return
     */
    @RequestMapping(
            value = "/treatments",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity getDoctorTreatments() {
        try {
            List<Treatment> treatments = treatmentService.getTreatmentsForDoctor();
            return new ResponseEntity(treatments, HttpStatus.OK);
        } catch(Exception e) {
            LOGGER.error("Error occurred while geting treatments for patient {} ",e.getMessage());
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
            value = "patient/{id}/treatments",
            method = RequestMethod.PUT,
            produces = "application/json"
    )
    @ResponseBody
    @SuppressWarnings("unchecked")
    public ResponseEntity savePatientTreatment(@PathVariable final Long id,
                                                      @RequestBody final List<Treatment> treatments) {
        //try {
            List<Treatment> updatedTreatments = treatmentService.saveTreatmentsForPatient(treatments, id);
            return new ResponseEntity(updatedTreatments, HttpStatus.OK);
        /*} catch (Exception e) {
            LOGGER.error("Error occurred while saving treatments for patient {} ",e.getMessage());
            return new ResponseEntity("Error Occurred while saving or updating treatment.", HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

    }

}
