package com.dk.dento.care.service;

import com.dk.dento.care.entity.DoctorPatientMappingEntity;
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

    public List<UserDetailEntity> getAllPatientForDoctor(UserCredentialsEntity doctor) {

        List<UserDetailEntity> patients = new ArrayList<UserDetailEntity>();

        Iterable<DoctorPatientMappingEntity> allPatients = doctorPatientMappingRepository.findAllPatientsForDoctor(doctor.getId());
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

    public Set<TreatmentEntity> getPatientTreatments(Long patientId) {
        return userDetailRepository.findOne(patientId).getTreatmentEntities();
    }

    public void savePatient(UserDetailEntity userDetailEntity) {


        UserDetailEntity userDetailEntity1 = new UserDetailEntity();
        userDetailEntity1.setName(userDetailEntity.getName());
        userDetailEntity1.setPhoneNumber(userDetailEntity.getPhoneNumber());
        userDetailEntity1.setGender(userDetailEntity.getGender());
        userDetailEntity1.setAddress(userDetailEntity.getAddress());
        userDetailEntity1.setDataOfBirth(userDetailEntity.getDataOfBirth());


        UserCredentialsEntity userCredentialsEntity = new UserCredentialsEntity();
        userCredentialsEntity.setRoleId(1L);
        userCredentialsEntity = userCredentialsRepository.save(userCredentialsEntity);

        /*userCredentialsEntity.setUserDetailEntity(userDetailEntity1);
        userDetailEntity1.setId(userCredentialsEntity.getId());
        userDetailRepository.save(userDetailEntity1);*/

    }

    public Iterable<TreatmentEntity> savePatientTreatments(List<TreatmentEntity> treatmentEntities) {
        Iterable<TreatmentEntity> treatmentEntities2 = treatmentRepository.save(treatmentEntities);
        return treatmentEntities2;
    }
}
