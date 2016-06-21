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

    private List<Payment> payment = new ArrayList<Payment>(0);

    private List<PatientOralExamination> patientOralExamination = new ArrayList<PatientOralExamination>(0);

    private List<ImagePath> preTreatmentImages = new ArrayList<ImagePath>(0);

    private List<ImagePath> postTreatmentImages = new ArrayList<ImagePath>(0);

    public List<ImagePath> getPreTreatmentImages() {
        return preTreatmentImages;
    }

    public void setPreTreatmentImages(List<ImagePath> preTreatmentImages) {
        this.preTreatmentImages = preTreatmentImages;
    }

    public List<ImagePath> getPostTreatmentImages() {
        return postTreatmentImages;
    }

    public void setPostTreatmentImages(List<ImagePath> postTreatmentImages) {
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
