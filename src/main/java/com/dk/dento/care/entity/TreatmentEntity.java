package com.dk.dento.care.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by khana on 06/02/16.
 */
@Entity
@Table(name ="treatment")
public class TreatmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "treatment_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="status_id")
    private StatusEntity statusEntity;

    @Column(name = "chief_complaint_description")
    private String chiefComplaintDescription;

    @Column(name = "notes")
    private String notes;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "treatment_id")
    private Set<PaymentEntity> paymentEntities;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "treatment_id")
    private Set<PatientOralExaminationEntity> patientOralExaminationEntities;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetailEntity userDetailEntity;

    public UserDetailEntity getUserDetailEntity() {
        return userDetailEntity;
    }

    public void setUserDetailEntity(UserDetailEntity userDetailEntity) {
        this.userDetailEntity = userDetailEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
    }

    public String getChiefComplaintDescription() {
        return chiefComplaintDescription;
    }

    public void setChiefComplaintDescription(String chiefComplaintDescription) {
        this.chiefComplaintDescription = chiefComplaintDescription;
    }

    public Set<PaymentEntity> getPaymentEntities() {
        return paymentEntities;
    }

    public void setPaymentEntities(Set<PaymentEntity> paymentEntities) {
        this.paymentEntities = paymentEntities;
    }

    public Set<PatientOralExaminationEntity> getPatientOralExaminationEntities() {
        return patientOralExaminationEntities;
    }

    public void setPatientOralExaminationEntities(Set<PatientOralExaminationEntity> patientOralExaminationEntities) {
        this.patientOralExaminationEntities = patientOralExaminationEntities;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        TreatmentEntity rhs = (TreatmentEntity) obj;
        return new EqualsBuilder().append(id, rhs.id)
                .isEquals();
    }
}
