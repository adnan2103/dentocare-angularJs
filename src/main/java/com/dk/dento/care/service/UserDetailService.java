package com.dk.dento.care.service;

import com.dk.dento.care.entity.DoctorPatientMappingEntity;
import com.dk.dento.care.entity.DoctorPatientMappingId;
import com.dk.dento.care.entity.TreatmentEntity;
import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.Patient;
import com.dk.dento.care.model.Treatment;
import com.dk.dento.care.repository.DoctorPatientMappingRepository;
import com.dk.dento.care.repository.RoleRepository;
import com.dk.dento.care.repository.TreatmentRepository;
import com.dk.dento.care.repository.UserCredentialsRepository;
import com.dk.dento.care.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private DoctorPatientMappingRepository doctorPatientMappingRepository;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private ModelEntityConversion modelEntityConversion;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationService authenticationService;

    //TODO service should throw appropriate exception to controller, like not found for null pointer.
    public List<Patient> getAllPatient() {
        Set<UserDetailEntity> userDetailEntities = getAllPatientForDoctor();
        return modelEntityConversion.userDetailsEntityToPatientList(userDetailEntities);
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

        return modelEntityConversion.userDetailsEntityToPatient(userDetailEntity);
    }

    public Set<Treatment> getPatientTreatments(Long patientId) {

        return modelEntityConversion.treatmentEntityToTreatmentList(treatmentRepository.findByUserDetailEntity(userDetailRepository.findOne(patientId)));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Patient savePatient(Patient patient) {
        UserDetailEntity patientEntity = modelEntityConversion.patientToUserDetailEntity(patient);

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

        return modelEntityConversion.userDetailsEntityToPatient(patientEntity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void savePatientTreatments(List<Treatment> treatments, Long patinetId) {
        Set<TreatmentEntity> treatmentEntities = modelEntityConversion.treatmentModelListToTreatmentEntityList(treatments, patinetId);
        treatmentRepository.save(treatmentEntities);
    }

    public List<Patient> getPatientsByNameOrPhoneNumber(String patientName, String phoneNumber) {

        Set<UserDetailEntity> userDetailEntities = new HashSet<UserDetailEntity>(0);
        if (null != patientName && !patientName.equals("")) {
            userDetailEntities.addAll(userDetailRepository.findByNameContaining(patientName));
        }

        if (null != phoneNumber && !phoneNumber.equals("")) {
            userDetailEntities.addAll(userDetailRepository.findByPhoneNumberContaining(phoneNumber));
        }
        userDetailEntities.retainAll(getAllPatientForDoctor());

        return modelEntityConversion.userDetailsEntityToPatientList(userDetailEntities);
    }
}
