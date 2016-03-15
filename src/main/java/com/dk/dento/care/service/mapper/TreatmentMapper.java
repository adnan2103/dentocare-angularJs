package com.dk.dento.care.service.mapper;

import com.dk.dento.care.entity.PatientOralExaminationEntity;
import com.dk.dento.care.entity.PaymentEntity;
import com.dk.dento.care.entity.TreatmentEntity;
import com.dk.dento.care.entity.TreatmentIdGenerator;
import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.PatientOralExamination;
import com.dk.dento.care.model.Payment;
import com.dk.dento.care.model.Treatment;
import com.dk.dento.care.repository.StatusRepository;
import com.dk.dento.care.repository.UserDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by khana on 22/02/16.
 */
@Service
public class TreatmentMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private TreatmentIdGenerator treatmentIdGenerator;

    public Set<Treatment> treatmentEntitiesToTreatments(Iterable<TreatmentEntity> treatmentEntities) {
        Set<Treatment> treatments = new HashSet<Treatment>(0);
        Treatment treatment;

        for (TreatmentEntity treatmentEntity : treatmentEntities) {
            treatment = modelMapper.map(treatmentEntity, Treatment.class);
            treatment.setStatus(treatmentEntity.getStatusEntity().getStatus());
            treatments.add(treatment);
        }

        return treatments;
    }

    public Set<TreatmentEntity> treatmentsToTreatmentEntities(Iterable<Treatment> treatments, Long patientId) {
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
            Set<PatientOralExaminationEntity> patientOralExaminationEntities = this.patientOralExaminationToPatientOralExaminationEntities(patientOralExaminations, treatmentEntity);
            treatmentEntity.setPatientOralExaminationEntities(patientOralExaminationEntities);

            Set<Payment> payments = treatment.getPayment();
            Set<PaymentEntity> paymentEntities = this.paymentsToPaymentEntities(payments, treatmentEntity);
            treatmentEntity.setPaymentEntities(paymentEntities);

            treatmentEntities.add(treatmentEntity);
        }

        return treatmentEntities;
    }

    private Set<PaymentEntity> paymentsToPaymentEntities(Set<Payment> payments, TreatmentEntity treatmentEntity) {
        Set<PaymentEntity> paymentEntities = new HashSet<PaymentEntity>(0);
        PaymentEntity paymentEntity;
        for (Payment payment : payments) {

            paymentEntity = modelMapper.map(payment, PaymentEntity.class);
            paymentEntity.setTreatmentEntity(treatmentEntity);
            paymentEntities.add(paymentEntity);
        }

        return paymentEntities;
    }

    private Set<PatientOralExaminationEntity> patientOralExaminationToPatientOralExaminationEntities
            (
                Set<PatientOralExamination> patientOralExaminations, TreatmentEntity treatmentEntity
            ) {
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
