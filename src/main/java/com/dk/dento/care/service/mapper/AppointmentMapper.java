package com.dk.dento.care.service.mapper;

import com.dk.dento.care.entity.AppointmentEntity;
import com.dk.dento.care.model.Appointment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khana on 17/07/16.
 */
@Service
public class AppointmentMapper {

    @Autowired
    private ModelMapper modelMapper;

    public List<Appointment> appointmentEntityListToAppointments(Iterable<AppointmentEntity> appointmentEntities) {
        List<Appointment> appointments = new ArrayList<Appointment>(0);
        Appointment appointment;

        for (AppointmentEntity appointmentEntity : appointmentEntities) {
            appointment = modelMapper.map(appointmentEntity, Appointment.class);
            appointment.setTitle("Patient Name : " + appointmentEntity.getPlannedTreatment());
            appointment.setAllDay(false);
            appointments.add(appointment);
        }
        return appointments;
    }

}
