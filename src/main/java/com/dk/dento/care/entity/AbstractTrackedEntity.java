package com.dk.dento.care.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

/**
 * Abstract support class for model objects that support creation and last
 * updated dates. These dates are set automatically.
 */
@MappedSuperclass
public abstract class AbstractTrackedEntity {

    /** The date that the model object was created */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false)
    private Calendar creationDate;

    /** The date that the model object was last updated */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_date", nullable = false)
    private Calendar updatedDate;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "last_modified_by", nullable = false)
    private Long lastUpdatedBy;


    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Called by Hibernate before a save operation. Sets both dates.
     */
    @PrePersist
    void onPersist() {
        Calendar now = Calendar.getInstance();
        creationDate = now;
        updatedDate = now;
    }

    /**
     * Called by Hibernate before an update operation. Only sets the last update
     * date.
     */
    @PreUpdate
    void onUpdate() {
        Calendar now = Calendar.getInstance();
        updatedDate = now;
    }

}
