package com.dk.dento.care.service;

import com.dk.dento.care.entity.AppointmentEntity;
import com.dk.dento.care.entity.UserCredentialsEntity;
import com.dk.dento.care.model.Appointment;
import com.dk.dento.care.repository.AppointmentRepository;
import com.dk.dento.care.service.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by khana on 17/07/16.
 */
@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    IAMService iamService;

    public List<Appointment> getAppointments() {

        UserCredentialsEntity loggedInDoctor = iamService.getAuthenticatedUser();
        Long loggedIdDoctorId = loggedInDoctor.getId();

        return appointmentMapper.appointmentEntityListToAppointments(
                appointmentRepository.findByDoctorId(iamService.getAuthenticatedUser().getId())
        );
    }

    public Appointment save(final Appointment appointment) {

        if(appointment.getDoctorId() ==null) {
            appointment.setDoctorId(iamService.getAuthenticatedUser().getId());
        }
        AppointmentEntity appointmentEntity =
                appointmentRepository.save(appointmentMapper.appointmentToAppointmentEntity(appointment));
        return appointmentMapper.appointmentEntityToAppointment(appointmentEntity);
    }
}
