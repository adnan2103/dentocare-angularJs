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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private Long id;

    @OneToOne
    @JoinColumn(name="status_id")
    private StatusEntity statusEntity;

    @Column(name = "chief_complaint_description")
    private String chiefComplaintDescription;

    //TODO add note column.

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "treatment_id")
    private Set<PaymentEntity> paymentEntities = new HashSet<PaymentEntity>(0);

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "treatment_id")
    private Set<PatientOralExaminationEntity> patientOralExaminationEntities = new HashSet<PatientOralExaminationEntity>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
