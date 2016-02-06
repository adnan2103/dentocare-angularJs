package com.dk.dento.care.model;

import com.dk.dento.care.entity.TreatmentEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by khana on 26/01/16.
 */
public class Patient {

    private Long id;
    private String firstName;
    private String lastName;
    private Date dataOfBirth;
    private String gender;
    private Integer mobile;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private List<TreatmentEntity> treatments;

    public List<TreatmentEntity> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<TreatmentEntity> treatments) {
        this.treatments = treatments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDataOfBirth() {
        return dataOfBirth;
    }

    public void setDataOfBirth(Date dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Patient() {
    }

    public Patient(Long id, String firstName, String lastName, Date dataOfBirth, String gender, Integer mobile, String addressLine1, String addressLine2, String city, String state, String country, String pincode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dataOfBirth = dataOfBirth;
        this.gender = gender;
        this.mobile = mobile;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}
