package com.dk.dento.care.service;

import com.dk.dento.care.entity.DoctorPatientMappingEntity;
import com.dk.dento.care.entity.DoctorPatientMappingId;
import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.Patient;
import com.dk.dento.care.repository.DoctorPatientMappingRepository;
import com.dk.dento.care.repository.RoleRepository;
import com.dk.dento.care.repository.UserCredentialsRepository;
import com.dk.dento.care.repository.UserDetailRepository;
import com.dk.dento.care.service.mapper.UserDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailService {

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailService.class);

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private DoctorPatientMappingRepository doctorPatientMappingRepository;

    @Autowired
    private UserDetailMapper userDetailMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    ServletContext context;

    //TODO service should throw appropriate exception to controller, like not found for null pointer.
    public List<Patient> getAllPatient() {
        Set<UserDetailEntity> userDetailEntities = getAllPatientForDoctor();
        return userDetailMapper.userDetailEntitiesToPatients(userDetailEntities);
    }

    private Set<UserDetailEntity> getAllPatientForDoctor() {
        Set<UserDetailEntity> userDetailEntities = new HashSet<UserDetailEntity>(0);

        UserCredentialsEntity doctor = authenticationService.getAuthenticatedUser();
        Iterable<DoctorPatientMappingEntity> allPatients = doctorPatientMappingRepository.findAllPatientsForDoctor(doctor.getId());
        for (DoctorPatientMappingEntity doctorPatientMappingEntity : allPatients) {

            UserDetailEntity userDetailEntity = userDetailRepository.findOne(doctorPatientMappingEntity.getDoctorPatientMappingId().getPatientId());
            userDetailEntities.add(userDetailEntity);
        }

        return userDetailEntities;
    }

    public Patient getPatientDetails(Long patientId) {
        UserDetailEntity userDetailEntity = userDetailRepository.findOne(patientId);

        return userDetailMapper.userDetailEntityToPatient(userDetailEntity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Patient savePatient(Patient patient) throws ParseException {
        UserDetailEntity patientEntity = userDetailMapper.patientToUserDetailEntity(patient);

        if (patientEntity.getId() != null) {
            patientEntity = userDetailRepository.save(patientEntity);
        } else {
            UserCredentialsEntity userCredentialsEntity = new UserCredentialsEntity();
            userCredentialsEntity.setRoleId(roleRepository.findByRole("PATIENT").getId());
            userCredentialsEntity = userCredentialsRepository.save(userCredentialsEntity);

            patientEntity.setId(userCredentialsEntity.getId());
            patientEntity = userDetailRepository.save(patientEntity);

            UserDetailEntity doctor = userDetailRepository.findOne(authenticationService.getAuthenticatedUser().getId());
            DoctorPatientMappingId doctorPatientMappingId = new DoctorPatientMappingId(doctor.getId(), patientEntity.getId());
            DoctorPatientMappingEntity doctorPatientMappingEntity = new DoctorPatientMappingEntity(doctorPatientMappingId);

            doctorPatientMappingRepository.save(doctorPatientMappingEntity);

        }

        return userDetailMapper.userDetailEntityToPatient(patientEntity);
    }

    public void uploadPhoto(MultipartFile photo, String photoName) throws IOException {
        byte[] bytes = photo.getBytes();

        String path = "/resources/app/images/patients/";
        //String uploadPath = context.getRealPath("") + path;
        String uploadPath = "/home/dentocam/images/patients/";
        LOGGER.info("Path to upload image is : "+uploadPath);
        System.out.println("Path to upload image is : "+uploadPath);
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            LOGGER.error("Directory Not Found. {}", dir);
            throw new IOException("Directory Not Found");
        }

        File serverFile = new File(uploadPath + photoName);

        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.flush();
        stream.close();
    }

    public byte[] getPhoto(String patientPhotoName) throws IOException {

        String path = "/resources/app/images/patients/";
        //String uploadPath = context.getRealPath("")  + path;
        String uploadPath = "/home/dentocam/images/patients/";
        LOGGER.info("Path to upload image is : "+uploadPath);
        System.out.println("Path to GET image is : "+uploadPath);
        File file = new File(uploadPath + patientPhotoName);

        if (!file.exists()) {
            LOGGER.error("File Not Found. {}", file);
            throw new IOException(" Logo Does not exists. ");
        }
        byte[] data = Files.readAllBytes(Paths.get(file.getPath()));
        return data;
    }
}
