package com.dk.dento.care.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

/**
 * Represents a phone number. Objects of this type are value objects. As such
 * they are immutable.
 */
@Embeddable
public class PhoneNumber {

    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * Instantiates a new Phone number.
     *
     * @param value the value
     */
    public PhoneNumber(final String value) {
        this.phoneNumber = Long.toString(parse(value).getNationalNumber());
    }

    /**
     * This constructor is needed by Hibernate
     */
    PhoneNumber() {
        // empty
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Parse com . google . i 18 n . phonenumbers . phonenumber . phone number.
     *
     * @param value the value
     * @return the com . google . i 18 n . phonenumbers . phonenumber . phone number
     */
    private com.google.i18n.phonenumbers.Phonenumber.PhoneNumber parse(
            final String value) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            return phoneNumberUtil.parse(value, "IN");
        } catch (NumberParseException e) {
            throw new IllegalArgumentException("Invalid phone number: " + value, e);
        }
    }

}
