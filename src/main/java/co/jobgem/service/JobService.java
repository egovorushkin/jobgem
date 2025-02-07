package co.jobgem.service;

import co.jobgem.domain.jobgem.model.JobDTO;
import co.jobgem.domain.jobgem.model.JobInputDTO;
import co.jobgem.entity.CompanyEntity;
import co.jobgem.entity.Job;
import co.jobgem.exception.CompanyNotFoundException;
import co.jobgem.exception.JobNotFoundException;
import co.jobgem.mapper.JobMapper;
import co.jobgem.repository.CompanyRepository;
import co.jobgem.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobService {
    Logger logger = LoggerFactory.getLogger(JobService.class);

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private static final JobMapper jobMapper = JobMapper.INSTANCE;

    public JobService(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(jobMapper::toJobDTO)
                .toList();
    }

    public JobDTO getJobById(Long id) {
        return jobRepository.findById(id)
                .map(jobMapper::toJobDTO)
                .orElseThrow(() -> new JobNotFoundException("Job with id: " + id + " not found"));
    }

    public JobDTO createJob(JobInputDTO jobInputDTO) {
        Job jobCreate = jobMapper.toJob(jobInputDTO);
        if (jobInputDTO.getCompanyId() != null) {
            CompanyEntity jobCompany = companyRepository.findById(jobInputDTO.getCompanyId())
                    .orElseThrow(() -> new CompanyNotFoundException("Company with id: " + jobInputDTO.getCompanyId() + " not found"));
            jobCreate.setCompany(jobCompany);

        }
        // TODO add:
        //  expirationDate
        
        jobCreate.setPostDate(LocalDate.now());
        Job savedJob = jobRepository.save(jobCreate);
        return jobMapper.toJobDTO(savedJob);
    }

    public JobDTO patchJob(Long id, JobInputDTO jobInputDTO) {
        Job jobForUpdate = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job with id: " + id + " not found"));
        jobMapper.updateJobFromDto(jobInputDTO, jobForUpdate);

        // TODO add:
        //  expirationDate
        //  updatedDate

        Job updatedJob = jobRepository.save(jobForUpdate);
        return jobMapper.toJobDTO(updatedJob);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
