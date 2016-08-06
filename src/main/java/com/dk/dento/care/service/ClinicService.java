package com.dk.dento.care.service;

import com.dk.dento.care.entity.ModuleEntity;
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

        //Merge the access from Step 1 & 2.
        Map<String, Boolean> userModules = new HashMap<String, Boolean>();

        if(DENTOCARE_ADMIN.equalsIgnoreCase(role)) {

            Iterable<ModuleEntity> moduleEntities = moduleRepository.findAll();
            for(ModuleEntity moduleEntity : moduleEntities) {
                userModules.put(moduleEntity.getCode(), false);
            }
            return userModules;
        }
        //Get the clinic id of logged in user.
        Long clinicId = userMappingRepository.findByUserId(userId).getClinicId();
        System.out.println("clinicId : "+clinicId);
        //Step 1 : Get the clinic's purchased modules.
        List<ModulesMappingEntity> modulesMappingEntityList = modulesMappingRepository.findByClinicId(clinicId);
        Map<String, Boolean> clinicModules = new HashMap<String, Boolean>();
        for(ModulesMappingEntity modulesMappingEntity : modulesMappingEntityList) {
            Boolean accessStatus = modulesMappingEntity.getExpiryDate().compareTo(new Date())>0 ? true : false;
            clinicModules.put(moduleRepository.findOne(modulesMappingEntity.getModuleId()).getCode(), accessStatus);
        }

        if(CLINIC_ADMIN.equalsIgnoreCase(role)) {
            return clinicModules;
        }

        //Step 2 : Get the user modules
        List<UserModuleAccessEntity> userModuleAccessEntityList = userModuleAccessRepository.findByUserId(userId);
        for(UserModuleAccessEntity userModuleAccessEntity : userModuleAccessEntityList) {
            String moduleCode = moduleRepository.findOne(userModuleAccessEntity.getModuleId()).getCode();
            if(clinicModules.containsKey(moduleCode) && clinicModules.get(moduleCode)) {
                userModules.put(moduleCode, true);
            }
        }

        return userModules;
    }
}
