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
import com.dk.dento.care.security.IAMService;
import com.dk.dento.care.service.mapper.UserDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

//@TODO split UserDetailService to UserDetailService and PatientService.
@Service
public class UserDetailService {

    /** Default page number if one is not specified */
    private static final Integer DEFAULT_PAGE_NUMBER = 0;

    /** Default page size if one is not specified */
    private static final Integer DEFAULT_PAGE_SIZE = Integer.valueOf(1000);

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
    private IAMService iamService;

    @Autowired
    private TreatmentService treatmentService;

    /**
     * Calculate the page number to use
     *
     * @param pageNumber             The provided page number. May be null
     * @return The actual page number to use
     */
    Integer calculatePageNumber(final Integer pageNumber) {
        return pageNumber != null ? pageNumber : DEFAULT_PAGE_NUMBER;
    }

    /**
     * Calculate the page size to use
     *
     * @param pageSize             The provided page size
     * @return The actual page size to use
     */
    Integer calculatePageSize(final Integer pageSize) {
        return pageSize != null ? pageSize : DEFAULT_PAGE_SIZE;
    }

    //TODO service should throw appropriate exception to controller, like not found for null pointer.
    public List<Patient> getAllPatient(final Integer pageNumber, final Integer pageSize, final Boolean loadTreatment) {

        Pageable pageable = new PageRequest(calculatePageNumber(pageNumber),
                calculatePageSize(pageSize));
        UserCredentialsEntity doctor = iamService.getAuthenticatedUser();
        Page<UserDetailEntity> userDetailEntities = userDetailRepository.findAllPatientOfLoggedInDoctor(doctor.getId(), pageable);

        List<Patient> patients = userDetailMapper.userDetailEntitiesToPatients(userDetailEntities);

        if(loadTreatment) {
            patients = loadTreatments(patients);
        }

        return patients;
    }

    private List<Patient> loadTreatments(List<Patient> patients) {
        for(Patient patient : patients) {
            patient.setTreatments(treatmentService.getTreatmentsForPatient(patient.getId()));
        }
        return patients;
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

            UserDetailEntity doctor = userDetailRepository.findOne(iamService.getAuthenticatedUser().getId());
            DoctorPatientMappingId doctorPatientMappingId = new DoctorPatientMappingId(doctor.getId(), patientEntity.getId());
            DoctorPatientMappingEntity doctorPatientMappingEntity = new DoctorPatientMappingEntity(doctorPatientMappingId);

            doctorPatientMappingRepository.save(doctorPatientMappingEntity);
        }
        return userDetailMapper.userDetailEntityToPatient(patientEntity);
    }
}
