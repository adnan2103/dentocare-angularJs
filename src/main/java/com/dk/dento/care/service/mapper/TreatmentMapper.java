package com.dk.dento.care.service.mapper;

import com.dk.dento.care.entity.PatientOralExaminationEntity;
import com.dk.dento.care.entity.PaymentEntity;
import com.dk.dento.care.entity.TreatmentEntity;
import com.dk.dento.care.entity.TreatmentIdGenerator;
import com.dk.dento.care.entity.UserDetailEntity;
import com.dk.dento.care.model.ImagePath;
import com.dk.dento.care.model.PatientOralExamination;
import com.dk.dento.care.model.Payment;
import com.dk.dento.care.model.Treatment;
import com.dk.dento.care.repository.StatusRepository;
import com.dk.dento.care.repository.UserDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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

    public List<Treatment> treatmentEntitiesToTreatments(Iterable<TreatmentEntity> treatmentEntities) {
        List<Treatment> treatments = new ArrayList<Treatment>(0);
        Treatment treatment;

        for (TreatmentEntity treatmentEntity : treatmentEntities) {
            treatment = modelMapper.map(treatmentEntity, Treatment.class);
            List<ImagePath> preTreatmentImages = new ArrayList<ImagePath>(0);
            List<ImagePath> postTreatmentImages = new ArrayList<ImagePath>(0);

            preTreatmentImages.add(new ImagePath("treatment/"+treatmentEntity.getId()+"/images/pre/"+1));
            preTreatmentImages.add(new ImagePath("treatment/"+treatmentEntity.getId()+"/images/pre/"+2));
            preTreatmentImages.add(new ImagePath("treatment/"+treatmentEntity.getId()+"/images/pre/"+1));
            postTreatmentImages.add(new ImagePath("treatment/"+treatmentEntity.getId()+"/images/post/"+2));

            treatment.setPreTreatmentImages(preTreatmentImages);
            treatment.setPostTreatmentImages(postTreatmentImages);

            treatment.setStatus(treatmentEntity.getStatusEntity().getStatus());
            treatments.add(treatment);
        }

        return treatments;
    }

    public List<TreatmentEntity> treatmentsToTreatmentEntities(Iterable<Treatment> treatments, Long patientId) {
        List<TreatmentEntity> treatmentEntities = new ArrayList<TreatmentEntity>(0);
        UserDetailEntity userDetailEntity = userDetailRepository.findOne(patientId);

        TreatmentEntity treatmentEntity;
        for (Treatment treatment : treatments) {
            treatmentEntity = modelMapper.map(treatment, TreatmentEntity.class);
            if (treatmentEntity.getId() == null) {
                treatmentEntity.setId(treatmentIdGenerator.getNextId());
            }
            treatmentEntity.setUserDetailEntity(userDetailEntity);
            treatmentEntity.setStatusEntity(statusRepository.findByStatus(treatment.getStatus()));

            List<PatientOralExamination> patientOralExaminations = treatment.getPatientOralExamination();
            List<PatientOralExaminationEntity> patientOralExaminationEntities = this.patientOralExaminationToPatientOralExaminationEntities(patientOralExaminations, treatmentEntity);
            treatmentEntity.setPatientOralExaminationEntities(patientOralExaminationEntities);

            List<Payment> payments = treatment.getPayment();
            List<PaymentEntity> paymentEntities = this.paymentsToPaymentEntities(payments, treatmentEntity);
            treatmentEntity.setPaymentEntities(paymentEntities);

            treatmentEntities.add(treatmentEntity);
        }

        return treatmentEntities;
    }

    private List<PaymentEntity> paymentsToPaymentEntities(List<Payment> payments, TreatmentEntity treatmentEntity) {
        List<PaymentEntity> paymentEntities = new ArrayList<PaymentEntity>(0);
        PaymentEntity paymentEntity;
        for (Payment payment : payments) {

            paymentEntity = modelMapper.map(payment, PaymentEntity.class);
            paymentEntity.setTreatmentEntity(treatmentEntity);
            paymentEntities.add(paymentEntity);
        }

        return paymentEntities;
    }

    private List<PatientOralExaminationEntity> patientOralExaminationToPatientOralExaminationEntities
            (
                    List<PatientOralExamination> patientOralExaminations, TreatmentEntity treatmentEntity
            ) {
        List<PatientOralExaminationEntity> patientOralExaminationEntities = new ArrayList<PatientOralExaminationEntity>(0);
        PatientOralExaminationEntity patientOralExaminationEntity;

        for (PatientOralExamination patientOralExamination : patientOralExaminations) {

            patientOralExaminationEntity = modelMapper.map(patientOralExamination, PatientOralExaminationEntity.class);
            patientOralExaminationEntity.setTreatmentEntity(treatmentEntity);

            patientOralExaminationEntities.add(patientOralExaminationEntity);
        }

        return patientOralExaminationEntities;
    }
}
