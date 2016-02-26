package com.dk.dento.care.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    private Long id;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "payment_amount")
    private Long paymentAmount;

    @Column(name = "treatment_done")
    private String treatmentDone;

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

    public String getTreatmentDone() {
        return treatmentDone;
    }

    public void setTreatmentDone(String treatmentDone) {
        this.treatmentDone = treatmentDone;
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
        PaymentEntity rhs = (PaymentEntity) obj;
        return new EqualsBuilder().append(id, rhs.id)
                .isEquals();
    }
}
