package com.dk.dento.care.entity;

import java.io.Serializable;

/**
 * Created by khana on 02/02/16.
 */
public class DoctorPatientMappingId implements Serializable {

    private UserDetailEntity doctorEntity;

    private UserDetailEntity patientEntity;

    public UserDetailEntity getDoctorEntity() {
        return doctorEntity;
    }

    public void setDoctorEntity(UserDetailEntity doctorEntity) {
        this.doctorEntity = doctorEntity;
    }

    public UserDetailEntity getPatientEntity() {
        return patientEntity;
    }

    public void setPatientEntity(UserDetailEntity patientEntity) {
        this.patientEntity = patientEntity;
    }
}
