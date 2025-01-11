package com.jobgem.service;

import com.jobgem.dto.JobDTO;
import com.jobgem.entity.JobEntity;
import com.jobgem.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public JobDTO getJobById(Long id) {
        JobEntity jobEntity = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return convertToDTO(jobEntity);
    }

    public JobDTO saveJob(JobDTO jobDTO) {
        JobEntity jobEntity = convertToEntity(jobDTO);
        return convertToDTO(jobRepository.save(jobEntity));
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    private JobDTO convertToDTO(JobEntity jobEntity) {
        JobDTO dto = new JobDTO();
        dto.setId(jobEntity.getId());
        dto.setTitle(jobEntity.getTitle());
        dto.setCompany(jobEntity.getCompany());
        dto.setLocation(jobEntity.getLocation());
        dto.setDescription(jobEntity.getDescription());
        dto.setEmploymentType(jobEntity.getEmploymentType());
        dto.setSalary(jobEntity.getSalary());
        return dto;
    }

    private JobEntity convertToEntity(JobDTO jobDTO) {
        JobEntity entity = new JobEntity();
        entity.setId(jobDTO.getId());
        entity.setTitle(jobDTO.getTitle());
        entity.setCompany(jobDTO.getCompany());
        entity.setLocation(jobDTO.getLocation());
        entity.setDescription(jobDTO.getDescription());
        entity.setEmploymentType(jobDTO.getEmploymentType());
        entity.setSalary(jobDTO.getSalary());
        return entity;
    }
}