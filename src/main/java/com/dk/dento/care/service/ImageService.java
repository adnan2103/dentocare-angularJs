package com.dk.dento.care.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Service to manage Images.
 */
@Service
public class ImageService {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);


    public void uploadImage(MultipartFile image, String path, String imageName) throws IOException {
        byte[] bytes = image.getBytes();
        File dir = new File(path);
        if (!dir.exists()) {
            LOGGER.error("Directory to upload image was not Found. {}", dir);
            throw new IOException("Directory to upload image was not Found");
        }

        File serverFile = new File(path + imageName);

        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.flush();
        stream.close();
    }

    public byte[] getImage(String path, String imageName) throws IOException {

        File file = new File(path + imageName);
        if (!file.exists()) {
            LOGGER.error("Patient Image was Not Found. {}", file);
            return "".getBytes();
        }
        byte[] data = Files.readAllBytes(Paths.get(file.getPath()));
        return data;
    }
}
