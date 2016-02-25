package com.dk.dento.care.entity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by khana on 02/02/16.
 */
@Entity
@Table(name ="doctor_patient_mapping")
public class DoctorPatientMappingEntity implements Serializable {


    public DoctorPatientMappingEntity() {
    }

    public DoctorPatientMappingEntity(DoctorPatientMappingId doctorPatientMappingId) {
        this.doctorPatientMappingId = doctorPatientMappingId;
    }

    @EmbeddedId
    private DoctorPatientMappingId doctorPatientMappingId;

    public DoctorPatientMappingId getDoctorPatientMappingId() {
        return doctorPatientMappingId;
    }

    public void setDoctorPatientMappingId(DoctorPatientMappingId doctorPatientMappingId) {
        this.doctorPatientMappingId = doctorPatientMappingId;
    }

}
