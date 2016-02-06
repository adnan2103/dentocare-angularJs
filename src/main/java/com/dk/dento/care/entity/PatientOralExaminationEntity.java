package com.dk.dento.care.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by khana on 06/02/16.
 */
@Entity @IdClass(PatientOralExaminationId.class)
@Table(name = "patient_oral_examination")
public class PatientOralExaminationEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "treatment_id", referencedColumnName = "treatment_id")
    private TreatmentEntity treatment;

    @Id
    @ManyToOne
    @JoinColumn(name = "oral_examination_id", referencedColumnName = "oral_examination_id")
    private DefaultOralExaminationEntity defaultOralExaminationEntity;

    private Long cost;

    public TreatmentEntity getTreatment() {
        return treatment;
    }

    public void setTreatment(TreatmentEntity treatment) {
        this.treatment = treatment;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public TreatmentEntity getTreatmentEntity() {
        return treatment;
    }

    public void setTreatmentEntity(TreatmentEntity treatmentEntity) {
        this.treatment = treatmentEntity;
    }

    public DefaultOralExaminationEntity getDefaultOralExaminationEntity() {
        return defaultOralExaminationEntity;
    }

    public void setDefaultOralExaminationEntity(DefaultOralExaminationEntity defaultOralExaminationEntity) {
        this.defaultOralExaminationEntity = defaultOralExaminationEntity;
    }
}
