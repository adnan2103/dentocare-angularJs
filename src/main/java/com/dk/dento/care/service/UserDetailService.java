package com.dk.dento.care.service;

import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.Patient;
import com.dk.dento.care.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    public List<Patient> getAllPatientForDoctor() {

        List<Patient> patients = new ArrayList<Patient>();

        Patient p = new Patient();
        p.setFirstName("Adnan");
        Patient p2 = new Patient();
        p2.setFirstName("Rohit");

        patients.add(p);
        patients.add(p2);

        //Iterable<UserDetailEntity> userDetailEntities = userDetailRepository.findAll();

        return patients;
    }

}
