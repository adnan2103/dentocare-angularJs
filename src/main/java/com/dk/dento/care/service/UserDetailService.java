package com.dk.dento.care.service;

import com.dk.dento.care.entity.EmailAddress;
import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.Patient;
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

    public List<Patient> getAllPatientForDoctor() {

        List<Patient> patients = new ArrayList<Patient>();

        Iterable<UserCredentialsEntity> userCredentialsEntities = userCredentialsRepository.findAll();
        Patient patient = null;

        for(UserCredentialsEntity userCredentialsEntity : userCredentialsEntities) {

            UserDetailEntity userDetailEntity = userCredentialsEntity.getUserDetailEntity();
            patient = new Patient();
            patient.setFirstName(userDetailEntity.getName().toString());
            patients.add(patient);
        }

        return patients;
    }

}
