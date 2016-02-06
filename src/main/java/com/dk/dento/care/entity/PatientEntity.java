package com.dk.dento.care.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by khana on 31/01/16.
 */
@Entity(name ="patient")
@DiscriminatorValue("PAT")
public class PatientEntity extends UserDetailEntity {

}
