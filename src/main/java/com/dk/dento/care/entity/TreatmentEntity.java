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
import java.util.List;

/**
 * Created by khana on 06/02/16.
 */
@Entity
@Table(name ="treatment")
public class TreatmentEntity extends AbstractTrackedEntity {

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

    @Column(name = "pre_image_count")
    private Long preImageCount;

    @Column(name = "post_image_count")
    private Long postImageCount;

    public Long getPreImageCount() {
        return preImageCount;
    }

    public void setPreImageCount(Long preImageCount) {
        this.preImageCount = preImageCount;
    }

    public Long getPostImageCount() {
        return postImageCount;
    }

    public void setPostImageCount(Long postImageCount) {
        this.postImageCount = postImageCount;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "treatment_id")
    private List<PaymentEntity> paymentEntities;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "treatment_id")
    private List<PatientOralExaminationEntity> patientOralExaminationEntities;



    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName="user_id")
    private UserDetailEntity patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName="user_id")
    private UserDetailEntity doctor;

    public UserDetailEntity getPatient() {
        return patient;
    }

    public void setPatient(UserDetailEntity patient) {
        this.patient = patient;
    }

    public UserDetailEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(UserDetailEntity doctor) {
        this.doctor = doctor;
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

    public List<PaymentEntity> getPaymentEntities() {
        return paymentEntities;
    }

    public void setPaymentEntities(List<PaymentEntity> paymentEntities) {
        this.paymentEntities = paymentEntities;
    }

    public List<PatientOralExaminationEntity> getPatientOralExaminationEntities() {
        return patientOralExaminationEntities;
    }

    public void setPatientOralExaminationEntities(List<PatientOralExaminationEntity> patientOralExaminationEntities) {
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
