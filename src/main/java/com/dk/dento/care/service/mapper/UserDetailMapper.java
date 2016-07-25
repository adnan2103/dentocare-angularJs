package com.dk.dento.care.service.mapper;

import com.dk.dento.care.entity.ContactEntity;
import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.Contact;
import com.dk.dento.care.model.Patient;
import com.dk.dento.care.model.UserCredentials;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
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
            patients.add(this.userDetailEntityToPatient(userDetailEntity));
        }
        return patients;
    }

    public Patient userDetailEntityToPatient(UserDetailEntity userDetailEntity) {
        Patient patient = modelMapper.map(userDetailEntity, Patient.class);
        patient.setImagePath("patient/"+patient.getId()+"/image");
        return patient;
    }

    public UserDetailEntity patientToUserDetailEntity(Patient patient) throws ParseException {
        UserDetailEntity userDetailEntity = modelMapper.map(patient, UserDetailEntity.class);
        userDetailEntity.setContactEntityList(contactListToContactEntityList(patient.getContactList(), userDetailEntity));
        return userDetailEntity;
    }

    public List<ContactEntity> contactListToContactEntityList(List<Contact> contactList, UserDetailEntity userDetailEntity) {
        List<ContactEntity> contactEntityList = new ArrayList<ContactEntity>();
        for(Contact contact : contactList) {
            ContactEntity contactEntity = modelMapper.map(contact, ContactEntity.class);
            contactEntity.setUserDetailEntity(userDetailEntity);
            contactEntityList.add(contactEntity);
        }
        return contactEntityList;
    }
    public UserCredentials userCredentialsEntityToUserCredentials(UserCredentialsEntity userCredentialsEntity) {
        return modelMapper.map(userCredentialsEntity, UserCredentials.class);
    }

}
