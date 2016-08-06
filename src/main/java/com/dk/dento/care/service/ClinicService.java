package com.dk.dento.care.service;

import com.dk.dento.care.entity.clinic.ModulesMappingEntity;
import com.dk.dento.care.entity.clinic.UserModuleAccessEntity;
import com.dk.dento.care.repository.ModuleRepository;
import com.dk.dento.care.repository.clinic.UserMappingRepository;
import com.dk.dento.care.repository.clinic.ModulesMappingRepository;
import com.dk.dento.care.repository.clinic.UserModuleAccessRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by khana on 30/07/16.
 */
@Service
public class ClinicService {

    @Autowired
    UserMappingRepository userMappingRepository;

    @Autowired
    ModulesMappingRepository modulesMappingRepository;

    @Autowired
    UserModuleAccessRepository userModuleAccessRepository;

    @Autowired
    ModuleRepository moduleRepository;

    private static final String DENTOCARE_ADMIN = "DENTOCARE_ADMIN";
    private static final String CLINIC_ADMIN = "CLINIC_ADMIN";
    private static final String CLINIC_USER = "CLINIC_USER";

    public Map<String, Boolean> getUserModules(Long userId, String role) {

        //Get the clinic id of logged in user.
        Long clinicId = userMappingRepository.findByUserId(userId).getClinicId();
        System.out.println("clinicId : "+clinicId);
        //Step 1 : Get the clinic's purchased modules.
        List<ModulesMappingEntity> modulesMappingEntityList = modulesMappingRepository.findByClinicId(clinicId);
        Map<Long, Boolean> clinicModules = new HashMap<Long, Boolean>();
        for(ModulesMappingEntity modulesMappingEntity : modulesMappingEntityList) {
            Long moduleId = modulesMappingEntity.getModuleId();
            System.out.println("clinicId : moduleId "+clinicId +" : " + moduleId);
            Boolean accessStatus = modulesMappingEntity.getExpiryDate().compareTo(new Date())>0 ? true : false;
            System.out.println("moduleId : accessStatus "+moduleId+" : "+accessStatus);
            clinicModules.put(moduleId, accessStatus);
        }

        //Step 2 : Get the user modules
        List<UserModuleAccessEntity> userModuleAccessEntityList = userModuleAccessRepository.findByUserId(userId);


        //Merge the access from Step 1 & 2.
        Map<String, Boolean> userModules = new HashMap<String, Boolean>();
        for(UserModuleAccessEntity userModuleAccessEntity : userModuleAccessEntityList) {

            Long moduleId = userModuleAccessEntity.getModuleId();
            System.out.println("User Module id : "+moduleId);
            if(clinicModules.containsKey(moduleId) && clinicModules.get(moduleId)) {
                System.out.println("User Module id is true for : "+moduleRepository.findOne(moduleId).getCode());
                userModules.put(moduleRepository.findOne(moduleId).getCode(), true);
            }
        }

        return userModules;
    }
}
