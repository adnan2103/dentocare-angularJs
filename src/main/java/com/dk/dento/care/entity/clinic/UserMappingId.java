package com.dk.dento.care.entity.clinic;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by khana on 30/07/16.
 */
public class UserMappingId implements Serializable {

    public UserMappingId() {

    }

    public UserMappingId(Long clinicId, Long userId) {
        this.clinicId = clinicId;
        this.userId = userId;
    }

    @Column(name = "clinic_id")
    private Long clinicId;

    @Column(name = "user_id")
    private Long userId;

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
