package com.dk.dento.care.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by khana on 24/02/16.
 */
public class Treatment {

    private Long id;

    private String chiefComplaintDescription;

    private String notes;

    private String status;

    private Long createdBy;

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    private Long preImageCount;

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

    private List<Payment> payment = new ArrayList<Payment>(0);

    private List<PatientOralExamination> patientOralExamination = new ArrayList<PatientOralExamination>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getChiefComplaintDescription() {
        return chiefComplaintDescription;
    }

    public void setChiefComplaintDescription(String chiefComplaintDescription) {
        this.chiefComplaintDescription = chiefComplaintDescription;
    }


    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PatientOralExamination> getPatientOralExamination() {
        return patientOralExamination;
    }

    public void setPatientOralExamination(List<PatientOralExamination> patientOralExamination) {
        this.patientOralExamination = patientOralExamination;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "id=" + id +
                ", chiefComplaintDescription='" + chiefComplaintDescription + '\'' +
                ", notes='" + notes + '\'' +
                ", status='" + status + '\'' +
                ", payment=" + payment +
                ", patientOralExamination=" + patientOralExamination +
                '}';
    }
}
