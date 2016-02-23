package com.dk.dento.care.service;

import com.dk.dento.care.entity.*;
import com.dk.dento.care.model.Patient;
import com.dk.dento.care.repository.DoctorPatientMappingRepository;
import com.dk.dento.care.repository.RoleRepository;
import com.dk.dento.care.repository.TreatmentRepository;
import com.dk.dento.care.repository.UserCredentialsRepository;
import com.dk.dento.care.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    //TODO service should throw appropriate exception to controller, like not found for null pointer.
    public List<Patient> getAllPatientForDoctor(UserCredentialsEntity doctor) {

        List<UserDetailEntity> userDetailEntities = new ArrayList<UserDetailEntity>();

        Iterable<DoctorPatientMappingEntity> allPatients = doctorPatientMappingRepository.findAllPatientsForDoctor(doctor.getId());
        for(DoctorPatientMappingEntity doctorPatientMappingEntity : allPatients) {

            UserDetailEntity userDetailEntity = doctorPatientMappingEntity.getPatientEntity();
            userDetailEntities.add(userDetailEntity);
        }

        return modelEntityConversion.userDetailsEntityToPatientList(userDetailEntities);
    }

    public Patient getPatientDetails(Long patientId) {
        UserDetailEntity userDetailEntity = userDetailRepository.findOne(patientId);

        return modelEntityConversion.userDetailsEntityToPatient(userDetailEntity);
    }

    public Set<TreatmentEntity> getPatientTreatments(Long patientId) {
        return userDetailRepository.findOne(patientId).getTreatmentEntities();
    }

    public Patient savePatient(Patient patient) {
        UserDetailEntity userDetailEntity = modelEntityConversion.patientToUserDetailEntity(patient);

        if(userDetailEntity.getId() != null) {
            userDetailEntity = userDetailRepository.save(userDetailEntity);
        } else {
            UserCredentialsEntity userCredentialsEntity = new UserCredentialsEntity();
            userCredentialsEntity.setRoleId(roleRepository.findByRole("PATIENT").getId());
            userCredentialsEntity = userCredentialsRepository.save(userCredentialsEntity);

            userDetailEntity.setId(userCredentialsEntity.getId());
            userDetailEntity = userDetailRepository.save(userDetailEntity);
        }
        //TODO need to map this patient to logged in doctor.
        return modelEntityConversion.userDetailsEntityToPatient(userDetailEntity);
    }

    //TODO Not done. Need to complete this with DTO mapping.
    public Iterable<TreatmentEntity> savePatientTreatments(List<TreatmentEntity> treatmentEntities) {
        Iterable<TreatmentEntity> treatmentEntities2 = treatmentRepository.save(treatmentEntities);
        return treatmentEntities2;
    }

    //TODO need return patients mapped to logged in doctor only.
    public List<Patient> getPatientsByNameOrPhoneNumber(String patientName, String phoneNumber) {

        List<UserDetailEntity> userDetailEntities = new ArrayList<UserDetailEntity>(0);
        if(null != patientName && !patientName.equals("")) {
            userDetailEntities.addAll(userDetailRepository.findByNameContaining(patientName));
        }

        if(null != phoneNumber && !phoneNumber.equals("")) {
            userDetailEntities.addAll(userDetailRepository.findByPhoneNumberContaining(phoneNumber));
        }

        return modelEntityConversion.userDetailsEntityToPatientList(userDetailEntities);
    }
}
