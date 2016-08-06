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
@Entity @IdClass(UserModuleAccessId.class)
@Table( name = "clinic_user_module_access")
public class UserModuleAccessEntity implements Serializable {

    public UserModuleAccessEntity() {
    }

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "module_id")
    private Long moduleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }
}
