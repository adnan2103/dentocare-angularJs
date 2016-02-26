package com.dk.dento.care.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by khana on 06/02/16.
 */
@Entity
@Table(name = "patient_oral_examination")
public class PatientOralExaminationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "oral_examination_id")
    private Long id;

    private String description;

    private Long cost;

    @ManyToOne
    @JoinColumn(name = "treatment_id")
    private TreatmentEntity treatmentEntity;

    public TreatmentEntity getTreatmentEntity() {
        return treatmentEntity;
    }

    public void setTreatmentEntity(TreatmentEntity treatmentEntity) {
        this.treatmentEntity = treatmentEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
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
        PatientOralExaminationEntity rhs = (PatientOralExaminationEntity) obj;
        return new EqualsBuilder().append(id, rhs.id)
                .isEquals();
    }
}
