package com.dk.dento.care.service;

import com.dk.dento.care.entity.TreatmentEntity;
import com.dk.dento.care.model.Treatment;
import com.dk.dento.care.repository.TreatmentRepository;
import com.dk.dento.care.repository.UserDetailRepository;
import com.dk.dento.care.service.mapper.TreatmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TreatmentService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private TreatmentMapper treatmentMapper;

    public List<Treatment> getTreatmentsForPatient(Long patientId) {
        return treatmentMapper.treatmentEntitiesToTreatments(
                treatmentRepository.findByUserDetailEntity(userDetailRepository.findOne(patientId))
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Treatment> saveTreatmentsForPatient(List<Treatment> treatments, Long patientId) {
        List<TreatmentEntity> treatmentEntities =
                treatmentMapper.treatmentsToTreatmentEntities(treatments, patientId);
        return treatmentMapper.treatmentEntitiesToTreatments(treatmentRepository.save(treatmentEntities));
    }
}
