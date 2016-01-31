package com.dk.dento.care.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by khana on 31/01/16.
 */
@Entity(name ="patient")
@DiscriminatorValue("PAT")
public class PatientEntity extends UserDetailEntity {

}
