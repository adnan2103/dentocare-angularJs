package com.dk.dento.care.entity.clinic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by khana on 29/07/16.
 */
@Entity
@Table( name = "clinic")
public class ClinicEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "clinic_id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
