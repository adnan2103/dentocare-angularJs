package com.dk.dento.care.controller;

import com.dk.dento.care.entity.TreatmentEntity;
import com.dk.dento.care.model.ImagePath;
import com.dk.dento.care.repository.TreatmentRepository;
import com.dk.dento.care.service.ImageService;
import com.dk.dento.care.service.TreatmentService;
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
import java.util.List;

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
    String homeDirectory;

    @Autowired
    ImageService imageService;

    //TODO remove direct dependency on repository.
    @Autowired
    TreatmentRepository treatmentRepository;

    @Autowired
    TreatmentService treatmentService;

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
            String path =  homeDirectory + "/images/patients/";
            String imageName = "Patient_" + patientId + ".png";

            imageService.uploadImage(patientImage, path, imageName);
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
            String path =  homeDirectory + "/images/patients/";
            String imageName = "Patient_" + patientId + ".png";

            byte[] data = imageService.getImage(path, imageName);

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
            value = "treatment/{id}/{type}/images/{sequence}",
            method = RequestMethod.GET,
            produces = "image/png"
    )
    @ResponseBody
    public ResponseEntity getPostTreatmentImages(@PathVariable final String id,
                                                 @PathVariable final String sequence,
                                                 @PathVariable final String type) {

        try {
            String path =  homeDirectory + "/images/treatments/";
            String imageName = type+ "Treatment_" + id + "_"+sequence + ".png";

            byte[] data = imageService.getImage(path, imageName);

            return new ResponseEntity(data, HttpStatus.OK);

        } catch (IOException exception) {
            LOGGER.error(" IOException for patient image retrieve ", exception.getMessage());
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            LOGGER.error(" Exception for patient image retrieve ", exception.getMessage());
            return new ResponseEntity("An Error Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            value = "/treatment/{id}/{type}/images",
            headers = "content-type=multipart/*",
            method = RequestMethod.POST,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity uploadTreatmentImages(
            @RequestParam("file") final MultipartFile treatmentImage,
            @PathVariable final Long id,
            @PathVariable final String type

    ) {

        if (treatmentImage.isEmpty()) {
            return new ResponseEntity("You failed to upload because the file was empty.", HttpStatus.NOT_FOUND);
        }

        String fileExtension = getFileExtension(treatmentImage);
        if (!isInEnum(fileExtension, SupportedExtension.class)) {
            return new ResponseEntity("Not a supported file type!", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        TreatmentEntity treatmentEntity = treatmentRepository.findOne(id);
        Long sequence = 0L;
        if(type.equalsIgnoreCase("pre")) {
            sequence = treatmentEntity.getPreImageCount() + 1;
            treatmentEntity.setPreImageCount(sequence);
        }
        if(type.equalsIgnoreCase("post")) {
            sequence = treatmentEntity.getPostImageCount() + 1;
            treatmentEntity.setPostImageCount(sequence);
        }

        try {
            String path =  homeDirectory + "/images/treatments/";
            String imageName = type+ "Treatment_" + id + "_"+sequence + ".png";

            imageService.uploadImage(treatmentImage, path, imageName);

            LOGGER.info("Treatment Image Uploaded Successfully.");

            treatmentRepository.save(treatmentEntity);

            return new ResponseEntity("Uploaded Successfully", HttpStatus.OK);
        } catch (IOException exception) {
            LOGGER.error(" IOException for treatment image upload ", exception.getMessage());
            return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            LOGGER.error(" Exception for treatment image upload ", exception.getMessage());
            return new ResponseEntity("Upload Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * End point to get all treatment images pre or post for given treatment id.
     * @param id
     * @return
     */
    @RequestMapping(
            value = "treatment/{id}/{type}/images/{count}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity getTreatmentImages(@PathVariable final Long id,
                                             @PathVariable final String type,
                                             @PathVariable final Integer count) {
        try {
            List<ImagePath> treatmentImages = treatmentService.getTreatmentImages(id, type, count);

            return new ResponseEntity(treatmentImages, HttpStatus.OK);
        } catch(Exception e) {
            LOGGER.error("Error occurred while geting treatment images {} ",e.getMessage());
            return new ResponseEntity("No treatment image found", HttpStatus.NOT_FOUND);
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
