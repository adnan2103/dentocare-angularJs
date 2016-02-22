package com.dk.dento.care.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by khana on 26/01/16.
 */

@Entity
@Table(name = "user_detail")
public class UserDetailEntity implements Serializable {

    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user_id"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String gender;

    @Column(name = "date_of_birth")
    private Date dataOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Embedded
    private Address address;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private Set<TreatmentEntity> treatmentEntities = new HashSet<TreatmentEntity>(0);

    public Set<TreatmentEntity> getTreatmentEntities() {
        return treatmentEntities;
    }

    public void setTreatmentEntities(Set<TreatmentEntity> treatmentEntities) {
        this.treatmentEntities = treatmentEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDataOfBirth() {
        return dataOfBirth;
    }

    public void setDataOfBirth(Date dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
