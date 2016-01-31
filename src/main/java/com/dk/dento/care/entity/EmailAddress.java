package com.dk.dento.care.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Represents an email address. This is a value object and is thus immutable.
 */
@Embeddable
public class EmailAddress {

    /** The string value of the email address */
    @Column(name = "email")
    private String email;

    /**
     * Public constructor. It will validate that the passed in string is a valid
     * email address. If it is not, an {@link IllegalArgumentException} will be
     * thrown
     *
     * @param email
     *            The string value of the email address
     */
    public EmailAddress(final String email) {
        validate(email);
        this.email = email;
    }

    /**
     * This constructor is needed by Hibernate
     */
    EmailAddress() {
        // empty
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        EmailAddress rhs = (EmailAddress) obj;
        return new EqualsBuilder().append(email, rhs.email).isEquals();
    }

    /**
     * Get the value of the email address as a string
     *
     * @return The string value of the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(email).toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return email;
    }

    /**
     * Validate the given email address string
     *
     * @param address
     *            The email address string to validate
     *
     * @throws IllegalArgumentException
     *             The passed in email address string is not valid
     */
    static void validate(final String address) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        boolean isValid = emailValidator.isValid(address);
        if (!isValid) {
            throw new IllegalArgumentException("Invalid email address: "
                    + address);
        }
    }

}
