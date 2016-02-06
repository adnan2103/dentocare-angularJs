package com.dk.dento.care.service;

import com.dk.dento.care.entity.DoctorPatientMappingEntity;
import com.dk.dento.care.entity.EmailAddress;
import com.dk.dento.care.entity.TreatmentEntity;
import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.Patient;
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

    public List<Patient> getAllPatientForDoctor(UserCredentialsEntity doctor) {

        List<Patient> patients = new ArrayList<Patient>();
        Patient patient = null;

        Iterable<DoctorPatientMappingEntity> allPatients = doctorPatientMappingRepository.findAllPatientsForDoctor(doctor.getUserId());

        for(DoctorPatientMappingEntity doctorPatientMappingEntity : allPatients) {

            UserDetailEntity userDetailEntity = doctorPatientMappingEntity.getPatientEntity();
            patient = new Patient();
            patient.setFirstName(userDetailEntity.getName().toString());
            patients.add(patient);
        }
        return patients;
    }

    public Patient getPatientDetails(Long patientId) {
        Patient patient = new Patient();
        UserDetailEntity userDetailEntity = userDetailRepository.findOne(patientId);
        List<TreatmentEntity> treatments = treatmentRepository.findAllTreatmentForPatient(patientId);
        patient.setFirstName(userDetailEntity.getName().toString());
        patient.setTreatments(treatments);
        return patient;
    }
}
