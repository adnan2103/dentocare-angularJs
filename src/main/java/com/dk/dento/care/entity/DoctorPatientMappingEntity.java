package com.dk.dento.care.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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


    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "user_id")
    private DoctorEntity doctorEntity;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "user_id")
    private PatientEntity patientEntity;

    @Column(name = "doctor_id", insertable = false, updatable = false)
    private Long doctorId;

    @Column(name = "patient_id", insertable = false, updatable = false)
    private Long patientId;

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
}
