package com.dk.dento.care.service;

import com.dk.dento.care.entity.TreatmentEntity;
import com.dk.dento.care.model.ImagePath;
import com.dk.dento.care.model.Treatment;
import com.dk.dento.care.repository.TreatmentRepository;
import com.dk.dento.care.repository.UserDetailRepository;
import com.dk.dento.care.security.IAMService;
import com.dk.dento.care.service.mapper.TreatmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class TreatmentService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private TreatmentMapper treatmentMapper;

    @Autowired
    private IAMService iamService;

    public List<Treatment> getTreatmentsForPatient(Long patientId) {
        //TODO Use join in query.
        return treatmentMapper.treatmentEntitiesToTreatments(
                treatmentRepository.findByPatient(userDetailRepository.findOne(patientId))
        );
    }

    public List<Treatment> getTreatmentsForDoctor() {
        //TODO Use join in query.
        return treatmentMapper.treatmentEntitiesToTreatments(
                treatmentRepository.findByDoctor(userDetailRepository.findOne(iamService.getAuthenticatedUser().getId()))
        );
    }

    public List<ImagePath> getTreatmentImages(Long treatmentId, String type, Integer count) {
        List<ImagePath> treatmentImages = new ArrayList<ImagePath>(0);

        for(int i = 1; i <= count; i++) {
            treatmentImages.add(new ImagePath("treatment/"+treatmentId+"/" + type + "/images/"+i));
        }

        return treatmentImages;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public List<Treatment> saveTreatmentsForPatient(List<Treatment> treatments, Long patientId) {
        List<TreatmentEntity> treatmentEntities =
                treatmentMapper.treatmentsToTreatmentEntities(treatments, patientId);
        return treatmentMapper.treatmentEntitiesToTreatments(treatmentRepository.save(treatmentEntities));
    }
}
