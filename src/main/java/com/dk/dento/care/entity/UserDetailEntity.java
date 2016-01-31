package com.dk.dento.care.entity;

import com.dk.dento.care.model.UserCredentials;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

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

    @Embedded
    private Name name;

    private String gender;

    @Column(name = "date_of_birth")
    private Date dataOfBirth;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private UserCredentialsEntity userCredentialsEntity;


    public UserCredentialsEntity getUserCredentialsEntity() {
        return userCredentialsEntity;
    }

    public void setUserCredentialsEntity(UserCredentialsEntity userCredentialsEntity) {
        this.userCredentialsEntity = userCredentialsEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
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

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
