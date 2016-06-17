package com.dk.dento.care.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by khana on 24/02/16.
 */
public class Treatment {

    private Long id;

    private String chiefComplaintDescription;

    private String notes;

    private String status;

    private Set<Payment> payment = new HashSet<Payment>(0);

    private Set<PatientOralExamination> patientOralExamination = new HashSet<PatientOralExamination>(0);

    private Set<ImagePath> preTreatmentImages = new HashSet<ImagePath>(0);

    private Set<ImagePath> postTreatmentImages = new HashSet<ImagePath>(0);

    public Set<ImagePath> getPreTreatmentImages() {
        return preTreatmentImages;
    }

    public void setPreTreatmentImages(Set<ImagePath> preTreatmentImages) {
        this.preTreatmentImages = preTreatmentImages;
    }

    public Set<ImagePath> getPostTreatmentImages() {
        return postTreatmentImages;
    }

    public void setPostTreatmentImages(Set<ImagePath> postTreatmentImages) {
        this.postTreatmentImages = postTreatmentImages;
    }

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


    public Set<Payment> getPayment() {
        return payment;
    }

    public void setPayment(Set<Payment> payment) {
        this.payment = payment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<PatientOralExamination> getPatientOralExamination() {
        return patientOralExamination;
    }

    public void setPatientOralExamination(Set<PatientOralExamination> patientOralExamination) {
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
