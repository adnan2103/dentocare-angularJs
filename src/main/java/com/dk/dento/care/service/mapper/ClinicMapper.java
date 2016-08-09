package com.dk.dento.care.service.mapper;

import com.dk.dento.care.entity.clinic.ClinicEntity;
import com.dk.dento.care.model.Appointment;
import com.dk.dento.care.model.Clinic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khana on 09/08/16.
 */
@Service
public class ClinicMapper {

    @Autowired
    private ModelMapper modelMapper;

    //TODO need to find out how can Entity List be mapped to DTO List without iterating manually.
    public List<Clinic> clinicEntityListToClinics(Iterable<ClinicEntity> clinicEntities) {
        List<Clinic> clinics = new ArrayList<Clinic>(0);
        Clinic clinic;
        for( ClinicEntity clinicEntity : clinicEntities) {
            clinic = modelMapper.map(clinicEntity, Clinic.class);
            clinics.add(clinic);
        }
        return clinics;
    }

    public Clinic clinicEntityToClinic(ClinicEntity clinicEntity) {
        return modelMapper.map(clinicEntity, Clinic.class);
    }

    public ClinicEntity clinicToClinicEntity(Clinic clinic) {
        return modelMapper.map(clinic, ClinicEntity.class);
    }
}
