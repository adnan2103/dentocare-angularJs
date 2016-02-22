package com.dk.dento.care.service;

import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khana on 22/02/16.
 */
@Service
public class ModelEntityConversion {

    @Autowired
    ModelMapper modelMapper;

    public List<Patient> userDetailsEntityToPatientList(List<UserDetailEntity> userDetailEntities) {
        List<Patient> patients = new ArrayList<Patient>(0);

        for(UserDetailEntity userDetailEntity : userDetailEntities) {
            patients.add(modelMapper.map(userDetailEntity, Patient.class));
        }

        return patients;
    }
}
