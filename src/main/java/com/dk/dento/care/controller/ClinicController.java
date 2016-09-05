package com.dk.dento.care.controller;

import com.dk.dento.care.model.Clinic;
import com.dk.dento.care.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by khana on 06/08/16.
 */
@Controller
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @RequestMapping(
            value = "/clinics",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity getAllClinics() {
        return new ResponseEntity(clinicService.getAllClinics(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/clinic",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity saveClinic(@RequestBody final Clinic clinic) {
        Clinic clinicSaved = clinicService.save(clinic);
        return new ResponseEntity(clinicSaved, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/clinic/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity getClinic(@PathVariable final Long id) {
        return new ResponseEntity(clinicService.findOne(id), HttpStatus.OK);
    }













    @RequestMapping(
            value = "/clinic/{id}/modules",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity getModules(@PathVariable final Long id) {
        // Join between clinic, clinic_modules_mapping, module AND return List of Module
        return new ResponseEntity(null, HttpStatus.OK);
    }



    @RequestMapping(
            value = "/clinic/{id}/modules",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity saveClinicModules(@PathVariable final Long id) {
        //insert or delete records in clinic_modules_mapping for selected module ids for clinic_id.
        return new ResponseEntity(null, HttpStatus.OK);
    }



    @RequestMapping(
            value = "/clinic/{id}/users",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity getClinicUsers(@PathVariable final Long id) {
        // returns List<User>
        return new ResponseEntity(null, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/clinic/{id}/user",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity saveClinicUser(@PathVariable final Long id) {

        //create a record in user_credentials.
        //create a record in user_detail.
        //create a record in clinic_user_mapping.
        return new ResponseEntity(null, HttpStatus.OK);
    }




    @RequestMapping(
            value = "/clinic/{clinicId}/user/{userId}/modules",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity getClinicUserModuleAccess(@PathVariable final Long clinicId, @PathVariable final Long userId) {
        // write a join between clinic_user_module_access, module and clinic_modules_mapping return back List of modules.
        return new ResponseEntity(null, HttpStatus.OK);
    }




    @RequestMapping(
            value = "/clinic/{clinicId}/user/{userId}/modules",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity saveClinicUserModuleAccess(@PathVariable final Long clinicId, @PathVariable final Long userId) {
        // insert/delete records in clinic_user_module_access
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
