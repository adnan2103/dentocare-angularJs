package com.dk.dento.care.entity;

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
    private Long oralExaminationId;

    private String description;

    private Long cost;

    public Long getOralExaminationId() {
        return oralExaminationId;
    }

    public void setOralExaminationId(Long oralExaminationId) {
        this.oralExaminationId = oralExaminationId;
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
}
