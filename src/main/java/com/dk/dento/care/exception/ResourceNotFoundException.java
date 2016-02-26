package com.dk.dento.care.exception;

/**
 * The Resource not found exception.
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Object resourceIdentifier;

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param resourceIdentifier the resource identifier
     */
    public ResourceNotFoundException(final Object resourceIdentifier) {
        super();
        this.resourceIdentifier = resourceIdentifier;
    }

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param message the message
     * @param resourceIdentifier the resource identifier
     */
    public ResourceNotFoundException(final String message, final Object resourceIdentifier) {
        super(message);
        this.resourceIdentifier = resourceIdentifier;
    }

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param message the message
     * @param resourceIdentifier the resource identifier
     * @param cause the cause
     */
    public ResourceNotFoundException(final String message, final Object resourceIdentifier,
                                     final Throwable cause) {
        super(message, cause);
        this.resourceIdentifier = resourceIdentifier;
    }

    /**
     * Instantiates a new Resource not found exception.
     *
     * @param message the message
     * @param resourceIdentifier the resource identifier
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public ResourceNotFoundException(final String message, final Object resourceIdentifier,
                                     final Throwable cause, final boolean enableSuppression,
                                     final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.resourceIdentifier = resourceIdentifier;
    }

    /**
     * Gets resource identifier.
     *
     * @return the resource identifier
     */
    public Object getResourceIdentifier() {
        return resourceIdentifier;
    }

}
