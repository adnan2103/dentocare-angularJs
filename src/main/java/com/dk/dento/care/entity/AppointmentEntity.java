package com.dk.dento.care.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

/**
 * Created by khana on 06/07/16.
 */
@Entity
@Table(name = "appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointment_id")
    private Long id;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "doctor_id")
    private Long doctorId;

    /** The date/time of the appointment start time.*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_start_time", nullable = false)
    private Calendar start;

    /** The date/time of the appointment end time.*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_end_time", nullable = false)
    private Calendar end;

    private String procedure;

}
