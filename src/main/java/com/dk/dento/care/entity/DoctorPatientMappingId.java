package com.dk.dento.care.entity;

import java.io.Serializable;

/**
 * Created by khana on 02/02/16.
 */
public class DoctorPatientMappingId implements Serializable {

    private DoctorEntity doctorEntity;

    private PatientEntity patientEntity;

    public DoctorEntity getDoctorEntity() {
        return doctorEntity;
    }

    public void setDoctorEntity(DoctorEntity doctorEntity) {
        this.doctorEntity = doctorEntity;
    }

    public PatientEntity getPatientEntity() {
        return patientEntity;
    }

    public void setPatientEntity(PatientEntity patientEntity) {
        this.patientEntity = patientEntity;
    }
}
