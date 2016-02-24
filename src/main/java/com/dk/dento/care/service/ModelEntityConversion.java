package com.dk.dento.care.service;

import com.dk.dento.care.entity.TreatmentEntity;
import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.Patient;
import com.dk.dento.care.model.Treatment;
import com.dk.dento.care.model.UserCredentials;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Patient userDetailsEntityToPatient(UserDetailEntity userDetailEntity) {
        return modelMapper.map(userDetailEntity, Patient.class);
    }

    public UserDetailEntity patientToUserDetailEntity(Patient patient) {
        return modelMapper.map(patient, UserDetailEntity.class);
    }

    public UserCredentials userCredentialsEntityToModel(UserCredentialsEntity userCredentialsEntity) {
        return modelMapper.map(userCredentialsEntity, UserCredentials.class);
    }

    public Set<Treatment> treatmentEntityToTreatmentList(Set<TreatmentEntity> treatmentEntities) {
        Set<Treatment> treatments = new HashSet<Treatment>(0);
        Treatment treatment;

        for(TreatmentEntity treatmentEntity : treatmentEntities) {
            treatment = modelMapper.map(treatmentEntity, Treatment.class);
            treatment.setStatus(treatmentEntity.getStatusEntity().getStatus());
            treatments.add(treatment);
        }

        return treatments;
    }
}
