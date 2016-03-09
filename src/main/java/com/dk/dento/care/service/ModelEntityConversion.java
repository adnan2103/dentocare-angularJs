package com.dk.dento.care.service;

import com.dk.dento.care.entity.PatientOralExaminationEntity;
import com.dk.dento.care.entity.PaymentEntity;
import com.dk.dento.care.entity.TreatmentEntity;
import com.dk.dento.care.entity.TreatmentIdGenerator;
import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.Patient;
import com.dk.dento.care.model.PatientOralExamination;
import com.dk.dento.care.model.Payment;
import com.dk.dento.care.model.Treatment;
import com.dk.dento.care.model.UserCredentials;
import com.dk.dento.care.repository.StatusRepository;
import com.dk.dento.care.repository.UserDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by khana on 22/02/16.
 */
@Service
public class ModelEntityConversion {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private TreatmentIdGenerator treatmentIdGenerator;

    public List<Patient> userDetailsEntityToPatientList(Set<UserDetailEntity> userDetailEntities) {
        List<Patient> patients = new ArrayList<Patient>(0);

        for (UserDetailEntity userDetailEntity : userDetailEntities) {
            Patient patient = modelMapper.map(userDetailEntity, Patient.class);
            patient.setDateOfBirth("");
            if(userDetailEntity.getDataOfBirth() != null) {
                patient.setDateOfBirth(new SimpleDateFormat("dd-MM-yyyy").format(userDetailEntity.getDataOfBirth()));
            }
            patients.add(patient);
        }

        return patients;
    }

    public Patient userDetailsEntityToPatient(UserDetailEntity userDetailEntity) {
        Patient patient = modelMapper.map(userDetailEntity, Patient.class);
        patient.setDateOfBirth("");
        if(userDetailEntity.getDataOfBirth() != null) {
            patient.setDateOfBirth(new SimpleDateFormat("dd-MM-yyyy").format(userDetailEntity.getDataOfBirth()));
        }
        return patient;
    }

    public UserDetailEntity patientToUserDetailEntity(Patient patient) throws ParseException {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        UserDetailEntity userDetailEntity = modelMapper.map(patient, UserDetailEntity.class);

        //TODO add isValid date and format kind of validations.
        if(patient.getDateOfBirth() !=null) {
            Date dob = simpleDateFormat.parse(patient.getDateOfBirth());
            userDetailEntity.setDataOfBirth(dob);
        }

        return userDetailEntity;

    }

    public UserCredentials userCredentialsEntityToModel(UserCredentialsEntity userCredentialsEntity) {
        return modelMapper.map(userCredentialsEntity, UserCredentials.class);
    }

    public Set<Treatment> treatmentEntityToTreatmentList(Set<TreatmentEntity> treatmentEntities) {
        Set<Treatment> treatments = new HashSet<Treatment>(0);
        Treatment treatment;

        for (TreatmentEntity treatmentEntity : treatmentEntities) {
            treatment = modelMapper.map(treatmentEntity, Treatment.class);
            treatment.setStatus(treatmentEntity.getStatusEntity().getStatus());
            treatments.add(treatment);
        }

        return treatments;
    }

    public Set<TreatmentEntity> treatmentModelListToTreatmentEntityList(Iterable<Treatment> treatments, Long patientId) {
        Set<TreatmentEntity> treatmentEntities = new HashSet<TreatmentEntity>(0);
        UserDetailEntity userDetailEntity = userDetailRepository.findOne(patientId);

        TreatmentEntity treatmentEntity;
        for (Treatment treatment : treatments) {
            treatmentEntity = modelMapper.map(treatment, TreatmentEntity.class);
            if (treatmentEntity.getId() == null) {
                treatmentEntity.setId(treatmentIdGenerator.getNextId());
            }
            treatmentEntity.setUserDetailEntity(userDetailEntity);
            treatmentEntity.setStatusEntity(statusRepository.findByStatus(treatment.getStatus()));

            Set<PatientOralExamination> patientOralExaminations = treatment.getPatientOralExamination();
            Set<PatientOralExaminationEntity> patientOralExaminationEntities = this.patientOralExaminationModelToEntityList(patientOralExaminations, treatmentEntity);
            treatmentEntity.setPatientOralExaminationEntities(patientOralExaminationEntities);

            Set<Payment> payments = treatment.getPayment();
            Set<PaymentEntity> paymentEntities = this.paymentModelToEntityList(payments, treatmentEntity);
            treatmentEntity.setPaymentEntities(paymentEntities);

            treatmentEntities.add(treatmentEntity);
        }

        return treatmentEntities;
    }

    public Set<PaymentEntity> paymentModelToEntityList(Set<Payment> payments, TreatmentEntity treatmentEntity) {
        Set<PaymentEntity> paymentEntities = new HashSet<PaymentEntity>(0);
        PaymentEntity paymentEntity;
        for (Payment payment : payments) {

            paymentEntity = modelMapper.map(payment, PaymentEntity.class);
            paymentEntity.setTreatmentEntity(treatmentEntity);

            paymentEntities.add(paymentEntity);
        }

        return paymentEntities;
    }

    public Set<PatientOralExaminationEntity> patientOralExaminationModelToEntityList(Set<PatientOralExamination> patientOralExaminations, TreatmentEntity treatmentEntity) {
        Set<PatientOralExaminationEntity> patientOralExaminationEntities = new HashSet<PatientOralExaminationEntity>(0);
        PatientOralExaminationEntity patientOralExaminationEntity;

        for (PatientOralExamination patientOralExamination : patientOralExaminations) {

            patientOralExaminationEntity = modelMapper.map(patientOralExamination, PatientOralExaminationEntity.class);
            patientOralExaminationEntity.setTreatmentEntity(treatmentEntity);

            patientOralExaminationEntities.add(patientOralExaminationEntity);
        }

        return patientOralExaminationEntities;
    }
}
