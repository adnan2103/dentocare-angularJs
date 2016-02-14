package com.dk.dento.care.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by khana on 06/02/16.
 */
@Entity
@Table(name ="treatment")
public class TreatmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "treatment_id")
    private Long treatmentId;

    @OneToOne
    @JoinColumn(name="status_id")
    private StatusEntity statusEntity;

    @Column(name = "chief_complaint_description")
    private String chiefComplaintDescription;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private Set<PaymentEntity> paymentEntities = new HashSet<PaymentEntity>(0);

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "oral_examination_id")
    private Set<PatientOralExaminationEntity> patientOralExaminationEntities = new HashSet<PatientOralExaminationEntity>(0);

    public Long getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
    }

    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
    }

    public String getChiefComplaintDescription() {
        return chiefComplaintDescription;
    }

    public void setChiefComplaintDescription(String chiefComplaintDescription) {
        this.chiefComplaintDescription = chiefComplaintDescription;
    }

    public Set<PaymentEntity> getPaymentEntities() {
        return paymentEntities;
    }

    public void setPaymentEntities(Set<PaymentEntity> paymentEntities) {
        this.paymentEntities = paymentEntities;
    }

    public Set<PatientOralExaminationEntity> getPatientOralExaminationEntities() {
        return patientOralExaminationEntities;
    }

    public void setPatientOralExaminationEntities(Set<PatientOralExaminationEntity> patientOralExaminationEntities) {
        this.patientOralExaminationEntities = patientOralExaminationEntities;
    }
}
