package com.dk.dento.care.controller;

import com.dk.dento.care.model.Patient;
import com.dk.dento.care.service.AuthenticationService;
import com.dk.dento.care.service.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@RequestMapping("/patient")
public class PatientController {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

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
            return new ResponseEntity("Error Occurred while saving or updating patient.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(
            value = "/{patientId}/photo",
            headers = "content-type=multipart/*",
            method = RequestMethod.POST,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity uploadPhoto(
            @RequestParam("file") final MultipartFile file,
            @PathVariable final Long patientId
    ) {


        if (file.isEmpty()) {
            return new ResponseEntity("You failed to upload because the file was empty.", HttpStatus.NOT_FOUND);
        }

        String fileExtension = getFileExtension(file);
        if (!isInEnum(fileExtension, SupportedExtension.class)) {
            return new ResponseEntity("Not a supported file type!", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        try {
            userDetailService.uploadPhoto(file, "Patient_" + patientId +".png");
            return new ResponseEntity("Uploaded Successfully", HttpStatus.OK);
        } catch (IOException exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity("Upload Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Handles GET action for patient photo.
     *
     * @param patientId The patientId
     * @return MultipartFile patient Photo.
     */
    @RequestMapping(
            value = "/{patientId}/photo",
            method = RequestMethod.GET,
            produces = "image/png"
    )
    @ResponseBody
    public ResponseEntity getPatientPhoto(@PathVariable final String patientId) {

        try {

            byte[] data = userDetailService.getPhoto("Patient_" + patientId + ".png");

            return new ResponseEntity(data, HttpStatus.OK);

        } catch (IOException exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity("An Error Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Get the Extension for a file.
     *
     * @param file uploaded file.
     * @return String file extension.
     */
    private String getFileExtension(final MultipartFile file) {
        return file.getContentType().substring(file.getContentType().lastIndexOf('/') + 1).toLowerCase();
    }

    /**
     * Determine whether an enum contains a value.
     *
     * @param value     file extension.
     * @param enumClass allowed file extensions.
     * @param <E>       enum data.
     * @return boolean
     */
    private <E extends Enum<E>> boolean isInEnum(final String value, final Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * enum supported file extensions.
     */
    private enum SupportedExtension {
        /**
         * PNG file format.
         */
        png,
        /**
         * JPEG file format.
         */
        jpeg,
        /**
         * JPG file format.
         */
        jpg,
        /**
         * GIF file format.
         */
        gif
    };

    @RequestMapping("/layout")
    public String getPatientPartialPage() {
        return "patient/layout";
    }
}
