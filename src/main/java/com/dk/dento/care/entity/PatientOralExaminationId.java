package com.dk.dento.care.entity;

import java.io.Serializable;

/**
 * Created by khana on 06/02/16.
 */
public class PatientOralExaminationId implements Serializable {

    private TreatmentEntity treatmentEntity;

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
