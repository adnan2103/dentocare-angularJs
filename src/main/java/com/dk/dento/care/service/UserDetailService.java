package com.dk.dento.care.service;

import com.dk.dento.care.entity.DoctorPatientMappingEntity;
import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.Patient;
import com.dk.dento.care.repository.DoctorPatientMappingRepository;
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

    public List<Patient> getAllPatientForDoctor() {

        List<DoctorPatientMappingEntity> patientsNew0 = doctorPatientMappingRepository.findByDoctorId(1L);
        List<Patient> patients = new ArrayList<Patient>();
        Iterable<UserCredentialsEntity> userCredentialsEntities = userCredentialsRepository.findAll();
        Patient patient = null;

        Iterable<DoctorPatientMappingEntity> doctorPatientMappingEntities = doctorPatientMappingRepository.findAll();


        for(UserCredentialsEntity userCredentialsEntity : userCredentialsEntities) {

            UserDetailEntity userDetailEntity = userCredentialsEntity.getUserDetailEntity();
            patient = new Patient();
            patient.setFirstName(userDetailEntity.getName().toString());
            patients.add(patient);
        }



       // saveUser();

        return patients;
    }

    /*private void saveUser() {
        UserCredentialsEntity userCredentialsEntity = new UserCredentialsEntity();
        userCredentialsEntity.setRoleType(UserCredentialsEntity.RoleType.PAT);
        UserDetailEntity userDetailEntity = new UserDetailEntity();
        Name name = new Name("Test","Last");
        userDetailEntity.setName(name);
        userDetailEntity.setGender("male");
        userDetailEntity.setPhoneNumber(new PhoneNumber("123456789"));
        userCredentialsEntity.setUserDetailEntity(userDetailEntity);
        userCredentialsRepository.save(userCredentialsEntity);
    }*/

}
