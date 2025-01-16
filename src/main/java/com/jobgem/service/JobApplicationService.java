package com.jobgem.service;

import com.jobgem.dto.JobApplicationDTO;
import com.jobgem.entity.ApplicationStatus;
import com.jobgem.entity.JobApplicationEntity;
import com.jobgem.mapper.JobApplicationMapper;
import com.jobgem.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobApplicationMapper jobApplicationMapper;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository, JobApplicationMapper jobApplicationMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobApplicationMapper = jobApplicationMapper;
    }

    public List<JobApplicationDTO> getAllJobApplications() {
        return jobApplicationRepository.findAll().stream()
                .map(jobApplicationMapper::jobApplicationToJobApplicationDTO)
                .toList();
    }

    public JobApplicationDTO getJobApplicationById(Long id) {
        return jobApplicationRepository.findById(id)
                .map(jobApplicationMapper::jobApplicationToJobApplicationDTO)
                .orElseThrow(() -> new RuntimeException("Job application not found"));
    }

    public JobApplicationDTO createJobApplication(JobApplicationDTO jobApplicationDTO) {
        JobApplicationEntity jobApplication = jobApplicationMapper.jobApplicationDTOToJobApplication(jobApplicationDTO);
        JobApplicationEntity savedJobApplication = jobApplicationRepository.save(jobApplication);
        return jobApplicationMapper.jobApplicationToJobApplicationDTO(savedJobApplication);
    }

    public JobApplicationDTO updateJobApplication(Long id, JobApplicationDTO jobApplicationDTO) {
        JobApplicationEntity jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job application not found"));

        jobApplication.setApplicationDate(jobApplicationDTO.getApplicationDate());
        jobApplication.setStatus(ApplicationStatus.valueOf(jobApplicationDTO.getStatus()));
        jobApplication.setCoverLetter(jobApplicationDTO.getCoverLetter());

        JobApplicationEntity updatedJobApplication = jobApplicationRepository.save(jobApplication);
        return jobApplicationMapper.jobApplicationToJobApplicationDTO(updatedJobApplication);
    }

    public void deleteJobApplication(Long id) {
        jobApplicationRepository.deleteById(id);
    }
}
