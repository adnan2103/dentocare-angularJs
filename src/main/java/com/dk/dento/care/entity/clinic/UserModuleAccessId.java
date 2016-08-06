package com.dk.dento.care.entity.clinic;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by khana on 30/07/16.
 */
public class UserModuleAccessId implements Serializable {

    public UserModuleAccessId() {

    }

    public UserModuleAccessId( Long userId, Long moduleId) {
        this.userId = userId;
        this.moduleId = moduleId;
    }

    @Column(name = "user_id")
    private Long userId;

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
