package co.jobgem.api;

import co.jobgem.domain.jobgem.api.JobApplicationApiDelegate;
import co.jobgem.domain.jobgem.model.JobApplicationDTO;
import co.jobgem.domain.jobgem.model.JobApplicationInputDTO;
import co.jobgem.exception.JobApplicationNotFoundException;
import co.jobgem.service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationApiDelegateImpl implements JobApplicationApiDelegate {

    private final JobApplicationService jobApplicationService;

    public JobApplicationApiDelegateImpl(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @Override
    public ResponseEntity<List<JobApplicationDTO>> getAllJobApplication() {
        return ResponseEntity.ok(jobApplicationService.getAllJobApplications());
    }

    @Override
    public ResponseEntity<JobApplicationDTO> getJobApplicationById(Long jobApplicationId) {
        try {
            return ResponseEntity.ok(jobApplicationService.getJobApplicationById(jobApplicationId));
        } catch (JobApplicationNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<JobApplicationDTO> createJobApplication(JobApplicationInputDTO jobApplicationInputDTO) {
        try {
            return ResponseEntity.ok(jobApplicationService.createJobApplication(jobApplicationInputDTO));
        } catch (JobApplicationNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteJobApplication(Long jobApplicationId) {
        jobApplicationService.deleteJobApplication(jobApplicationId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<JobApplicationDTO> patchJobApplication(Long jobApplicationId, JobApplicationInputDTO jobApplicationInputDTO) {
        return ResponseEntity.ok(jobApplicationService.patchJobApplication(jobApplicationId, jobApplicationInputDTO));
    }
}
