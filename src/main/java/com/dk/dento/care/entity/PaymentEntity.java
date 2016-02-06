package com.dk.dento.care.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;


/**
 * Created by khana on 06/02/16.
 */
@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "treatment_id", referencedColumnName = "treatment_id")
    private TreatmentEntity treatmentEntity;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "payment_amount")
    private Long paymentAmount;

    @Column(name = "notes")
    private String notes;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public TreatmentEntity getTreatmentEntity() {
        return treatmentEntity;
    }

    public void setTreatmentEntity(TreatmentEntity treatmentEntity) {
        this.treatmentEntity = treatmentEntity;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Long paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
