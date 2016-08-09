package com.dk.dento.care.repository;


import com.dk.dento.care.entity.UserDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserDetailRepository extends PagingAndSortingRepository<UserDetailEntity, Long> {

    @Query("SELECT ud FROM UserDetailEntity ud, DoctorPatientMappingEntity dpm WHERE ud.id = dpm.doctorPatientMappingId.patientId AND dpm.doctorPatientMappingId.doctorId = ?1 ORDER BY ud.id DESC")
    Page<UserDetailEntity> findAllPatientOfLoggedInDoctor(final Long doctorId, Pageable pageReguest);
}
