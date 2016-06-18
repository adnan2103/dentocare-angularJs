package com.dk.dento.care.service.mapper;

import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.Patient;
import com.dk.dento.care.model.UserCredentials;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by khana on 22/02/16.
 */
@Service
public class UserDetailMapper {

    @Autowired
    private ModelMapper modelMapper;

    public List<Patient> userDetailEntitiesToPatients(Set<UserDetailEntity> userDetailEntities) {
        List<Patient> patients = new ArrayList<Patient>(0);

        for (UserDetailEntity userDetailEntity : userDetailEntities) {
            Patient patient = modelMapper.map(userDetailEntity, Patient.class);
            patient.setDateOfBirth("");
            if(userDetailEntity.getDataOfBirth() != null) {
                patient.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").format(userDetailEntity.getDataOfBirth()));
            }
            patient.setImagePath("patient/"+patient.getId()+"/image");
            patients.add(patient);
        }

        return patients;
    }

    public Patient userDetailEntityToPatient(UserDetailEntity userDetailEntity) {
        Patient patient = modelMapper.map(userDetailEntity, Patient.class);
        patient.setDateOfBirth("");
        if(userDetailEntity.getDataOfBirth() != null) {
            patient.setDateOfBirth(new SimpleDateFormat("MM-dd-yyyy").format(userDetailEntity.getDataOfBirth()));
        }
        patient.setImagePath("patient/"+patient.getId()+"/image");
        return patient;
    }

    public UserDetailEntity patientToUserDetailEntity(Patient patient) throws ParseException {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        UserDetailEntity userDetailEntity = modelMapper.map(patient, UserDetailEntity.class);

        //TODO add isValid date and format kind of validations.
        if(patient.getDateOfBirth() !=null) {
            Date dob = simpleDateFormat.parse(patient.getDateOfBirth());
            userDetailEntity.setDataOfBirth(dob);
        }

        return userDetailEntity;

    }

    public UserCredentials userCredentialsEntityToUserCredentials(UserCredentialsEntity userCredentialsEntity) {
        return modelMapper.map(userCredentialsEntity, UserCredentials.class);
    }

}
