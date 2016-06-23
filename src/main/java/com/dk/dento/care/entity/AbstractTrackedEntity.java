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
     * Get the date that the object was created
     * 
     * @return The creation date
     */
    public Calendar getCreationDate() {
        return creationDate;
    }

    /**
     * Get the date the object was last updated
     * 
     * @return The last update date
     */
    public Calendar getUpdatedDate() {
        return updatedDate;
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
