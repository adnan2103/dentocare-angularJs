package com.dk.dento.care.service;

import com.dk.dento.care.entity.DoctorPatientMappingEntity;
import com.dk.dento.care.entity.EmailAddress;
import com.dk.dento.care.entity.TreatmentEntity;
import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.repository.DoctorPatientMappingRepository;
import com.dk.dento.care.repository.TreatmentRepository;
import com.dk.dento.care.repository.UserCredentialsRepository;
import com.dk.dento.care.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<UserDetailEntity> getAllPatientForDoctor(UserCredentialsEntity doctor) {

        List<UserDetailEntity> patients = new ArrayList<UserDetailEntity>();

        Iterable<DoctorPatientMappingEntity> allPatients = doctorPatientMappingRepository.findAllPatientsForDoctor(doctor.getUserId());
        for(DoctorPatientMappingEntity doctorPatientMappingEntity : allPatients) {

            UserDetailEntity userDetailEntity = doctorPatientMappingEntity.getPatientEntity();
            patients.add(userDetailEntity);
        }
        return patients;
    }

    public UserDetailEntity getPatientDetails(Long patientId) {
        UserDetailEntity userDetailEntity = userDetailRepository.findOne(patientId);
        return userDetailEntity;
    }
}
