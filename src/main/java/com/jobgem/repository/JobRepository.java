package com.jobgem.repository;

import com.jobgem.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<JobEntity, Long> {
    List<JobEntity> findByTitleContaining(String keyword);
    List<JobEntity> findByLocation(String location);
}
