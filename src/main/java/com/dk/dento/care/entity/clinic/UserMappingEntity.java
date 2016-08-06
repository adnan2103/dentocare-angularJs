package com.dk.dento.care.entity.clinic;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by khana on 30/07/16.
 */
@Entity @IdClass(UserMappingId.class)
@Table( name = "clinic_user_mapping")
public class UserMappingEntity implements Serializable {

    public UserMappingEntity() {
    }

    @Id
    @Column(name = "clinic_id")
    private Long clinicId;

    @Id
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
