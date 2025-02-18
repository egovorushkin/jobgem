package co.jobgem.service;

import co.jobgem.domain.jobgem.model.JobApplicationDTO;
import co.jobgem.domain.jobgem.model.JobApplicationInputDTO;
import co.jobgem.entity.*;
import co.jobgem.exception.JobApplicationNotFoundException;
import co.jobgem.exception.JobNotFoundException;
import co.jobgem.exception.ResumeNotFoundException;
import co.jobgem.exception.UserNotFoundException;
import co.jobgem.mapper.JobApplicationMapper;
import co.jobgem.repository.JobApplicationRepository;
import co.jobgem.repository.JobRepository;
import co.jobgem.repository.ResumeRepository;
import co.jobgem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private static final JobApplicationMapper jobApplicationMapper = JobApplicationMapper.INSTANCE;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository, JobRepository jobRepository, UserRepository userRepository, ResumeRepository resumeRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.resumeRepository = resumeRepository;
    }

    public List<JobApplicationDTO> getAllJobApplications() {
        return jobApplicationRepository.findAll().stream()
                .map(jobApplicationMapper::toJobApplicationDTO)
                .toList();
    }

    public JobApplicationDTO getJobApplicationById(Long id) {
        return jobApplicationRepository.findById(id)
                .map(jobApplicationMapper::toJobApplicationDTO)
                .orElseThrow(() -> new JobApplicationNotFoundException("Job application with id: " + id + " not found"));
    }

    public JobApplicationDTO createJobApplication(JobApplicationInputDTO jobApplicationInputDTO) {
        JobApplication jobApplication = jobApplicationMapper.toJobApplication(jobApplicationInputDTO);
        if (jobApplicationInputDTO.getJobId() != null) {
            Job job = jobRepository.findById(jobApplicationInputDTO.getJobId())
                    .orElseThrow(() -> new JobNotFoundException("Job with id: " + jobApplicationInputDTO.getJobId() + " not found"));
            jobApplication.setJob(job);
        }

        if (jobApplicationInputDTO.getUserId() != null) {
            UserEntity user = userRepository.findById(jobApplicationInputDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User with id: " + jobApplicationInputDTO.getUserId() + " not found"));
            jobApplication.setUser(user);
        }

        if (jobApplicationInputDTO.getResumeId() != null) {
            Resume resume = resumeRepository.findById(jobApplicationInputDTO.getResumeId())
                    .orElseThrow(() -> new ResumeNotFoundException("Resume with id: " + jobApplicationInputDTO.getResumeId() + " not found"));
            jobApplication.setResume(resume);
        }

        jobApplication.setApplicationDate(LocalDateTime.now());
        JobApplication savedJobApplication = jobApplicationRepository.save(jobApplication);
        return jobApplicationMapper.toJobApplicationDTO(savedJobApplication);
    }

    public JobApplicationDTO patchJobApplication(Long id, JobApplicationInputDTO jobApplicationInputDTO) {
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new JobApplicationNotFoundException("Job application with id: " + id + " not found"));

        jobApplicationMapper.updateJobApplicationFromDto(jobApplicationInputDTO, jobApplication);
        jobApplication.setApplicationDate(LocalDateTime.now());

        // TODO add:
        //  updatedDate

        JobApplication updatedJobApplication = jobApplicationRepository.save(jobApplication);
        return jobApplicationMapper.toJobApplicationDTO(updatedJobApplication);
    }

    public void deleteJobApplication(Long id) {
        jobApplicationRepository.deleteById(id);
    }
}
