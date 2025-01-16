package com.jobgem.repository;

import com.jobgem.entity.SavedJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedJobRepository extends JpaRepository<SavedJobEntity, Long> {
}
