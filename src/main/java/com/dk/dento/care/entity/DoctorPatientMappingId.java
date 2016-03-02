package com.dk.dento.care.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by khana on 02/02/16.
 */
@Embeddable
public class DoctorPatientMappingId implements Serializable {


    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "patient_id")
    private Long patientId;

    public DoctorPatientMappingId() {
    }

    public DoctorPatientMappingId(Long doctorId, Long patientId) {
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    //TODO write equals and hoshcode methods.
}
