package com.dk.dento.care.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(doctorId)
                .append(patientId)
                .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DoctorPatientMappingId rhs = (DoctorPatientMappingId) obj;
        return new EqualsBuilder()
                .append(doctorId, rhs.doctorId)
                .append(patientId, rhs.patientId)
                .isEquals();
    }
}
