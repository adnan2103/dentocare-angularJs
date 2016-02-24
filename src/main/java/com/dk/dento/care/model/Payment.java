package com.dk.dento.care.model;

import java.util.Date;

/**
 * Created by khana on 24/02/16.
 */
public class Payment {

    private Long id;

    private Date paymentDate;

    private Long paymentAmount;

    private String treatmentDone;

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
}
