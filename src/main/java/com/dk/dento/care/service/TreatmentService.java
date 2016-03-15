package com.dk.dento.care.service;

import com.dk.dento.care.entity.TreatmentEntity;
import com.dk.dento.care.model.Treatment;
import com.dk.dento.care.repository.TreatmentRepository;
import com.dk.dento.care.repository.UserDetailRepository;
import com.dk.dento.care.service.conversion.ModelEntityConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class TreatmentService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private ModelEntityConversion modelEntityConversion;

    public Set<Treatment> getTreatmentsForPatient(Long patientId) {
        return modelEntityConversion.treatmentEntityToTreatmentList(
                treatmentRepository.findByUserDetailEntity(userDetailRepository.findOne(patientId))
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Set<Treatment> saveTreatmentsForPatient(List<Treatment> treatments, Long patientId) {
        Set<TreatmentEntity> treatmentEntities =
                modelEntityConversion.treatmentModelListToTreatmentEntityList(treatments, patientId);
        return modelEntityConversion.treatmentEntityToTreatmentList(treatmentRepository.save(treatmentEntities));
    }
}
