package com.dk.dento.care.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by khana on 02/02/16.
 */
@Entity @IdClass(DoctorPatientMappingId.class)
@Table(name ="doctor_patient_mapping")
public class DoctorPatientMappingEntity implements Serializable {


    public DoctorPatientMappingEntity() {
    }

    public DoctorPatientMappingEntity(DoctorPatientMappingId doctorPatientMappingId) {
        this.doctorEntity = doctorPatientMappingId.getDoctorEntity();
        this.patientEntity = doctorPatientMappingId.getPatientEntity();
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "user_id")
    private UserDetailEntity doctorEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "user_id")
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
