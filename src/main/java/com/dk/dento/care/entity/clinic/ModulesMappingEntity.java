package com.dk.dento.care.entity.clinic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by khana on 30/07/16.
 */
@Entity @IdClass(ModulesMappingId.class)
@Table(name = "clinic_modules_mapping")
public class ModulesMappingEntity implements Serializable{

    public ModulesMappingEntity() {
    }

    @Id
    @Column(name = "clinic_id")
    private Long clinicId;

    @Id
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
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "expiry_date")
    private Date expiryDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
