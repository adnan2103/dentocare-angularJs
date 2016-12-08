package com.dk.dento.care.controller;

import com.dk.dento.care.model.Appointment;
import com.dk.dento.care.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by khana on 06/07/16.
 */
@Controller
public class AppointmentController {

    /**
     * Logger instance.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);
    @Autowired
    AppointmentService appointmentService;

    /**
     * Get end point to fetch all appointments of logged id doctor.
     */
    @RequestMapping(
            value = "/appointments",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity getAppointments() {
        try {
            List<Appointment> appointments = appointmentService.getAppointments();
            return new ResponseEntity(appointments, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching Appointment {} ",e.getMessage());
            return new ResponseEntity("No appointment were found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * PUT end point to create/update appointment resource.
     */
    @RequestMapping(
            value = "/appointment",
            method = RequestMethod.PUT,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity saveAppointment(@RequestBody final Appointment appointment) {
        Appointment appointment1 = appointmentService.save(appointment);
        return new ResponseEntity(appointment1, HttpStatus.OK);
    }
}
