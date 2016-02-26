package com.dk.dento.care.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.math.BigInteger;

/**
 * Generate Ids for transactions.
 */
@Service
public class TreatmentIdGenerator {
    /**
     * Entity Manager Factory dependency.
     */
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    /**
     * Get the next id to use for transactions.
     * @return next Id.
     */
    public Long getNextId() {
        Object nextId = entityManagerFactory.createEntityManager()
                .createNativeQuery("SELECT nextval ('hibernate_sequence')")
                .getSingleResult();

        if (nextId instanceof BigInteger) {
            return ((BigInteger) nextId).longValue();
        }

        if (nextId instanceof Integer) {
            return ((Integer) nextId).longValue();
        }

        if (!(nextId instanceof Long)) {
            return 0L;
        }
        return (Long) nextId;
    }
}
