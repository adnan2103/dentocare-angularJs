package com.dk.dento.care.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
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
    private Long treatmentId;

    @OneToOne
    @JoinColumn(name="user_id")
    private UserDetailEntity userDetailEntity;

    @OneToOne
    @JoinColumn(name="status_id")
    private StatusEntity statusEntity;

    @Column(name = "chief_complaint_description")
    private String chiefComplaintDescription;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "treatment")
    private Set<PaymentEntity> paymentEntitySet = new HashSet<PaymentEntity>(0);

    public Long getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
    }

    public UserDetailEntity getUserDetailEntity() {
        return userDetailEntity;
    }

    public void setUserDetailEntity(UserDetailEntity userDetailEntity) {
        this.userDetailEntity = userDetailEntity;
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

    public Set<PaymentEntity> getPaymentEntitySet() {
        return paymentEntitySet;
    }

    public void setPaymentEntitySet(Set<PaymentEntity> paymentEntitySet) {
        this.paymentEntitySet = paymentEntitySet;
    }
}
