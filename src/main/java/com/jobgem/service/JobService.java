package com.jobgem.service;

import com.jobgem.dto.JobDTO;
import com.jobgem.entity.JobEntity;
import com.jobgem.entity.JobType;
import com.jobgem.mapper.JobMapper;
import com.jobgem.repository.CompanyRepository;
import com.jobgem.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final JobMapper jobMapper;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.jobMapper = jobMapper;
    }

    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(jobMapper::jobToJobDTO)
                .toList();
    }

    public JobDTO getJobById(Long id) {
        return jobRepository.findById(id)
                .map(jobMapper::jobToJobDTO)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public JobDTO createJob(JobDTO jobDTO) {
        JobEntity job = jobMapper.jobDTOToJob(jobDTO);
        job.setCompany(companyRepository.findById(jobDTO.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found")));
        JobEntity savedJob = jobRepository.save(job);
        return jobMapper.jobToJobDTO(savedJob);
    }

    public JobDTO updateJob(Long id, JobDTO jobDTO) {
        JobEntity job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setCompany(companyRepository.findById(jobDTO.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found")));
        job.setSalaryMin(jobDTO.getSalaryMin());
        job.setSalaryMax(jobDTO.getSalaryMax());
        job.setPostDate(jobDTO.getPostDate());
        job.setExpirationDate(jobDTO.getExpirationDate());
        job.setJobType(JobType.valueOf(jobDTO.getJobType()));
        job.setLocation(jobDTO.getLocation());

        JobEntity updatedJob = jobRepository.save(job);
        return jobMapper.jobToJobDTO(updatedJob);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
