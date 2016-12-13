package com.dk.dento.care.entity.clinic;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Created by khana on 30/07/16.
 */
public class ModulesMappingId implements Serializable {

    public ModulesMappingId() {

    }

    public ModulesMappingId(Long clinicId, Long moduleId) {
        this.clinicId = clinicId;
        this.moduleId = moduleId;
    }

    @Column(name = "clinic_id")
    private Long clinicId;

    @Column(name = "module_id")
    private Long moduleId;

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }
}
