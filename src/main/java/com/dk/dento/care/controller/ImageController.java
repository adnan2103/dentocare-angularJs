package com.dk.dento.care.controller;

import com.dk.dento.care.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by khana on 22/06/16.
 */
@Controller
public class ImageController {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    String home;

    @Autowired
    ImageService imageService;

    @RequestMapping(
            value = "/patient/{patientId}/image",
            headers = "content-type=multipart/*",
            method = RequestMethod.POST,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity uploadPatientImage(
            @RequestParam("file") final MultipartFile patientImage,
            @PathVariable final Long patientId
    ) {


        if (patientImage.isEmpty()) {
            return new ResponseEntity("You failed to upload because the file was empty.", HttpStatus.NOT_FOUND);
        }

        String fileExtension = getFileExtension(patientImage);
        if (!isInEnum(fileExtension, SupportedExtension.class)) {
            return new ResponseEntity("Not a supported file type!", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        try {
            String path =  home + "/images/patients/";
            imageService.uploadImage(patientImage, "Patient_" + patientId + ".png", path);
            LOGGER.info("Patient Image Uploaded Successfully.");
            return new ResponseEntity("Uploaded Successfully", HttpStatus.OK);
        } catch (IOException exception) {
            LOGGER.error(" IOException for patient image upload ", exception.getMessage());
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            LOGGER.error(" Exception for patient image upload ", exception.getMessage());
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
            value = "/patient/{patientId}/image",
            method = RequestMethod.GET,
            produces = "image/png"
    )
    @ResponseBody
    public ResponseEntity getPatientImage(@PathVariable final String patientId) {

        try {
            String path =  home + "/images/patients/";
            byte[] data = imageService.getImage(path, "Patient_" + patientId + ".png");

            return new ResponseEntity(data, HttpStatus.OK);

        } catch (IOException exception) {
            LOGGER.error(" IOException for patient image retrieve ", exception.getMessage());
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            LOGGER.error(" Exception for patient image retrieve ", exception.getMessage());
            return new ResponseEntity("An Error Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Handles GET action for pre and post treatment photo.
     *
     * @param id The treatment Id
     * @return MultipartFile patient Photo.
     */
    @RequestMapping(
            value = "treatment/{id}/images/{state}/{sequence}",
            method = RequestMethod.GET,
            produces = "image/png"
    )
    @ResponseBody
    public ResponseEntity getPostTreatmentImages(@PathVariable final String id,
                                                 @PathVariable final String sequence,
                                                 @PathVariable final String state) {

        try {
            String path =  home + "/images/treatments/";
            byte[] data = imageService.getImage(path, state+ "Treatment_" + id + "_"+sequence + ".png");

            return new ResponseEntity(data, HttpStatus.OK);

        } catch (IOException exception) {
            LOGGER.error(" IOException for patient image retrieve ", exception.getMessage());
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            LOGGER.error(" Exception for patient image retrieve ", exception.getMessage());
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

}
