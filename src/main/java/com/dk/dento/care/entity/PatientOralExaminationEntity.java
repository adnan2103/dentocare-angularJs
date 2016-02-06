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
    private TreatmentEntity treatmentEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "oral_examination_id", referencedColumnName = "oral_examination_id")
    private DefaultOralExaminationEntity defaultOralExaminationEntity;


    public TreatmentEntity getTreatmentEntity() {
        return treatmentEntity;
    }

    public void setTreatmentEntity(TreatmentEntity treatmentEntity) {
        this.treatmentEntity = treatmentEntity;
    }

    public DefaultOralExaminationEntity getDefaultOralExaminationEntity() {
        return defaultOralExaminationEntity;
    }

    public void setDefaultOralExaminationEntity(DefaultOralExaminationEntity defaultOralExaminationEntity) {
        this.defaultOralExaminationEntity = defaultOralExaminationEntity;
    }
}
